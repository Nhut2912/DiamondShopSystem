package com.example.server.Repository;

import com.example.server.Pojo.MaterialPriceList;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IMaterialPriceListRepository extends CrudRepository<MaterialPriceList, Long> {
    public Optional<MaterialPriceList> findByMaterial_id(Long id);
}
