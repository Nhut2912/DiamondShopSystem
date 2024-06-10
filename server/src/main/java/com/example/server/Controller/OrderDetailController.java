package com.example.server.Controller;

import com.example.server.Pojo.OrderDetail;
import com.example.server.Service.OrderDetail.IOrderDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/order_detail")
@CrossOrigin(origins = "http://localhost:3000")
public class OrderDetailController {

    @Autowired
    private IOrderDetailService orderDetailService;

    @GetMapping("")
    public ResponseEntity<?> getOrderDetailList(@RequestParam Long order_id){
            List<OrderDetail> orderDetails = orderDetailService.getOrderDetailByOrderID(order_id);
            if(orderDetails != null){
                return  ResponseEntity.ok(orderDetails);
            }else{
                return ResponseEntity.ofNullable(null);
            }
    }


}
