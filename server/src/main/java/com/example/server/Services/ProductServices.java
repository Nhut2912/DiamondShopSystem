package com.example.server.Services;

import com.example.server.Pojo.*;
import com.example.server.Repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;


@Service
public class ProductServices implements IProductServices{
    @Autowired
    private IProductRepository ProductRepository;

    @Autowired
    private ICategoryRepository categoryRepository;

    @Autowired
    private IMaterialRepository materialRepository;

    @Autowired
    private ISizeRepository sizeRepository;

    @Autowired
    private IImagesRepository imageRepository;

    /*
     * Author: Pham Trong Hieu
     * Date: 24/5/2024
     */

    public ResponseEntity<?> saveProduct(Product product) {

        System.out.println(product.getProductMaterialSet().get(0).getMaterials() == null);
        System.out.println(product.getProductMaterialSet().get(0).getProducts() == null);
        System.out.println(product.getProductMaterialSet().get(0).getWeight());
        Product productAfterAdd = ProductRepository.save(product);

        return new ResponseEntity<>(productAfterAdd, HttpStatus.OK);
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

