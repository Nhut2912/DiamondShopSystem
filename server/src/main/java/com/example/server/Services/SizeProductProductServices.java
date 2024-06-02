package com.example.server.Services;

import com.example.server.Pojo.Size;
import com.example.server.Repository.ISizeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SizeProductProductServices implements ISizeProductServices {
    @Autowired
    ISizeRepository iSizeRepository;
    @Override
    public boolean saveSize(Size size) {
        try{

            iSizeRepository.save(size);
            return true;
        }catch (Exception ex){
            return false;
        }
    }
}
