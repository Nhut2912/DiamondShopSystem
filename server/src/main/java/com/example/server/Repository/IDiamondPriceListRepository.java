package com.example.server.Repository;

import com.example.server.Pojo.DiamondPriceList;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

<<<<<<< HEAD
import java.util.Optional;

@Repository
public interface IDiamondPriceListRepository extends CrudRepository<DiamondPriceList,Integer> {
    Optional<DiamondPriceList> findByCaratAndClarity_idAndColor_idAndCut_idAndOrigin_id(double carat,
                                                                                        Long clarity_id, Long color_id,
                                                                                        Long cut_id, Long origin_id);

=======
@Repository
public interface IDiamondPriceListRepository extends CrudRepository<DiamondPriceList, Long> {
>>>>>>> 2e4e67df43c194e77ba142c3846983cb9ccab8c0
}
