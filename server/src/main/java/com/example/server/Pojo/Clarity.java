package com.example.server.Pojo;

import jakarta.persistence.*;

import java.util.Set;

@Entity
@Table(name = "CLARITY")
public class Clarity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @Column(name = "Clarity")
    private String clarity;


    @OneToMany(cascade = CascadeType.ALL,mappedBy = "DiamondClarity")
    private Set<Diamond> diamondClaritys;

    @OneToMany(cascade = CascadeType.ALL,mappedBy = "DiamondClarity")
    private Set<DiamondPriceList> diamondClarityss;
}
