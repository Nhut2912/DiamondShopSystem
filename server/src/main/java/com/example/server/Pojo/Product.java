package com.example.server.Pojo;


import jakarta.persistence.*;
import lombok.Data;

import java.util.Objects;
import java.util.Set;
@Data
@Entity
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

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "Products")
    private Set<Image> Images;

    //Pham Trong Hieu
    @ManyToOne() //many to one khong nen dung cascade(Tran Viet Hoang)
    @JoinColumn(name = "CategoryID")
    private Category ProductCategory;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "products")
    private Set<ProductMaterial> productMaterialSet;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "WarrantyID", referencedColumnName = "id")
    private Warranty warranty;

//<<<<<<< HEAD
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "productId")
    private Set<OrderDetail> productDetailSet;

    @OneToMany(cascade = CascadeType.ALL,mappedBy = "productId")
    private Set<Promotions_Product> products;
//=======
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "DiamondProduct")
    private Set<Diamond> DiamondProducts;

    public Product(Long id, String name, String code, boolean active, double secondaryDiamondCost, double secondaryMaterialCost, double productionCost, int priceRate, Size productSizes, Set<Image> images, Category productCategory, Set<ProductMaterial> productMaterialSet, Warranty warranty, Set<OrderDetail> productDetailSet, Set<Promotions_Product> products, Set<Diamond> diamondProducts) {
        Id = id;
        Name = name;
        Code = code;
        Active = active;
        SecondaryDiamondCost = secondaryDiamondCost;
        SecondaryMaterialCost = secondaryMaterialCost;
        ProductionCost = productionCost;
        PriceRate = priceRate;
        ProductSizes = productSizes;
        Images = images;
        ProductCategory = productCategory;
        this.productMaterialSet = productMaterialSet;
        this.warranty = warranty;
        this.productDetailSet = productDetailSet;
        this.products = products;
        DiamondProducts = diamondProducts;
    }

    public Product() {
    }

    public Product(Product p) {
        Id = p.Id;
        Name = p.Name;
        Code = p.Code;
        Active = p.Active;
        SecondaryDiamondCost = p.SecondaryDiamondCost;
        SecondaryMaterialCost = p.SecondaryMaterialCost;
        ProductionCost = p.ProductionCost;
        PriceRate = p.PriceRate;
        ProductSizes = p.ProductSizes;
        Images = p.Images;
        ProductCategory = p.ProductCategory;
        this.productMaterialSet = p.productMaterialSet;
        this.warranty = p.warranty;
        this.productDetailSet = p.productDetailSet;
        this.products = p.products;
        DiamondProducts = p.DiamondProducts;
    }

    @Override
    public String toString() {
        return "Product{" +
                "Id=" + Id +
                ", Name='" + Name + '\'' +
                ", Code='" + Code + '\'' +
                ", Active=" + Active +
                ", SecondaryDiamondCost=" + SecondaryDiamondCost +
                ", SecondaryMaterialCost=" + SecondaryMaterialCost +
                ", ProductionCost=" + ProductionCost +
                ", PriceRate=" + PriceRate +
                ", ProductSizes=" + ProductSizes +
                ", Images=" + Images +
                ", ProductCategory=" + ProductCategory +
                ", productMaterialSet=" + productMaterialSet +
                ", warranty=" + warranty +
                ", productDetailSet=" + productDetailSet +
                ", products=" + products +
                ", DiamondProducts=" + DiamondProducts +
                '}';
    }
    //>>>>>>> f5decc3a6366a04ddcdf6c125a5a14f5fae2046b
//    @Override
//    public String toString() {
//        return "Product{" +
//                "Id=" + Id +
//                ", Code='" + Code + '\'' +
//                ", Active=" + Active +
//                ", SecondaryDiamondCost=" + SecondaryDiamondCost +
//                ", SecondaryMaterialCost=" + SecondaryMaterialCost +
//                ", ProductionCost=" + ProductionCost +
//                ", PriceRate=" + PriceRate +
//                ", ProductSizes=" + ProductSizes +
//                ", Images=" + Images +
//                '}';
//    }
//
//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//        Product product = (Product) o;
//        return Active == product.Active && Double.compare(SecondaryDiamondCost, product.SecondaryDiamondCost) == 0 && Double.compare(SecondaryMaterialCost, product.SecondaryMaterialCost) == 0 && Double.compare(ProductionCost, product.ProductionCost) == 0 && PriceRate == product.PriceRate && Objects.equals(Id, product.Id) && Objects.equals(Code, product.Code) && Objects.equals(ProductSizes, product.ProductSizes) && Objects.equals(Images, product.Images);
//    }
//
//    @Override
//    public int hashCode() {
//        return Objects.hash(Id, Code, Active, SecondaryDiamondCost, SecondaryMaterialCost, ProductionCost, PriceRate, ProductSizes, Images);
//    }
}
