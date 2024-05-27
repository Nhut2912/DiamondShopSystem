package com.example.server.Controller;


import com.example.server.Pojo.Account;
import com.example.server.Repository.IAccountRepository;
import com.example.server.Requests.RegistrationRequest;
import com.example.server.Services.IRegistrationServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
        Account accounts = accountRepository.findAllByEmailAndPhone(account.getEmail(),account.getPhone());
        return new ResponseEntity(accounts, HttpStatus.OK);
    }

    @GetMapping ("/ForgetByEmail")
    public ResponseEntity<?> findPasswordByEmail(@RequestBody String email) {
        Account accounts = accountRepository.findByEmail(email);
        return new ResponseEntity (accounts.getPassword(), HttpStatus.OK);
    }

    @GetMapping ("/ForgetByPhone")
    public ResponseEntity<?> findPasswordByPhone(@RequestBody String phone) {
        Account accounts = accountRepository.findByPhone(phone);
        return new ResponseEntity (accounts.getPhone(), HttpStatus.OK);
    }

}
