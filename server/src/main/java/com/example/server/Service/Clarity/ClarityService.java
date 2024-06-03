package com.example.server.Service.Clarity;

import com.example.server.Pojo.Clarity;
import com.example.server.Repository.IClarityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClarityService implements IClarityService{

    @Autowired
    private IClarityRepository clarityRepository;

    @Override
    public void save(Clarity clarity) {
        clarityRepository.save(clarity);
    }

    @Override
    public Clarity getClarity(String clarity) {
        Clarity clarityObject = clarityRepository.getClarityByClarity(clarity);
        if(clarityObject == null){
            Clarity newClarity = new Clarity();
            newClarity.setClarity(clarity);
            save(newClarity);
            return clarityRepository.getClarityByClarity(clarity);
        }else return clarityObject;
    }


}
