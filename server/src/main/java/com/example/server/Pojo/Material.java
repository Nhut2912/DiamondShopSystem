package com.example.server.Pojo;

import jakarta.persistence.*;
import jakarta.persistence.criteria.CriteriaBuilder;
import lombok.Data;

import java.util.Set;

@Entity
@Table(name = "material")
@Data
public class Material {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;


    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "material_id")
    private Set<ProductMaterial> productMaterials;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "material_id")
    private Set<MaterialPriceList> materialPriceLists;
}
