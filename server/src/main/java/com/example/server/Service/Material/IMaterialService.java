package com.example.server.Service.Material;

import com.example.server.Pojo.Material;

import java.util.Set;

public interface IMaterialService {

    public void save(Material material);

    public Material getMaterial(String name);

    public Set<Material> getMaterialByIDProduct(Long id);
}
