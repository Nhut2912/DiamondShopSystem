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

    public ProductMaterial(Product products, Material materials, double weight) {
        this.products = products;
        this.materials = materials;
        this.weight = weight;
    }

    public ProductMaterial() {
    }

    public ProductMaterial(ProductMaterial p) {
        this.products = p.products;
        this.materials = p.materials;
        this.weight = p.weight;
    }

    @Override
    public String toString() {
        return "ProductMaterial{" +
                "products=" + products +
                ", materials=" + materials +
                ", weight=" + weight +
                '}';
    }
}
