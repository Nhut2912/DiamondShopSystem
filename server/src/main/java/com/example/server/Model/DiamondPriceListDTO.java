package com.example.server.Model;

import lombok.Data;

import java.sql.Date;

@Data
public class DiamondPriceListDTO {
    private Long id;

    private float caratFrom;

    private float caratTo;

    private Date effDate;

    private double price;

    private String clarity;

    private String color;

    private String cut;

    private String origin;

}
