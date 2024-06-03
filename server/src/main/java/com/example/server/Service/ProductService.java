package com.example.server.Service;


import com.example.server.Pojo.*;
import com.example.server.Repository.IProductRepository;
import com.example.server.Service.Category.ICategoryService;
import com.example.server.Service.Clarity.IClarityService;
import com.example.server.Service.Color.IColorService;
import com.example.server.Service.Cut.ICutService;
import com.example.server.Service.Material.IMaterialService;
import com.example.server.Service.Origin.IOriginService;
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

    @Autowired
    private IMaterialService materialService;

    @Autowired
    private ICategoryService categoryService;

    @Autowired
    private IColorService colorService;

    @Autowired
    private ICutService cutService;

    @Autowired
    private IClarityService clarityService;

    @Autowired
    private IOriginService originService;


    @Override
    public boolean save(Product product) {

        Size size = sizeService.getSize(product.getSize().getSize());
        if(size != null)  product.setSize(size);

        product.getProductMaterials().forEach((element) -> {
            Material material =materialService.getMaterial(element.getMaterial().getName());
            if(material != null) element.setMaterial(material);
        });

        Category category = categoryService.getCategory(product.getCategory().getName());
        if(category != null) product.setCategory(category);

        product.getDiamonds().forEach((element) -> {

            Color color = colorService.getColor(element.getColor().getColor());
            if(color != null ) element.setColor(color);

            Clarity clarity =clarityService.getClarity(element.getClarity().getClarity());
            if(clarity != null) element.setClarity(clarity);

            Cut cut = cutService.getCut(element.getCut().getCut());
            if(cut != null) element.setCut(cut);

            Origin origin = originService.getOrigin(element.getOrigin().getOrigin());
            if(origin != null)  element.setOrigin(origin);
        });


        productRepository.save(product);
        return false;
    }

    @Override
    public List<Product> getProducts() {
        return productRepository.findAll();
    }
}
