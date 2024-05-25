package com.example.server.Services;

import com.example.server.Pojo.Category;
import com.example.server.Pojo.Product;
import com.example.server.Pojo.Size;
import com.example.server.Pojo.Warranty;
import com.example.server.Repository.ICategoryRepository;
import com.example.server.Repository.IProductRepository;
import com.example.server.Repository.ISizeRepository;
import com.example.server.Repository.IWarrantyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class ProductServices implements IProductServices{

    private final IProductRepository ProductRepository;
    private final ICategoryRepository CategoryRepository;
    private final ISizeRepository SizeRepository;
    private final IWarrantyRepository WarrantyRepository;


    @Autowired
    public ProductServices(IProductRepository productRepository, ICategoryRepository categoryRepository, ISizeRepository sizeRepository, IWarrantyRepository warrantyRepository) {
        this.ProductRepository = productRepository;
        CategoryRepository = categoryRepository;
        SizeRepository = sizeRepository;
        WarrantyRepository = warrantyRepository;
    }

    /*
     * Author: Pham Trong Hieu
     * Date: 24/5/2024
     */
    @Override
    public ResponseEntity<Product> save(Product product, Long categoryID, Long sizeID, Long warrantyID) {

        Category category = CategoryRepository.findById(categoryID)
                .orElseThrow(() -> new RuntimeException("Category not found"));
        product.setProductCategory(category);

        Size size = SizeRepository.findById(sizeID).orElseThrow(() -> new RuntimeException("Size not found"));
        product.setProductSizes(size);

        Warranty warranty = WarrantyRepository.findById(warrantyID).orElseThrow(() -> new RuntimeException("Warranty not found"));
        product.setWarranty(warranty);
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
