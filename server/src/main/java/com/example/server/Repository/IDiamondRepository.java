package com.example.server.Repository;

import com.example.server.Pojo.Diamond;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface IDiamondRepository extends JpaRepository<Diamond,Long> {

    @Query("SELECT d FROM Diamond d WHERE d.product.id = :product_id")
    public List<Diamond> getDiamondsByProductId(@Param("product_id") Long Id);
}
