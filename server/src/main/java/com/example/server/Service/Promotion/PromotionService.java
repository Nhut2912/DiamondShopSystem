package com.example.server.Service.Promotion;

import com.example.server.Model.PromotionDTO;
import com.example.server.Pojo.Product;
import com.example.server.Pojo.Promotion;
import com.example.server.Repository.IProductRepository;
import com.example.server.Repository.IPromotionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class PromotionService {

    @Autowired
    private IProductRepository iProductRepository;

    @Autowired
    private IPromotionRepository iPromotionRepository;


    public boolean createPromotion(PromotionDTO promotionDTO){
        //kiem tra product co ton tai trong DB
        List<Product> products = new ArrayList<>();
        for (Long productId : promotionDTO.getProductIds()) {
            Optional<Product> productOptional = iProductRepository.findById(productId);
            if (productOptional.isPresent()) {
                products.add(productOptional.get());
            } else {
                System.out.println("Product with ID " + productId + " does not exist");
                return false;
            }
        }


        try{
            Promotion promotion = new Promotion();
            promotion.setPromotionRate(promotionDTO.getPromotionRate());
            promotion.setActive(promotionDTO.isActive());
            promotion.setDateStart(promotionDTO.getDateStart());
            promotion.setDateEnd(promotionDTO.getDateEnd());
            promotion.setNamePromotion(promotionDTO.getNamePromotion());
            for(int i = 0; i < products.size(); i++){
                promotion.getProducts().add(products.get(i));
            }
            iPromotionRepository.save(promotion);
            return true;
        }catch(Exception e){
            System.out.println(e.getMessage());
            return false;
        }
    }
}
