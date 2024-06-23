package com.example.server.Service.Order;

import com.example.server.Model.OrderDTO;
import com.example.server.Model.PaymentDTO;
import com.example.server.Pojo.Order;
import com.example.server.Pojo.Product;
import org.springframework.web.bind.annotation.RequestParam;

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

    public Map<LocalDate, Integer> getOrdersByMonthAndYear(int month, int year);
    public Map<LocalDate, Long> getStatisticByWeek() throws ParseException;
    public Map<LocalDate, Long> getStatisticByMonth() throws ParseException;

    public Map<LocalDate, Double> getTotalPriceStatisticByWeek() throws ParseException;
    public Map<LocalDate, Double> getTotalPriceStatisticByMonth() throws ParseException;

    public double getSumTotalPriceStatisticByWeek() throws ParseException;
    public double getSumTotalPriceStatisticByMonth() throws ParseException;
    public Long getTheSumOfOrderStatisticByWeek() throws ParseException;
    public Long getTheSumOfOrderStatisticByMonth() throws ParseException;

    public boolean buyProduct(OrderDTO orderDto);
    public boolean updatePayment(PaymentDTO paymentDTO, Long orderId);
}
