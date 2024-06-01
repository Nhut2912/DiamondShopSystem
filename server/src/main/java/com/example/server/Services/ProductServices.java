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
    private final IProductRepository ProductRepository;
    @Autowired
    private final ICategoryRepository CategoryRepository;
//    @Autowired
//    private final ISizeRepository SizeRepository;
//    private final IWarrantyRepository WarrantyRepository;
//    @Autowired
//    private final IImagesRepository ImagesRepository;
//    private final IMaterialRepository MaterialRepository;
//
////    @Autowired
//    private final IDiamondRepository DiamondRepository;


    @Autowired
    public ProductServices(IProductRepository productRepository, ICategoryRepository categoryRepository) {
        this.ProductRepository = productRepository;
        CategoryRepository = categoryRepository;
    }
=======
    private IProductRepository ProductRepository;
>>>>>>> 7ecfc1bd0c699532b8f9d339266719ce099652fc

    /*
     * Author: Pham Trong Hieu
     * Date: 24/5/2024
     */
<<<<<<< HEAD
    @Override
    public ResponseEntity<Product> saveProduct(Product product){
        return new ResponseEntity<>(ProductRepository.save(product), HttpStatus.CREATED);
=======

    public ResponseEntity<?> saveProduct(Product product) {
        Product productAfterAdd = ProductRepository.save(product);
        return new ResponseEntity<>(productAfterAdd, HttpStatus.OK);
>>>>>>> 7ecfc1bd0c699532b8f9d339266719ce099652fc
    }


    @Override
    public void delete(Long ProductID) {
        ProductRepository.deleteById(ProductID);
    }


    @Override
    public Iterable<Product> getProducts() {
        return ProductRepository.findAll();
    }
}

