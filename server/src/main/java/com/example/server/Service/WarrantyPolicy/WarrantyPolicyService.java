package com.example.server.Service.WarrantyPolicy;

import com.example.server.Pojo.WarrantyPolicy;
import com.example.server.Repository.IWarrantyPolicyRepository;
import com.example.server.Service.Warranty.IWarrantyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class WarrantyPolicyService implements IWarrantyPolicyService {
    @Autowired
    IWarrantyPolicyRepository iWarrantyPolicyRepository;
    @Override
    public boolean createWarrantyPolicy(WarrantyPolicy warrantyPolicy){
        try{
            iWarrantyPolicyRepository.save(warrantyPolicy);
        }catch (Exception ex){
            System.out.println(ex.getMessage());
            return false;
        }
        return true;
    }
    @Override
    public boolean updateWarrantyPolicy(WarrantyPolicy warrantyPolicy) {
        Optional<WarrantyPolicy> warrantyPolicyVerify = iWarrantyPolicyRepository.
                findById(warrantyPolicy.getId());
        try {
            warrantyPolicyVerify.orElseThrow(() -> new ClassNotFoundException("Not found warranty policy by" + warrantyPolicy.getId()));
            WarrantyPolicy warrantyPolicyUp = new WarrantyPolicy();
            warrantyPolicyUp.setId(warrantyPolicy.getId());
            warrantyPolicyUp.setName(warrantyPolicy.getName());
            warrantyPolicyUp.setWarrantyPeriod(warrantyPolicy.getWarrantyPeriod());
            iWarrantyPolicyRepository.save(warrantyPolicyUp);
            return true;
        } catch (Exception ex){
            System.out.println(ex.getMessage());
            return false;
        }
    }
    @Override
    public boolean deleteWarrantyPolicy(WarrantyPolicy warrantyPolicy) {
        Optional<WarrantyPolicy> warrantyPolicyVerify = Optional.ofNullable(iWarrantyPolicyRepository.
                findByNameAndWarrantyPeriod(warrantyPolicy.getName(), warrantyPolicy.getWarrantyPeriod()));
        try {
            warrantyPolicyVerify.orElseThrow(() -> new ClassNotFoundException("Not found warranty policy by" + warrantyPolicy.getName() + ", " + +warrantyPolicy.getWarrantyPeriod()));
            warrantyPolicyVerify.get().setActive(false);
            iWarrantyPolicyRepository.save(warrantyPolicyVerify.get());
            return true;
        } catch (Exception ex){
            System.out.println(ex.getMessage());
            return false;
        }
    }
    @Override
    public List<WarrantyPolicy> getWarrantyPolicies() {
        return iWarrantyPolicyRepository.findByIsActive(true);
    }
}
