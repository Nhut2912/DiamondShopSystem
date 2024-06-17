package com.example.server.Pojo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name ="promotions_products")
public class Promotions_products {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    Product product;


    @ManyToOne(cascade = CascadeType.ALL)
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
                "id=" + id +
                ", promotion=" + promotion +
                '}';
    }
}
