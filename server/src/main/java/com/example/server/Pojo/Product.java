package com.example.server.Pojo;


import jakarta.persistence.*;

import java.util.Set;


@Entity
@Table(name = "PRODUCTS")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

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


    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "SizeID")
    private Size ProductSizes;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "Products")
    private Set<Image> Images;

    public Product(Long id, String code, boolean active, double secondaryDiamondCost, double secondaryMaterialCost, double productionCost, int priceRate, Size productSizes, Set<Image> images) {
        Id = id;
        Code = code;
        Active = active;
        SecondaryDiamondCost = secondaryDiamondCost;
        SecondaryMaterialCost = secondaryMaterialCost;
        ProductionCost = productionCost;
        PriceRate = priceRate;
        ProductSizes = productSizes;
        Images = images;
    }

    public Product() {

    }

    @Override
    public String toString() {
        return "Product{" +
                "Id=" + Id +
                ", Code='" + Code + '\'' +
                ", Active=" + Active +
                ", SecondaryDiamondCost=" + SecondaryDiamondCost +
                ", SecondaryMaterialCost=" + SecondaryMaterialCost +
                ", ProductionCost=" + ProductionCost +
                ", PriceRate=" + PriceRate +
                ", ProductSizes=" + ProductSizes +
                ", Images=" + Images +
                '}';
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
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
}
