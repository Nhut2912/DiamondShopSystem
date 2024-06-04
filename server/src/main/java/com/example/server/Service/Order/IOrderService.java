package com.example.server.Service.Order;

import com.example.server.Pojo.Order;

public interface IOrderService {
    public Order getAllOrders();
    public Order getOrderByStatus();
    public Order updateOrderStatus();
}
