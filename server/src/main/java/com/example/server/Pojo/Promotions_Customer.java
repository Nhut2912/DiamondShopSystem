package com.example.server.Pojo;

import jakarta.persistence.*;

@Entity
@Table(name = "Promotions_Customer")
public class Promotions_Customer {

    @Id
    @ManyToOne
    @JoinColumn(name = "PromotionID")
    private Promotion promotionId;

    @Id
    @ManyToOne
    @JoinColumn(name = "CustomerID")
    private Customer customerId;
}
