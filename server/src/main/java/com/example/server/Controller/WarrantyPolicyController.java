package com.example.server.Controller;

import com.example.server.Pojo.WarrantyPolicy;
import com.example.server.Services.IWarrantyPolicyServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "api/warrantyPolicyController")
public class WarrantyPolicyController {
    @Autowired
    IWarrantyPolicyServices iWarrantyPolicyServices;
    public ResponseEntity<?> saveWarrantyPolicy(@RequestBody WarrantyPolicy warrantyPolicy){
        return new ResponseEntity<>(iWarrantyPolicyServices.saveWarrantyPolicy(warrantyPolicy), HttpStatus.CREATED);
    }
}
