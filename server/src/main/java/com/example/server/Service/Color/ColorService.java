package com.example.server.Service.Color;

import com.example.server.Pojo.Color;
import com.example.server.Repository.IColorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ColorService implements IColorService{

    @Autowired
    private IColorRepository colorRepository;

    @Override
    public void save(Color color) {
        colorRepository.save(color);
    }

    @Override
    public Color getColor(String color) {

        Color colorObject =  colorRepository.getColorByColor(color);
        if(colorObject == null){
            Color newColor = new Color();
            newColor.setColor(color);
            save(newColor);
            return colorRepository.getColorByColor(color);
        }return colorObject;
    }
}
