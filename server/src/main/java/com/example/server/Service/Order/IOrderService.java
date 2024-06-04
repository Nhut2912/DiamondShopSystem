package com.example.server.Service.Order;

import com.example.server.Pojo.Order;

import java.util.List;

public interface IOrderService {
    public List<Order> getAllOrders();
    public List<Order> getOrderByStatus(String orderStatus);
    public Order updateOrderStatus();
}
