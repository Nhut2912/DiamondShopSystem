package com.example.server.Pojo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.io.Serializable;

@Data
public class Promotions_products_Id implements Serializable {
    @JsonIgnore
    private Product product;
    @JsonIgnore
    private Promotion promotion;
}