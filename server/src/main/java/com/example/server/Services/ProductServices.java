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

    private final IProductRepository ProductRepository;
    private final ICategoryRepository CategoryRepository;
    private final ISizeRepository SizeRepository;
    private final IWarrantyRepository WarrantyRepository;
    private final IImagesRepository ImagesRepository;
    private final IMaterialRepository MaterialRepository;

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
    public Product save(Product product) {

        System.out.println("Services: "+product.getName());
        Product p = new Product();
        p.setName(product.getName());
        p.setCode(product.getCode());
        p.setProductionCost(product.getProductionCost());
        p.setSecondaryDiamondCost(product.getSecondaryDiamondCost());
        p.setSecondaryMaterialCost(product.getSecondaryMaterialCost());
        p.setProductSizes(product.getProductSizes());
        p.setImages(product.getImages());
        p.setDiamondProducts(product.getDiamondProducts());
        p.setProductCategory(product.getProductCategory());


        return ProductRepository.save(p);
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
