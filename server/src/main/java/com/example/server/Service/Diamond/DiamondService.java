package com.example.server.Service.Diamond;

import com.example.server.Model.DiamondDTO;
import com.example.server.Pojo.Diamond;
import com.example.server.Repository.IDiamondRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
public class DiamondService implements IDiamondService{

    @Autowired
    private IDiamondRepository diamondRepository;

    @Override
    public List<Diamond> getDiamondByProductID(Long Id) {
        return diamondRepository.getDiamondByProduct_Id(Id);
    }

    @Override
    public List<DiamondDTO> getDiamondDetailByProductID(Long Id) {
        List<DiamondDTO> diamondDTOS = new ArrayList<>();
        List<Diamond> diamonds = diamondRepository.getDiamondByProduct_Id(Id);
        diamonds.forEach((diamond) -> {
            DiamondDTO diamondDTO = new DiamondDTO();
            diamondDTO.setId(diamond.getId());
            diamondDTO.setCut(diamond.getCut().getCut());
            diamondDTO.setColor(diamond.getColor().getColor());
            diamondDTO.setCarat(diamond.getCarat());
            diamondDTO.setOrigin(diamond.getOrigin().getOrigin());
            diamondDTO.setClarity(diamond.getClarity().getClarity());
            diamondDTOS.add(diamondDTO);
        });
        return diamondDTOS;
    }
}
