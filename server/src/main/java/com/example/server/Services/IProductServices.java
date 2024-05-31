package com.example.server.Services;

import com.example.server.Pojo.*;
import com.example.server.Requests.ProductDTO;

import java.util.List;

/*
 * author : TRAN MINH NHUT
 * date : 20/5/2024
 * purpose : MO TA CHUC NANG CO TRONG PRODUCTS
 *
 */


public interface IProductServices {
    /*
     * Author: Pham Trong Hieu
     * Date: 24/5/2024
     */
<<<<<<< HEAD
    public ResponseEntity<?> saveProduct(Product product);
=======
    public Product save(ProductDTO product) throws Exception;

>>>>>>> 66cd59eec7390c32ee0e12456e935defed9dec98
    public void delete(Long ProductID);

    public List<Product> getProducts();


}
