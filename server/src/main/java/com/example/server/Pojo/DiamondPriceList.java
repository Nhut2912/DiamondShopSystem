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
    private Cut diamondCut;

    @ManyToOne()
    @JoinColumn(name = "originID")
    private Origin diamondOrigin;

    @ManyToOne()
    @JoinColumn(name = "colorID")
    private Color diamondColor;

    @ManyToOne()
    @JoinColumn(name = "clarityID")
    private Clarity diamondClarity;



    public DiamondPriceList() {
    }



    @Override
    public String toString() {
        return "DiamondPriceList{" +
                "Id=" + Id +
                ", price=" + price +
                ", effDate=" + effDate +
                ", carat=" + carat +
                ", DiamondCut=" + diamondCut +
                ", DiamondOrigin=" + diamondOrigin +
                ", DiamondColor=" + diamondColor +
                ", DiamondClarity=" + diamondClarity +
                '}';
    }
}
