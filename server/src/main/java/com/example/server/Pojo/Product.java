package com.example.server.Pojo;


<<<<<<< HEAD
import com.fasterxml.jackson.annotation.JsonIgnore;
=======
>>>>>>> 66cd59eec7390c32ee0e12456e935defed9dec98
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;

import java.util.*;

<<<<<<< HEAD
@Data
=======
//@Data
>>>>>>> 66cd59eec7390c32ee0e12456e935defed9dec98
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



<<<<<<< HEAD
    //material embedd
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "product")
    @JsonIgnore
=======
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "Products")
    @JsonManagedReference
    private Set<Image> images;

    //Pham Trong Hieu
    @ManyToOne() //many to one khong nen dung cascade(Tran Viet Hoang)
    @JoinColumn(name = "CategoryID")
    private Category ProductCategory;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "products")
>>>>>>> 66cd59eec7390c32ee0e12456e935defed9dec98
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

<<<<<<< HEAD
    //diamond: 4 thang origin, color, clarity, cut phai? co truoc, sau do khi them product moi them duoc kim cuong
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "diamondProduct")
    @JsonIgnore
=======
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "DiamondProduct")
>>>>>>> 66cd59eec7390c32ee0e12456e935defed9dec98
    private Set<Diamond> diamondProducts;



<<<<<<< HEAD


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
=======
    public void addProductDiamond(Diamond d){
        diamondProducts.add(d);
        d.setDiamondProduct(this);
    }

    public Product(Long id, String name, String code, boolean active, double secondaryDiamondCost, double secondaryMaterialCost, double productionCost, int priceRate, Size productSizes, Set<Image> imagess, Category productCategory, Set<ProductMaterial> productMaterialSet, Warranty warranty, Set<OrderDetail> productDetailSet, Set<Promotions_Product> products, Set<Diamond> diamondProductss) {
        Id = id;
        Name = name;
        Code = code;
        Active = active;
        SecondaryDiamondCost = secondaryDiamondCost;
        SecondaryMaterialCost = secondaryMaterialCost;
        ProductionCost = productionCost;
        PriceRate = priceRate;
        ProductSizes = productSizes;
        images = imagess;
        ProductCategory = productCategory;
        this.productMaterialSet = productMaterialSet;
        this.warranty = warranty;
        this.productDetailSet = productDetailSet;
        this.products = products;
>>>>>>> 66cd59eec7390c32ee0e12456e935defed9dec98
        diamondProducts = diamondProductss;
    }


    public Product() {

    }

<<<<<<< HEAD

=======
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
        images = p.images;
        ProductCategory = p.ProductCategory;
        this.productMaterialSet = p.productMaterialSet;
        this.warranty = p.warranty;
        this.productDetailSet = p.productDetailSet;
        this.products = p.products;
        diamondProducts = p.diamondProducts;
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
                ", Images=" + images +
                ", ProductCategory=" + ProductCategory +
                ", productMaterialSet=" + productMaterialSet +
                ", warranty=" + warranty +
                ", productDetailSet=" + productDetailSet +
                ", products=" + products +
                ", DiamondProducts=" + diamondProducts +
                '}';
    }
>>>>>>> 66cd59eec7390c32ee0e12456e935defed9dec98

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




<<<<<<< HEAD
=======
    public String getCode() {
        return Code;
    }

    public void setCode(String code) {
        Code = code;
    }

    public boolean isActive() {
        return Active;
    }

    public void setActive(boolean active) {
        Active = active;
    }

    public double getSecondaryDiamondCost() {
        return SecondaryDiamondCost;
    }

    public void setSecondaryDiamondCost(double secondaryDiamondCost) {
        SecondaryDiamondCost = secondaryDiamondCost;
    }

    public double getSecondaryMaterialCost() {
        return SecondaryMaterialCost;
    }

    public void setSecondaryMaterialCost(double secondaryMaterialCost) {
        SecondaryMaterialCost = secondaryMaterialCost;
    }

    public double getProductionCost() {
        return ProductionCost;
    }

    public void setProductionCost(double productionCost) {
        ProductionCost = productionCost;
    }

    public int getPriceRate() {
        return PriceRate;
    }

    public void setPriceRate(int priceRate) {
        PriceRate = priceRate;
    }

    public Size getProductSizes() {
        return ProductSizes;
    }

    public void setProductSizes(Size productSizes) {
        ProductSizes = productSizes;
    }


    public Set<Image> getImages() {
        return images;
    }

    public void setImages(Set<Image> imageSet) {
        images = imageSet;
    }

    public Category getProductCategory() {
        return ProductCategory;
    }

    public void setProductCategory(Category productCategory) {
        ProductCategory = productCategory;
    }

    public Set<ProductMaterial> getProductMaterialSet() {
        return productMaterialSet;
    }

    public void setProductMaterialSet(Set<ProductMaterial> productMaterialSet) {
        this.productMaterialSet = productMaterialSet;
    }

    public Warranty getWarranty() {
        return warranty;
    }

    public void setWarranty(Warranty warranty) {
        this.warranty = warranty;
    }

    public Set<OrderDetail> getProductDetailSet() {
        return productDetailSet;
    }

    public void setProductDetailSet(Set<OrderDetail> productDetailSet) {
        this.productDetailSet = productDetailSet;
    }

    public Set<Promotions_Product> getProducts() {
        return products;
    }

    public void setProductsPromotion(Set<Promotions_Product> products) {
        this.products = products;
    }

    public Set<Diamond> getDiamondProducts() {
        return diamondProducts;
    }

    public void setDiamondProducts(Set<Diamond> diamondProductsSet) {
        diamondProducts = diamondProductsSet;
    }
>>>>>>> 66cd59eec7390c32ee0e12456e935defed9dec98
}
