package com.example.server.Service.Warranty;

import com.example.server.Model.WarrantyDTO;
import com.example.server.Pojo.Product;
import com.example.server.Pojo.Warranty;
import com.example.server.Pojo.WarrantyPolicy;
import com.example.server.Repository.IProductRepository;
import com.example.server.Repository.IWarrantyPolicyRepository;
import com.example.server.Repository.IWarrantyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class WarrantyService implements IWarrantyService {
    @Autowired
    IWarrantyRepository iWarrantyRepository;
    @Autowired
    IProductRepository iProductRepository;
    @Autowired
    IWarrantyPolicyRepository iWarrantyPolicyRepository;

    public boolean createWarranty(WarrantyDTO warrantyDTO) {
        LocalDate startDate = LocalDate.now();
        Optional<Product> product = iProductRepository.findById(warrantyDTO.getProduct().getId());
        Optional<WarrantyPolicy> policy = iWarrantyPolicyRepository.findById(warrantyDTO.getWarrantyPolicies().getId());
        try {

            product.orElseThrow(() -> new ClassNotFoundException("Not found by id product: " + warrantyDTO.getProduct().getId()));
            policy.orElseThrow(() -> new ClassNotFoundException("Not found by id product: " + warrantyDTO.getWarrantyPolicies().getId()));
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

        int periodMonth = policy.get().getWarrantyPeriod();
        LocalDate endDate = startDate.plusMonths(periodMonth);
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

        Warranty warranty = new Warranty();
        warranty.setUserName(warrantyDTO.getUserName());
        warranty.setPhoneNumber(warrantyDTO.getPhoneNumber());
        warranty.setAddress(warrantyDTO.getAddress());
        warranty.setProduct(warrantyDTO.getProduct());
        warranty.setStatus(true);
        try {
            warranty.setDateStart(formatter.parse(startDate.toString()));
            warranty.setDateEnd(formatter.parse(endDate.toString()));

            policy.ifPresent(warranty::setWarrantyPolicies);
            product.ifPresent(warranty::setProduct);

            iWarrantyRepository.save(warranty);

        } catch (Exception e) {
            System.out.println(e.getMessage() + "Error at warranty");

        }
        return true;
    }

    @Override
    public boolean updateWarranty(WarrantyDTO warrantyDTO) {
        Optional<Warranty> warrantyNeedToBeCheck = iWarrantyRepository.findById(warrantyDTO.getId());
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        try {
            warrantyNeedToBeCheck.orElseThrow(() -> new ClassNotFoundException("Not found warranty by" + warrantyDTO.getId()));
            Warranty warranty = new Warranty();
            warranty.setId(warrantyDTO.getId());
            warranty.setUserName(warrantyDTO.getUserName());
            warranty.setPhoneNumber(warrantyDTO.getPhoneNumber());
            warranty.setAddress(warrantyDTO.getAddress());
            warranty.setProduct(warrantyDTO.getProduct());
            warranty.setStatus(true);
            warranty.setWarrantyPolicies(warrantyDTO.getWarrantyPolicies());
            iWarrantyRepository.save(warranty);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return false;
    }

    @Override
    public boolean deleteWarranty(WarrantyDTO warrantyDTO) {
        Optional<Warranty> warrantyVerify = iWarrantyRepository.findById(warrantyDTO.getId());
        warrantyVerify.get().setStatus(false);
        iWarrantyRepository.save(warrantyVerify.get());
        return false;
    }

    @Override
    public List<WarrantyDTO> getWarranties() {
        List<Warranty> warranties = iWarrantyRepository.findByStatus(true);
        List<WarrantyDTO> warrantyDTOs = new ArrayList<>();
        for (Warranty warranty : warranties) {
            WarrantyDTO warrantyDTO = new WarrantyDTO();
            warrantyDTO.setAddress(warranty.getAddress());
            warrantyDTO.setUserName(warranty.getUserName());
            warrantyDTO.setPhoneNumber(warranty.getPhoneNumber());
            warrantyDTO.setDateStart(warranty.getDateStart());
            warrantyDTO.setDateEnd(warranty.getDateEnd());
            Product product = warranty.getProduct();
            product.setPromotions_products(null);
            warrantyDTO.setProduct(product);
            warrantyDTOs.add(warrantyDTO);
        }
        return warrantyDTOs;
    }

    @Override
    public WarrantyDTO getWarrantyByProduct(Long id) {
        Optional<Product> product = iProductRepository.findById(id);
        WarrantyDTO warrantyDTOReturn = new WarrantyDTO();
        if (product.isPresent()) {
            Warranty warranty = iWarrantyRepository.findByProduct_Id(product.get().getId());
            warrantyDTOReturn = new WarrantyDTO();
            warrantyDTOReturn.setId(warranty.getId());
            product.get().setPromotions_products(null);
            warrantyDTOReturn.setProduct(product.get());
            warrantyDTOReturn.setWarrantyPolicies(warranty.getWarrantyPolicies());
            warrantyDTOReturn.setAddress(warranty.getAddress());
            warrantyDTOReturn.setUserName(warranty.getUserName());
            warrantyDTOReturn.setDateStart(warranty.getDateStart());
            warrantyDTOReturn.setDateEnd(warranty.getDateEnd());
            warrantyDTOReturn.setStatus(warranty.isStatus());
            warrantyDTOReturn.setPhoneNumber(warranty.getPhoneNumber());

        }
        return warrantyDTOReturn;
    }


}
