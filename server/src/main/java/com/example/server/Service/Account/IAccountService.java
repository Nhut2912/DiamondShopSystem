package com.example.server.Service.Account;

import com.example.server.Model.AccountDTO;
import com.example.server.Pojo.Account;

import java.util.Optional;

public interface IAccountService {
    public boolean isAccountExist(Long id);
    public boolean isSamePhone(String phone);
    public boolean updateNewestInfoForAccount(AccountDTO accountDTO);
    boolean registerAccount(AccountDTO accountDTO);
    String loginAccount(AccountDTO accountDTO);
    String generateOtp();
    void sendEmailVerification(String email, String otp);
    AccountDTO verifyOtp(String otp, AccountDTO accountDTO);
}
