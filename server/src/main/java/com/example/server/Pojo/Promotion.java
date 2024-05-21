package com.example.server.Pojo;

import jakarta.persistence.*;

import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "Promotion")
public class Promotion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "DateStart")
    private Date dateStart;

    @Column(name = "DateEnd")
    private Date dateEnd;

    @Column(name = "Active")
    private boolean active;

    @OneToMany(cascade = CascadeType.ALL,mappedBy = "promotionId")
    private Set<Promotions_Customer> promotions;
}
