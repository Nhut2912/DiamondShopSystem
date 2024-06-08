package com.example.server.Repository;

import com.example.server.Pojo.PaymentMethod;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IPaymentMethodRepository extends CrudRepository<PaymentMethod, Long> {
}
