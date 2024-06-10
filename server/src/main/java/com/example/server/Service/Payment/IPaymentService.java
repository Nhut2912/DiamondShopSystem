package com.example.server.Service.Payment;

import com.example.server.Model.OrderDTO;
import com.example.server.Pojo.Order;
import com.example.server.Pojo.Payment;

import java.util.List;

public interface IPaymentService {
    public boolean createPayment(OrderDTO orderDTO, Order order);

    public List<Payment> getPaymentByOrderId(Long id);
    public Payment getPaymentByOrderIdToUpdatePayment(Long id);
    public boolean updatePayment(Payment payment);
}
