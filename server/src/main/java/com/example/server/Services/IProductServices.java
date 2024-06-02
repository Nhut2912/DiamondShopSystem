package com.example.server.Services;

import com.example.server.Pojo.Product;

public interface IProductServices {


    public boolean save(Product product);


    public boolean delete(Long ProductID);

    public Iterable<Product> getProducts();

}
