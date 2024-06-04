package com.example.server.Service.ProductMaterial;

import com.example.server.Pojo.ProductMaterial;

import java.util.Set;

public interface IProductMaterialService {

    public Set<ProductMaterial> getProductMaterials(Long productID);

}
