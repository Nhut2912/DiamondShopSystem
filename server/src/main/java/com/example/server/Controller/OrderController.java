package com.example.server.Controller;

import com.example.server.Pojo.Account;
import com.example.server.Pojo.Product;
import com.example.server.Service.Account.IAccountService;
import com.example.server.Service.Product.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping(path = "api/order")
@CrossOrigin(origins = "http://localhost:3000")
public class OrderController {
    @Autowired
    IAccountService iAccountService;
    @Autowired
    IProductService iProductService;
    @PostMapping("/buy")
    public ResponseEntity<?> buyProduct(@RequestBody Account account){
        if(iAccountService.isAccountExist(account.getId())){
            try {
                List<Product> product = new ArrayList<>();
                account.getOrders().forEach(orderDetail -> orderDetail.getOrderDetails().forEach((findProduct) ->
                        product.add(iProductService.getProductToSetStatus(findProduct.getProduct().getId()))));
                return new ResponseEntity<>(product, HttpStatus.ACCEPTED);
            }catch (Exception ex){
                System.out.println(ex.getMessage());
                return new ResponseEntity<>(ex.getMessage(), HttpStatus.ACCEPTED);
            }
        }
        return new ResponseEntity<>("Account not exist", HttpStatus.ACCEPTED);
    }
}
