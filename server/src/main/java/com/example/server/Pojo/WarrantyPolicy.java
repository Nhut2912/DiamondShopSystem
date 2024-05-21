package com.example.server.Pojo;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Set;
/*
 * Author: Pham Trong Hieu
 */
@Data
@Entity
@Table(name = "WarrantyPolicy")
public class WarrantyPolicy {

    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private Long id;

    @Column(name = "Name")
    private String name;

    @Column(name = "WarrantyPeriod", nullable = false)
    private int warrantyPeriod;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "warrantyPolicy")
    private Set<Warranty> warrantySet;


    @Override
    public String toString() {
        return "WarrantyPolicy{" +
                "Id=" + id +
                ", name='" + name + '\'' +
                ", warrantyPeriod=" + warrantyPeriod +
                '}';
    }

}
