package com.example.server.Controller;


import com.example.server.Pojo.Account;
import com.example.server.Repository.IAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/Account")
public class AccountController {
    @Autowired
    private IAccountRepository accountRepository;

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody Account account){
         List<Account> accounts = accountRepository.findByEmail(account.getEmail());
         return new ResponseEntity(accounts, HttpStatus.OK);
    }
}
