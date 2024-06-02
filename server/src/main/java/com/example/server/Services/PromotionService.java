package com.example.server.Services;

import com.example.server.Pojo.Promotion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PromotionService implements IPromotionService{

    @Autowired
    private IPromotionService promotionService;

    @Override
    public boolean savePromotion(Promotion promotion) {
        try{
            promotionService.savePromotion(promotion);
            return true;
        }catch (Exception e){
            System.out.println(e.getMessage());
            return false;
        }
    }
}
