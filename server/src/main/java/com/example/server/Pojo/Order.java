package com.example.server.Pojo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import org.aspectj.weaver.ast.Or;

import java.util.Set;

/*
 *Author: Tran Viet Hoang
 * Date: 21/5/2024
 */
@Data
@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "orderId")
    private Long id;

    @Column(name = "totalPrice")
    private String totalPrice;

    @Column(name = "date")
    private String date;

    @Column(name = "address")
    private String address;

    @Column(name = "deliveryStatus")
    private boolean deliveryStatus;


    @ManyToOne()
    @JoinColumn(name = "accountID")
    private Account accountOrder;

    @Column(name = "orderStatus")
    private int orderStatus;

    @Column(name = "cancelReason")
    private String cancelReason;


    @OneToMany(cascade = CascadeType.ALL, mappedBy = "orderPayment")
    private Set<Payment> orderPaymentSet;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "orderId")
    @JsonIgnore
    private Set<OrderDetail> orderDetailSet;

    public Order() {
    }

    public Order(Long id, String totalPrice, String date, String address, boolean deliveryStatus, Account accountOrder, int orderStatus, String cancelReason) {
        this.id = id;
        this.totalPrice = totalPrice;
        this.date = date;
        this.address = address;
        this.deliveryStatus = deliveryStatus;
        this.accountOrder = accountOrder;
        this.orderStatus = orderStatus;
        this.cancelReason = cancelReason;
    }
}
