package com.example.server.Service.Promotion;

import com.example.server.Model.PromotionDTO;

public interface IPromotionService{
    public boolean createPromotion(PromotionDTO promotionDTO);
    public boolean updatePromotion(PromotionDTO promotionDTO);
}
