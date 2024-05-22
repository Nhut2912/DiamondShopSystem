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

    public OrderDetail(Order orderId, Product productId, double priceBeforeSizeAdjustment, double priceAfterSizeAdjustment, int size) {
        this.orderId = orderId;
        this.productId = productId;
        this.priceBeforeSizeAdjustment = priceBeforeSizeAdjustment;
        this.priceAfterSizeAdjustment = priceAfterSizeAdjustment;
        this.size = size;
    }

    public OrderDetail() {
    }

    public OrderDetail(OrderDetail o) {
        this.orderId = o.orderId;
        this.productId = o.productId;
        this.priceBeforeSizeAdjustment = o.priceBeforeSizeAdjustment;
        this.priceAfterSizeAdjustment = o.priceAfterSizeAdjustment;
        this.size = o.size;
    }

    @Override
    public String toString() {
        return "OrderDetail{" +
                "orderId=" + orderId +
                ", productId=" + productId +
                ", priceBeforeSizeAdjustment=" + priceBeforeSizeAdjustment +
                ", priceAfterSizeAdjustment=" + priceAfterSizeAdjustment +
                ", size=" + size +
                '}';
    }
}
