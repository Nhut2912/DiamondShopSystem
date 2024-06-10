package com.example.server.Repository;

import com.example.server.Pojo.OrderDetail;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IOrderDetailRepository extends CrudRepository<OrderDetail, Long> {

    public List<OrderDetail> getOrderDetailsByOrder_Id(Long id);
}
