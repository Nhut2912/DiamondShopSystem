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


}
