package com.example.server.Controller;

import com.example.server.Model.DiamondPriceListDTO;
import com.example.server.Model.MaterialPriceListDTO;
import com.example.server.Service.MaterialPriceList.IMaterialPriceListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "api/materialpricelist")
@CrossOrigin(origins = "http://localhost:3000")
public class MaterialPriceListController {
    @Autowired
    IMaterialPriceListService iMaterialPriceListService;

    @PostMapping("/updateMaterialPriceList")
    public boolean updateDiamondPriceList(@RequestBody MaterialPriceListDTO materialPriceListDTO){
        return iMaterialPriceListService.updateMaterialPrice(materialPriceListDTO);
    }
}
