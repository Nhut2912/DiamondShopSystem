package com.example.server.Repository;

import com.example.server.Pojo.OrderDetail;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IOrderDetailRepository extends CrudRepository<OrderDetail, Long> {
}
