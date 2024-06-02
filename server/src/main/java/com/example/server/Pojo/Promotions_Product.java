package com.example.server.Pojo;

import jakarta.persistence.*;
import lombok.Data;


import java.io.Serializable;

@Entity
@Table(name = "promotions_Product")
public class Promotions_Product {
    public static class Promotions_ProductId implements Serializable{
        @Column(name = "promotionId")
        protected long promotionId;
        @Column(name = "productId")
        protected long productId;
    }
    @EmbeddedId
    protected Promotions_ProductId promotionsProductId;


    @ManyToOne
    @JoinColumn(name = "promotionId", insertable = false, updatable = false)
    //@MapsId("promotionId")
    private Promotion promotionId;


    @ManyToOne
    @JoinColumn(name = "productId", insertable = false, updatable = false)
    //@MapsId("productId")
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
