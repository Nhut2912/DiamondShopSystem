package com.example.server.Service.Diamond;

import com.example.server.Model.DiamondDTO;
import com.example.server.Pojo.Diamond;

import java.util.List;
import java.util.Set;

public interface IDiamondService {

    public List<Diamond> getDiamondByProductID(Long Id);

    public List<DiamondDTO> getDiamondDetailByProductID(Long Id);

}
