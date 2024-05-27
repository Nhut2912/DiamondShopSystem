package com.example.server.Pojo;


import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
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


    @OneToMany(cascade = CascadeType.ALL, mappedBy = "productId")
    private Set<OrderDetail> productDetailSet;

    @OneToMany(cascade = CascadeType.ALL,mappedBy = "productId")
    private Set<Promotions_Product> products;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "DiamondProduct")
    private Set<Diamond> DiamondProducts;

    public void addProductMaterial(Material m, double weight){

        ProductMaterial pm = new ProductMaterial(this,m,weight);
        productMaterialSet.add(pm);
        m.getMaterialSet().add(pm);
    }

    public void addProductDiamond(Diamond d){
        DiamondProducts.add(d);
        d.setDiamondProduct(this);
    }

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

    public Product(String name, String code, boolean active, double secondaryDiamondCost, double secondaryMaterialCost, double productionCost, int priceRate, Size productSizes, Set<Image> images, Category productCategory, Set<ProductMaterial> productMaterialSet, Warranty warranty, Set<OrderDetail> productDetailSet, Set<Promotions_Product> products, Set<Diamond> diamondProducts) {
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

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

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
        return Images;
    }

    public void setImages(Set<Image> images) {
        Images = images;
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
        return DiamondProducts;
    }

    public void setDiamondProducts(Set<Diamond> diamondProducts) {
        DiamondProducts = diamondProducts;
    }
}
