package com.example.server.Controller;


import com.example.server.Pojo.Diamond;
import com.example.server.Service.Diamond.IDiamondService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/diamond")
@CrossOrigin(origins = "http://localhost:3000")
public class DiamondController {

    @Autowired
    private IDiamondService diamondService;

    @GetMapping("/product/{id}")
    public ResponseEntity<?> getDiamondsByProductId(@PathVariable Long id){
        List<Diamond> diamonds = diamondService.getDiamondByProductID(id);
        if(diamonds != null) return ResponseEntity.ok(diamonds);
        return ResponseEntity.ofNullable(null);
    }

}
