package com.example.server.Service;


import com.example.server.Pojo.Product;
import com.example.server.Pojo.Size;
import com.example.server.Repository.IProductRepository;
import com.example.server.Service.Size.ISizeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class ProductService implements IProductService{

    @Autowired
    private IProductRepository productRepository;

    @Autowired
    private ISizeService sizeService;
    @Override
    public boolean save(Product product) {

        Size size = sizeService.getSize(product.getSize().getSize());
        if(size != null)  product.setSize(size);



        productRepository.save(product);
        return false;
    }

    @Override
    public List<Product> getProducts() {
        return productRepository.findAll();
    }
}
