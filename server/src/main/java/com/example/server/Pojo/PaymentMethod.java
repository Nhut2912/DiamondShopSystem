package com.example.server.Pojo;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name ="payment_method")
@Data
public class PaymentMethod {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String method;
}
