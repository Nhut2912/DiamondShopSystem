package com.example.server.Pojo;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;

import java.util.HashSet;
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
    private Set<Diamond> diamonds;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "product_id")
    private Set<Image> images = new HashSet<>();

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "category_id")
    private Category category;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "product_id")
    private Set<ProductMaterial> productMaterials;

    @ManyToMany(mappedBy = "products")
    private Set<Promotion> promotions = new HashSet<Promotion>();

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name ="product_id")
    private Set<OrderDetail> orderDetails;



}
