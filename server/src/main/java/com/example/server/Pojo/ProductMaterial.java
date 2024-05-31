package com.example.server.Pojo;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.Cascade;

import java.io.Serializable;

/*
 * Author: Pham Trong Hieu
 */
@Data
@Entity
@Table(name = "ProductMaterial")
public class ProductMaterial {
    @Data
    @Embeddable
    public static class ProductMaterialId implements Serializable {
        @Column(name = "productId")
        protected long productId;
        @Column(name = "materialId")
        protected long materialId;
    }
    @EmbeddedId
    protected ProductMaterialId id;


    @ManyToOne
    @MapsId("productId")
    @JoinColumn(name = "productID", insertable = false, updatable = false)
    protected Product product;


    @ManyToOne
    @MapsId("materialID")
    @JoinColumn(name = "materialID", insertable = false, updatable = false)
    protected Material material;

    @Column(name = "weight")
    protected double weight;


    @Override
    public String toString() {
        return "ProductMaterial{" +
                "products=" + product +
                ", materials=" + material +
                ", weight=" + weight +
                '}';
    }


}
