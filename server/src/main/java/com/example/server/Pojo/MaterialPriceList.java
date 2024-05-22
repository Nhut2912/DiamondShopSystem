package com.example.server.Pojo;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

/*
* Author: Pham Trong Hieu
*/
@Data
@Entity
@Table(name = "MaterialPriceList")
public class MaterialPriceList {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "EffDate")
    private Date EffDate;

    @Column(name = "SellPrice")
    private double SellPrice;

    @Column(name = "BuyPrice")
    private double BuyPrice;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "MaterialID")
    private Material materials;

    public MaterialPriceList(Long id, Date effDate, double sellPrice, double buyPrice, Material materials) {
        this.id = id;
        EffDate = effDate;
        SellPrice = sellPrice;
        BuyPrice = buyPrice;
        this.materials = materials;
    }

    public MaterialPriceList() {
    }

    public MaterialPriceList(MaterialPriceList m) {
        this.id = m.id;
        EffDate = m.EffDate;
        SellPrice = m.SellPrice;
        BuyPrice = m.BuyPrice;
        this.materials = m.materials;
    }
}
