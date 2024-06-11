package com.example.server.Repository;

import com.example.server.Pojo.Order;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IOrderRepository extends CrudRepository<Order, Long> {
     List<Order> findAllByOrderStatus(String orderStatus);

     @Query("SELECT o FROM Order o WHERE o.account.id =:account_id ")
     List<Order> getOrdersByAccount_Id(@Param("account_id") Long id);


}
