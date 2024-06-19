package com.example.server.Repository;

import com.example.server.Pojo.DiamondPriceList;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IDiamondPriceListRepository extends CrudRepository<DiamondPriceList, Long> {
    Optional<DiamondPriceList> findByClarity_idAndColor_idAndCut_idAndOrigin_id(
                                                                                        Long clarity_id, Long color_id,
                                                                                        Long cut_id, Long origin_id);

    @Query(value = "select * from diamond_price_list where clarity_id = :clarity_id and color_id = :color_id and cut_id = :cut_id and origin_id = :origin_id and :carat between carat_from and carat_to", nativeQuery = true)
    Optional<DiamondPriceList> findByCaratAndClarity_idAndColor_idAndCut_idAndOrigin_idAndCaratBetweenCaratFromAndCaratTo(double carat,
                                                                                                                          Long clarity_id, Long color_id,
                                                                                                                          Long cut_id, Long origin_id);


}
