package com.example.server.Service.Promotion;

import com.example.server.Model.PromotionDTO;
import com.example.server.Pojo.Product;
import com.example.server.Pojo.Promotion;
import com.example.server.Repository.IProductRepository;
import com.example.server.Repository.IPromotionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class PromotionService {

    @Autowired
    private IProductRepository iProductRepository;

    @Autowired
    private IPromotionRepository iPromotionRepository;


    public Promotion createPromotion(PromotionDTO promotionDTO){
        //kiem tra product co ton tai trong DB
        Set<Product> products = new HashSet<>();
        for (Long productId : promotionDTO.getProductIds()) {
            Optional<Product> productOptional = iProductRepository.findById(productId);
            if (productOptional.isPresent()) {
                products.add(productOptional.get());
            } else {
                throw new RuntimeException("Product with ID " + productId + " does not exist");
            }
        }

        Promotion promotion = new Promotion();
        promotion.setPromotionRate(promotionDTO.getPromotionRate());
        promotion.setActive(promotionDTO.isActive());
        promotion.setDateStart(promotionDTO.getDateStart());
        promotion.setDateEnd(promotionDTO.getDateEnd());
        promotion.setProducts((Set<Product>) products);

        return iPromotionRepository.save(promotion);
    }
}
