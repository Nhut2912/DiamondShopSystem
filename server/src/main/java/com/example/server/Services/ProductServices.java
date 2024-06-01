package com.example.server.Services;

import com.example.server.Pojo.*;
import com.example.server.Repository.*;
import com.example.server.Requests.ImageDTO;
import com.example.server.Requests.ProductDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Service
public class ProductServices implements IProductServices{

    @Autowired
    private IProductRepository ProductRepository;

    /*
     * Author: Pham Trong Hieu
     * Date: 24/5/2024
     */

    public ResponseEntity<?> saveProduct(Product product) {
        Product productAfterAdd = ProductRepository.save(product);
        return new ResponseEntity<>(productAfterAdd, HttpStatus.OK);
    }


    @Override
    public void delete(Long ProductID) {
        ProductRepository.deleteById(ProductID);
    }


    @Override
    public List<Product> getProducts() {
        return ProductRepository.findAll();
    }
}

