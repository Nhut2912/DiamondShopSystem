package com.example.server.Pojo;

import jakarta.persistence.*;
import java.util.Set;
/*
 * Author: Pham Trong Hieu
 */
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

    public WarrantyPolicy() {
    }

    public WarrantyPolicy(Long id, String name, int warrantyPeriod) {
        this.id = id;
        this.name = name;
        this.warrantyPeriod = warrantyPeriod;
    }

    @Override
    public String toString() {
        return "WarrantyPolicy{" +
                "Id=" + id +
                ", name='" + name + '\'' +
                ", warrantyPeriod=" + warrantyPeriod +
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

    public int getWarrantyPeriod() {
        return warrantyPeriod;
    }

    public void setWarrantyPeriod(int warrantyPeriod) {
        this.warrantyPeriod = warrantyPeriod;
    }
}
