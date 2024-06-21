package com.example.server.Service.MaterialPriceList;

import com.example.server.Model.MaterialPriceListDTO;
import com.example.server.Pojo.MaterialPriceList;

import java.util.List;

public interface IMaterialPriceListService {
    public MaterialPriceListDTO getMaterialPriceListById(Long id);
    public boolean updateMaterialPrice(MaterialPriceListDTO materialPriceListDTO);
    public boolean createMaterialPrice(MaterialPriceListDTO materialPriceListDTO);
    public List<MaterialPriceListDTO> getMaterialPriceLists();
}
