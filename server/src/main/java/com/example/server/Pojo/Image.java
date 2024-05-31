package com.example.server.Pojo;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Collection;
//@Data
@Entity
@Table(name = "IMAGES")
public class Image {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @Column(name = "URI", nullable = false)
    private String uri;

    @ManyToOne(cascade = CascadeType.ALL, optional = false)
    @JoinColumn(name = "ProductID")
    @JsonBackReference
    private Product Products;

    public Image(Long id, String uri, Product products) {
        Id = id;
        this.uri = uri;
        Products = products;
    }

    public Image() {
    }

    public Image(Image i) {
        Id = i.Id;
        this.uri = uri;
        Products = i.Products;
    }

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
