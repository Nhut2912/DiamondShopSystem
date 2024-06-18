package com.example.server.Model;

import lombok.Data;

import java.sql.Date;

@Data
public class MaterialPriceListDTO {
    private Long id;
    private Date effDate;
    private double sellPrice;
    private String material;
}
