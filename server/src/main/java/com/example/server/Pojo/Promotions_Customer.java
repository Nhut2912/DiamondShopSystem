package com.example.server.Pojo;

import jakarta.persistence.*;

@Entity
@Table(name = "promotions_Customer")
public class Promotions_Customer {

    @Id
    @ManyToOne
    @JoinColumn(name = "promotionID")
    private Promotion promotionId;

    @Id
    @ManyToOne
    @JoinColumn(name = "customerID")
    private Customer customerId;

    public Promotions_Customer(Promotion promotionId, Customer customerId) {
        this.promotionId = promotionId;
        this.customerId = customerId;
    }

    public Promotions_Customer() {
    }

    public Promotions_Customer(Promotions_Customer p) {
        this.promotionId = p.promotionId;
        this.customerId = p.customerId;
    }

    @Override
    public String toString() {
        return "Promotions_Customer{" +
                "promotionId=" + promotionId +
                ", customerId=" + customerId +
                '}';
    }
}
