package com.example.server.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MaterialService implements IMaterialService{

    @Autowired
    private IMaterialService materialService;
}
