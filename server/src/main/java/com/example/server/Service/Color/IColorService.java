package com.example.server.Service.Color;

import com.example.server.Pojo.Color;

public interface IColorService {

    public void save(Color color);

    public Color getColor(String color);
}
