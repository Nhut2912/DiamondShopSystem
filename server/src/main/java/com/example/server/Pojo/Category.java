package com.example.server.Pojo;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Set;

/*
 *Author: Pham Trong Hieu
 * Date: 21/5/2024
 */
@Data
@Entity
@Table(name = "Category")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "Name", nullable = false)
    private String name;

    @Column(name = "Active", nullable = false)
    private boolean active;

    @Column(name = "CategoryType")
    private int CategoryType;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "ProductCategory")
    private Set<Product> products;

    @Override
    public String toString() {
        return "Category{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", active=" + active +
                ", CategoryType=" + CategoryType +
                ", products=" + products +
                '}';
    }


}
