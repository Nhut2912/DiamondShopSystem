package com.example.server.Services;

import com.example.server.Pojo.*;
import com.example.server.Repository.*;
import com.example.server.Requests.ImageDTO;
import com.example.server.Requests.ProductDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Service
public class ProductServices implements IProductServices{

    @Autowired
    private final IProductRepository ProductRepository;
    @Autowired
    private final ICategoryRepository CategoryRepository;
    @Autowired
    private final ISizeRepository SizeRepository;
    private final IWarrantyRepository WarrantyRepository;
//    @Autowired
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
    public Product save(ProductDTO product)  throws Exception{

        Product p = new Product();
        p.setName(product.getName());
        p.setCode(product.getCode());
        p.setProductionCost(product.getProductionCost());
        p.setSecondaryDiamondCost(product.getSecondaryDiamondCost());
        p.setSecondaryMaterialCost(product.getSecondaryMaterialCost());

        //Category, Size, Diamond, Img
        Category category = CategoryRepository.findById(product.getCategoryID())
                .orElseThrow(() -> new IllegalArgumentException("Category not found with id: " + product.getCategoryID()));
        p.setProductCategory(category);

        Size size = SizeRepository.findById(product.getSizeID())
                .orElseThrow(() -> new IllegalArgumentException("Size not found with id: "+ product.getSizeID()));
        p.setProductSizes(size);

//        Set<Image> images = new HashSet<>();
//        for(ImageDTO img : product.getImages()){
//            Image image = new Image();
//            image.setUri(img.getUri());
//            image.setProducts(p);
//            images.add(image);
//        }
//        p.setImages(images);

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
