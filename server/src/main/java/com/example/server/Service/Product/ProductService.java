package com.example.server.Service.Product;


import com.example.server.Model.DiamondDTO;
import com.example.server.Model.MaterialDTO;
import com.example.server.Model.ProductDTO;
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

import java.util.*;


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



            double totalPrice = 0;
            double caratInterval = 0;
            
            List<Diamond> listDiamondReturn = diamondService.getDiamondByProductID(item.getId());
            List<ProductMaterial> listProductMaterial = productMaterialService.getProductMaterials(item.getId());

            System.out.println(listDiamondReturn);

            for (Diamond diamond : listDiamondReturn) {
                if(diamond.getCarat() >= 0.1 && diamond.getCarat() <= 0.3) {
                    caratInterval = 0.1;
                }else if (diamond.getCarat() >= 0.4 && diamond.getCarat() <= 0.7){
                    caratInterval = 0.5;
                }else if (diamond.getCarat() >= 0.8 && diamond.getCarat() <= 1.4) {
                    caratInterval = 1;
                }else if (diamond.getCarat() >= 1.5 && diamond.getCarat() <= 1.7) {
                    caratInterval = 1.5;
                }else if (diamond.getCarat() >= 1.8 && diamond.getCarat() <= 2) {
                    caratInterval = 2;
                }
                DiamondPriceList diamondPriceList = iDiamondPriceListService.getDiamondPriceListBy4C(caratInterval,
                        diamond.getClarity().getId(), diamond.getColor().getId()
                        , diamond.getCut().getId(), diamond.getOrigin().getId());
                totalPrice += diamondPriceList.getPrice() * diamond.getCarat() *10;
            }




            for (ProductMaterial productMaterial : listProductMaterial){
                MaterialPriceList materialPriceList = iMaterialPriceListService.getMaterialPriceListById(productMaterial.getMaterial().getId());
                totalPrice += materialPriceList.getSellPrice();
            }
            totalPrice += item.getProductionCost() + item.getSecondaryDiamondCost() + item.getSecondaryMaterialCost();
            totalPrice += totalPrice*((double) item.getPriceRate() / 100);

            productDTO.setPrice(totalPrice);



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
            double caratInterval = 0;

            List<Diamond> listDiamondReturn = diamondService.getDiamondByProductID(product.get().getId());
            List<ProductMaterial> listProductMaterial = productMaterialService.getProductMaterials(product.get().getId());



            for (Diamond diamond : listDiamondReturn) {
                if(diamond.getCarat() >= 0.1 && diamond.getCarat() <= 0.3) {
                    caratInterval = 0.1;
                }else if (diamond.getCarat() >= 0.4 && diamond.getCarat() <= 0.7){
                    caratInterval = 0.5;
                }else if (diamond.getCarat() >= 0.8 && diamond.getCarat() <= 1.4) {
                    caratInterval = 1;
                }else if (diamond.getCarat() >= 1.5 && diamond.getCarat() <= 1.7) {
                    caratInterval = 1.5;
                }else if (diamond.getCarat() >= 1.8 && diamond.getCarat() <= 2) {
                    caratInterval = 2;
                }
                DiamondPriceList diamondPriceList = iDiamondPriceListService.getDiamondPriceListBy4C(caratInterval,
                        diamond.getClarity().getId(), diamond.getColor().getId()
                        , diamond.getCut().getId(), diamond.getOrigin().getId());
                totalPrice += diamondPriceList.getPrice() * diamond.getCarat() *10;
            }




            for (ProductMaterial productMaterial : listProductMaterial){
                MaterialPriceList materialPriceList = iMaterialPriceListService.getMaterialPriceListById(productMaterial.getMaterial().getId());
                totalPrice += materialPriceList.getSellPrice();
            }
            totalPrice += product.get().getProductionCost() + product.get().getSecondaryDiamondCost() + product.get().getSecondaryMaterialCost();
            totalPrice += totalPrice*((double) product.get().getPriceRate() / 100);

            productDTO.setPrice(totalPrice);






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

}
