package com.example.server.Services;

import com.example.server.Pojo.*;
<<<<<<< HEAD
import org.springframework.http.ResponseEntity;
=======
import com.example.server.Requests.ProductDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
>>>>>>> 7ecfc1bd0c699532b8f9d339266719ce099652fc

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
<<<<<<< HEAD
    public ResponseEntity<Product> saveProduct(Product product);
=======

    public ResponseEntity<?> saveProduct(Product product);
>>>>>>> 7ecfc1bd0c699532b8f9d339266719ce099652fc

    public void delete(Long ProductID);

    public Iterable<Product> getProducts();


}
