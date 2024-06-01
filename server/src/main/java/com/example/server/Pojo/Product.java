package com.example.server.Pojo;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.util.*;

@Data
@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
@Table(name = "PRODUCTS")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @Column(name = "Name")
    private String Name;

    @Column(name = "Code", unique = true, length = 50)
    private String Code;

    @Column(name = "Active", nullable = false)
    private boolean Active;

    @Column(name = "SecondaryDiamondCost")
    private double SecondaryDiamondCost;

    @Column(name = "SecondaryMaterialCost")
    private double SecondaryMaterialCost;

    @Column(name = "ProductionCost", nullable = false)
    private double ProductionCost;

    @Column(name = "PriceRate", nullable = false)
    private int PriceRate;

    @ManyToOne() //many to one khong nen dung cascade(Tran Viet Hoang)
    @JoinColumn(name = "SizeID")
    private Size ProductSizes;

    @JsonManagedReference
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "Products")
    private Set<Image> images;

    //Pham Trong Hieu

    @Getter
    @ManyToOne(fetch = FetchType.LAZY) //many to one khong nen dung cascade(Tran Viet Hoang)
    @JoinColumn(name = "CategoryID")
    private Category ProductCategory;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "products")
    private Set<ProductMaterial> productMaterialSet;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "WarrantyID", referencedColumnName = "id")
    private Warranty warranty;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "productId")
    private Set<OrderDetail> productDetailSet;

    @OneToMany(cascade = CascadeType.ALL,mappedBy = "productId")
    private Set<Promotions_Product> products;


    @OneToMany(cascade = CascadeType.ALL, mappedBy = "DiamondProduct")
    private Set<Diamond> diamondProducts;

    @Override
    public String toString() {
        return "Product{" +
                "Id=" + Id +
                ", Name='" + Name + '\'' +
                ", Code='" + Code + '\'' +
                ", ProductCategory=" + ProductCategory +
                ", Active=" + Active +
                ", SecondaryDiamondCost=" + SecondaryDiamondCost +
                ", SecondaryMaterialCost=" + SecondaryMaterialCost +
                ", ProductionCost=" + ProductionCost +
                ", PriceRate=" + PriceRate +
                ", ProductSizes=" + ProductSizes +
                ", images=" + images +

                ", productMaterialSet=" + productMaterialSet +
                ", warranty=" + warranty +
                ", productDetailSet=" + productDetailSet +
                ", products=" + products +
                ", diamondProducts=" + diamondProducts +
                '}';
    }
}
