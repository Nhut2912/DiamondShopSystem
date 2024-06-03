package com.example.server.Pojo;

import jakarta.persistence.*;
import lombok.Data;

import java.sql.Date;
import java.util.Set;

@Entity
@Table(name = "material_price_list")
@Data
public class MaterialPriceList {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Date effDate;

    private double sellPrice;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name ="material_id")
    private Material material;

}
