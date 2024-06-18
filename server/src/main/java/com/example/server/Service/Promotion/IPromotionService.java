package com.example.server.Service.Promotion;

import com.example.server.Model.PromotionDTO;
import com.example.server.Pojo.Product;
import com.example.server.Pojo.Promotion;

import java.util.List;

public interface IPromotionService{
    public boolean createPromotion(PromotionDTO promotionDTO);
    public boolean updatePromotion(PromotionDTO promotionDTO);
    public List<PromotionDTO> getPromotions();
    public List<Product> getProductsByActivePromotion();
}
