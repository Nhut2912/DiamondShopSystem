package com.example.server.Service.Account;

import com.example.server.Pojo.Account;
import com.example.server.Repository.IAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AccountService implements IAccountService{
    @Autowired
    IAccountRepository iAccountRepository;
    @Override
    public boolean isAccountExist(Long id) {
        Optional<Account> accountNeedToCheck = iAccountRepository.findById(id);
        return accountNeedToCheck.isPresent();
    }
}
