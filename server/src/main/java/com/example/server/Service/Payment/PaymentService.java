package com.example.server.Service.Payment;

import com.example.server.Pojo.Order;
import com.example.server.Pojo.Payment;
import com.example.server.Repository.IPaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class PaymentService implements IPaymentService{
    @Autowired
    IPaymentRepository iPaymentRepository;
    public boolean createPayment(Order order){
        String payTime = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
        Payment payment = new Payment();


        return false;
    }
}
