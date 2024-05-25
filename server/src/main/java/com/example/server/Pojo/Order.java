package com.example.server.Pojo;

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


    @ManyToOne()
    @JoinColumn(name = "CustomerId")
    private Customer customerOrder;

    @Column(name = "OrderStatus")
    private int orderStatus;

    @Column(name = "CancelReason")
    private String cancelReason;


    @OneToMany(cascade = CascadeType.ALL, mappedBy = "orderPayment")
    private Set<Payment> orderPaymentSet;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "orderId")
    private Set<OrderDetail> orderDetailSet;

    public Order() {
    }

    public Order(Long id, String totalPrice, String date, String address, boolean deliveryStatus, Customer customerOrder, int orderStatus, String cancelReason, Set<Payment> orderPaymentSet, Set<OrderDetail> orderDetailSet) {
        this.id = id;
        this.totalPrice = totalPrice;
        Date = date;
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
        Date = o.Date;
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
                ", Date='" + Date + '\'' +
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
