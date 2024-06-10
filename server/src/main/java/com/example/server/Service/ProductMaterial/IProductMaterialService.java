package com.example.server.Service.ProductMaterial;

import com.example.server.Pojo.ProductMaterial;

import java.util.List;
import java.util.Set;

public interface IProductMaterialService {

    public List<ProductMaterial> getProductMaterials(Long productID);

}
