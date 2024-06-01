package com.example.server.Controller;

import com.example.server.Pojo.*;
import com.example.server.Services.IProductServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequestMapping(path = "api/product")
//@CrossOrigin(origins = "http://localhost:3000")
public class ProductController {
    @Autowired
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
<<<<<<< HEAD
    public ResponseEntity<Product> saveProduct(@RequestBody Product product){
=======
    public ResponseEntity<?> save(@RequestBody Product product) {

>>>>>>> 7ecfc1bd0c699532b8f9d339266719ce099652fc
        return productServices.saveProduct(product);
    }

    @GetMapping("/getProducts")
    public Iterable<Product> getProducts() {
        return productServices.getProducts();
    }


}
