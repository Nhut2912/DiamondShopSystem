package com.example.server.Service.Account;

import com.example.server.Model.AccountDTO;
import com.example.server.Pojo.Account;
import com.example.server.Repository.IAccountRepository;
import com.example.server.Service.Email.IEmailService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Random;

@Service
public class AccountService implements IAccountService{
    @Autowired
    IAccountRepository iAccountRepository;
    @Autowired
    private IEmailService emailService;
    @Autowired
    private HttpSession httpSession;
    @Override
    public boolean isAccountExist(Long id) {
        System.out.println(id);
        Optional<Account> accountNeedToCheck = iAccountRepository.findById(id);
        System.out.println(accountNeedToCheck.isPresent());
        return accountNeedToCheck.isPresent();
    }
    public boolean isSamePhone(String phone){
        Optional<Account> account = iAccountRepository.findByNumberPhone(phone);
        if(account.isPresent()) return  true;
        else return false;
    }
    public boolean updateNewestInfoForAccount(AccountDTO accountDTO){
        try {
            Optional<Account> account = iAccountRepository.findById(accountDTO.getId());
            Account accountUpdate = account.get();
            accountUpdate.setName(accountDTO.getName());
            accountUpdate.setAddress(accountDTO.getAddress());
            accountUpdate.setBirthDay(accountDTO.getBirthDay());
            accountUpdate.setNumberPhone(accountDTO.getNumberPhone());
            return true;
        }catch (Exception ex){
            System.out.println(ex.getMessage());
            return false;
        }

    }

    @Override
    public boolean registerAccount(AccountDTO accountDTO) {
        String otp = generateOtp();
        sendEmailVerification(accountDTO.getEmail(), otp);
       try{
           System.out.println(accountDTO);
           httpSession.setAttribute(otp,accountDTO);
           return true;
       }catch (Exception e){
           return false;
       }
    }

    @Override
    public String loginAccount(AccountDTO accountDTO) {
        Optional<Account> account = iAccountRepository.findByEmail(accountDTO.getEmail());
        if(account.isPresent()) return account.get().getRole();
        return "Account does not exsit!!";
    }

    @Override
    public String generateOtp() {
        Random random = new Random();
        int otpValue = 100000 + random.nextInt(900000);
        return String.valueOf(otpValue);
    }

    @Override
    public void sendEmailVerification(String email, String otp) {
        String subject = "Email verification";
        String body ="Your verification otp is: " + otp;
        emailService.sendEmail(email,subject,body);
    }

    @Override
    public AccountDTO verifyOtp(String otp, AccountDTO accountDTO) {
        Account account = new Account();
        Optional<Account> checkAccountExist = iAccountRepository.findByEmail(accountDTO.getEmail());
        if(checkAccountExist.isEmpty()){
            account.setEmail(accountDTO.getEmail());
            account.setRole(accountDTO.getRole());
            try {
                Account accountValid = iAccountRepository.save(account);

                AccountDTO accountReturn = new AccountDTO();
                accountReturn.setId(accountValid.getId());
                accountReturn.setEmail(accountValid.getEmail());
                accountReturn.setRole(accountValid.getRole());
                return accountReturn;
            }catch (Exception e){
                System.out.println(e.getMessage());
                return null;
            }
        }

        AccountDTO accountDTO1 = new AccountDTO();
        accountDTO1.setId(checkAccountExist.get().getId());
        accountDTO1.setEmail(checkAccountExist.get().getEmail());
        accountDTO1.setRole(checkAccountExist.get().getRole());
        return accountDTO1;
    }




}
