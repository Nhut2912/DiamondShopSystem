package com.example.server.Controller;

import com.example.server.Pojo.Category;
import com.example.server.Pojo.Product;
import com.example.server.Services.IProductServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.Set;

@RestController
@RequestMapping(path = "api/product")
@CrossOrigin(origins = "http://localhost:3000")
public class ProductController {
    private final IProductServices productServices;

    @Autowired
    public ProductController(IProductServices productServices) {
        this.productServices = productServices;
    }

    /*
     * Author: Pham Trong Hieu
     * Date: 24/5/2024
     */
        @PostMapping("/save")
        public ResponseEntity<Product> save(@RequestBody Product product, @RequestParam Long categoryID, @RequestParam Long sizeID, @RequestParam Long warrantyID, @RequestParam Long imageID, @RequestParam Set<Long> materialID, List<Integer> quantities){
            Product addedProduct = productServices.save(product, categoryID, sizeID, warrantyID, imageID, materialID, quantities).getBody();
            return ResponseEntity.status(HttpStatus.CREATED).body(addedProduct);
        }

        @GetMapping("/getProducts")
        public List<Product> getProducts(){
            return productServices.getProducts();
        }




}
