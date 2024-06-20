package com.example.server.Service.Account;

import com.example.server.Model.AccountDTO;
import com.example.server.Pojo.Account;

import java.util.List;
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

    public boolean deactivateByEmail(String email);

    public List<AccountDTO> findAllActive();

    public Optional<AccountDTO> findByEmail(String email);

    public Optional<AccountDTO> findById(Long id);

    public AccountDTO save(AccountDTO accountDto);
}
