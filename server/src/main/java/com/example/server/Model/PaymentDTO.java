package com.example.server.Model;

import lombok.Data;

import java.sql.Date;
@Data
public class PaymentDTO {

    private double amount;

    private Date payTime;

    private String transactionCode;

    private String image;

    private PaymentMethodDTO paymentMethodDTO;
}
