package com.example.server.Repository;

import com.example.server.Pojo.Account;
import com.example.server.Pojo.Order;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface IAccountRepository extends CrudRepository<Account, Long> {
    public Optional<Account> findByNumberPhone(String phone);
    public Optional<Account> findByEmail(String email);
    public Optional<Account> findByEmailAndPassword(String email, String password);
    List<Account> findByActive(boolean active);
    @Query("SELECT a  FROM Account a WHERE a.dateAdd BETWEEN :startDate AND :endDate")
    List<Account> getAccountByStartDateAndEndDate(@Param("startDate") LocalDateTime startDate, @Param("endDate")LocalDateTime endDate);
}

