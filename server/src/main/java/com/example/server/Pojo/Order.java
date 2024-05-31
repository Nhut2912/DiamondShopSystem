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
    @JoinColumn(name = "customerId")
    private Customer customerOrder;

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

    public Order(Long id, String totalPrice, String date, String address, boolean deliveryStatus, Customer customerOrder, int orderStatus, String cancelReason, Set<Payment> orderPaymentSet, Set<OrderDetail> orderDetailSet) {
        this.id = id;
        this.totalPrice = totalPrice;
        date = date;
        this.address = address;
        this.deliveryStatus = deliveryStatus;
        this.customerOrder = customerOrder;
        this.orderStatus = orderStatus;
        this.cancelReason = cancelReason;
        this.orderPaymentSet = orderPaymentSet;
        this.orderDetailSet = orderDetailSet;
    }

    public Order(Order o) {
        this.id = o.id;
        this.totalPrice = o.totalPrice;
        date = o.date;
        this.address = o.address;
        this.deliveryStatus = o.deliveryStatus;
        this.customerOrder = o.customerOrder;
        this.orderStatus = o.orderStatus;
        this.cancelReason = o.cancelReason;
        this.orderPaymentSet = o.orderPaymentSet;
        this.orderDetailSet = o.orderDetailSet;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", totalPrice='" + totalPrice + '\'' +
                ", Date='" + date + '\'' +
                ", address='" + address + '\'' +
                ", deliveryStatus=" + deliveryStatus +
                ", customerOrder=" + customerOrder +
                ", orderStatus=" + orderStatus +
                ", cancelReason='" + cancelReason + '\'' +
                ", orderPaymentSet=" + orderPaymentSet +
                ", orderDetailSet=" + orderDetailSet +
                '}';
    }

}
