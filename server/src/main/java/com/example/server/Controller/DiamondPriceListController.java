package com.example.server.Controller;

import com.example.server.Pojo.DiamondPriceList;
import com.example.server.Service.DiamondPriceList.IDiamondPriceListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "api/diamondpricelist")
@CrossOrigin(origins = "http://localhost:3000")
public class DiamondPriceListController {

    @Autowired
    private IDiamondPriceListService iDiamondPriceListService;

    @GetMapping("/getAll")
    public ResponseEntity<?> getDiamonds(){
        return ResponseEntity.ok(iDiamondPriceListService.getDiamondsPriceList());
    }


}
