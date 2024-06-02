package com.example.server.Services;

<<<<<<< HEAD
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
=======
>>>>>>> 308af14b9d231cd737fe4c3dd64cb555c9abde74

import com.example.server.Pojo.Product;

public interface IProductServices {
<<<<<<< HEAD
    /*
     * Author: Pham Trong Hieu
     * Date: 24/5/2024
     */

    public ResponseEntity<?> saveProduct(Product product);

=======

    public boolean save(Product product);
>>>>>>> 308af14b9d231cd737fe4c3dd64cb555c9abde74

    public boolean delete(Long ProductID);

    public Iterable<Product> getProducts();

}
