package com.example.server.Controller;

import com.example.server.Pojo.Material;
import com.example.server.Services.IMaterialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "api/material")
public class MaterialController {

    @Autowired
    IMaterialService materialService;
    @PostMapping("/save")
    public ResponseEntity<?> save(@RequestBody Material material){
        return new ResponseEntity<>(materialService.saveMaterial(material), HttpStatus.CREATED);
    }
}
