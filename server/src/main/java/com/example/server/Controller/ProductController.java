package com.example.server.Controller;

import com.example.server.Pojo.Product;
import com.example.server.Service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/product")
@CrossOrigin(origins = "http://localhost:3001")
public class ProductController {

    @Autowired
    private IProductService productService;

    @PostMapping("/save")
    public ResponseEntity<Product> saveProduct(@RequestBody Product product){
        productService.save(product);
        return null;
    }

    @GetMapping("/getProducts")
    public List<Product> getProducts() {
        return productService.getProducts();
    }

}
