package com.example.server.Service.Product;


import com.example.server.Model.*;
import com.example.server.Pojo.*;
import com.example.server.Repository.*;
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
import com.example.server.Service.Promotion.IPromotionService;
import com.example.server.Service.Size.ISizeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;


@Service
@Transactional
public class ProductService implements IProductService{

    @Autowired
    private IProductRepository productRepository;

    @Autowired
    private ICategoryRepository categoryRepository;

    @Autowired
    private ISizeRepository sizeRepository;

    @Autowired
    private IImageRepository imageRepository;

    @Autowired
    IClarityService clarityService;

    @Autowired
    IColorService colorService;

    @Autowired
    ICutService cutService;

    @Autowired
    IOriginService originService;

    @Autowired
    IDiamondRepository diamondRepository;

    @Autowired
    private ISizeService sizeService;

    @Autowired
    private IMaterialService materialService;

    @Autowired
    private ICategoryService categoryService;

    @Autowired
    private IProductMaterialService productMaterialService;

    @Autowired
    private IDiamondService diamondService;

    @Autowired
    private IDiamondPriceListService iDiamondPriceListService;

    @Autowired
    private IMaterialPriceListService iMaterialPriceListService;

    @Autowired
    private IPromotionRepository promotionRepository;

    @Autowired
    private IProductMaterialRepository productMaterialRepository;


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
            int countTwoComponentToEstablishPriceOfProduct = 0;
            for (Diamond diamond : listDiamondReturn) {
                DiamondDTO diamondDTO = new DiamondDTO();
                diamondDTO.setId(diamond.getId());
                diamondDTO.setOrigin(diamond.getOrigin().getOrigin());
                diamondDTO.setClarity(diamond.getClarity().getClarity());
                diamondDTO.setCut(diamond.getCut().getCut());
                diamondDTO.setColor(diamond.getColor().getColor());
                diamondDTO.setCarat(diamond.getCarat());
                diamondDTO.setImage(diamond.getCertificate());
                diamondDTOS.add(diamondDTO);



                try {
                    Optional<DiamondPriceList> diamondPriceList = Optional.ofNullable(iDiamondPriceListService.getDiamondPriceListBy4C(diamond.getCarat(),
                            diamond.getClarity().getId(), diamond.getColor().getId()
                            , diamond.getCut().getId(), diamond.getOrigin().getId()));
                    if(diamondPriceList.isPresent()) {
                        totalPrice += diamondPriceList.get().getPrice() * diamond.getCarat() * 100 ;
                    }
                } catch (ClassNotFoundException e) {
                    System.out.println(e.getMessage());
                    countTwoComponentToEstablishPriceOfProduct++;
                }

            }

            productDTO.setDiamonds(diamondDTOS);

            Set<MaterialDTO> materialDTOS = new HashSet<>();

            for (ProductMaterial productMaterial : listProductMaterial){

                MaterialDTO materialDTO = new MaterialDTO();
                materialDTO.setName(productMaterial.getMaterial().getName());
                materialDTO.setWeight(productMaterial.getWeight());
                materialDTOS.add(materialDTO);

                MaterialPriceListDTO materialPriceListDTO = iMaterialPriceListService.getMaterialPriceListById(productMaterial.getMaterial().getId());

                if(materialPriceListDTO != null && countTwoComponentToEstablishPriceOfProduct == 0) {totalPrice += materialPriceListDTO.getSellPrice();}

            }
            if(countTwoComponentToEstablishPriceOfProduct != 0){
                totalPrice = -1;
            }else {
                totalPrice += item.getProductionCost() + item.getSecondaryDiamondCost() + item.getSecondaryMaterialCost();
                totalPrice += totalPrice*((double) item.getPriceRate() / 100);
            }

            productDTO.setMaterials(materialDTOS);
            productDTO.setPrice(totalPrice);
            System.out.println("Total price without diamond or material: " + totalPrice);

