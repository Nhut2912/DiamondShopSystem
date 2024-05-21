package com.example.server.Services;

import com.example.server.Pojo.Product;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

/*
 * author : TRAN MINH NHUT
 * date : 20/5/2024
 * purpose : MO TA CHUC NANG CO TRONG PRODUCTS
 *
 */


public interface IProductServices {

    public void save(Product product);

    public void delete(Long ProductID);

    public Product update(Long id,Product product);


    public List<Product> getProducts();


}
