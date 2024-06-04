package com.example.server.Controller;

import com.example.server.Pojo.Account;
import com.example.server.Pojo.Product;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "api/order")
@CrossOrigin(origins = "http://localhost:3000")
public class OrderController {
    @PostMapping("/buy")
    public ResponseEntity<?> buyProduct(@RequestBody Account account){

        return null;
    }
}