            List<PromotionDTO> promotionDTOS = new ArrayList<>();
            for(Promotions_products promotions_products : item.getPromotions_products()) {
                java.util.Date now = new java.util.Date();
                if (promotions_products.getPromotion().isActive() && promotions_products.getPromotion().getDateStart().getTime() <= now.getTime() && promotions_products.getPromotion().getDateEnd().getTime() >= now.getTime()) {
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
            Set<Image> imagesSet = imageRepository.getImagesByProduct_Id(item.getId());
            Set<String> images = new HashSet<>();
            imagesSet.forEach(image -> {
                images.add(image.getUrl());
            });
            System.out.println(item.getImages().size());
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


            int countTwoComponentToEstablishPriceOfProduct = 0;
            for (Diamond diamond : listDiamondReturn) {


                System.out.println(diamond.getCarat());

                try {
                    Optional<DiamondPriceList> diamondPriceList = Optional.ofNullable(iDiamondPriceListService.getDiamondPriceListBy4C(diamond.getCarat(),
                            diamond.getClarity().getId(), diamond.getColor().getId()
                            , diamond.getCut().getId(), diamond.getOrigin().getId()));
                    if(diamondPriceList.isPresent()) {
                        totalPrice += diamondPriceList.get().getPrice() * diamond.getCarat() * 100;
                    }

                } catch (ClassNotFoundException e) {
                    System.out.println(e.getMessage());
                    countTwoComponentToEstablishPriceOfProduct++;
                }



            }




            for (ProductMaterial productMaterial : listProductMaterial){

                MaterialPriceListDTO materialPriceListDTO = iMaterialPriceListService.getMaterialPriceListById(productMaterial.getMaterial().getId());

                if(materialPriceListDTO != null && countTwoComponentToEstablishPriceOfProduct == 0) {totalPrice += materialPriceListDTO.getSellPrice();}

            }
            if(countTwoComponentToEstablishPriceOfProduct != 0){
                totalPrice = -1;
            }else {
                totalPrice += product.get().getProductionCost() + product.get().getSecondaryDiamondCost() + product.get().getSecondaryMaterialCost();
                totalPrice += totalPrice*((double) product.get().getPriceRate() / 100);
            }


            productDTO.setPrice(totalPrice);
            System.out.println("Total price without diamond or material: " + totalPrice);

            List<PromotionDTO> promotionDTOS = new ArrayList<>();
            for(Promotions_products promotions_products : product.get().getPromotions_products()){
                java.util.Date now = new java.util.Date();
                if(promotions_products.getPromotion().isActive() && promotions_products.getPromotion().getDateStart().getTime() <= now.getTime() && promotions_products.getPromotion().getDateEnd().getTime() >= now.getTime()){
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


            Set<Image> imagesSet = imageRepository.getImagesByProduct_Id(product.get().getId());
            Set<String> images = new HashSet<>();
            imagesSet.forEach(image -> {
                images.add(image.getUrl());
            });

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
                  diamondDTO.setImage(item.getCertificate());
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
        try{
            List<Product> productActive = productRepository.getProductsByActive(true);
            List<Product> productInactive = productRepository.getProductsByActive(false);
            List<Product> productList = new ArrayList<>();
            productList.addAll(productInactive);
            productList.addAll(productActive);
            List<Product> productReturn = new ArrayList<>();
            for(Product product : productList){
                if(LocalDateTime.now().minusDays(7).isBefore(product.getDateAdd())){
                    productReturn.add(product);
                }
            }
            return productReturn;
        }catch(Exception ex){
            System.out.println("Error: "+ex.getMessage());
            return null;
        }

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
                totalPrice += diamondPriceList.getPrice() * 100 * diamond.getCarat() ;
            } catch (ClassNotFoundException e) {
                System.out.println(e.getMessage());
            }

        }


        for (ProductMaterial productMaterial : listProductMaterial) {

            totalPrice += iMaterialPriceListService.getMaterialPriceListById(productMaterial.getMaterial().getId()).getSellPrice();
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

            productDTO.setSizeUnitPrice(item.getSizeUnitPrice());
            productDTO.setProductionCost(item.getProductionCost());
            productDTO.setSecondaryDiamondCost(item.getSecondaryDiamondCost());
            productDTO.setPriceRate(item.getPriceRate());
            productDTO.setSecondaryMaterialCost(item.getSecondaryMaterialCost());
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
                diamondDTO.setCode(diamond.getCode());
                diamondDTO.setImage(diamond.getCertificate());
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


                totalPrice += iMaterialPriceListService.getMaterialPriceListById(productMaterial.getMaterial().getId()).getSellPrice();
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


            Set<Image> imagesSet = imageRepository.getImagesByProduct_Id(item.getId());
            Set<String> images = new HashSet<>();
            imagesSet.forEach(image -> {
                images.add(image.getUrl());
            });
            productDTO.setImages(images);


            productDTOS.add(productDTO);
        });
        return productDTOS;
    }

