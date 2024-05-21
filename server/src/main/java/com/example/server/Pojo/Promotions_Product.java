package com.example.server.Pojo;

import jakarta.persistence.*;

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
}
