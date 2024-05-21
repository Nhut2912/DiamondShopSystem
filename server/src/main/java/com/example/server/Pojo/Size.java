package com.example.server.Pojo;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Collection;
import java.util.Set;

@Data
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
}
