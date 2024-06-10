package com.example.server.Service.DiamondPriceList;

<<<<<<< HEAD
import com.example.server.Pojo.Diamond;
import com.example.server.Pojo.DiamondPriceList;

public interface IDiamondPriceListService {
    public DiamondPriceList getDiamondPriceListBy4C(double carat,
                                                    Long clarity_id, Long color_id,
                                                    Long cut_id, Long origin_id);
=======
import com.example.server.Pojo.DiamondPriceList;

import java.util.List;

public interface IDiamondPriceListService {

    public List<DiamondPriceList> getAll();
>>>>>>> 2e4e67df43c194e77ba142c3846983cb9ccab8c0

}
