package com.example.server.Pojo;

<<<<<<< HEAD
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
=======
import com.fasterxml.jackson.annotation.JsonIgnore;
>>>>>>> 7ecfc1bd0c699532b8f9d339266719ce099652fc
import jakarta.persistence.*;
import lombok.Data;

import java.util.Set;

/*
 *Author: Pham Trong Hieu
 * Date: 21/5/2024
 */
@Data
@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
@Table(name = "Category")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "categoryId")
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "active", nullable = false)
    private boolean active;

    @Column(name = "categoryType")
    private int categoryType;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "productCategory")
    @JsonIgnore
    private Set<Product> products;

<<<<<<< HEAD
=======
    public Category() {
    }

    public Category(Long id, String name, boolean active, int categoryType, Set<Product> products) {
        this.id = id;
        this.name = name;
        this.active = active;
        this.categoryType = categoryType;
        this.products = products;
    }

    public Category(Category cate) {
        this.id = cate.id;
        this.name = cate.name;
        this.active = cate.active;
        this.categoryType = cate.categoryType;
        this.products = cate.products;
    }
>>>>>>> 7ecfc1bd0c699532b8f9d339266719ce099652fc

    @Override
    public String toString() {
        return "Category{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", active=" + active +
                ", CategoryType=" + categoryType +
                ", products=" + products +
                '}';
    }
}
