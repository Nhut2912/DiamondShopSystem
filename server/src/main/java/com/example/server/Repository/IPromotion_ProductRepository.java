package com.example.server.Repository;

import com.example.server.Pojo.Promotions_products;
import com.example.server.Pojo.Promotions_products_Id;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface IPromotion_ProductRepository extends CrudRepository<Promotions_products, Promotions_products_Id> {
    @Modifying
    @Transactional
    @Query("Delete from Promotions_products p where p.promotion.id = :id")
    public void deleteAllByPromotionId(@Param("id") Long id);
}
