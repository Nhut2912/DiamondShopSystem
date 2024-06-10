package com.example.server.Service.ProductMaterial;

import com.example.server.Pojo.ProductMaterial;
import com.example.server.Repository.IProductMaterialRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class ProductMaterialService implements IProductMaterialService {

    @Autowired
    private IProductMaterialRepository productMaterialRepository;

    @Override
    public List<ProductMaterial> getProductMaterials(Long productID) {
        return productMaterialRepository.getProductMaterialByProduct_Id(productID);
    }
}
