package com.example.server.Service.Order;

import com.example.server.Pojo.Order;
import com.example.server.Repository.IOrderRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class OrderService implements  IOrderService{
    private IOrderRepository iOrderRepository;

    @Override
    public Order getAllOrders() {
        return null;
    }

    @Override
    public Order getOrderByStatus() {
        return null;
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
