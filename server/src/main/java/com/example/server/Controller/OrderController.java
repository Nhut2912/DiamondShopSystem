package com.example.server.Controller;

import com.example.server.Pojo.Account;
import com.example.server.Pojo.Order;
import com.example.server.Pojo.Product;

import com.example.server.Service.Account.IAccountService;
import com.example.server.Service.Order.OrderService;
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
    IAccountService iAccountService;
    @Autowired
    IProductService iProductService;
    @PostMapping("/buy")
    public ResponseEntity<?> buyProduct(@RequestBody Account account) {
        if (iAccountService.isAccountExist(account.getId())) {
            try {
                List<Product> product = new ArrayList<>();
                account.getOrders().forEach(orderDetail -> orderDetail.getOrderDetails().forEach((findProduct) ->
                        product.add(iProductService.getProductToSetStatus(findProduct.getProduct().getId()))));
                return new ResponseEntity<>(product, HttpStatus.ACCEPTED);
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
                return new ResponseEntity<>(ex.getMessage(), HttpStatus.ACCEPTED);
            }
        }
        return new ResponseEntity<>("Account not exist", HttpStatus.ACCEPTED);
    }

    @Autowired
    private OrderService orderService;


    @GetMapping("/getAll")
    public List<Order> getAll(){
        return orderService.getAllOrders();
    }

    @GetMapping("/getOrderByStatus")
    public List<Order> getOrderByStatus(String orderStatus){
        return orderService.getOrderByStatus(orderStatus);
    }

    @PostMapping("/Pending")
    public ResponseEntity<String> pendingOrder(@PathVariable Long id){
        boolean updated = orderService.updateOrderStatus(id, "Pending");
        if(updated){
            return ResponseEntity.ok("Order status updated to 'Pending Orders'.");
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Order not found.");
        }
    }


    @PostMapping("/Preparing")
    public ResponseEntity<String> prepareOrder(@PathVariable Long id){
        boolean updated = orderService.updateOrderStatus(id, "Preparing");
        if(updated){
            return ResponseEntity.ok("Order status updated to 'Preparing Orders'.");
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Order not found.");
        }
    }


    @PostMapping("/Delivering")
    public ResponseEntity<String> deliveringOrder(@PathVariable Long id){
        boolean updated = orderService.updateOrderStatus(id, "Delivering");
        if(updated){
            return ResponseEntity.ok("Order status updated to 'Delivering Orders'.");
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Order not found.");
        }
    }

    @PostMapping("/Completed")
    public ResponseEntity<String> completedOrder(@PathVariable Long id){
        boolean updated = orderService.updateOrderStatus(id, "Completed !!!");
        if(updated){
            return ResponseEntity.ok("Order status updated to 'Completed Orders'.");
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Order not found.");
        }
    }

    @PostMapping("/Canceled")
    public ResponseEntity<String> canceledOrder(@PathVariable Long id){
        boolean updated = orderService.updateOrderStatus(id, "Canceled");
        if(updated){
            return ResponseEntity.ok("Order status updated to 'Canceled Orders'.");
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Order not found.");
        }
    }

}
