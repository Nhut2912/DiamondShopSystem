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

<<<<<<< HEAD

=======

    @JsonBackReference
    @ManyToOne(cascade = CascadeType.ALL, optional = false, fetch = FetchType.LAZY)
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

    @Override
    public String toString() {
        return "Image{" +
                "Id=" + Id +
                ", uri='" + uri + '\''+
                '}';
    }

>>>>>>> 308af14b9d231cd737fe4c3dd64cb555c9abde74
    @ManyToOne(cascade = CascadeType.ALL, optional = false)
    @JoinColumn(name = "productId")
    @JsonBackReference
    private Product product;

<<<<<<< HEAD


=======
>>>>>>> 308af14b9d231cd737fe4c3dd64cb555c9abde74
}
