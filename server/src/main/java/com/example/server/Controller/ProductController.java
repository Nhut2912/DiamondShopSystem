package com.example.server.Controller;


import com.example.server.Model.ProductDTO;
import com.example.server.Pojo.Product;
import com.example.server.Service.Product.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping(path = "api/product")
@CrossOrigin(origins = "http://localhost:3000")
public class ProductController {

    @Autowired
    private IProductService productService;

    @PostMapping("/save")
    public ResponseEntity<Product> saveProduct(@RequestBody Product product){
        productService.save(product);
        return null;
    }

    @GetMapping("/getProducts")
    public ResponseEntity<List<ProductDTO>> getProducts() {
        return ResponseEntity.status(200).body(productService.getProducts());
    }

    @GetMapping("/getProduct/{id}")
    public ResponseEntity<ProductDTO> getProduct( @PathVariable Long id){
       return ResponseEntity.status(200).body(productService.getProduct(id));
    }
}
