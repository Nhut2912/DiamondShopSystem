package com.example.server.Pojo;

import jakarta.persistence.*;
import lombok.Data;

import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "promotion")
@Data
public class Promotion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int promotionRate;

    private String namePromotion;

    private boolean active = true;

    private Date dateStart;

    private Date dateEnd;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name ="promotions_products",
        joinColumns = @JoinColumn(name = "promotion_id"),
            inverseJoinColumns = @JoinColumn(name ="product_id")
    )
    private Set<Product> products = new HashSet<Product>();

}
