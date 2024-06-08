package com.example.server.Service.Payment;

import com.example.server.Model.OrderDTO;
import com.example.server.Pojo.Order;

public interface IPaymentService {
    public boolean createPayment(OrderDTO orderDTO, Order order);

}
