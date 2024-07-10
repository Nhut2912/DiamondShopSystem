package com.example.server.Pojo;

import jakarta.persistence.*;
import lombok.Data;

import java.sql.Date;

@Entity
@Table(name = "diamond_price_list")
@Data
public class DiamondPriceList {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private double caratFrom;

    private double caratTo;

    private Date effDate;

    private double price;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name ="color_id")
    private Color color;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name ="clarity_id")
    private Clarity clarity;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name ="cut_id")
    private Cut cut;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name ="origin_id")
    private Origin origin;
}
