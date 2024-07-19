package com.example.server.Repository;


import com.example.server.Pojo.Category;
import com.example.server.Pojo.Product;
import com.example.server.Pojo.ProductMaterial;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface IProductRepository extends JpaRepository<Product,Long> {


    public List<Product> getProductsByActive(boolean active);

        List<Product>  findAllByProductMaterialsOrCategory(Set<ProductMaterial> materials, Category category);

         List<Product> findByNameContainingIgnoreCase(String name);
}
