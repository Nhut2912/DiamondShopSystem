package com.example.server.Pojo;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Set;
/*
 * Author: Pham Trong Hieu
 */
@Data
@Entity
@Table(name = "warrantyPolicy")
public class WarrantyPolicy {

    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    @Column(name = "warrantyPolicyId")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "warrantyPeriod", nullable = false)
    private int warrantyPeriod;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "warrantyPolicy")
    @JsonManagedReference
    private Set<Warranty> warrantySet;

    public WarrantyPolicy(Long id, String name, int warrantyPeriod, Set<Warranty> warrantySet) {
        this.id = id;
        this.name = name;
        this.warrantyPeriod = warrantyPeriod;
        this.warrantySet = warrantySet;
    }

    public WarrantyPolicy() {
    }

    public WarrantyPolicy(WarrantyPolicy w) {
        this.id = w.id;
        this.name = w.name;
        this.warrantyPeriod = w.warrantyPeriod;
        this.warrantySet = w.warrantySet;
    }

    @Override
    public String toString() {
        return "WarrantyPolicy{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", warrantyPeriod=" + warrantyPeriod +
                ", warrantySet=" + warrantySet +
                '}';
    }
}
