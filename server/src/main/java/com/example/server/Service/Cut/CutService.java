package com.example.server.Service.Cut;

import com.example.server.Pojo.Cut;
import com.example.server.Repository.ICutRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CutService implements ICutService {


    @Autowired
    private ICutRepository cutRepository;

    @Override
    public void save(Cut cut) {
        cutRepository.save(cut);
    }

    @Override
    public Cut getCut(String cut) {

        Cut cutObject = cutRepository.getCutByCut(cut);
        if(cutObject == null){
            Cut newCut = new Cut();
            newCut.setCut(cut);
            save(newCut);
            return cutRepository.getCutByCut(cut);
        }else return cutObject;
    }
}
