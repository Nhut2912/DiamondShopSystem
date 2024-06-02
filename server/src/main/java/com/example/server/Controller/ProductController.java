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
<<<<<<< HEAD

    public ResponseEntity<?> save(@RequestBody Product product) {

        return productServices.saveProduct(product);
=======
    public ResponseEntity<Product> saveProduct(@RequestBody Product product){
        System.out.println(product.toString());
        return null;
>>>>>>> 308af14b9d231cd737fe4c3dd64cb555c9abde74
    }

    @GetMapping("/getProducts")
    public Iterable<Product> getProducts() {
        return productServices.getProducts();
    }


}
