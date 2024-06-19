package com.example.server.Repository;

import com.example.server.Pojo.WarrantyPolicy;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IWarrantyPolicyRepository extends CrudRepository<WarrantyPolicy, Long> {

    WarrantyPolicy findByNameAndWarrantyPeriod(String name, Integer period);
    List<WarrantyPolicy> findByIsActive(boolean isActive);

}
