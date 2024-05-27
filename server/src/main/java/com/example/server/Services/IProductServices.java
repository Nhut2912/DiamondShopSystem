package com.example.server.Services;

import com.example.server.Pojo.*;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Set;

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
    public ResponseEntity<Product> save(Product product, Category category, Size size, WarrantyPolicy wp, Warranty warranty, Set<Image> img, Set<Long> materialID, List<Double> weight, Set<Diamond> diamonds, Origin o, Color color, Cut cut, Clarity clarity);

    public void delete(Long ProductID);

    public List<Product> getProducts();


}
