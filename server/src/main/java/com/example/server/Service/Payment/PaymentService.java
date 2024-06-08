package com.example.server.Service.Payment;

import com.example.server.Model.OrderDTO;
import com.example.server.Pojo.Order;
import com.example.server.Pojo.Payment;
import com.example.server.Repository.IPaymentRepository;
import com.example.server.Service.PaymenMethod.IPaymentMethodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class PaymentService implements IPaymentService{
    @Autowired
    IPaymentRepository iPaymentRepository;
    @Autowired
    IPaymentMethodService iPaymentMethodService;
    public boolean createPayment(OrderDTO orderDTO, Order order){
        String payTime = new SimpleDateFormat("yyyy-MM-dd").format(new Date());

        try{
                Payment payment = new Payment();
                payment.setOrder(order);
                if(orderDTO.getPaymentDTOS().getPaymentMethodDTO().getMethod().equals("BANKTRANSFER")){
                    payment.setImage(orderDTO.getPaymentDTOS().getImage());
                }else payment.setTransactionCode(orderDTO.getPaymentDTOS().getTransactionCode());
                payment.setPaymentMethod(iPaymentMethodService.getPaymentMethod(orderDTO.getPaymentDTOS().getPaymentMethodDTO().getId()));
                payment.setAmount(orderDTO.getPaymentDTOS().getAmount());
                payment.setPayTime(java.sql.Date.valueOf(payTime));
                iPaymentRepository.save(payment);
            return true;
        }catch(Exception ex){
            System.out.println(ex.getMessage());
            return false;
        }
    }
}
