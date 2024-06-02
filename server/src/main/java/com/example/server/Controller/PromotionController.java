package com.example.server.Controller;

import com.example.server.Pojo.Promotion;
import com.example.server.Services.IPromotionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "api/promotion")
public class PromotionController {

    @Autowired
    private IPromotionService promotionService;

    public ResponseEntity<?> save(Promotion promotion){
        return new ResponseEntity<>(promotionService.savePromotion(promotion), HttpStatus.CREATED);
    }
}
