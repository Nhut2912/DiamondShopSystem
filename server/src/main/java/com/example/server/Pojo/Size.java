package com.example.server.Pojo;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Collection;
import java.util.Set;

@Data
@Entity
@Table(name = "SIZES")
public class Size {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "size", nullable = false)
    private int size;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "ProductSizes")
    private Set<Product> products;

    public Size(Long id, int size, Set<Product> products) {
        this.id = id;
        this.size = size;
        this.products = products;
    }

    public Size() {
    }

    public Size(Size s) {
        this.id = s.id;
        this.size = s.size;
        this.products = s.products;
    }

}
