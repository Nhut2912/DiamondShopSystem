package com.example.server.Service.Origin;

import com.example.server.Pojo.Origin;
import com.example.server.Repository.IOriginRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OriginService implements  IOriginService{

    @Autowired
    private IOriginRepository originRepository;

    @Override
    public void save(Origin origin) {
        originRepository.save(origin);
    }

    @Override
    public Origin getOrigin(String origin) {

        Origin originObject = originRepository.getOriginByOrigin(origin);
        if(originObject == null){
            Origin newOrigin = new Origin();
            newOrigin.setOrigin(origin);
            save(newOrigin);
            return originRepository.getOriginByOrigin(origin);
        }else return originObject;
    }
}
