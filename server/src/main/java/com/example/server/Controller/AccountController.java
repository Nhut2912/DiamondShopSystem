package com.example.server.Controller;

import com.example.server.Model.AccountDTO;
import com.example.server.Pojo.Account;
import com.example.server.Service.Account.IAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping(path = "api/account")
public class AccountController {

    @Autowired
    private IAccountService accountService;

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody AccountDTO accountDTO) {
        return new ResponseEntity<>(accountService.registerAccount(accountDTO), HttpStatus.ACCEPTED);
    }
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AccountDTO accountDTO){
        return new ResponseEntity<>(accountService.loginAccount(accountDTO), HttpStatus.ACCEPTED);
    }

    @PostMapping("/verifyOtp")
    public ResponseEntity<?>verifyOtp(@RequestParam String otp) {
        return new ResponseEntity<>(accountService.verifyOtp(otp), HttpStatus.OK);
    }

}