    @Override
    public boolean updateProduct(Product product) {
        Optional<Product> productOptional = productRepository.findById(product.getId());
        try {
            productOptional.orElseThrow(() -> new ClassNotFoundException("Product Not Found by id: " + product.getId()));
        }catch(ClassNotFoundException ex){
            System.out.println(ex.getMessage());
            return false;
        }

        // delete
        imageRepository.deleteImagesByProduct_Id(productOptional.get().getId());
        productMaterialRepository.deleteProductMaterialsByProduct_Id(productOptional.get().getId());
        diamondRepository.deleteDiamondsByProductID(productOptional.get().getId());

        //

        productOptional.get().setPromotions_products(product.getPromotions_products());

        Size size = sizeService.getSize(product.getSize().getSize());
        if(size != null){
            productOptional.get().setSize(size);
        }else  productOptional.get().setSize(product.getSize());


        product.getProductMaterials().forEach((element) -> {
            Material material =materialService.getMaterial(element.getMaterial().getName());
            if(material != null) element.setMaterial(material);
        });



        productOptional.get().setCode(product.getCode());

        productOptional.get().setName(product.getName());

        productOptional.get().setPriceRate(product.getPriceRate());

        productOptional.get().setProductionCost(product.getProductionCost());

        productOptional.get().setSecondaryDiamondCost(product.getSecondaryDiamondCost());

        productOptional.get().setSecondaryMaterialCost(product.getSecondaryMaterialCost());

        productOptional.get().setSizeUnitPrice(product.getSizeUnitPrice());

        productOptional.get().setProductMaterials(product.getProductMaterials());


        product.getDiamonds().forEach(diamond -> {
            Color color = colorService.getColor(diamond.getColor().getColor());
            if(color != null ) diamond.setColor(color);

            Clarity clarity =clarityService.getClarity(diamond.getClarity().getClarity());
            if(clarity != null) diamond.setClarity(clarity);

            Cut cut = cutService.getCut(diamond.getCut().getCut());
            if(cut != null) diamond.setCut(cut);

            Origin origin = originService.getOrigin(diamond.getOrigin().getOrigin());
            if(origin != null)  diamond.setOrigin(origin);
        });
        productOptional.get().setDiamonds( product.getDiamonds());

        productOptional.get().setImages(product.getImages());

        Category category = categoryService.getCategory(product.getCategory().getName());
        if(category != null){
            productOptional.get().setCategory(category);
        }else productOptional.get().setCategory(product.getCategory());

        productOptional.get().setOrderDetails(product.getOrderDetails());

        productRepository.save(productOptional.get());
        return true;
    }

    @Override
    public boolean deleteProduct(Long id) {
        Optional<Product> product = productRepository.findById(id);
        try {
            product.orElseThrow(() -> new ClassNotFoundException("Product Not Found by id: " + id));
        }catch(ClassNotFoundException ex){
            System.out.println(ex.getMessage());
            return false;
        }
        Product productDelete = product.get();
        productDelete.setActive(false);
        productRepository.save(productDelete);
        return true;
    }




    @Override
    public List<ProductDTO> searchProduct(String name) {
        List<Product> products = productRepository.findByNameContainingIgnoreCase(name);
        return products.stream()
                .map(this::convertToProductDTO)
                .collect(Collectors.toList());
    }

