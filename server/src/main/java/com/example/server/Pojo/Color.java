package com.example.server.Pojo;

import jakarta.persistence.*;

import java.util.Set;

@Entity
@Table(name = "COLOR")
public class Color {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @Column(name = "Color")
    private String color;

    @OneToMany(cascade = CascadeType.ALL,mappedBy = "DiamondColor")
    private Set<Diamond> diamondColors;

    @OneToMany(cascade = CascadeType.ALL,mappedBy = "DiamondColor")
    private Set<DiamondPriceList> diamondColorss;
}
