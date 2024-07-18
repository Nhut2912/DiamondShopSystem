package com.example.server.Pojo;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "product")
@Data
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String code;

    private String name;

    @CreationTimestamp
    private LocalDateTime dateAdd;

    private boolean active = true;

    private int priceRate;

    private double productionCost;

    private double secondaryDiamondCost;

    private double secondaryMaterialCost;

    private double sizeUnitPrice;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "size_id")
    private Size size;


    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "product_id")
    private Set<Diamond> diamonds = new HashSet<>();

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "product_id")
    private Set<Image> images ;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "category_id")
    private Category category;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "product_id")
    private Set<ProductMaterial> productMaterials = new HashSet<>();

    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private List<Promotions_products> promotions_products = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name ="product_id")
    private Set<OrderDetail> orderDetails;
}
