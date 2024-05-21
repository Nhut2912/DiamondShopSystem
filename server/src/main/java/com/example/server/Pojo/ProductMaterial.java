package com.example.server.Pojo;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.Cascade;

/*
 * Author: Pham Trong Hieu
 */
@Data
@Entity
@Table(name = "ProductMaterial")
public class ProductMaterial {

    @Id
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "ProductID")
    private Product products;

    @Id
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "MaterialID")
    private Material materials;

    @Column(name = "Weight")
    private double weight;
}
