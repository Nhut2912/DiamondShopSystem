package com.example.server.Service.PaymenMethod;

import com.example.server.Pojo.PaymentMethod;
import com.example.server.Repository.IPaymentMethodRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PaymentMethodService implements IPaymentMethodService{
    @Autowired
    IPaymentMethodRepository iPaymentMethodRepository;

    @Override
    public PaymentMethod getPaymentMethod(Long id) {
        Optional<PaymentMethod> paymentMethod = iPaymentMethodRepository.findById(id);
        return paymentMethod.orElse(null);
    }

    @Override
    public PaymentMethod getPaymentMethod(String name) {
        Optional<PaymentMethod> paymentMethod = Optional.ofNullable(iPaymentMethodRepository.getPaymentMethodByMethod(name));
        if(paymentMethod.isEmpty()){
            PaymentMethod paymentMethodNew = new PaymentMethod();
            paymentMethodNew.setMethod(name);
            iPaymentMethodRepository.save(paymentMethodNew);
            return iPaymentMethodRepository.getPaymentMethodByMethod(name);
        }else{
            return paymentMethod.orElse(null);
        }
    }
}
