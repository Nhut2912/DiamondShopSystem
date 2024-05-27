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
    public ResponseEntity<Product> save(Product product, Category category, Size size, WarrantyPolicy wp, Warranty warranty, Set<Image> img, Set<Long> materialID, List<Double> weights, Set<Diamond> diamonds, Origin o, Color color, Cut cut, Clarity clarity) {

        product.setProductCategory(category);

        product.setProductSizes(size);

        warranty.setWarrantyPolicy(wp);
        product.setWarranty(warranty);

        product.setImages(img);

        List<Material> materials = MaterialRepository.findAllById(materialID);
        for(int i = 0; i < materials.size(); i++){
            Material m = materials.get(i);
            double weight = weights.get(i);
            product.addProductMaterial(m,weight);
        }

        for(Diamond d: diamonds){
            d.setDiamondColor(color);
            d.setDiamondClarity(clarity);
            d.setDiamondCut(cut);
            d.setDiamondOrigin(o);
            d.setDiamondProduct(product);
        }
        product.setDiamondProducts(diamonds);

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
