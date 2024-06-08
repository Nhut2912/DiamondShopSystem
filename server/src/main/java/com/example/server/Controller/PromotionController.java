package com.example.server.Controller;

import com.example.server.Model.PromotionDTO;
import com.example.server.Pojo.Promotion;
import com.example.server.Service.Promotion.PromotionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/Promotion")
@CrossOrigin(origins = "http://localhost:3000")
public class PromotionController {

    @Autowired
    private PromotionService promotionService;

    @PostMapping("/CreatePromotion")
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
