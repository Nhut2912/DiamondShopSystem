package com.example.server.Service.MaterialPriceList;

import com.example.server.Pojo.MaterialPriceList;
import com.example.server.Repository.IMaterialPriceListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MaterialPriceListService implements IMaterialPriceListService{
    @Autowired
    private IMaterialPriceListRepository iMaterialPriceListRepository;
    @Override
    public MaterialPriceList getMaterialPriceListById(Long id) {
        Optional<MaterialPriceList> materialPriceList = iMaterialPriceListRepository.findByMaterial_id(id);
        return materialPriceList.orElse(null);
    }
}
