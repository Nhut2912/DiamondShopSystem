package com.example.server.Service.DiamondPriceList;

import com.example.server.Pojo.DiamondPriceList;
import com.example.server.Repository.IDiamondPriceListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DiamondPriceListService implements IDiamondPriceListService{

    @Autowired
    private IDiamondPriceListRepository iDiamondPriceListRepository;

    @Override
    public List<DiamondPriceList> getAll() {
        return (List<DiamondPriceList>) iDiamondPriceListRepository.findAll();
    }
}
