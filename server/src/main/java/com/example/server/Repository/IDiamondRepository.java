package com.example.server.Repository;

import com.example.server.Pojo.Diamond;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Repository
public interface IDiamondRepository extends JpaRepository<Diamond,Long> {

    public List<Diamond>  getDiamondByProduct_Id(Long Id);

    @Modifying
    @Transactional
    @Query("DELETE FROM Diamond d WHERE d.product.id =:product_id")
    public void deleteDiamondsByProductID(@Param("product_id") Long product_id);
}
