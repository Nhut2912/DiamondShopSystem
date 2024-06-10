package com.example.server.Service.Order;

import com.example.server.Model.OrderDTO;
import com.example.server.Pojo.Order;
import com.example.server.Pojo.Product;

import java.util.List;

public interface IOrderService {
    public Order saveOrder(OrderDTO order);
    public List<Order> getAllOrders();
    public List<Order> getOrderByStatus(String orderStatus);
    public boolean updateOrderStatus(Long id, String status);
    public Order getOrderById(Long id);
    public List<Order> getOrdersByAccountID(Long id);
}
