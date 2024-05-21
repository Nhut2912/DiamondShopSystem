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



    @Override
    public String toString() {
        return "Material{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

}
