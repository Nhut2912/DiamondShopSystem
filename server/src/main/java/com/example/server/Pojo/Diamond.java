package com.example.server.Pojo;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "diamond")
@Data
public class Diamond {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String code;

    private String certificate;

    private double carat;

    private boolean active = true;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "color_id")
    private Color color;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "cut_id")
    private Cut cut;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "clarity_id")
    private Clarity clarity;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "origin_id")
    private Origin origin;
}
