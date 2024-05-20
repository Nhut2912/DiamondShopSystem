package com.example.server.Pojo;

import jakarta.persistence.*;

import java.util.Set;


@Entity
@Table(name = "SIZES")
public class Size {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    private int size;

    @OneToMany(mappedBy = "ProductSizes" ,cascade = CascadeType.ALL)
    private Set<Product> products;
}
