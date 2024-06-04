package com.example.server.Service.Order;

import com.example.server.Pojo.Order;
import com.example.server.Repository.IOrderRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderService implements  IOrderService{

    @Autowired
    private IOrderRepository iOrderRepository;


    @Override
    public List<Order> getAllOrders() {
        return (List<Order>) iOrderRepository.findAll();
    }

    @Override
    public List<Order> getOrderByStatus(String orderStatus) {

        return iOrderRepository.findAllByOrderStatus(orderStatus);
    }

    @Override
    public boolean updateOrderStatus(Long id, String status) {
        Optional<Order> optionalOrder = iOrderRepository.findById(id);
        if(optionalOrder.isPresent()){
            Order order = optionalOrder.get();
            order.setOrderStatus(status);
            iOrderRepository.save(order);
            return true;
        }else {
            return false;
        }
    }


}
