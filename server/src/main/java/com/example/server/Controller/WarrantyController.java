package com.example.server.Controller;

import com.example.server.Pojo.Warranty;
import com.example.server.Service.Warranty.IWarrantyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "api/warranty")
@CrossOrigin(origins = "http://localhost:3000")
public class WarrantyController {
    @Autowired
    IWarrantyService iWarrantyService;

    @PostMapping("/create")
    public ResponseEntity<?> createWarranty(@RequestBody Warranty warranty){
        return new ResponseEntity<>(iWarrantyService.createWarranty(warranty), HttpStatus.CREATED);
    }
}
