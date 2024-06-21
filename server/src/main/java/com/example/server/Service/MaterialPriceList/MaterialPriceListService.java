package com.example.server.Service.MaterialPriceList;

import com.example.server.Model.MaterialPriceListDTO;
import com.example.server.Pojo.DiamondPriceList;
import com.example.server.Pojo.Material;
import com.example.server.Pojo.MaterialPriceList;
import com.example.server.Repository.IMaterialPriceListRepository;
import com.example.server.Repository.IMaterialRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class MaterialPriceListService implements IMaterialPriceListService{

    @Autowired
    private IMaterialPriceListRepository iMaterialPriceListRepository;

    @Autowired
    private IMaterialRepository iMaterialRepository;

    @Override
    public List<MaterialPriceListDTO> getMaterialPriceLists() {
        Optional<List<MaterialPriceList>> materialPriceList = Optional.of((List<MaterialPriceList>) iMaterialPriceListRepository.findAll());
        List<MaterialPriceListDTO> materialPriceListDTOS = new ArrayList<>();
        try {
            materialPriceList.orElseThrow(() -> new ClassNotFoundException("Not Found"));
            for(MaterialPriceList materialPriceListLoop : materialPriceList.get()){
                MaterialPriceListDTO materialPriceListDTO = new MaterialPriceListDTO();
                materialPriceListDTO.setId(materialPriceListLoop.getId());
                materialPriceListDTO.setSellPrice(materialPriceListLoop.getSellPrice());
                materialPriceListDTO.setEffDate(materialPriceListLoop.getEffDate());
                materialPriceListDTO.setMaterial(materialPriceListLoop.getMaterial().getName());
                materialPriceListDTOS.add(materialPriceListDTO);
            }
        }catch(Exception ex){
            System.out.println(ex.getMessage());
            return null;
        }
        return materialPriceListDTOS;
    }
    @Override
    public MaterialPriceListDTO getMaterialPriceListById(Long id) {
        Optional<MaterialPriceList> materialPriceList = iMaterialPriceListRepository.findByMaterial_id(id);
        MaterialPriceListDTO materialPriceListDTO = new MaterialPriceListDTO();
        try{
            materialPriceList.orElseThrow(() -> new ClassNotFoundException("Not Found"));
            materialPriceListDTO.setMaterial(materialPriceList.get().getMaterial().getName());
            materialPriceListDTO.setSellPrice(materialPriceList.get().getSellPrice());
            materialPriceListDTO.setId(materialPriceList.get().getId());
            materialPriceListDTO.setEffDate(materialPriceList.get().getEffDate());
        }catch(Exception ex){
            System.out.println(ex.getMessage());
            return materialPriceListDTO;
        }
        return materialPriceListDTO;
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
    public boolean createMaterialPrice(MaterialPriceListDTO materialPriceListDTO){
        Optional<Material> material = Optional.ofNullable(iMaterialRepository.getMaterialByName(materialPriceListDTO.getMaterial()));
        try {
            iMaterialPriceListRepository.findById(materialPriceListDTO.getId()).orElseThrow(() ->
                    new ClassNotFoundException("Material Price List Not Found by id material price list: " +
                            materialPriceListDTO.getId()));
            material.orElseThrow(() -> new ClassNotFoundException("Material Price List Not Found by Material: " + materialPriceListDTO.getMaterial()));
            MaterialPriceList materialPriceListSave = new MaterialPriceList();
//            materialPriceListSave.setId(materialPriceListDTO.getId());
            materialPriceListSave.setEffDate(materialPriceListDTO.getEffDate());
            materialPriceListSave.setMaterial(material.get());
            materialPriceListSave.setSellPrice(materialPriceListDTO.getSellPrice());
            iMaterialPriceListRepository.save(materialPriceListSave);
            return true;
        }catch(Exception ex){
            System.out.println(ex.getMessage());
            return false;
        }


    }
}
