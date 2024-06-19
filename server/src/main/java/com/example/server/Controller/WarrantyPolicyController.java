package com.example.server.Controller;


import com.example.server.Pojo.WarrantyPolicy;
import com.example.server.Service.WarrantyPolicy.IWarrantyPolicyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "api/warrantypolicy")
@CrossOrigin(origins = "http://localhost:3000")
public class WarrantyPolicyController {
    @Autowired
    IWarrantyPolicyService iWarrantyPolicyService;

    @PostMapping("/create")
    public ResponseEntity<?> createWarrantyPolicy(@RequestBody WarrantyPolicy warrantyPolicy){
        return new ResponseEntity<>(iWarrantyPolicyService.createWarrantyPolicy(warrantyPolicy), HttpStatus.CREATED);
    }

    @PostMapping("/update")
    public ResponseEntity<?> updateWarrantyPolicy(@RequestBody WarrantyPolicy warrantyPolicy){
        return new ResponseEntity<>(iWarrantyPolicyService.updateWarrantyPolicy(warrantyPolicy), HttpStatus.CREATED);
    }
    @PostMapping("/delete")
    public ResponseEntity<?> deleteWarrantyPolicy(@RequestBody WarrantyPolicy warrantyPolicy){
        return new ResponseEntity<>(iWarrantyPolicyService.deleteWarrantyPolicy(warrantyPolicy), HttpStatus.CREATED);
    }

    @GetMapping("/get")
    public ResponseEntity<?> GetWarrantyPolicies(){
        return new ResponseEntity<>(iWarrantyPolicyService.getWarrantyPolicies(), HttpStatus.CREATED);
    }

}
