package com.example.server.Pojo;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Entity
@Data
@Table(name = "payment")
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "payTime")
    private Date payTime;

    @Column(name = "amount")
    private Long amount;

    @Column(name = "transactionCode")
    private String transactionCode;

    @ManyToOne()
    @JoinColumn(name = "orderID")
    private Order orderPayment;

    @ManyToOne
    @JoinColumn(name = "paymentModeId")
    private PaymentMethod paymentMethod;



    @Override
    public String toString() {
        return "Payment{" +
                "id=" + id +
                ", PayTime=" + payTime +
                ", Amount=" + amount +
                ", TransactionCode='" + transactionCode + '\'' +
                ", orderPayment=" + orderPayment +
                ", paymentMethod=" + paymentMethod +
                '}';
    }
}
