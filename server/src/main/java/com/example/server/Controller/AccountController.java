package com.example.server.Controller;

import com.example.server.Model.AccountDTO;
import com.example.server.Pojo.Account;
import com.example.server.Service.Account.IAccountService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpCookie;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping(path = "api/account")
@CrossOrigin(origins = "http://localhost:3000")
public class AccountController {

    @Autowired
    private IAccountService accountService;

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody AccountDTO accountDTO) {
        return new ResponseEntity<>(accountService.registerAccount(accountDTO), HttpStatus.ACCEPTED);
    }

    @PostMapping("/verifyOtp")
    public ResponseEntity<?>verifyOtp(@RequestParam String otp, @RequestBody AccountDTO accountDTO) {
        return new ResponseEntity<>(accountService.verifyOtp(otp,accountDTO), HttpStatus.OK);
    }

    @GetMapping
    public List<AccountDTO> getAllActiveAccounts() {
        return accountService.findAllActive();
    }

    @GetMapping("/AccountEmail")
    public ResponseEntity<AccountDTO> getAccountByEmail(@RequestParam(value = "email") String email) {
        Optional<AccountDTO> account = accountService.findByEmail(email);
        return account.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    

    @PostMapping("/InactiveAccount")
    public ResponseEntity<Boolean> deactivateAccount(@RequestParam(value = "email") String email) {
        boolean success = accountService.deactivateByEmail(email);
        if (success) {
            return ResponseEntity.ok(true);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(false);
        }
    }

}
