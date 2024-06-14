package com.example.server.Pojo;

import lombok.Data;

import java.io.Serializable;

@Data
public class Promotions_products_Id implements Serializable {
    private Product product;
    private Promotion promotion;
}
