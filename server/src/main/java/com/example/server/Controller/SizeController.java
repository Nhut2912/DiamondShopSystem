package com.example.server.Controller;

import com.example.server.Pojo.Size;
import com.example.server.Services.ISizeProductServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "api/size")
public class SizeController {
    @Autowired
    ISizeProductServices iSizeProductServices;
    public ResponseEntity<?> saveProduct(@RequestBody Size size){
        return new ResponseEntity<>(iSizeProductServices.saveSize(size), HttpStatus.CREATED);
    }
}
