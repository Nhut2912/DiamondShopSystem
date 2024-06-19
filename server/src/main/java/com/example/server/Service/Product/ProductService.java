package com.example.server.Service.Product;


import com.example.server.Model.DiamondDTO;
import com.example.server.Model.MaterialDTO;
import com.example.server.Model.ProductDTO;
import com.example.server.Model.PromotionDTO;
import com.example.server.Pojo.*;
import com.example.server.Repository.IProductRepository;
import com.example.server.Service.Category.ICategoryService;
import com.example.server.Service.Clarity.IClarityService;
import com.example.server.Service.Color.IColorService;
import com.example.server.Service.Cut.ICutService;
import com.example.server.Service.Diamond.IDiamondService;
import com.example.server.Service.DiamondPriceList.IDiamondPriceListService;
import com.example.server.Service.Material.IMaterialService;
import com.example.server.Service.MaterialPriceList.IMaterialPriceListService;
import com.example.server.Service.Origin.IOriginService;
import com.example.server.Service.ProductMaterial.IProductMaterialService;
import com.example.server.Service.Size.ISizeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;


@Service
public class ProductService implements IProductService{

    @Autowired
    private IProductRepository productRepository;

    @Autowired
    private ISizeService sizeService;

    @Autowired
    private IMaterialService materialService;

    @Autowired
    private ICategoryService categoryService;

    @Autowired
    private IColorService colorService;

    @Autowired
    private ICutService cutService;

    @Autowired
    private IClarityService clarityService;

    @Autowired
    private IOriginService originService;

    @Autowired
    private IProductMaterialService productMaterialService;

    @Autowired
    private IDiamondService diamondService;

    @Autowired
    private IDiamondPriceListService iDiamondPriceListService;

    @Autowired
    private IMaterialPriceListService iMaterialPriceListService;

    @Override
    public boolean save(Product product) {
        try{
            System.out.println(product.toString());
            Size size = sizeService.getSize(product.getSize().getSize());
            if(size != null)  product.setSize(size);

            product.getProductMaterials().forEach((element) -> {
                Material material =materialService.getMaterial(element.getMaterial().getName());
                if(material != null) element.setMaterial(material);
            });

            Category category = categoryService.getCategory(product.getCategory().getName());
            if(category != null) product.setCategory(category);

            product.getDiamonds().forEach((element) -> {

                Color color = colorService.getColor(element.getColor().getColor());
                if(color != null ) element.setColor(color);

                Clarity clarity =clarityService.getClarity(element.getClarity().getClarity());
                if(clarity != null) element.setClarity(clarity);

                Cut cut = cutService.getCut(element.getCut().getCut());
                if(cut != null) element.setCut(cut);

                Origin origin = originService.getOrigin(element.getOrigin().getOrigin());
                if(origin != null)  element.setOrigin(origin);
            });

            product.setDateAdd(LocalDateTime.now());

            productRepository.save(product);
            return true;
        }catch (Exception e){
            System.out.println("Error add product : " + e.getMessage());
            return false;
        }
    }

