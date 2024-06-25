package com.example.server.Controller;


import com.example.server.Config.HostFrontEnd;
import com.example.server.Model.DiamondDTO;
import com.example.server.Pojo.Diamond;
import com.example.server.Service.Diamond.IDiamondService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/diamond")
@CrossOrigin(origins = HostFrontEnd.hostFrontEnd)
public class DiamondController {

    @Autowired
    private IDiamondService diamondService;

    @GetMapping("/product/{id}")
    public ResponseEntity<?> getDiamondsByProductId(@PathVariable Long id){
        List<DiamondDTO> diamonds = diamondService.getDiamondDetailByProductID(id);
        if(diamonds != null) return ResponseEntity.ok(diamonds);
        return ResponseEntity.ofNullable(null);
    }



}
