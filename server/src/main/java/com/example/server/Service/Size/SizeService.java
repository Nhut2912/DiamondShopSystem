package com.example.server.Service.Size;

import com.example.server.Pojo.Size;
import com.example.server.Repository.ISizeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SizeService implements ISizeService{

    @Autowired
    private ISizeRepository sizeRepository;

    @Override
    public Size getSize(int size) {
        return sizeRepository.getSizeBySize(size);
    }
}