    @Override
    public List<ProductDTO> getProducts() {
        List<ProductDTO> productDTOS = new ArrayList<>();
        List<Product> products = productRepository.getProductsByActive(true);

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

            for (Diamond diamond : listDiamondReturn) {
                DiamondDTO diamondDTO = new DiamondDTO();
                diamondDTO.setId(diamond.getId());
                diamondDTO.setOrigin(diamond.getOrigin().getOrigin());
                diamondDTO.setClarity(diamond.getClarity().getClarity());
                diamondDTO.setCut(diamond.getCut().getCut());
                diamondDTO.setColor(diamond.getColor().getColor());
                diamondDTO.setCarat(diamond.getCarat());
                diamondDTOS.add(diamondDTO);

                DiamondPriceList diamondPriceList = null;
                try {
                    diamondPriceList = iDiamondPriceListService.getDiamondPriceListBy4C(diamond.getCarat(),
                            diamond.getClarity().getId(), diamond.getColor().getId()
                            , diamond.getCut().getId(), diamond.getOrigin().getId());
                    totalPrice += diamondPriceList.getPrice() * diamond.getCarat() ;
                } catch (ClassNotFoundException e) {
                    System.out.println(e.getMessage());
                }

            }

            productDTO.setDiamonds(diamondDTOS);

            Set<MaterialDTO> materialDTOS = new HashSet<>();

            for (ProductMaterial productMaterial : listProductMaterial){

                MaterialDTO materialDTO = new MaterialDTO();
                materialDTO.setName(productMaterial.getMaterial().getName());
                materialDTO.setWeight(productMaterial.getWeight());
                materialDTOS.add(materialDTO);

                MaterialPriceList materialPriceList = iMaterialPriceListService.getMaterialPriceListById(productMaterial.getMaterial().getId());
                totalPrice += materialPriceList.getSellPrice();
            }
            totalPrice += item.getProductionCost() + item.getSecondaryDiamondCost() + item.getSecondaryMaterialCost();
            totalPrice += totalPrice*((double) item.getPriceRate() / 100);

            productDTO.setMaterials(materialDTOS);
            productDTO.setPrice(totalPrice);


            List<PromotionDTO> promotionDTOS = new ArrayList<>();
            for(Promotions_products promotions_products : item.getPromotions_products()) {
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
            productDTO.setPromotions(promotionDTOS);


            Set<String> images = new HashSet<>();
            item.getImages().forEach((image -> images.add(image.getUrl())));
            productDTO.setImages(images);


            productDTOS.add(productDTO);
        });
        return productDTOS;

    }




    @Override
    public ProductDTO getProduct(Long id) {
        Optional<Product> product = productRepository.findById(id);
        ProductDTO productDTO = new ProductDTO();
        if(product.isPresent()){
              productDTO.setId(product.get().getId());
              productDTO.setName(product.get().getName());
              productDTO.setCode(product.get().getCode());
              productDTO.setSize(product.get().getSize().getSize());

              productDTO.setSizeUnitPrice(product.get().getSizeUnitPrice());



            double totalPrice = 0;


            List<Diamond> listDiamondReturn = diamondService.getDiamondByProductID(product.get().getId());
            List<ProductMaterial> listProductMaterial = productMaterialService.getProductMaterials(product.get().getId());



            for (Diamond diamond : listDiamondReturn) {


                System.out.println(diamond.getCarat());
                DiamondPriceList diamondPriceList = null;
                try {
                    diamondPriceList = iDiamondPriceListService.getDiamondPriceListBy4C(diamond.getCarat(),
                            diamond.getClarity().getId(), diamond.getColor().getId()
                            , diamond.getCut().getId(), diamond.getOrigin().getId());
                    totalPrice += diamondPriceList.getPrice() * diamond.getCarat() ;
                } catch (ClassNotFoundException e) {
                    System.out.println(e.getMessage());
                }



            }




            for (ProductMaterial productMaterial : listProductMaterial){
                MaterialPriceList materialPriceList = iMaterialPriceListService.getMaterialPriceListById(productMaterial.getMaterial().getId());
                totalPrice += materialPriceList.getSellPrice();
            }
            totalPrice += product.get().getProductionCost() + product.get().getSecondaryDiamondCost() + product.get().getSecondaryMaterialCost();
            totalPrice += totalPrice*((double) product.get().getPriceRate() / 100);

            productDTO.setPrice(totalPrice);


            List<PromotionDTO> promotionDTOS = new ArrayList<>();
            for(Promotions_products promotions_products : product.get().getPromotions_products()){
                if(promotions_products.getPromotion().isActive()){
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

            productDTO.setPromotions(promotionDTOS);



            Set<String> images = new HashSet<>();
              product.get().getImages().forEach((image -> images.add(image.getUrl())));
              productDTO.setImages(images);
              productDTO.setCategory(product.get().getCategory().getName());


              List<ProductMaterial> productMaterials = productMaterialService.getProductMaterials(id);
              Set<MaterialDTO> materialDTOS = new HashSet<>();
              productMaterials.forEach((item) -> {
                  MaterialDTO materialDTO = new MaterialDTO();
                  materialDTO.setWeight(item.getWeight());
                  materialDTO.setName(item.getMaterial().getName());
                  materialDTOS.add(materialDTO);
              });
              productDTO.setMaterials(materialDTOS);

              List<Diamond> diamonds = diamondService.getDiamondByProductID(id);
              List<DiamondDTO> diamondDTOS = new ArrayList<>();
              diamonds.forEach((item) -> {
                  DiamondDTO diamondDTO = new DiamondDTO();
                  diamondDTO.setCarat(item.getCarat());
                  diamondDTO.setId(item.getId());
                  diamondDTOS.add(diamondDTO);
              });
              productDTO.setDiamonds(diamondDTOS);

              return productDTO;
        }else return null;
    }

    public Product getProductById(Long id){
        Optional<Product> product = productRepository.findById(id);
        return product.orElse(null);
    }

    @Override
    public List<Product> getProductsNewArrival() {
        return null;
    }

    @Override
    public Product getProductToSetStatus(Long id) {
        Optional<Product> product = productRepository.findById(id);
        if(product.isPresent()){
            product.get().setActive(false);
            try{
                return productRepository.save(product.get());
            }catch (Exception ex){
                System.out.println(ex.getMessage());
            }
        }
        return null;
    }





    @Override
    public List<ProductDTO> findSimilarProducts(Product product) {
        List<Product> products = productRepository.findAllByProductMaterialsOrCategory(product.getProductMaterials(), product.getCategory());
        List<ProductMaterial> listProductMaterial = productMaterialService.getProductMaterials(product.getId());
        List<Diamond> listDiamondReturn = diamondService.getDiamondByProductID(product.getId());
        System.out.println(products.size());
        System.out.println(product.getId());
        System.out.println(listProductMaterial.size());
        System.out.println(listDiamondReturn.size());

        double totalPrice = 0;


        for (Diamond diamond : listDiamondReturn) {

            DiamondPriceList diamondPriceList = null;
            try {
                diamondPriceList = iDiamondPriceListService.getDiamondPriceListBy4C(diamond.getCarat(),
                        diamond.getClarity().getId(), diamond.getColor().getId()
                        , diamond.getCut().getId(), diamond.getOrigin().getId());
                totalPrice += diamondPriceList.getPrice() * diamond.getCarat() ;
            } catch (ClassNotFoundException e) {
                System.out.println(e.getMessage());
            }

        }


        for (ProductMaterial productMaterial : listProductMaterial) {
            MaterialPriceList materialPriceList = iMaterialPriceListService.getMaterialPriceListById(productMaterial.getMaterial().getId());
            totalPrice += materialPriceList.getSellPrice();
        }
        totalPrice += product.getProductionCost() + product.getSecondaryDiamondCost() + product.getSecondaryMaterialCost();
        totalPrice += totalPrice * ((double) product.getPriceRate() / 100);

        System.out.println(totalPrice);
        List<ProductDTO> result;
        List<ProductDTO> productDTOS = getProducts();

        double finalTotalPrice = totalPrice;
        result = productDTOS.stream()
                .filter(dto -> (dto.getPrice() >= finalTotalPrice - 1000 && dto.getPrice() <= finalTotalPrice + 1000 && !dto.getId().equals(product.getId())) || products.stream().anyMatch(p -> (p.getId().equals(dto.getId())) && !p.getId().equals(product.getId())))
                .collect(Collectors.toList());

        for(ProductDTO productDTO : result){
            productDTO.setCategory(product.getCategory().getName());
            List<ProductMaterial> productMaterials = productMaterialService.getProductMaterials(productDTO.getId());
            Set<MaterialDTO> materialDTOS = new HashSet<>();
            productMaterials.forEach((item) -> {
                MaterialDTO materialDTO = new MaterialDTO();
                materialDTO.setId(item.getId());
                materialDTO.setWeight(item.getWeight());
                materialDTO.setName(item.getMaterial().getName());
                materialDTOS.add(materialDTO);
            });
            productDTO.setMaterials(materialDTOS);

            List<Diamond> diamonds = diamondService.getDiamondByProductID(product.getId());
            List<DiamondDTO> diamondDTOS = new ArrayList<>();
            diamonds.forEach((item) -> {
                DiamondDTO diamondDTO = new DiamondDTO();
                diamondDTO.setId(item.getId());
                diamondDTO.setCarat(item.getCarat());
                diamondDTO.setCut(item.getCut().getCut());
                diamondDTO.setOrigin(item.getOrigin().getOrigin());
                diamondDTO.setColor(item.getColor().getColor());
                diamondDTO.setClarity(item.getClarity().getClarity());
                diamondDTOS.add(diamondDTO);
            });
            productDTO.setDiamonds(diamondDTOS);
        }

        return result;
    }

    @Override
    public List<ProductDTO> getProductsRoleAdmin() {
        List<ProductDTO> productDTOS = new ArrayList<>();
        List<Product> products = productRepository.findAll();

        products.forEach((item) -> {
            ProductDTO productDTO = new ProductDTO();

            productDTO.setId(item.getId());

            productDTO.setName(item.getName());

            productDTO.setCode(item.getCode());

            productDTO.setSizeUnitPrice(item.getSizeUnitPrice());

            productDTO.setSize(item.getSize().getSize());

            productDTO.setCategory(item.getCategory().getName());

            productDTO.setActive(item.isActive());

            double totalPrice = 0;


            List<Diamond> listDiamondReturn = diamondService.getDiamondByProductID(item.getId());
            List<ProductMaterial> listProductMaterial = productMaterialService.getProductMaterials(item.getId());

            System.out.println(listDiamondReturn);


            List<DiamondDTO> diamondDTOS = new ArrayList<>();

            for (Diamond diamond : listDiamondReturn) {


                DiamondDTO diamondDTO = new DiamondDTO();
                diamondDTO.setId(diamond.getId());
                diamondDTO.setOrigin(diamond.getOrigin().getOrigin());
                diamondDTO.setClarity(diamond.getClarity().getClarity());
                diamondDTO.setCut(diamond.getCut().getCut());
                diamondDTO.setColor(diamond.getColor().getColor());
                diamondDTO.setCarat(diamond.getCarat());
                diamondDTOS.add(diamondDTO);

                DiamondPriceList diamondPriceList = null;
                try {
                    diamondPriceList = iDiamondPriceListService.getDiamondPriceListBy4C(diamond.getCarat(),
                            diamond.getClarity().getId(), diamond.getColor().getId()
                            , diamond.getCut().getId(), diamond.getOrigin().getId());
                    totalPrice += diamondPriceList.getPrice() * diamond.getCarat() ;
                } catch (ClassNotFoundException e) {
                    System.out.println(e.getMessage());
                }

            }

            productDTO.setDiamonds(diamondDTOS);

            Set<MaterialDTO> materialDTOS = new HashSet<>();

            for (ProductMaterial productMaterial : listProductMaterial){

                MaterialDTO materialDTO = new MaterialDTO();
                materialDTO.setName(productMaterial.getMaterial().getName());
                materialDTO.setWeight(productMaterial.getWeight());
                materialDTOS.add(materialDTO);

                MaterialPriceList materialPriceList = iMaterialPriceListService.getMaterialPriceListById(productMaterial.getMaterial().getId());
                totalPrice += materialPriceList.getSellPrice();
            }
            totalPrice += item.getProductionCost() + item.getSecondaryDiamondCost() + item.getSecondaryMaterialCost();
            totalPrice += totalPrice*((double) item.getPriceRate() / 100);

            productDTO.setMaterials(materialDTOS);
            productDTO.setPrice(totalPrice);


            List<PromotionDTO> promotionDTOS = new ArrayList<>();
            for(Promotions_products promotions_products : item.getPromotions_products()) {
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
            productDTO.setPromotions(promotionDTOS);


            Set<String> images = new HashSet<>();
            item.getImages().forEach((image -> images.add(image.getUrl())));
            productDTO.setImages(images);


            productDTOS.add(productDTO);
        });
        return productDTOS;
    }


}
