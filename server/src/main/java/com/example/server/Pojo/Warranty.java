package com.example.server.Pojo;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "warranty")
@Data
public class Warranty {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String userName;

    private String phoneNumber;

    private String address;

    private boolean status ;

    private Date dateStart;

    private Date dateEnd;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "product_id")
    private Product product;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "warranty_policy_id")
    private WarrantyPolicy warrantyPolicies;

}
