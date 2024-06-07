package com.example.server.Service.Promotion;

import com.example.server.Model.PromotionDTO;
import com.example.server.Pojo.Product;
import com.example.server.Pojo.Promotion;
import com.example.server.Repository.IProductRepository;
import com.example.server.Repository.IPromotionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
        List<Product> products = promotionDTO.getProductIds().stream().map(iProductRepository::findById).filter(Optional::isPresent).map(Optional::get).collect(Collectors.toList());

        if(products.isEmpty()){
            throw new RuntimeException("No valid products found");
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
