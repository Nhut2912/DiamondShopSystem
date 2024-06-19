package com.example.server.Service.MaterialPriceList;

import com.example.server.Model.MaterialPriceListDTO;
import com.example.server.Pojo.DiamondPriceList;
import com.example.server.Pojo.Material;
import com.example.server.Pojo.MaterialPriceList;
import com.example.server.Repository.IMaterialPriceListRepository;
import com.example.server.Repository.IMaterialRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MaterialPriceListService implements IMaterialPriceListService{

    @Autowired
    private IMaterialPriceListRepository iMaterialPriceListRepository;

    @Autowired
    private IMaterialRepository iMaterialRepository;

    @Override
    public MaterialPriceList getMaterialPriceListById(Long id) {
        Optional<MaterialPriceList> materialPriceList = iMaterialPriceListRepository.findByMaterial_id(id);
        System.out.println(materialPriceList);
        return materialPriceList.orElse(null);
    }
    @Override
    public boolean updateMaterialPrice(MaterialPriceListDTO materialPriceListDTO) {
        Optional<Material> material = Optional.ofNullable(iMaterialRepository.getMaterialByName(materialPriceListDTO.getMaterial()));

        try {
            iMaterialPriceListRepository.findById(materialPriceListDTO.getId()).orElseThrow(() ->
                    new ClassNotFoundException("Material Price List Not Found by id material price list: " +
                    materialPriceListDTO.getId()));
            material.orElseThrow(() -> new ClassNotFoundException("Material Price List Not Found by Material: " + materialPriceListDTO.getMaterial()));
        }catch(ClassNotFoundException ex){
            System.out.println(ex.getMessage());
            return false;
        }
        MaterialPriceList materialPriceListSave = new MaterialPriceList();
        materialPriceListSave.setId(materialPriceListDTO.getId());
        materialPriceListSave.setEffDate(materialPriceListDTO.getEffDate());
        materialPriceListSave.setMaterial(material.get());
        materialPriceListSave.setSellPrice(materialPriceListDTO.getSellPrice());
        iMaterialPriceListRepository.save(materialPriceListSave);
        return true;
    }
}
