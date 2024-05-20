package com.example.server.Pojo;

import jakarta.persistence.*;

import java.util.Collection;
import java.util.Set;


@Entity
@Table(name = "SIZES")
public class Size {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "size", nullable = false)
    private int size;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "ProductSizes")
    private Set<Product> products;

    @Override
    public String toString() {
        return "Size{" +
                "id=" + id +
                ", size=" + size +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }


}
