package com.example.server.Services;

import com.example.server.Pojo.Material;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MaterialService implements IMaterialService{

    @Autowired
    private IMaterialService materialService;

    @Override
    public boolean saveMaterial(Material material) {
        try{
            materialService.saveMaterial(material);
            return true;
        }catch (Exception e){
            System.out.println(e.getMessage());
            return false;
        }
    }
}
