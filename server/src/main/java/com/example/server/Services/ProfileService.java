package com.example.server.Services;

import com.example.server.Pojo.Account;
import com.example.server.Repository.IAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProfileService {
@Autowired
    private IAccountRepository accountRepository;

public void saveCustomer(String ipAddress){
    Account account = new Account();
    account.setEmail(ipAddress);
    accountRepository.save(account);
}

public Account getProfileById(Long id){
 return accountRepository.findById(id).orElse(null);
}

}
