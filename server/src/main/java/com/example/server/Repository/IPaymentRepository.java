package com.example.server.Repository;

import com.example.server.Pojo.Payment;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IPaymentRepository extends CrudRepository<Payment, Long> {

    public List<Payment> getPaymentsByOrder_Id(Long id);
<<<<<<< HEAD
    public Payment getPaymentByOrder_Id(Long order_id);
=======

>>>>>>> e074036b69d5bf41e2fb5fa1911130350670a651
}
