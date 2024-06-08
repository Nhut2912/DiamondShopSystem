package com.example.server.Repository;

import com.example.server.Pojo.DiamondPriceList;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IDiamondPriceListRepository extends CrudRepository<DiamondPriceList, Long> {
}
