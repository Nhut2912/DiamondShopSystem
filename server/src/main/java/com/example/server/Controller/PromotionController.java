package com.example.server.Controller;

import com.example.server.Model.PromotionDTO;
import com.example.server.Pojo.Promotion;
import com.example.server.Service.Promotion.PromotionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/Promotion")
public class PromotionController {

    @Autowired
    private PromotionService promotionService;

    @PostMapping
    public ResponseEntity<PromotionDTO> createPromotion(@RequestBody PromotionDTO promotionDTO){
        Promotion promotion = promotionService.createPromotion(promotionDTO);

        PromotionDTO responeDTO = new PromotionDTO();
        responeDTO.setPromotionRate(promotion.getPromotionRate());
        responeDTO.setActive(promotion.isActive());
        responeDTO.setDateStart(promotion.getDateStart());
        responeDTO.setDateEnd(promotion.getDateEnd());
        responeDTO.setProductIds(promotionDTO.getProductIds());

        return ResponseEntity.ok(responeDTO);
    }
}
