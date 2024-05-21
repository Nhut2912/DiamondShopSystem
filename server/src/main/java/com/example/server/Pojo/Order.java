package com.example.server.Pojo;

import jakarta.persistence.*;
import lombok.Data;

/*
 *Author: Tran Viet Hoang
 * Date: 21/5/2024
 */
@Data
@Entity
@Table(name = "Orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "TotalPrice")
    private String totalPrice;

    @Column(name = "Date")
    private String Date;

    @Column(name = "Address")
    private String address;

    @Column(name = "DeliveryStatus")
    private boolean deliveryStatus;

    @Column(name = "CustomerId")
    private long customerId;

    @Column(name = "OrderStatus")
    private int orderStatus;

    @Column(name = "OrderId")
    private int orderId;

    @Column(name = "CancelReason")
    private String cancelReason;


}
