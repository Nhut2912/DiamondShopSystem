package com.example.server.Pojo;

import jakarta.persistence.*;

import java.util.Set;

/*
 *Author: Pham Trong Hieu
 * Date: 21/5/2024
 */
@Entity
@Table(name = "Category")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "active", nullable = false)
    private boolean active;

    @Column(name = "CategoryType")
    private int CategoryType;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "ProductCategory")
    private Set<Product> products;

    public Category(Long id, String name, boolean active, int categoryType, Set<Product> products) {
        this.id = id;
        this.name = name;
        this.active = active;
        CategoryType = categoryType;
        this.products = products;
    }

    public Category() {
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public int getCategoryType() {
        return CategoryType;
    }

    public void setCategoryType(int categoryType) {
        CategoryType = categoryType;
    }

    public Set<Product> getProducts() {
        return products;
    }

    public void setProducts(Set<Product> products) {
        this.products = products;
    }
}
