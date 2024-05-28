package com.example.server.Services;

import com.example.server.Pojo.Diamond;
import com.example.server.Repository.IDiamondRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
@Transactional
public class DiamondServices {

    @Autowired
    private IDiamondRepository diamondRepository;

    public List<Diamond> listAll(){
        return diamondRepository.findAll();
    }

    public void save (Diamond diamond){
        diamondRepository.save(diamond);
    }

    public Diamond get(Long id){
        return diamondRepository.findById(id).get();
    }

    public void delete(Long id){
        diamondRepository.deleteById(id);
    }
}
