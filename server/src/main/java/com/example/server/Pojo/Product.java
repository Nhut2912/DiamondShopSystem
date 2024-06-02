package com.example.server.Pojo;

import com.fasterxml.jackson.annotation.JsonIgnore;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.util.*;

@Data
@Entity

@Table(name = "PRODUCTS")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "productId")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "code", unique = true, length = 50)
    private String code;

    @Column(name = "active", nullable = false)
    private boolean active;

    @Column(name = "secondaryDiamondCost")
    private double secondaryDiamondCost;

    @Column(name = "secondaryMaterialCost")
    private double secondaryMaterialCost;

    @Column(name = "productionCost", nullable = false)
    private double productionCost;

    @Column(name = "priceRate", nullable = false)
    private int priceRate;

    //add category truoc
    @ManyToOne()
    @JoinColumn(name = "categoryId")
    private Category productCategory;

    //add product size truoc
    @ManyToOne()
    @JoinColumn(name = "sizeID")
    private Size productSize;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "productId", referencedColumnName = "productId")
    @JsonManagedReference
    private Set<Image> images;



    //material embedd
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "product")
    @JsonIgnore
    private Set<ProductMaterial> productMaterialSet;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "productId", referencedColumnName = "productId")
    private Warranty warranty;


    //order detail embedd

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "productId")
    @JsonIgnore
    private Set<OrderDetail> productDetailSet;

    //promotion_product embedd
    @OneToMany(cascade = CascadeType.ALL,mappedBy = "productId")
    @JsonIgnore
    private Set<Promotions_Product> products;


    //diamond: 4 thang origin, color, clarity, cut phai? co truoc, sau do khi them product moi them duoc kim cuong
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "diamondProduct")
    @JsonIgnore
    private Set<Diamond> diamondProducts;





    public Product(Long id, String name, String code, boolean active, double secondaryDiamondCost, double secondaryMaterialCost, double productionCost, int priceRate, Size productSizes, Set<Image> imagess, Category productCategory, Set<ProductMaterial> productMaterialSet, Warranty warranty, Set<OrderDetail> productDetailSet, Set<Promotions_Product> products, Set<Diamond> diamondProductss) {
        this.id = id;
        this.name = name;
        this.code = code;
        this.active = active;
        this.secondaryDiamondCost = secondaryDiamondCost;
        this.secondaryMaterialCost = secondaryMaterialCost;
        this.productionCost = productionCost;
        this.priceRate = priceRate;
        this.productSize = productSizes;
        this.images = imagess;
        this.productCategory = productCategory;
        this.productMaterialSet = productMaterialSet;
        this.warranty = warranty;
        this.productDetailSet = productDetailSet;
//        this.products = products;
        diamondProducts = diamondProductss;
    }


    public Product() {

    }



//    @Override
//    public String toString() {
//        return "Product{" +
//                "Id=" + id +
//                ", Name='" + name + '\'' +
//                ", Code='" + code + '\'' +
//                ", Active=" + active +
//                ", SecondaryDiamondCost=" + secondaryDiamondCost +
//                ", SecondaryMaterialCost=" + secondaryMaterialCost +
//                ", ProductionCost=" + productionCost +
//                ", PriceRate=" + priceRate +
//                ", ProductSizes=" + productSize +
//                ", Images=" + images +
//                ", ProductCategory=" + productCategory +
//                ", productMaterialSet=" + productMaterialSet +
//                ", warranty=" + warranty +
//                ", productDetailSet=" + productDetailSet +
//                ", products=" + products +
//                ", DiamondProducts=" + diamondProducts +
//                '}';
//    }





}
