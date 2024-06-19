package com.example.server.Service.WarrantyPolicy;

import com.example.server.Pojo.WarrantyPolicy;

import java.util.List;

public interface IWarrantyPolicyService {
    public boolean createWarrantyPolicy(WarrantyPolicy warrantyPolicy);
    public boolean updateWarrantyPolicy(WarrantyPolicy warrantyPolicy);
    public boolean deleteWarrantyPolicy(WarrantyPolicy warrantyPolicy);
    public List<WarrantyPolicy> getWarrantyPolicies();
}
