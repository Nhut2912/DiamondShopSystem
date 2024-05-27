package com.example.server.Controller;


import com.example.server.Pojo.Account;
import com.example.server.Repository.IAccountRepository;
import com.example.server.Requests.RegistrationRequest;
import com.example.server.Services.IRegistrationServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/*
 * author : Tran Viet Hoang
 * date : 23/5/2024
 *
 *
 */
@RestController
@RequestMapping("api/account")
public class AccountController {
    @Autowired
    private IAccountRepository accountRepository;

    @Autowired
    private IRegistrationServices userService;

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegistrationRequest registerRequest) {
        return userService.register(registerRequest);

    }

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody Account account) {
        List<Account> accounts = accountRepository.findByEmail(account.getEmail(),account.getPhone());
        return new ResponseEntity(accounts, HttpStatus.OK);
    }
}
