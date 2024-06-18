package com.example.server.Service.DiamondPriceList;


import com.example.server.Model.DiamondPriceListDTO;
import com.example.server.Pojo.Diamond;
import com.example.server.Pojo.DiamondPriceList;

import java.util.List;

public interface IDiamondPriceListService {
    public DiamondPriceList getDiamondPriceListBy4C(double carat,
                                                    Long clarity_id, Long color_id,
                                                    Long cut_id, Long origin_id);


    public List<DiamondPriceListDTO> getDiamondsPriceList();
    public boolean updateDiamondPrice(DiamondPriceListDTO diamondPriceListDTO);
}