    private ProductDTO convertToProductDTO(Product product) {
        ProductDTO productDTO = new ProductDTO();
        productDTO.setId(product.getId());
        productDTO.setName(product.getName());
        productDTO.setCode(product.getCode());
        productDTO.setSize(product.getSize().getSize());
        productDTO.setSizeUnitPrice(product.getSizeUnitPrice());

        double totalPrice = calculateTotalPrice(product);
        productDTO.setPrice(totalPrice);

        // Set promotions
        List<PromotionDTO> promotionDTOS = product.getPromotions_products().stream()
                .filter(promotions_products ->
                        promotions_products.getPromotion().isActive()
                                && promotions_products.getPromotion().getDateStart().getTime() <= System.currentTimeMillis()
                                && promotions_products.getPromotion().getDateEnd().getTime() >= System.currentTimeMillis())
                .map(promotions_products -> {
                    PromotionDTO promotionDTO = new PromotionDTO();
                    promotionDTO.setNamePromotion(promotions_products.getPromotion().getNamePromotion());
                    promotionDTO.setPromotionRate(promotions_products.getPromotion().getPromotionRate());
                    promotionDTO.setIdPromotion(promotions_products.getPromotion().getId());
                    promotionDTO.setActive(promotions_products.getPromotion().isActive());
                    promotionDTO.setDateStart(promotions_products.getPromotion().getDateStart());
                    promotionDTO.setDateEnd(promotions_products.getPromotion().getDateEnd());
                    return promotionDTO;
                })
                .collect(Collectors.toList());
        productDTO.setPromotions(promotionDTOS);

        // Set images
        Set<String> images = product.getImages().stream()
                .map(Image::getUrl)
                .collect(Collectors.toSet());
        productDTO.setImages(images);

        // Set category
        productDTO.setCategory(product.getCategory().getName());

        // Set materials
        Set<MaterialDTO> materialDTOS = productMaterialService.getProductMaterials(product.getId()).stream()
                .map(item -> {
                    MaterialDTO materialDTO = new MaterialDTO();
                    materialDTO.setWeight(item.getWeight());
                    materialDTO.setName(item.getMaterial().getName());
                    return materialDTO;
                })
                .collect(Collectors.toSet());
        productDTO.setMaterials(materialDTOS);

        // Set diamonds
        List<DiamondDTO> diamondDTOS = diamondService.getDiamondByProductID(product.getId()).stream()
                .map(item -> {
                    DiamondDTO diamondDTO = new DiamondDTO();
                    diamondDTO.setId(item.getId());
                    diamondDTO.setCarat(item.getCarat());
                    diamondDTO.setImage(item.getCertificate());
                    return diamondDTO;
                })
                .collect(Collectors.toList());
        productDTO.setDiamonds(diamondDTOS);

        return productDTO;
    }

    private double calculateTotalPrice(Product product) {
        double totalPrice = 0;

        // Calculate total price based on diamonds
        List<Diamond> diamonds = diamondService.getDiamondByProductID(product.getId());
        int countTwoComponentToEstablishPriceOfProduct = 0;
        for (Diamond diamond : diamonds) {
            try {
                DiamondPriceList diamondPriceList = iDiamondPriceListService.getDiamondPriceListBy4C(
                        diamond.getCarat(),
                        diamond.getClarity().getId(),
                        diamond.getColor().getId(),
                        diamond.getCut().getId(),
                        diamond.getOrigin().getId()
                );
                totalPrice += diamondPriceList.getPrice() * diamond.getCarat() * 100;
            } catch (ClassNotFoundException e) {
                System.out.println(e.getMessage());
                countTwoComponentToEstablishPriceOfProduct++;
            }
        }

        // Calculate total price based on materials
        List<ProductMaterial> productMaterials = productMaterialService.getProductMaterials(product.getId());
        for (ProductMaterial productMaterial : productMaterials) {
            MaterialPriceListDTO materialPriceListDTO = iMaterialPriceListService.getMaterialPriceListById(productMaterial.getMaterial().getId());
            if (materialPriceListDTO != null && countTwoComponentToEstablishPriceOfProduct == 0) {
                totalPrice += materialPriceListDTO.getSellPrice();
            }
        }

        // Handle total price calculation based on errors
        if (countTwoComponentToEstablishPriceOfProduct != 0) {
            totalPrice = -1;
        } else {
            totalPrice += product.getProductionCost() + product.getSecondaryDiamondCost() + product.getSecondaryMaterialCost();
            totalPrice += totalPrice * ((double) product.getPriceRate() / 100);
        }

        return totalPrice;
    }


}


