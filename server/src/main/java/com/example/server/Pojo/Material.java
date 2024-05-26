package com.example.server.Pojo;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Set;
@Data
@Entity
@Table(name = "Material")
public class Material {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "Name")
    private String name;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "materials")
    private Set<MaterialPriceList> materialPrice;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "materials")
    private Set<ProductMaterial> materialSet;

    public Material(Long id, String name, Set<MaterialPriceList> materialPrice, Set<ProductMaterial> materialSet) {
        this.id = id;
        this.name = name;
        this.materialPrice = materialPrice;
        this.materialSet = materialSet;
    }

    public Material() {
    }

    public Material(Material m) {
        this.id = m.id;
        this.name = m.name;
        this.materialPrice = m.materialPrice;
        this.materialSet = m.materialSet;
    }

}
