package com.example.server.Controller;

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

    @Autowired
    private IPaymentService iPaymentService;

    @Autowired
    private IPaymentMethodService iPaymentMethodService;

    @PostMapping("/buy")
    public ResponseEntity<?> buyProduct(@RequestBody OrderDTO orderDto) {
        if (iAccountService.isAccountExist(orderDto.getAccountDTO().getId())) {
            try{

                if(iAccountService.updateNewestInfoForAccount(orderDto.getAccountDTO())){
                    orderDto.getOrderDetailDTOS().forEach(orderDetail -> iProductService.getProductToSetStatus(orderDetail.getProductID()));
                        Order order = iorderService.saveOrder(orderDto);
                    if(order != null){
                        iorderDetailService.saveOrderDetail(orderDto, order);
                        iPaymentService.createPayment(orderDto, order);
                    }
                }
                return new ResponseEntity<>(true, HttpStatus.OK);
            }catch (Exception ex){
                return new ResponseEntity<>(false, HttpStatus.OK);
            }
        }
        return new ResponseEntity<>(false, HttpStatus.OK);

    }
    @PostMapping("/updatePayment")
    public boolean updatePayment(@RequestBody PaymentDTO paymentDTO, @RequestParam Long orderId){
        try {

            Payment newPayment = new Payment();

            Order order = iorderService.getOrderById(orderId);
            newPayment.setOrder(order);
            newPayment.setAmount(paymentDTO.getAmount());
            newPayment.setPayTime(paymentDTO.getPayTime());
            if(paymentDTO.getPaymentMethodDTO().getMethod().equals("BANKTRANSFER")){
                newPayment.setImage(paymentDTO.getImage());
            }else newPayment.setTransactionCode(paymentDTO.getTransactionCode());
            newPayment.setPaymentMethod(
                    iPaymentMethodService.getPaymentMethod(paymentDTO.getPaymentMethodDTO().getMethod())

            );
            iPaymentService.updatePayment(newPayment);
            return true;
        }catch(Exception ex){
            System.out.println(ex.getMessage());
            return false;
        }
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
    @GetMapping("/statisticOrderByDay/{day}")
    public ResponseEntity<List<Order>> statisticOrderByDay(@PathVariable int day){
        return new ResponseEntity<>(iorderService.getOrdersByDay(day), HttpStatus.OK);
    }


}
