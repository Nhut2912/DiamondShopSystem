package com.example.server.Pojo;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Collection;
@Data
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

    //    @Override
//    public String toString() {
//        return "Image{" +
//                "Id=" + Id +
//                ", uri='" + uri +
//                '}';
//    }



}
