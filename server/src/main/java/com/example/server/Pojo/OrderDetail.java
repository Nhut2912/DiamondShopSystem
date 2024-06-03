package com.example.server.Pojo;

import jakarta.persistence.*;
import lombok.Data;


@Entity
@Table(name = "order_detail")
@Data
public class OrderDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private double priceAfterSizeAdjustment;

    private double priceBeforeSizeAdjustment;

    private int size;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "order_id")
    private Order order;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "product_id")
    private Product product;

}
