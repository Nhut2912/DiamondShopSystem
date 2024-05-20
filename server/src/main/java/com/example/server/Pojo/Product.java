package com.example.server.Pojo;


import jakarta.persistence.*;


@Entity
@Table(name = "PRODUCTS")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @Column(name = "Code", unique = true, length = 50)
    private String Code;

    @Column(name = "Active")
    private boolean Active;

    @Column(name = "SecondaryDiamondCost")
    private double SecondaryDiamondCost;

    @Column(name = "SecondaryMaterialCost")
    private double SecondaryMaterialCost;

    @Column(name = "ProductionCost")
    private double ProductionCost;

    @Column(name = "PriceRate")
    private int PriceRate;


    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "SizeID")
    private Size ProductSizes;
}
