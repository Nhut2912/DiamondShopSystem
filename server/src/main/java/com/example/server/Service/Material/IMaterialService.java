package com.example.server.Service.Material;

import com.example.server.Pojo.Material;

public interface IMaterialService {

    public void save(Material material);

    public Material getMaterial(String name);
}
