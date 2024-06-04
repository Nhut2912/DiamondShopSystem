package com.example.server.Service;


import com.example.server.Model.ProductDTO;
import com.example.server.Pojo.Product;

import java.util.List;
import java.util.Set;

public interface IProductService {

    public boolean save(Product product) ;

    public List<ProductDTO> getProducts() ;

}
