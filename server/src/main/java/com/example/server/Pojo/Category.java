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

    public Category() {
    }

    public Category(Long id, String name, boolean active, int categoryType, Set<Product> products) {
        this.id = id;
        this.name = name;
        this.active = active;
        CategoryType = categoryType;
        this.products = products;
    }

    public Category(Category cate) {
        this.id = cate.id;
        this.name = cate.name;
        this.active = cate.active;
        this.CategoryType = cate.CategoryType;
        this.products = cate.products;
    }

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
