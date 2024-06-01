package com.example.server.Pojo;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Collection;
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


}
