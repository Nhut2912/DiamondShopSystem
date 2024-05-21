package com.example.server.Pojo;

import jakarta.persistence.*;

@Entity
@Table(name = "OrderDetail")
public class OrderDetail {

    @Id
    @ManyToOne
    @JoinColumn(name = "OrderID")
    private Order orderId;

    @Id
    @ManyToOne
    @JoinColumn(name = "ProductID")
    private Product productId;

    @Column(name = "PriceBeforeSizeAdjustment")
    private double priceBeforeSizeAdjustment;

    @Column(name = "PriceAfterSizeAdjustment")
    private double priceAfterSizeAdjustment;

    @Column(name = "Size")
    private int size;
}
