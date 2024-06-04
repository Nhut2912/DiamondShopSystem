package com.example.server.Model;

import lombok.Data;

import java.util.Set;

@Data
public class ProductDTO {

    private Long id;
    private String name;
    private String code;
    private double price;
    private double sizeUnitPrice;
    private int size;
    private Set<String> images;

}
