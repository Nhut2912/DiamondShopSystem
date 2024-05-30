package com.example.server.Pojo;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "Promotions_Product")
public class Promotions_Product {

    @Id
    @ManyToOne
    @JoinColumn(name = "PromotionID")
    private Promotion promotionId;

    @Id
    @ManyToOne
    @JoinColumn(name = "ProductID")
    private Product productId;

    public Promotions_Product(Promotion promotionId, Product productId) {
        this.promotionId = promotionId;
        this.productId = productId;
    }

    public Promotions_Product() {
    }
    public Promotions_Product(Promotions_Product p) {
        this.promotionId = p.promotionId;
        this.productId = p.productId;
    }

    @Override
    public String toString() {
        return "Promotions_Product{" +
                "promotionId=" + promotionId +
                ", productId=" + productId +
                '}';
    }
}
