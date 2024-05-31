package com.example.server.Requests;

import java.util.List;
import java.util.Set;

public class ProductDTO {
    private String name;
    private String Code;
    private boolean active;
    private double productionCost;
    private double secondaryDiamondCost;
    private double secondaryMaterialCost;
    private double priceRate;
    private Long categoryID;
    private Long sizeID;
    private Long warrantyID;

    private Set<ImageDTO> images;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return Code;
    }

    public void setCode(String code) {
        Code = code;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public double getProductionCost() {
        return productionCost;
    }

    public void setProductionCost(double productionCost) {
        this.productionCost = productionCost;
    }

    public double getSecondaryDiamondCost() {
        return secondaryDiamondCost;
    }

    public void setSecondaryDiamondCost(double secondaryDiamondCost) {
        this.secondaryDiamondCost = secondaryDiamondCost;
    }

    public double getSecondaryMaterialCost() {
        return secondaryMaterialCost;
    }

    public void setSecondaryMaterialCost(double secondaryMaterialCost) {
        this.secondaryMaterialCost = secondaryMaterialCost;
    }

    public double getPriceRate() {
        return priceRate;
    }

    public void setPriceRate(double priceRate) {
        this.priceRate = priceRate;
    }

    public Long getCategoryID() {
        return categoryID;
    }

    public void setCategoryID(Long categoryID) {
        this.categoryID = categoryID;
    }

    public Long getSizeID() {
        return sizeID;
    }

    public void setSizeID(Long sizeID) {
        this.sizeID = sizeID;
    }

    public Long getWarrantyID() {
        return warrantyID;
    }

    public void setWarrantyID(Long warrantyID) {
        this.warrantyID = warrantyID;
    }

    public Set<ImageDTO> getImages() {
        return images;
    }

    public void setImages(Set<ImageDTO> images) {
        this.images = images;
    }
}
