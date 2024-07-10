package com.example.server.Repository;

import com.example.server.Pojo.Order;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface IOrderRepository extends CrudRepository<Order, Long> {
     List<Order> findAllByOrderStatus(String orderStatus);

     @Query("SELECT o FROM Order o WHERE o.account.id =:account_id ")
     List<Order> getOrdersByAccount_Id(@Param("account_id") Long id);

     @Query("Select o from Order o where MONTH(o.date) = :month and YEAR(o.date) = :year")
     List<Order> getOrderByMonthAndYear(@Param("month") int month, @Param("year") int year);

     @Query("Select o from Order o where DAY(o.date) = :day")
     List<Order> getOrderByDay(@Param("day") int day);

     @Query(value = "SELECT o FROM Order  o WHERE o.date >= :startDate AND o.date <= :endDate")
     List<Order> getOrderByStartDateAndEndDate(@Param("startDate")Date startDate, @Param("endDate")Date endDate);
}
