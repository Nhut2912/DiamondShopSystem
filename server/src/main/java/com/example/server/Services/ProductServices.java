package com.example.server.Services;

import com.example.server.Pojo.*;
import com.example.server.Repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


@Service
public class ProductServices implements IProductServices{

    @Autowired
<<<<<<< HEAD
    private IProductRepository ProductRepository;


    /*
     * Author: Pham Trong Hieu
     * Date: 24/5/2024
     */

    public ResponseEntity<?> saveProduct(Product product) {
        Product productAfterAdd = ProductRepository.save(product);
        return new ResponseEntity<>(productAfterAdd, HttpStatus.OK);
    }

=======
    private IProductRepository productRepository;
>>>>>>> 308af14b9d231cd737fe4c3dd64cb555c9abde74

    @Override
    public boolean save(Product product) {
        try{
            productRepository.save(product);
            return true;
        }catch (Exception e){
            System.out.println(e.getMessage());
            return false;
        }
    }

    @Override
    public boolean delete(Long ProductID) {
        return false;
    }

    @Override
    public Iterable<Product> getProducts() {
        return null;
    }
}

