package com.example.server.Services;

import com.example.server.Pojo.*;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


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

    public ResponseEntity<?> saveProduct(Product product);


    public void delete(Long ProductID);

    public Iterable<Product> getProducts();


}
