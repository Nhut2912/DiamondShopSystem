package com.example.server.Repository;

import com.example.server.Pojo.PaymentMethod;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface IPaymentMethodRepository extends CrudRepository<PaymentMethod, Long> {

    public PaymentMethod getPaymentMethodByMethod(String method);
}
