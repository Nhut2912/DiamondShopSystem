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
<<<<<<< HEAD
    @Autowired
    private IProductRepository ProductRepository;
=======

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

>>>>>>> 66cd59eec7390c32ee0e12456e935defed9dec98

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
<<<<<<< HEAD

    public ResponseEntity<?> saveProduct(Product product) {

        System.out.println(product.getProductMaterialSet().get(0).getMaterials() == null);
        System.out.println(product.getProductMaterialSet().get(0).getProducts() == null);
        System.out.println(product.getProductMaterialSet().get(0).getWeight());
        Product productAfterAdd = ProductRepository.save(product);

        return new ResponseEntity<>(productAfterAdd, HttpStatus.OK);
=======
    @Override
    public Product save(ProductDTO product)  throws Exception{

        Product p = new Product();
        p.setName(product.getName());
        p.setCode(product.getCode());
        p.setProductionCost(product.getProductionCost());
        p.setSecondaryDiamondCost(product.getSecondaryDiamondCost());
        p.setSecondaryMaterialCost(product.getSecondaryMaterialCost());

        //Category, Size, Diamond, Img, Material
        Category category = CategoryRepository.findById(product.getCategoryID())
                .orElseThrow(() -> new IllegalArgumentException("Category not found with id: " + product.getCategoryID()));
        p.setProductCategory(category);

        Size size = SizeRepository.findById(product.getSizeID())
                .orElseThrow(() -> new IllegalArgumentException("Size not found with id: "+ product.getSizeID()));
        p.setProductSizes(size);

        Set<Image> images = new HashSet<>();
        System.out.println("Chuan bi vao");
        System.out.println(product.getImages());
        for(ImageDTO img : product.getImages()){
            System.out.println("Vao vong lap");
            System.out.println("Img uri: "+img.getUri());
            Image image = new Image();
            image.setUri(img.getUri());
            System.out.println("Image uri: "+image.getUri());
            image.setProducts(p);
            System.out.println("Product p: "+ p.getName());
            images.add(image);
        }
        System.out.println("Ra vong lap");
        p.setImages(images);

        return ProductRepository.save(p);
>>>>>>> 66cd59eec7390c32ee0e12456e935defed9dec98
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

