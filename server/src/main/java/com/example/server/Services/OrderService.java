package com.example.server.Services;

import com.example.server.Pojo.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderService implements IOrderService{

    @Autowired
    private IOrderService orderService;

    @Override
    public boolean saveOrder(Order order) {
        try{
            orderService.saveOrder(order);
            return true;
        }catch (Exception e){
            System.out.println(e.getMessage());
            return false;
        }
    }
}
