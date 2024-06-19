package com.example.server.Repository;

import com.example.server.Pojo.WarrantyPolicy;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IWarrantyPolicyRepository extends CrudRepository<WarrantyPolicy, Long> {

    WarrantyPolicy findByNameAndWarrantyPeriod(String name, Integer period);


}
