package com.example.server.Service.Account;

import com.example.server.Model.AccountDTO;
import com.example.server.Pojo.Account;

import java.text.ParseException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
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

    public AccountDTO updateAccount(Long accountId, AccountDTO accountDetails);

    public String createAccount(AccountDTO accountDto);
    public String isStaffOrAdmin(AccountDTO accountDTO);

    public Map<LocalDateTime, Long> getNewAccountStatisticByWeek() throws ParseException;
    public Map<LocalDateTime, Long> getNewAccountStatisticByMonth() throws ParseException;

    public long getSumNewAccountStatisticByWeek() throws ParseException;
    public long getSumNewAccountStatisticByMonth() throws ParseException;

    public long getSumNewAccountStatisticByDay() throws ParseException ;


}
