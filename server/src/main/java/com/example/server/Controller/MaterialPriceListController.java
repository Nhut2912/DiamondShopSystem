package com.example.server.Controller;

import com.example.server.Model.DiamondPriceListDTO;
import com.example.server.Model.MaterialPriceListDTO;
import com.example.server.Service.MaterialPriceList.IMaterialPriceListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "api/materialpricelist")
@CrossOrigin(origins = "http://localhost:3000")
public class MaterialPriceListController {
    @Autowired
    IMaterialPriceListService iMaterialPriceListService;

    @PostMapping("/updateMaterialPriceList")
    public ResponseEntity<?> updateDiamondPriceList(@RequestBody MaterialPriceListDTO materialPriceListDTO){
        return new ResponseEntity<>(iMaterialPriceListService.updateMaterialPrice(materialPriceListDTO), HttpStatus.OK);
    }

    @GetMapping("/getMaterialPriceLists")
    public ResponseEntity<?> getMaterialPriceLists(){
        return new ResponseEntity<>(iMaterialPriceListService.getMaterialPriceLists(), HttpStatus.OK);
    }

    @PostMapping("/getMaterialPriceListById/{Id}")
    public ResponseEntity<?> getDiamondPriceListById(@PathVariable("Id") Long id){
        return new ResponseEntity<>(iMaterialPriceListService.getMaterialPriceListById(id), HttpStatus.OK);
    }
}
