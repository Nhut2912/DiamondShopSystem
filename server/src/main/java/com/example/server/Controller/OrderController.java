package com.example.server.Controller;

import com.example.server.Pojo.Account;
import com.example.server.Pojo.Order;
import com.example.server.Pojo.Product;
import com.example.server.Service.Order.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/order")
@CrossOrigin(origins = "http://localhost:3000")
public class OrderController {

    @Autowired
    private IOrderService iOrderService;

    @PostMapping("/buy")
    public ResponseEntity<?> buyProduct(@RequestBody Account account){


        return null;
    }

    @GetMapping("/getAll")
    public List<Order> getAll(){
        return iOrderService.getAllOrders();
    }

    @PostMapping("/getOrderByStatus")
    public List<Order> getByStatus(@RequestParam String orderStatus){
        return iOrderService.getOrderByStatus(orderStatus);
    }
}
