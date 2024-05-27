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
    public ResponseEntity<Product> save(Product product, Long categoryID, Long sizeID, Long warrantyID, Long imagesID, Set<Long> materialID, List<Integer> quantities, Set<Long> diamondID) {

        Category category = CategoryRepository.findById(categoryID)
                .orElseThrow(() -> new RuntimeException("Category not found"));
        product.setProductCategory(category);

        Size size = SizeRepository.findById(sizeID).orElseThrow(() -> new RuntimeException("Size not found"));
        product.setProductSizes(size);

        Warranty warranty = WarrantyRepository.findById(warrantyID).orElseThrow(() -> new RuntimeException("Warranty not found"));
        product.setWarranty(warranty);

        Image img = ImagesRepository.findById(imagesID).orElseThrow(() -> new RuntimeException("Image not found"));
        product.setImages((Set<Image>) img);

        List<Material> materials = MaterialRepository.findAllById(materialID);
        for(int i = 0; i < materials.size(); i++){
            Material m = materials.get(i);
            int quantity = quantities.get(i);
            product.addProductMaterial(m,quantity);
        }

        List<Diamond> diamonds = DiamondRepository.findAllById(diamondID);
        return new ResponseEntity<>(product, HttpStatus.CREATED);
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
