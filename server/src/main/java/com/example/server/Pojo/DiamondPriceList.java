package com.example.server.Pojo;

import jakarta.persistence.*;

import java.util.Date;


@Entity
@Table(name = "diamondPriceList")
public class DiamondPriceList {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "diamondPriceListId")
    private Long Id;

    @Column(name = "price")
    private Double price;

    @Column(name = "effDate")
    private Date effDate;

    @Column(name = "carat")
    private Double carat;

    @ManyToOne()
    @JoinColumn(name = "cutID")
    private Cut DiamondCut;

    @ManyToOne()
    @JoinColumn(name = "originID")
    private Origin DiamondOrigin;

    @ManyToOne()
    @JoinColumn(name = "colorID")
    private Color DiamondColor;

    @ManyToOne()
    @JoinColumn(name = "clarityID")
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
        this.price = dl.price;
        this.effDate = dl.effDate;
        this.carat = dl.carat;
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
