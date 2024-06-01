package com.example.server.Pojo;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;

@Entity
@Table(name = "orderDetail")
@Data
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
