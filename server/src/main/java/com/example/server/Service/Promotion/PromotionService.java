package com.example.server.Service.Promotion;

import com.example.server.Model.PromotionDTO;
import com.example.server.Pojo.Product;
import com.example.server.Pojo.Promotion;
import com.example.server.Pojo.Promotions_products;
import com.example.server.Repository.IProductRepository;
import com.example.server.Repository.IPromotionRepository;
import com.example.server.Repository.IPromotion_ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class PromotionService implements IPromotionService{

    @Autowired
    private IProductRepository iProductRepository;

    @Autowired
    private IPromotionRepository iPromotionRepository;

    @Autowired
    private IPromotion_ProductRepository iPromotionProductRepository;

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
//                promotion..getProducts().add(products.get(i));
                promotion.getPromotions_products().add(new Promotions_products(products.get(i), promotion));
            }

            iPromotionRepository.save(promotion);

            return true;
        }catch(Exception e){
            System.out.println(e.getMessage());
            return false;
        }
    }

    @Override
    public boolean updatePromotion(PromotionDTO promotionDTO) {
        iPromotionProductRepository.deleteAllByPromotionId(promotionDTO.getIdPromotion());
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
            promotion.setId(promotionDTO.getIdPromotion());
            for(int i = 0; i < products.size(); i++){
                promotion.getPromotions_products().add(new Promotions_products(products.get(i), promotion));
            }
            iPromotionRepository.save(promotion);
            return true;
        }catch(Exception e){
            System.out.println(e.getMessage());
            return false;
        }
    }


    @Override
    public List<PromotionDTO> getPromotions(){
        Iterable<Promotion> promotions = iPromotionRepository.findAll();
        List<PromotionDTO> promotionDTOS = new ArrayList<>();
        promotions.forEach((promotion -> {
            PromotionDTO promotionDTO = new PromotionDTO();
            promotionDTO.setIdPromotion(promotion.getId());
            promotionDTO.setNamePromotion(promotion.getNamePromotion());
            promotionDTO.setPromotionRate(promotion.getPromotionRate());
            promotionDTO.setDateStart(promotion.getDateStart());
            promotionDTO.setDateEnd(promotion.getDateEnd());
            promotionDTO.setActive(promotion.isActive());

            List<Promotions_products> promotions_products = promotion.getPromotions_products();
            List<Long> productsID = new ArrayList<>();
            promotions_products.forEach(promotionsProducts -> {
                productsID.add(promotionsProducts.getProduct().getId());
            });
            promotionDTO.setProductIds(productsID);
            promotionDTOS.add(promotionDTO);
        }));

        return promotionDTOS;
    }
}
