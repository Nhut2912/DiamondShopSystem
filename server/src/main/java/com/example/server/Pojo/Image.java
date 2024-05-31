package com.example.server.Pojo;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Collection;
//@Data
@Entity
@Table(name = "images")
public class Image {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "uri", nullable = false)
    private String uri;

    @ManyToOne(cascade = CascadeType.ALL, optional = false)
<<<<<<< HEAD
    @JoinColumn(name = "productId")
    @JsonBackReference
    private Product product;
=======
    @JoinColumn(name = "ProductID")
    @JsonBackReference
    private Product Products;
>>>>>>> 66cd59eec7390c32ee0e12456e935defed9dec98


    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }


    public Product getProducts() {
        return Products;
    }

    public void setProducts(Product products) {
        Products = products;
    }
}
