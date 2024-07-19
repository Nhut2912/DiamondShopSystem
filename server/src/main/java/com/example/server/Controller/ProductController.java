package com.example.server.Controller;


import com.example.server.Config.HostFrontEnd;
import com.example.server.Model.ProductDTO;
import com.example.server.Pojo.Product;
import com.example.server.Repository.IProductRepository;
import com.example.server.Service.Product.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping(path = "api/product")
@CrossOrigin(origins = HostFrontEnd.hostFrontEnd)
public class ProductController {



    @Autowired
    private IProductRepository productRepository;

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

    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductDetails(@PathVariable Long id) {
        Optional<Product> productOptional = productRepository.findById(id);
        if (productOptional.isPresent()) {
            Product product = productOptional.get();
            return ResponseEntity.ok(product);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/similar/{id}")
    public ResponseEntity<List<ProductDTO>> getSimilarProducts(@PathVariable Long id) {
        Optional<Product> productOptional = productRepository.findById(id);
        if (productOptional.isPresent()) {
            Product product = productOptional.get();
            List<ProductDTO> similarProducts = productService.findSimilarProducts(product);
            return ResponseEntity.ok(similarProducts);
        } else {
            return ResponseEntity.notFound().build();
        }
    }


    @GetMapping("/getProducts/roleAdmin")
    public ResponseEntity<List<ProductDTO>> getProductsRoleAdmin() {
        return ResponseEntity.status(200).body(productService.getProductsRoleAdmin());
    }

    @PostMapping("/updateProduct")
    public ResponseEntity<?> updateProduct(@RequestBody Product product){
        return ResponseEntity.ok(productService.updateProduct(product));
    }

    @PostMapping("/deleteProduct/{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable Long id){
        return ResponseEntity.ok((productService.deleteProduct(id)));
    }

    @GetMapping("/newArrival")
    public ResponseEntity<List<Product>> getNewArrival(){
        return ResponseEntity.ok((productService.getProductsNewArrival()));
    }
}
