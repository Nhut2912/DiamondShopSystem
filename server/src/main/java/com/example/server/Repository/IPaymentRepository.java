package com.example.server.Repository;

import com.example.server.Pojo.Payment;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IPaymentRepository extends CrudRepository<Payment, Long> {

    public List<Payment> getPaymentsByOrder_Id(Long id);
    public Payment getPaymentByOrder_Id(Long order_id);
}
