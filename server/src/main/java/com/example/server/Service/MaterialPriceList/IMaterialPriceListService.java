package com.example.server.Service.MaterialPriceList;

import com.example.server.Model.MaterialPriceListDTO;
import com.example.server.Pojo.MaterialPriceList;

public interface IMaterialPriceListService {
    public MaterialPriceList getMaterialPriceListById(Long id);
    public boolean updateMaterialPrice(MaterialPriceListDTO materialPriceListDTO);
    public boolean addMaterialPrice(MaterialPriceListDTO materialPriceListDTO);

}
