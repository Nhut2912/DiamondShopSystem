package com.example.server.Pojo;

import jakarta.persistence.*;

import java.util.Date;


@Entity
@Table(name = "DIAMONDPRICELIST")
public class DiamondPriceList {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @Column(name = "Price")
    private Double price;

    @Column(name = "EffDate")
    private Date effDate;

    @Column(name = "Carat")
    private Double carat;

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
