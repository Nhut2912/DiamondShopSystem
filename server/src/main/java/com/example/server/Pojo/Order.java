package com.example.server.Pojo;

import jakarta.persistence.*;
import lombok.Data;

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

//<<<<<<< HEAD
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "orderPayment")
    private Set<Payment> orderPaymentSet;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "orderId")
    private Set<OrderDetail> orderDetailSet;

    public Order() {
    }

    public Order(Long id, String totalPrice, int orderStatus, String date, String address, boolean deliveryStatus, /*long customerId, int orderId,*/ String cancelReason) {
        this.id = id;
        this.totalPrice = totalPrice;
        this.orderStatus = orderStatus;
        Date = date;
        this.address = address;
        this.deliveryStatus = deliveryStatus;
 //       this.customerId = customerId;
  //      this.orderId = orderId;
        this.cancelReason = cancelReason;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(String totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public boolean isDeliveryStatus() {
        return deliveryStatus;
    }

    public void setDeliveryStatus(boolean deliveryStatus) {
        this.deliveryStatus = deliveryStatus;
    }

//    public long getCustomerId() {
//        return customerId;
//    }
//
//    public void setCustomerId(long customerId) {
//        this.customerId = customerId;
//    }

    public int getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(int orderStatus) {
        this.orderStatus = orderStatus;
    }

//    public int getOrderId() {
//        return orderId;
//    }
//
//    public void setOrderId(int orderId) {
//        this.orderId = orderId;
//    }

    public String getCancelReason() {
        return cancelReason;
    }

    public void setCancelReason(String cancelReason) {
        this.cancelReason = cancelReason;
    }
//=======
//
//>>>>>>> 367fdd224da5a4f45dd0623da5c612480fb252c3
}
