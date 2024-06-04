package com.example.server.Controller;

import com.example.server.Model.AccountDTO;
import com.example.server.Model.OrderDTO;
import com.example.server.Pojo.Account;
import com.example.server.Pojo.Order;
import com.example.server.Pojo.Product;

import com.example.server.Service.Account.IAccountService;
import com.example.server.Service.Order.IOrderService;
import com.example.server.Service.Order.OrderService;
import com.example.server.Service.OrderDetail.IOrderDetailService;
import com.example.server.Service.Product.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping(path = "api/order")
@CrossOrigin(origins = "http://localhost:3000")
public class OrderController {

    @Autowired
    private IAccountService iAccountService;
    @Autowired
    private IProductService iProductService;
    @Autowired
    private IOrderService iorderService;
    @Autowired
    private IOrderDetailService iorderDetailService;
    @PostMapping("/buy")

    public ResponseEntity<?> buyProduct(@RequestBody OrderDTO orderDto) {
        if (iAccountService.isAccountExist(orderDto.getAccountDTO().getId()) && iAccountService.isSamePhone(orderDto.getAccountDTO().getNumberPhone())) {
            try{
                
                if(iAccountService.updateNewestInfoForAccount(orderDto.getAccountDTO())){
                    orderDto.getOrderDetailDTOS().forEach(orderDetail -> iProductService.getProductToSetStatus(orderDetail.getProductID()));
                        Order order = iorderService.saveOrder(orderDto);

                    if(order != null){
                        iorderDetailService.saveOrderDetail(orderDto, order);

                    }
                }
                return new ResponseEntity<>(true, HttpStatus.OK);
            }catch (Exception ex){
                return new ResponseEntity<>(false, HttpStatus.OK);
            }
        }
        return new ResponseEntity<>(false, HttpStatus.OK);

    }







    @GetMapping("/getAll")
    public List<Order> getAll(){
        return iorderService.getAllOrders();
    }

    @GetMapping("/getOrderByStatus")
    public List<Order> getOrderByStatus(String orderStatus){
        return iorderService.getOrderByStatus(orderStatus);
    }

    @PostMapping("/Pending")
    public ResponseEntity<String> pendingOrder(@PathVariable Long id){
        boolean updated = iorderService.updateOrderStatus(id, "Pending");
        if(updated){
            return ResponseEntity.ok("Order status updated to 'Pending Orders'.");
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Order not found.");
        }
    }


    @PostMapping("/Preparing")
    public ResponseEntity<String> prepareOrder(@PathVariable Long id){
        boolean updated = iorderService.updateOrderStatus(id, "Preparing");
        if(updated){
            return ResponseEntity.ok("Order status updated to 'Preparing Orders'.");
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Order not found.");
        }
    }


    @PostMapping("/Delivering")
    public ResponseEntity<String> deliveringOrder(@PathVariable Long id){
        boolean updated = iorderService.updateOrderStatus(id, "Delivering");
        if(updated){
            return ResponseEntity.ok("Order status updated to 'Delivering Orders'.");
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Order not found.");
        }
    }

    @PostMapping("/Completed")
    public ResponseEntity<String> completedOrder(@PathVariable Long id){
        boolean updated = iorderService.updateOrderStatus(id, "Completed !!!");
        if(updated){
            return ResponseEntity.ok("Order status updated to 'Completed Orders'.");
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Order not found.");
        }
    }

    @PostMapping("/Canceled")
    public ResponseEntity<String> canceledOrder(@PathVariable Long id){
        boolean updated = iorderService.updateOrderStatus(id, "Canceled");
        if(updated){
            return ResponseEntity.ok("Order status updated to 'Canceled Orders'.");
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Order not found.");
        }
    }

}
