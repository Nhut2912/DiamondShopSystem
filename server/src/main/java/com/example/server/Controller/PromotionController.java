package com.example.server.Controller;

import com.example.server.Config.HostFrontEnd;
import com.example.server.Model.PromotionDTO;
import com.example.server.Service.Promotion.IPromotionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/Promotion")
@CrossOrigin(origins = HostFrontEnd.hostFrontEnd)
public class PromotionController {

    @Autowired
    private IPromotionService iPromotionService;

    @PostMapping("create")
    public ResponseEntity<?> createPromotion(@RequestBody PromotionDTO promotionDTO){

        return ResponseEntity.ok(iPromotionService.createPromotion(promotionDTO));
    }
    @PostMapping("update")
    public ResponseEntity<?> updatePromotion(@RequestBody PromotionDTO promotionDTO){

        return ResponseEntity.ok(iPromotionService.updatePromotion(promotionDTO));
    }

    @GetMapping("getPromotions")
    public ResponseEntity<?> getPromotions(){
        return ResponseEntity.ok(iPromotionService.getPromotions());
    }

    @GetMapping("/getProductsByActivePromotion")
    public ResponseEntity<?> getProductsByActivePromotion() {
        return ResponseEntity.ok(iPromotionService.getProductsByActivePromotion());
    }
}
