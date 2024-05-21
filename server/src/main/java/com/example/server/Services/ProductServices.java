package com.example.server.Services;

import com.example.server.Pojo.Product;
import com.example.server.Repository.IProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class ProductServices implements IProductServices{

    private final IProductRepository ProductRepository;

    @Autowired
    public ProductServices(IProductRepository productRepository) {
        this.ProductRepository = productRepository;
    }


    @Override
    public void save(Product product) {
        ProductRepository.save(product);
    }

    @Override
    public void delete(Long ProductID) {
        ProductRepository.deleteById(ProductID);
    }

//    @Override
//    public void update(Product product) {
//
//    }

    @Override
    public List<Product> getProducts() {
        return ProductRepository.findAll();
    }
}
