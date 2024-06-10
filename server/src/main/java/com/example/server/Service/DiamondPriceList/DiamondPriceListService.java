package com.example.server.Service.DiamondPriceList;

import com.example.server.Pojo.DiamondPriceList;
import com.example.server.Repository.IDiamondPriceListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.Optional;

@Service
public class DiamondPriceListService implements IDiamondPriceListService {
    @Autowired
    IDiamondPriceListRepository iDiamondPriceListRepository;

    @Override
    public DiamondPriceList getDiamondPriceListBy4C(double carat, Long clarity_id, Long color_id, Long cut_id, Long origin_id) {
        System.out.println("carat: " + carat + " clarity: " + clarity_id + " color: " + color_id + " cut: " + cut_id + " origin: " + origin_id);
        Optional<DiamondPriceList> diamondPriceList = iDiamondPriceListRepository.findByCaratAndClarity_idAndColor_idAndCut_idAndOrigin_id(carat, clarity_id, color_id, cut_id, origin_id);
        return diamondPriceList.orElse(null);


    }
}
