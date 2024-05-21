package com.example.server.Pojo;

import jakarta.persistence.*;
import org.hibernate.annotations.Cascade;

/*
 * Author: Pham Trong Hieu
 */
@Entity
@Table(name = "ProductMaterial")
public class ProductMaterial {

    @EmbeddedId
    private Product_Material_Key id;

    @ManyToOne(cascade = CascadeType.ALL)
    @MapsId("productId")
    @JoinColumn(name = "ProductID")
    private Product products;

    @ManyToOne(cascade = CascadeType.ALL)
    @MapsId("materialId")
    @JoinColumn(name = "MaterialID")
    private Material materials;

    @Column(name = "Weight")
    private double weight;
}
