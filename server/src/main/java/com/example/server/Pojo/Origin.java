package com.example.server.Pojo;

import jakarta.persistence.*;

import java.util.Set;

@Entity
@Table(name = "ORIGIN")
public class Origin {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @Column(name = "Origin",nullable = false)
    private boolean origin;

    @OneToMany(cascade = CascadeType.ALL,mappedBy = "DiamondOrigin")
    private Set<Diamond> diamondOrigins;

    @OneToMany(cascade = CascadeType.ALL,mappedBy = "DiamondOrigin")
    private Set<DiamondPriceList> diamondOriginss;
}
