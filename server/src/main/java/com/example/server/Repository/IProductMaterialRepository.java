package com.example.server.Repository;

import com.example.server.Pojo.ProductMaterial;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface IProductMaterialRepository extends JpaRepository<ProductMaterial,Long> {

    @Query("SELECT p FROM ProductMaterial p WHERE p.product.id =:product_id")
    public List<ProductMaterial> getProductMaterialByProduct_Id(@Param("product_id") Long id);
}
