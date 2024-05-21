package com.example.server.Pojo;

import jakarta.persistence.*;

@Entity
@Table(name="DIAMOND")
public class Diamond {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @Column(name = "Code", unique = true, length =50)
    private String code;

    @Column(name ="Certificate")
    private String certificate;

    @Column(name ="Active",nullable = false)
    private boolean active;

    @Column(name = "Carat")
    private Double carat;

    @ManyToOne()
    @JoinColumn(name = "ProductID")
    private Product DiamondProduct;


    @ManyToOne()
    @JoinColumn(name = "CutID")
    private Cut DiamondCut;

    @ManyToOne()
    @JoinColumn(name = "OriginID")
    private Origin DiamondOrigin;

    @ManyToOne()
    @JoinColumn(name = "ColorID")
    private Color DiamondColor;

    @ManyToOne()
    @JoinColumn(name = "ClarityID")
    private Clarity DiamondClarity;
}
