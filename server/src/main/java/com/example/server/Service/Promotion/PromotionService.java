package com.example.server.Service.Promotion;

import com.example.server.Model.*;
import com.example.server.Pojo.*;
import com.example.server.Repository.IProductRepository;
import com.example.server.Repository.IPromotionRepository;
import com.example.server.Repository.IPromotion_ProductRepository;
import com.example.server.Service.Diamond.IDiamondService;
import com.example.server.Service.DiamondPriceList.IDiamondPriceListService;
import com.example.server.Service.MaterialPriceList.IMaterialPriceListService;
import com.example.server.Service.ProductMaterial.IProductMaterialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class PromotionService implements IPromotionService {

    @Autowired
    private IProductRepository iProductRepository;

    @Autowired
    private IPromotionRepository iPromotionRepository;

    @Autowired
    private IPromotion_ProductRepository iPromotionProductRepository;

    @Autowired
    private IDiamondService diamondService;

    @Autowired
    private IProductMaterialService productMaterialService;

    @Autowired
    private IDiamondPriceListService iDiamondPriceListService;

    @Autowired
    private IMaterialPriceListService iMaterialPriceListService;

    public boolean createPromotion(PromotionDTO promotionDTO) {
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

        try {
            Promotion promotion = new Promotion();
            promotion.setPromotionRate(promotionDTO.getPromotionRate());
            promotion.setActive(promotionDTO.isActive());
            promotion.setDateStart(promotionDTO.getDateStart());
            promotion.setDateEnd(promotionDTO.getDateEnd());
            promotion.setNamePromotion(promotionDTO.getNamePromotion());

            for (int i = 0; i < products.size(); i++) {
//                promotion..getProducts().add(products.get(i));
                promotion.getPromotions_products().add(new Promotions_products(products.get(i), promotion));
            }

            iPromotionRepository.save(promotion);

            return true;
        } catch (Exception e) {
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

        try {
            Promotion promotion = new Promotion();
            promotion.setPromotionRate(promotionDTO.getPromotionRate());
            promotion.setActive(promotionDTO.isActive());
            promotion.setDateStart(promotionDTO.getDateStart());
            promotion.setDateEnd(promotionDTO.getDateEnd());
            promotion.setNamePromotion(promotionDTO.getNamePromotion());
            promotion.setId(promotionDTO.getIdPromotion());
            for (int i = 0; i < products.size(); i++) {
                promotion.getPromotions_products().add(new Promotions_products(products.get(i), promotion));
            }
            iPromotionRepository.save(promotion);
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }


    @Override
    public List<PromotionDTO> getPromotions() {
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

    public List<ProductDTO> getProductsByActivePromotion() {
        List<ProductDTO> productDTOS = new ArrayList<>();
        List<Product> products = iProductRepository.getProductsByActive(true);

        products.forEach((item) -> {
            ProductDTO productDTO = new ProductDTO();

            productDTO.setId(item.getId());

            productDTO.setName(item.getName());

            productDTO.setCode(item.getCode());

            productDTO.setSizeUnitPrice(item.getSizeUnitPrice());

            productDTO.setSize(item.getSize().getSize());

            productDTO.setCategory(item.getCategory().getName());


            double totalPrice = 0;


            List<Diamond> listDiamondReturn = diamondService.getDiamondByProductID(item.getId());
            List<ProductMaterial> listProductMaterial = productMaterialService.getProductMaterials(item.getId());

            System.out.println(listDiamondReturn);


            List<DiamondDTO> diamondDTOS = new ArrayList<>();
            int countTwoComponentToEstablishPriceOfProduct = 0;
            for (Diamond diamond : listDiamondReturn) {
                DiamondDTO diamondDTO = new DiamondDTO();
                diamondDTO.setId(diamond.getId());
                diamondDTO.setOrigin(diamond.getOrigin().getOrigin());
                diamondDTO.setClarity(diamond.getClarity().getClarity());
                diamondDTO.setCut(diamond.getCut().getCut());
                diamondDTO.setColor(diamond.getColor().getColor());
                diamondDTO.setCarat(diamond.getCarat());
                diamondDTOS.add(diamondDTO);


                try {
                    Optional<DiamondPriceList> diamondPriceList = Optional.ofNullable(iDiamondPriceListService.getDiamondPriceListBy4C(diamond.getCarat(),
                            diamond.getClarity().getId(), diamond.getColor().getId()
                            , diamond.getCut().getId(), diamond.getOrigin().getId()));
                    if(diamondPriceList.isPresent()) {
                        totalPrice += diamondPriceList.get().getPrice() * diamond.getCarat() ;
                    }else countTwoComponentToEstablishPriceOfProduct++;
                } catch (ClassNotFoundException e) {
                    System.out.println(e.getMessage());
                }

            }

            productDTO.setDiamonds(diamondDTOS);

            Set<MaterialDTO> materialDTOS = new HashSet<>();

            for (ProductMaterial productMaterial : listProductMaterial) {

                MaterialDTO materialDTO = new MaterialDTO();
                materialDTO.setName(productMaterial.getMaterial().getName());
                materialDTO.setWeight(productMaterial.getWeight());
                materialDTOS.add(materialDTO);


                MaterialPriceListDTO materialPriceListDTO = iMaterialPriceListService.getMaterialPriceListById(productMaterial.getMaterial().getId());
                if(materialPriceListDTO != null) {totalPrice += materialPriceListDTO.getSellPrice();}
                else countTwoComponentToEstablishPriceOfProduct++;
            }
            if(countTwoComponentToEstablishPriceOfProduct != 0){
                totalPrice = -1;
            }else {
                totalPrice += item.getProductionCost() + item.getSecondaryDiamondCost() + item.getSecondaryMaterialCost();
                totalPrice += totalPrice*((double) item.getPriceRate() / 100);
            }

            productDTO.setMaterials(materialDTOS);
            productDTO.setPrice(totalPrice);



            Set<String> images = new HashSet<>();
            item.getImages().forEach((image -> images.add(image.getUrl())));
            productDTO.setImages(images);



            List<PromotionDTO> promotionDTOS = new ArrayList<>();
            for (Promotions_products promotions_products : item.getPromotions_products()) {
                if (promotions_products.getPromotion().isActive()) {
                    PromotionDTO promotionDTO = new PromotionDTO();
                    promotionDTO.setNamePromotion(promotions_products.getPromotion().getNamePromotion());
                    promotionDTO.setPromotionRate(promotions_products.getPromotion().getPromotionRate());
                    promotionDTO.setIdPromotion(promotions_products.getPromotion().getId());
                    promotionDTO.setActive(promotions_products.getPromotion().isActive());
                    promotionDTO.setDateStart(promotions_products.getPromotion().getDateStart());
                    promotionDTO.setDateEnd(promotions_products.getPromotion().getDateEnd());
                    promotionDTOS.add(promotionDTO);
                }
            }

            if( promotionDTOS.size() > 0){
                productDTO.setPromotions(promotionDTOS);
                productDTOS.add(productDTO);
            }
        });
        return productDTOS;
    }
}
