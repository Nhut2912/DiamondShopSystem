package com.example.server.Service.Product;


import com.example.server.Model.ProductDTO;
import com.example.server.Pojo.Product;

import java.util.List;

public interface IProductService {

    public boolean save(Product product) ;

    public List<ProductDTO> getProducts() ;

    public ProductDTO getProduct(Long id);

    public Product getProductToSetStatus(Long id);
}
