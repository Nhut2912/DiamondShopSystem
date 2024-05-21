package com.example.server.Services;

import com.example.server.Pojo.Product;

import java.util.List;

/*
 * author : TRAN MINH NHUT
 * date : 20/5/2024
 * purpose : MO TA CHUC NANG CO TRONG PRODUCTS
 *
 */


public interface IProductServices {

    public void save(Product product);

    public void delete(Long ProductID);

<<<<<<< HEAD
    public Product update(Long id, Product product);
=======
//<<<<<<< HEAD
//    public void update(Product product);
//
//=======
//    public void  update(Product product);
//>>>>>>> 367fdd224da5a4f45dd0623da5c612480fb252c3
>>>>>>> f5decc3a6366a04ddcdf6c125a5a14f5fae2046b

    public List<Product> getProducts();


}
