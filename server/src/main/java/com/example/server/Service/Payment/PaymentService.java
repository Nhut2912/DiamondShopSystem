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
import java.util.List;

@Service
public class PaymentService implements IPaymentService{
    @Autowired
    IPaymentRepository iPaymentRepository;
    @Autowired
    IPaymentMethodService iPaymentMethodService;
    public boolean createPayment(OrderDTO orderDTO, Order order){
        try{
                Payment payment = new Payment();
                System.out.println();
                payment.setOrder(order);
                if(orderDTO.getPaymentDTOS().getPaymentMethodDTO().getMethod().equals("BANKTRANSFER")){
                    payment.setImage(orderDTO.getPaymentDTOS().getImage());
                }else payment.setTransactionCode(orderDTO.getPaymentDTOS().getTransactionCode());


                payment.setPaymentMethod(
                        iPaymentMethodService.getPaymentMethod(orderDTO.getPaymentDTOS().getPaymentMethodDTO().getMethod())

                );



                payment.setAmount(orderDTO.getPaymentDTOS().getAmount());
                payment.setPayTime(orderDTO.getPaymentDTOS().getPayTime());

                System.out.println(payment);
                iPaymentRepository.save(payment);
            return true;
        }catch(Exception ex){
            System.out.println(ex.getMessage());
            return false;
        }
    }

    @Override
    public List<Payment> getPaymentByOrderId(Long id) {
        return iPaymentRepository.getPaymentsByOrder_Id(id);
    }

    public Payment getPaymentByOrderIdToUpdatePayment(Long id){return iPaymentRepository.getPaymentsByOrder_Id(id.toString());}

    public boolean updatePayment(Payment payment){
        try {
            iPaymentRepository.save(payment);
            return true;
        }catch(Exception ex){
            System.out.println(ex.getMessage());
            return false;
        }

    }
}
