package com.example.server.Controller;

import com.example.server.Pojo.*;
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
    public ResponseEntity<Product> save(@RequestBody Product product, @RequestBody Category category, @RequestBody Size size, @RequestBody WarrantyPolicy wp, @RequestBody Warranty warranty, @RequestBody Set<Image> img, @RequestParam Set<Long> materialID, @RequestParam List<Double> weights, @RequestBody Set<Diamond> diamonds, @RequestBody Origin origin, @RequestBody Color color, @RequestBody Cut cut, @RequestBody Clarity clarity) {
        Product addedProduct = productServices.save(product, category, size, wp, warranty, img, materialID, weights, diamonds, origin, color, cut, clarity).getBody();
        return ResponseEntity.status(HttpStatus.CREATED).body(addedProduct);
    }

    @GetMapping("/getProducts")
    public List<Product> getProducts() {
        return productServices.getProducts();
    }


}
