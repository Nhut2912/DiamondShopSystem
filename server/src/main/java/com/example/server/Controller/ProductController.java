package com.example.server.Controller;

import com.example.server.Pojo.*;
import com.example.server.Requests.ProductDTO;
import com.example.server.Services.IProductServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;

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
    public ResponseEntity<Product> save(@RequestBody ProductDTO product) throws Exception{

        Product addedProduct = productServices.save(product);
        return new ResponseEntity<>(addedProduct,HttpStatus.CREATED);
    }

    @GetMapping("/getProducts")
    public List<Product> getProducts() {
        return productServices.getProducts();
    }


}
