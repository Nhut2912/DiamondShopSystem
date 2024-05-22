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

    public Diamond(Long id, String code, String certificate, boolean active, Double carat, Product diamondProduct, Cut diamondCut, Origin diamondOrigin, Color diamondColor, Clarity diamondClarity) {
        Id = id;
        this.code = code;
        this.certificate = certificate;
        this.active = active;
        this.carat = carat;
        DiamondProduct = diamondProduct;
        DiamondCut = diamondCut;
        DiamondOrigin = diamondOrigin;
        DiamondColor = diamondColor;
        DiamondClarity = diamondClarity;
    }

    public Diamond() {
    }

    public Diamond(Diamond d) {
        Id = d.Id;
        this.code = code;
        this.certificate = certificate;
        this.active = active;
        this.carat = carat;
        DiamondProduct = d.DiamondProduct;
        DiamondCut = d.DiamondCut;
        DiamondOrigin = d.DiamondOrigin;
        DiamondColor = d.DiamondColor;
        DiamondClarity = d.DiamondClarity;
    }

    @Override
    public String toString() {
        return "Diamond{" +
                "Id=" + Id +
                ", code='" + code + '\'' +
                ", certificate='" + certificate + '\'' +
                ", active=" + active +
                ", carat=" + carat +
                ", DiamondProduct=" + DiamondProduct +
                ", DiamondCut=" + DiamondCut +
                ", DiamondOrigin=" + DiamondOrigin +
                ", DiamondColor=" + DiamondColor +
                ", DiamondClarity=" + DiamondClarity +
                '}';
    }
}
