package com.example.server.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PromotionService implements IPromotionService{

    @Autowired
    private IPromotionService promotionService;
}
