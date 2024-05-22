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

    public DiamondPriceList(Long id, Double price, Date effDate, Double carat, Cut diamondCut, Origin diamondOrigin, Color diamondColor, Clarity diamondClarity) {
        Id = id;
        this.price = price;
        this.effDate = effDate;
        this.carat = carat;
        DiamondCut = diamondCut;
        DiamondOrigin = diamondOrigin;
        DiamondColor = diamondColor;
        DiamondClarity = diamondClarity;
    }

    public DiamondPriceList() {
    }

    public DiamondPriceList(DiamondPriceList dl) {
        Id = dl.Id;
        this.price = price;
        this.effDate = effDate;
        this.carat = carat;
        DiamondCut = dl.DiamondCut;
        DiamondOrigin = dl.DiamondOrigin;
        DiamondColor = dl.DiamondColor;
        DiamondClarity = dl.DiamondClarity;
    }

    @Override
    public String toString() {
        return "DiamondPriceList{" +
                "Id=" + Id +
                ", price=" + price +
                ", effDate=" + effDate +
                ", carat=" + carat +
                ", DiamondCut=" + DiamondCut +
                ", DiamondOrigin=" + DiamondOrigin +
                ", DiamondColor=" + DiamondColor +
                ", DiamondClarity=" + DiamondClarity +
                '}';
    }
}
