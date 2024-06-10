package com.example.server.Pojo;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Table(name = "orders")
@Data
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String address;

    private LocalDateTime date;

    private boolean isDelivery;

    private String orderStatus;

    private double totalPrice;

    private String cancelReason;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "account_id")
    private Account account;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name ="order_id")
    private Set<OrderDetail> orderDetails;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name ="order_id")
    private Set<Payment> payments;


}
