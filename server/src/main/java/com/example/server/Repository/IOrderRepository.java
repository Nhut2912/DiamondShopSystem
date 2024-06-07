package com.example.server.Repository;

import com.example.server.Pojo.Order;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IOrderRepository extends CrudRepository<Order, Long> {
     List<Order> findAllByOrderStatus(String orderStatus);
}
