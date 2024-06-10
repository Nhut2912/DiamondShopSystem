package com.example.server.Repository;

import com.example.server.Pojo.DiamondPriceList;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IDiamondPriceListRepository extends CrudRepository<DiamondPriceList,Integer> {
    Optional<DiamondPriceList> findByCaratAndClarity_idAndColor_idAndCut_idAndOrigin_id(double carat,
                                                                                        Long clarity_id, Long color_id,
                                                                                        Long cut_id, Long origin_id);

}
