package com.example.server.Services;

import com.example.server.Pojo.Product;
import org.springframework.http.ResponseEntity;

import java.util.List;

/*
 * author : TRAN MINH NHUT
 * date : 20/5/2024
 * purpose : MO TA CHUC NANG CO TRONG PRODUCTS
 *
 */


public interface IProductServices {
    /*
     * Author: Pham Trong Hieu
     * Date: 24/5/2024
     */
    public ResponseEntity<Product> save(Product product, Long categoryID, Long sizeID, Long warrantyID);

    public void delete(Long ProductID);

    public List<Product> getProducts();


}
