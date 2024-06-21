package com.example.server.Controller;

import com.example.server.Model.WarrantyDTO;
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
    public ResponseEntity<?> createWarranty(@RequestBody WarrantyDTO warrantyDTO){
        return new ResponseEntity<>(iWarrantyService.createWarranty(warrantyDTO), HttpStatus.CREATED);
    }
    @PostMapping("/update")
    public ResponseEntity<?> updateWarranty(@RequestBody WarrantyDTO warrantyDTO){
        return new ResponseEntity<>(iWarrantyService.updateWarranty(warrantyDTO), HttpStatus.CREATED);
    }
    @PostMapping("/delete")
    public ResponseEntity<?> deleteWarranty(@RequestBody WarrantyDTO warrantyDTO){
        return new ResponseEntity<>(iWarrantyService.deleteWarranty(warrantyDTO), HttpStatus.CREATED);
    }
    @GetMapping("/get")
    public ResponseEntity<?> getWarranties(){
        return new ResponseEntity<>(iWarrantyService.getWarranties(), HttpStatus.CREATED);
    }

    @PostMapping("/getByProductId/{id}")
    public ResponseEntity<?> getWarranties(@PathVariable Long id){
        return new ResponseEntity<>(iWarrantyService.getWarrantyByProduct(id), HttpStatus.CREATED);
    }

}
