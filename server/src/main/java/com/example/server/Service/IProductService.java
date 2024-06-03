package com.example.server.Service;

import com.example.server.Pojo.Product;

import java.util.List;

public interface IProductService {

    public boolean save(Product product) ;

    public List<Product> getProducts() ;
}
