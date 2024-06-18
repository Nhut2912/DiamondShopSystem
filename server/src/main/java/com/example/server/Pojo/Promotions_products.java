package com.example.server.Pojo;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@IdClass(Promotions_products_Id.class)
public class Promotions_products {
    @Id
    @ManyToOne
    @JoinColumn(name = "product_id")
    Product product;

    @Id
    @ManyToOne
    @JoinColumn(name = "promotion_id")
    Promotion promotion;

    public Promotions_products(Product addProduct, Promotion addPromotion){
        product = addProduct;
        promotion = addPromotion;
    }
    public Promotions_products(){

    }

    @Override
    public String toString() {
        return "Promotions_products{" +
                ", promotion=" + promotion +
                '}';
    }
}