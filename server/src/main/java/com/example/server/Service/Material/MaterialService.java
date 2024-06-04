package com.example.server.Service.Material;

import com.example.server.Pojo.Material;
import com.example.server.Repository.IMaterialRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class MaterialService implements IMaterialService{

    @Autowired
    private IMaterialRepository materialRepository;

    @Override
    public void save(Material material) {
        materialRepository.save(material);
    }

    @Override
    public Material getMaterial(String name) {

        Material material = materialRepository.getMaterialByName(name);
        if(material == null){
            Material newMaterial = new Material();
            newMaterial.setName(name);
            save(newMaterial);
            return materialRepository.getMaterialByName(name);
        }return material;
    }

    @Override
    public Set<Material> getMaterialByIDProduct(Long id) {
        return null;
    }
}
