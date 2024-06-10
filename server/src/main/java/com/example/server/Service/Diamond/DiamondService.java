package com.example.server.Service.Diamond;

import com.example.server.Pojo.Diamond;
import com.example.server.Repository.IDiamondRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
