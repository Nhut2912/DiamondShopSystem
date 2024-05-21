package com.example.server.Pojo;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.io.Serializable;

/*
 * Author: Pham Trong Hieu
 */
@Embeddable
public class Product_Material_Key implements Serializable {
    @Column(name = "ProductID")
    private Long productId;
    @Column(name = "MaterialID")
    private Long materialId;
}
