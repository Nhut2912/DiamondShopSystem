package com.example.server.Services;

import com.example.server.Pojo.WarrantyPolicy;
import com.example.server.Repository.IWarrantyPolicyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WarrantyPolicyServices implements IWarrantyPolicyServices{
    @Autowired
    private IWarrantyPolicyRepository iWarrantyPolicyRepository;
    @Override
    public boolean saveWarrantyPolicy(WarrantyPolicy warrantyPolicy) {
        try {
            iWarrantyPolicyRepository.save(warrantyPolicy);
            return true;
        }catch(Exception ex){
            System.out.println(ex.getMessage());
            return false;
        }
    }
}
