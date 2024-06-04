package com.example.server.Repository;

import com.example.server.Pojo.Account;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IAccountRepository extends CrudRepository<Account, Long> {
    public Optional<Account> findByNumberPhone(String phone);
}
