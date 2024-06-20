package com.example.server.Pojo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.sql.Date;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
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

    private boolean active ;

    private Date dateStart;

    private Date dateEnd;


    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "promotion_id")
    private List<Promotions_products> promotions_products = new ArrayList<>();

    @Override
    public String toString() {
        return "Promotion{" +
                "id=" + id +
                ", promotionRate=" + promotionRate +
                ", namePromotion='" + namePromotion + '\'' +
                ", active=" + active +
                ", dateStart=" + dateStart +
                ", dateEnd=" + dateEnd +
                '}';
    }
}
