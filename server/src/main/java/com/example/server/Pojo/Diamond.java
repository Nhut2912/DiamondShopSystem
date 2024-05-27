package com.example.server.Pojo;

import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Table(name="DIAMOND")
public class Diamond {

    @Getter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @Getter
    @Column(name = "Code", unique = true, length =50)
    private String code;

    @Getter
    @Column(name ="Certificate")
    private String certificate;

    @Getter
    @Column(name ="Active",nullable = false)
    private boolean active;

    @Getter
    @Column(name = "Carat")
    private Double carat;

    @Getter
    @ManyToOne()
    @JoinColumn(name = "ProductID")
    private Product DiamondProduct;


    @Getter
    @ManyToOne()
    @JoinColumn(name = "CutID")
    private Cut DiamondCut;

    @Getter
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
        this.code = d.code;
        this.certificate = d.certificate;
        this.active = d.active;
        this.carat = d.carat;
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

    public void setId(Long id) {
        Id = id;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setCertificate(String certificate) {
        this.certificate = certificate;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public void setCarat(Double carat) {
        this.carat = carat;
    }

    public void setDiamondProduct(Product diamondProduct) {
        DiamondProduct = diamondProduct;
    }

    public void setDiamondCut(Cut diamondCut) {
        DiamondCut = diamondCut;
    }

    public void setDiamondOrigin(Origin diamondOrigin) {
        DiamondOrigin = diamondOrigin;
    }

    public Color getDiamondColor() {
        return DiamondColor;
    }

    public void setDiamondColor(Color diamondColor) {
        DiamondColor = diamondColor;
    }

    public Clarity getDiamondClarity() {
        return DiamondClarity;
    }

    public void setDiamondClarity(Clarity diamondClarity) {
        DiamondClarity = diamondClarity;
    }
}
