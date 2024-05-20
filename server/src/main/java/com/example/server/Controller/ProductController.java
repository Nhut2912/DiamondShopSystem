package com.example.server.Controller;

import com.example.server.Pojo.Product;
import com.example.server.Services.IProductServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequestMapping(path = "api/product")
public class ProductController {
    private final IProductServices productServices;

    @Autowired
    public ProductController(IProductServices productServices) {
        this.productServices = productServices;
    }


        @PostMapping("/save")
        public void save(@RequestBody Product product){
            productServices.save(product);
        }

        @GetMapping("/getProducts")
        public List<Product> getProducts(){
            return productServices.getProducts();
        }
}
