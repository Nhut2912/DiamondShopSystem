package com.example.server.Repository;

import com.example.server.Pojo.Account;
import com.example.server.Pojo.Order;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface IAccountRepository extends CrudRepository<Account, Long> {
    public Optional<Account> findByNumberPhone(String phone);
    public Optional<Account> findByEmail(String email);
    public Optional<Account> findByNameAndPassword(String name, String password);
    List<Account> findByActive(boolean active);
    @Query(value = "SELECT * FROM Account WHERE CONVERT(date, date_add) BETWEEN :startDate AND :endDate", nativeQuery = true)
    List<Account> getAccountByStartDateAndEndDate(@Param("startDate") Date startDate, @Param("endDate")Date endDate);
}

