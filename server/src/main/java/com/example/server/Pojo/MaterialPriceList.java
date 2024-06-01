package com.example.server.Pojo;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

/*
 * Author: Pham Trong Hieu
 */
@Data
@Entity
@Table(name = "materialPriceList")
public class MaterialPriceList {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "materialPriceListId")
    private Long id;

    @Column(name = "effDate")
    private Date effDate;

    @Column(name = "sellPrice")
    private double sellPrice;

    @Column(name = "buyPrice")
    private double buyPrice;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "materialId")
    private Material material;


}
