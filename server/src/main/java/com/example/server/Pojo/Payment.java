package com.example.server.Pojo;
import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "Payment")
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "PayTime")
    private Date PayTime;

    @Column(name = "Amount")
    private Long Amount;

    @Column(name = "TransactionCode")
    private String TransactionCode;

    @ManyToOne()
    @JoinColumn(name = "OrderID")
    private Order orderPayment;

    @ManyToOne
    @JoinColumn(name = "PaymentModeID")
    private PaymentMethod paymentMethod;

}
