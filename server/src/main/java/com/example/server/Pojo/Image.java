package com.example.server.Pojo;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.util.Collection;

import java.util.Objects;


@Data
@Entity
@Table(name = "images")
public class Image {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "uri", nullable = false)
    private String uri;

    @ManyToOne(cascade = CascadeType.ALL, optional = false)
    @JoinColumn(name = "productId")
    @JsonBackReference
    private Product product;

    public Image(Long id, String uri, Product products) {
        id = id;
        this.uri = uri;
        product = products;
    }

    public Image() {
    }

    public Image(Image i) {
        id = i.id;
        this.uri = uri;
        product = i.product;
    }

    @Override
    public String toString() {
        return "Image{" +
                "Id=" + id +
                ", uri='" + uri + '\''+
                '}';
    }


}
