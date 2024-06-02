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
    private IProductRepository productRepository;

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

