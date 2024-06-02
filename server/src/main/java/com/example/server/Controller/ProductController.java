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
    private IProductServices productServices;

    @PostMapping("/save")

    public ResponseEntity<Product> saveProduct(@RequestBody Product product){
        System.out.println(product.toString());
        return null;
    }

    @GetMapping("/getProducts")
    public Iterable<Product> getProducts() {
        return productServices.getProducts();
    }


}
