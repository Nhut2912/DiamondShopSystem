package com.example.server.Pojo;

import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Table(name="DIAMOND")
public class Diamond {

    @Getter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "diamondId")
    private Long Id;

    @Getter
    @Column(name = "code", unique = true, length =50)
    private String code;

    @Getter
    @Column(name ="certificate")
    private String certificate;

    @Getter
    @Column(name ="active",nullable = false)
    private boolean active;

    @Getter
    @Column(name = "carat")
    private Double carat;

    @Getter
    @ManyToOne()
    @JoinColumn(name = "productId")
    private Product diamondProduct;


    @Getter
    @ManyToOne()
    @JoinColumn(name = "cutID")
    private Cut diamondCut;

    @Getter
    @ManyToOne()
    @JoinColumn(name = "originID")
    private Origin diamondOrigin;

    @ManyToOne()
    @JoinColumn(name = "colorID")
    private Color diamondColor;

    @ManyToOne()
    @JoinColumn(name = "clarityID")
    private Clarity diamondClarity;



    @Override
    public String toString() {
        return "Diamond{" +
                "Id=" + Id +
                ", code='" + code + '\'' +
                ", certificate='" + certificate + '\'' +
                ", active=" + active +
                ", carat=" + carat +
                ", DiamondProduct=" + diamondProduct +
                ", DiamondCut=" + diamondCut +
                ", DiamondOrigin=" + diamondOrigin +
                ", DiamondColor=" + diamondColor +
                ", DiamondClarity=" + diamondClarity +
                '}';
    }


}
