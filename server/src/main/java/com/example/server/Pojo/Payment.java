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

    public Payment(Long id, Date payTime, Long amount, String transactionCode, Order orderPayment, PaymentMethod paymentMethod) {
        this.id = id;
        PayTime = payTime;
        Amount = amount;
        TransactionCode = transactionCode;
        this.orderPayment = orderPayment;
        this.paymentMethod = paymentMethod;
    }

    public Payment() {
    }

    public Payment(Payment p) {
        this.id = p.id;
        PayTime = p.PayTime;
        Amount = p.Amount;
        TransactionCode = p.TransactionCode;
        this.orderPayment = p.orderPayment;
        this.paymentMethod = p.paymentMethod;
    }

    @Override
    public String toString() {
        return "Payment{" +
                "id=" + id +
                ", PayTime=" + PayTime +
                ", Amount=" + Amount +
                ", TransactionCode='" + TransactionCode + '\'' +
                ", orderPayment=" + orderPayment +
                ", paymentMethod=" + paymentMethod +
                '}';
    }
}
