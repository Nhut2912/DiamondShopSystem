package com.example.server.Model;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Data
public class ProductDTO {
    private Long id;
    private String name;
    private String code;
    private double price;
    private double sizeUnitPrice;
    private int size;
    private  boolean active;
    private String category;
    private Set<String> images;
    private Set<MaterialDTO> materials;
    private List<DiamondDTO> diamonds;
    private List<PromotionDTO> promotions;

    @CreationTimestamp
    private LocalDateTime dateAdd;
    private int priceRate;

    private double productionCost;

    private double secondaryDiamondCost;

    private double secondaryMaterialCost;


}
