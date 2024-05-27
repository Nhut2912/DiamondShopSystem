package com.example.server.Controller;


import com.example.server.Pojo.Diamond;
import com.example.server.Services.DiamondServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "api/diamond")
@CrossOrigin(origins = "http://localhost:3000")
public class DiamondController {

 @Autowired
    private DiamondServices diamondServices;

    @RequestMapping(value = "/save",method = RequestMethod.POST)
    public String saveDiamond(@ModelAttribute("diamond")Diamond diamond){
        diamondServices.save(diamond);
        return "redicrect:/";
    }

}
