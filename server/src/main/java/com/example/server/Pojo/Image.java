package com.example.server.Pojo;

import jakarta.persistence.*;

import java.util.Collection;

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
    private Product Products;

    public Image() {
    }

    public Image(Long id, String uri, Product products) {
        Id = id;
        this.uri = uri;
        Products = products;
    }

    @Override
    public String toString() {
        return "Image{" +
                "Id=" + Id +
                ", uri='" + uri +
                '}';
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

}
