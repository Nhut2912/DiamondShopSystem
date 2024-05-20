package com.example.server.Repository;


import com.example.server.Pojo.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


/*
 * author : TRAN MINH NHUT
 * date : 20/5/2024
 * purpose : MO TA CHUC NANG CO TRONG PRODUCTS
 *
 */

@Repository
public interface IProductRepository extends JpaRepository<Product,Long> {

}
