package com.example.server.Service.Order;

import com.example.server.Model.OrderDTO;
import com.example.server.Pojo.Order;
import com.example.server.Pojo.Product;

import java.text.ParseException;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public interface IOrderService {
    public Order saveOrder(OrderDTO order);
    public List<Order> getAllOrders();
    public List<Order> getOrderByStatus(String orderStatus);
    public boolean updateOrderStatus(Long id, String status);
    public Order getOrderById(Long id);
    public List<Order> getOrdersByAccountID(Long id);
    public List<Order> getOrdersByDay(int id);
    public Map<LocalDate, Integer> getOrdersByMonthAndYear(int month, int year);
    public Map<LocalDate, Long> getStatisticByWeek() throws ParseException;
}
