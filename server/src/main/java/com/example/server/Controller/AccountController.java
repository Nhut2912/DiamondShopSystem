package com.example.server.Controller;

import com.example.server.Config.HostFrontEnd;
import com.example.server.Model.AccountDTO;
import com.example.server.Pojo.Account;
import com.example.server.Service.Account.IAccountService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpCookie;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping(path = "api/account")
@CrossOrigin(origins = HostFrontEnd.hostFrontEnd)
public class AccountController {

    @Autowired
    private IAccountService accountService;

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody AccountDTO accountDTO) {
        return new ResponseEntity<>(accountService.registerAccount(accountDTO), HttpStatus.ACCEPTED);
    }

    @PostMapping("/checkUserNameAndPassword")
    public ResponseEntity<?> checkAdmin(@RequestBody AccountDTO accountDTO) {
        return new ResponseEntity<>(accountService.isStaffOrAdmin(accountDTO), HttpStatus.OK);
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
    public ResponseEntity<?> getAccountByEmail(@RequestParam(value = "email") String email) {
        Optional<AccountDTO> account = accountService.findByEmail(email);
        if(account.isPresent() && account.get().getEmail().equals(email)){
            return ResponseEntity.ok(account);
        }else return ResponseEntity.ok(false);
    }


    @PutMapping("/UpdateAccount")
    public ResponseEntity<AccountDTO> updateAccount(@PathVariable(value = "id") Long accountId,
                                                    @Valid @RequestBody AccountDTO accountDetails) {
        AccountDTO updatedAccount = accountService.updateAccount(accountId, accountDetails);
        if (updatedAccount != null) {
            return ResponseEntity.ok(updatedAccount);
        } else {
            return ResponseEntity.notFound().build();
        }
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

    @PostMapping("/create")
    public ResponseEntity<?> createAccount(@RequestBody AccountDTO accountDTO) {
        return new ResponseEntity<>(accountService.createAccount(accountDTO), HttpStatus.OK);
    }

    @GetMapping("/statisticNewCustomerByWeek")
    public ResponseEntity<?> statisticToTalPriceByWeek() throws ParseException {

        return new ResponseEntity<>(accountService.getNewAccountStatisticByWeek(), HttpStatus.OK);
    }


    @GetMapping("/statisticNewCustomerByMonth")
    public ResponseEntity<?> statisticToTalPriceByMonth() throws ParseException {

        return new ResponseEntity<>(accountService.getNewAccountStatisticByMonth(), HttpStatus.OK);
    }
    @GetMapping("/getSumNewAccountStatisticByWeek")
    public ResponseEntity<?> getSumNewAccountStatisticByWeek() throws ParseException {

        return new ResponseEntity<>(accountService.getSumNewAccountStatisticByWeek(), HttpStatus.OK);
    }

    @GetMapping("/getSumNewAccountStatisticByMonth")
    public ResponseEntity<?> getSumNewAccountStatisticByMonth() throws ParseException {

        return new ResponseEntity<>(accountService.getSumNewAccountStatisticByMonth(), HttpStatus.OK);
    }

    @GetMapping("/getSumNewAccountStatisticByDay")
    public ResponseEntity<?> getSumNewAccountStatisticByDay() throws ParseException {
        return new ResponseEntity<>(accountService.getSumNewAccountStatisticByDay(), HttpStatus.OK);
    }

}
