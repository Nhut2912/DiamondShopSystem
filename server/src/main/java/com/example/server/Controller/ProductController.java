package com.example.server.Controller;

import com.example.server.Pojo.Product;
import com.example.server.Services.IProductServices;
import org.springframework.beans.factory.annotation.Autowired;
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


        @PostMapping("/save")
        public void save(@RequestBody Product product){
            productServices.save(product);
        }

        @GetMapping("/getProducts")
        public List<Product> getProducts(){
            return productServices.getProducts();
        }


     @DeleteMapping("/{id}")
     public void deleteProduct (@PathVariable Long id){
        productServices.delete(id);
     }

    @PutMapping("/{id}")
    public Product updateProduct(@PathVariable Long id,@RequestBody Product product){
        return productServices.update(id,product);
    }


}
