package com.example.server.Controller;

import com.example.server.Pojo.Order;
import com.example.server.Services.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "api/order")
public class OrderController {

    @Autowired
    private IOrderService orderService;

    @PostMapping("/save")
    public ResponseEntity<?> save(@RequestBody Order order){
        return new ResponseEntity<>(orderService.saveOrder(order), HttpStatus.CREATED);
    }
}
