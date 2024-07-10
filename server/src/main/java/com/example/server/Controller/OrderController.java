package com.example.server.Controller;

import com.example.server.Config.HostFrontEnd;
import com.example.server.Model.AccountDTO;
import com.example.server.Model.OrderDTO;
import com.example.server.Model.PaymentDTO;
import com.example.server.Pojo.Account;
import com.example.server.Pojo.Order;
import com.example.server.Pojo.Payment;
import com.example.server.Pojo.Product;

import com.example.server.Service.Account.IAccountService;
import com.example.server.Service.Order.IOrderService;
import com.example.server.Service.Order.OrderService;
import com.example.server.Service.OrderDetail.IOrderDetailService;
import com.example.server.Service.PaymenMethod.IPaymentMethodService;
import com.example.server.Service.Payment.IPaymentService;
import com.example.server.Service.Product.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping(path = "api/order")
@CrossOrigin(origins = HostFrontEnd.hostFrontEnd)
public class OrderController {

    @Autowired
    private IAccountService iAccountService;

    @Autowired
    private IProductService iProductService;

    @Autowired
    private IOrderService iorderService;

    @Autowired
    private IOrderDetailService iorderDetailService;

    @Autowired
    private IPaymentService iPaymentService;

    @Autowired
    private IPaymentMethodService iPaymentMethodService;

    @PostMapping("/buy")
    public ResponseEntity<?> buyProduct(@RequestBody OrderDTO orderDto) {
        return new ResponseEntity<>(iorderService.buyProduct(orderDto), HttpStatus.OK);

    }
    @PostMapping("/updatePayment")
    public ResponseEntity<?> updatePayment(@RequestBody PaymentDTO paymentDTO, @RequestParam Long orderId){
        return new ResponseEntity<>(iorderService.updatePayment(paymentDTO, orderId), HttpStatus.OK);
    }


    @GetMapping("/getAll")
    public List<Order> getAll(){
        return iorderService.getAllOrders();
    }

    @GetMapping("/getOrderByStatus")
    public List<Order> getOrderByStatus(String orderStatus){
        return iorderService.getOrderByStatus(orderStatus);
    }

    @PostMapping("/Pending/{id}")
    public ResponseEntity<String> pendingOrder(@PathVariable Long id){
        boolean updated = iorderService.updateOrderStatus(id, "PENDING");
        if(updated){
            return ResponseEntity.ok("true");
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("false");
        }
    }


    @PostMapping("/Preparing/{id}")
    public ResponseEntity<String> prepareOrder(@PathVariable Long id){
        boolean updated = iorderService.updateOrderStatus(id, "PREPARING");
        if(updated){
            return ResponseEntity.ok("true");
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("false");
        }
    }


    @PostMapping("/Prepared/{id}")
    public ResponseEntity<String> preparedOrder(@PathVariable Long id){
        boolean updated = iorderService.updateOrderStatus(id, "PREPARED");
        if(updated){
            return ResponseEntity.ok("true");
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("false");
        }
    }



    @PostMapping("/Delivering/{id}")
    public ResponseEntity<String> deliveringOrder(@PathVariable Long id){
        boolean updated = iorderService.updateOrderStatus(id, "DELIVERING");
        if(updated){
            return ResponseEntity.ok("true");
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("false");
        }
    }

    @PostMapping("/Completed/{id}")
    public ResponseEntity<String> completedOrder(@PathVariable Long id){
        boolean updated = iorderService.updateOrderStatus(id, "COMPLETED");
        if(updated){
            return ResponseEntity.ok("true");
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("false");
        }
    }

    @PostMapping("/Canceled/{id}")
    public ResponseEntity<String> canceledOrder(@PathVariable Long id){
        boolean updated = iorderService.updateOrderStatus(id, "CANCELLED");
        if(updated){
            return ResponseEntity.ok("true");
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("false");
        }
    }


    @GetMapping("/account/{id}")
    public ResponseEntity<List<Order>> getOrderByAccount(@PathVariable Long id){
        List<Order> orders = iorderService.getOrdersByAccountID(id);
        if(orders != null){
            return ResponseEntity.status(200).body(orders);
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @GetMapping("/statisticOrderByWeek")
    public ResponseEntity<?> statisticOrderByWeek() throws ParseException {

        return new ResponseEntity<>(iorderService.getStatisticByWeek(), HttpStatus.OK);
    }
    @GetMapping("/statisticOrderByMonth")
    public ResponseEntity<?> statisticOrderByMonth() throws ParseException {

        return new ResponseEntity<>(iorderService.getStatisticByMonth(), HttpStatus.OK);
    }

    @GetMapping("/statisticToTalPriceByWeek")
    public ResponseEntity<?> statisticToTalPriceByWeek() throws ParseException {

        return new ResponseEntity<>(iorderService.getTotalPriceStatisticByWeek(), HttpStatus.OK);
    }
    @GetMapping("/statisticToTalPriceByMonth")
    public ResponseEntity<?> statisticToTalPriceByMonth() throws ParseException {

        return new ResponseEntity<>(iorderService.getTotalPriceStatisticByMonth(), HttpStatus.OK);
    }
    @GetMapping("/getSumTotalPriceStatisticByWeek")
    public ResponseEntity<?> getSumTotalPriceStatisticByWeek() throws ParseException {

        return new ResponseEntity<>(iorderService.getSumTotalPriceStatisticByWeek(), HttpStatus.OK);
    }
    @GetMapping("/getSumTotalPriceStatisticByMonth")
    public ResponseEntity<?> getSumTotalPriceStatisticByMonth() throws ParseException {

        return new ResponseEntity<>(iorderService.getSumTotalPriceStatisticByMonth(), HttpStatus.OK);
    }

    @GetMapping("/getSumTotalPriceStatisticByDay")
    public ResponseEntity<?> getSumTotalPriceStatisticByDay() throws ParseException {

        return new ResponseEntity<>(iorderService.getSumTotalPriceStatisticByDay(), HttpStatus.OK);
    }

    @GetMapping("/getTheSumOfOrderStatisticByWeek")
    public ResponseEntity<?> getTheSumOfOrderStatisticByWeek() throws ParseException {

        return new ResponseEntity<>(iorderService.getTheSumOfOrderStatisticByWeek(), HttpStatus.OK);
    }
    @GetMapping("/getTheSumOfOrderStatisticByMonth")
    public ResponseEntity<?> getTheSumOfOrderStatisticByMonth() throws ParseException {

        return new ResponseEntity<>(iorderService.getTheSumOfOrderStatisticByMonth(), HttpStatus.OK);
    }

    @GetMapping("/getTheSumOfOrderStatisticByDay")
    public ResponseEntity<?> getTheSumOfOrderStatisticByDay() throws ParseException {
        return new ResponseEntity<>(iorderService.getTheSumOfOrderStatisticByDay(), HttpStatus.OK);
    }

}
