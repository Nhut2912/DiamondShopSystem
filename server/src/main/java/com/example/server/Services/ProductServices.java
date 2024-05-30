package com.example.server.Services;

import com.example.server.Pojo.*;
import com.example.server.Repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;


@Service
public class ProductServices implements IProductServices{

    @Autowired
    private final IProductRepository ProductRepository;
    @Autowired
    private final ICategoryRepository CategoryRepository;
    @Autowired
    private final ISizeRepository SizeRepository;
    private final IWarrantyRepository WarrantyRepository;
    @Autowired
    private final IImagesRepository ImagesRepository;
    private final IMaterialRepository MaterialRepository;

//    @Autowired
    private final IDiamondRepository DiamondRepository;


    @Autowired
    public ProductServices(IProductRepository productRepository, ICategoryRepository categoryRepository, ISizeRepository sizeRepository, IWarrantyRepository warrantyRepository, IImagesRepository imagesRepository, IMaterialRepository materialRepository, IDiamondRepository diamondRepository) {
        this.ProductRepository = productRepository;
        CategoryRepository = categoryRepository;
        SizeRepository = sizeRepository;
        WarrantyRepository = warrantyRepository;
        ImagesRepository = imagesRepository;
        MaterialRepository = materialRepository;
        DiamondRepository = diamondRepository;
    }

    /*
     * Author: Pham Trong Hieu
     * Date: 24/5/2024
     */
    @Override
    public ResponseEntity<Product> saveProduct(Product product){

        return new ResponseEntity<>(ProductRepository.save(product), HttpStatus.CREATED);
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
