package com.example.server.Pojo;

import jakarta.persistence.*;
import lombok.Data;

import java.sql.Date;

@Entity
@Table(name = "warranty_policy")
@Data
public class WarrantyPolicy {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private Integer warrantyPeriod;

    private boolean isActive;

}
