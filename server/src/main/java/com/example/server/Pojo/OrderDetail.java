package com.example.server.Pojo;

import jakarta.persistence.*;
import lombok.Data;


import java.io.Serializable;

@Entity
@Table(name = "orderDetail")

public class OrderDetail {
    @Data
    @Embeddable
    public static class OrderDetailId implements Serializable {
        @Column(name = "productId")
        protected long productId;
        @Column(name = "orderId")
        protected long orderId;
    }
    @EmbeddedId
    OrderDetailId orderDetailId;

    @ManyToOne
    @MapsId("orderId")
    @JoinColumn(name = "orderId", insertable = false, updatable = false)
    private Order orderId;


    @ManyToOne
    @MapsId("productId")
    @JoinColumn(name = "productId", insertable = false, updatable = false)
    private Product productId;

    @Column(name = "priceBeforeSizeAdjustment")
    private double priceBeforeSizeAdjustment;

    @Column(name = "priceAfterSizeAdjustment")
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
