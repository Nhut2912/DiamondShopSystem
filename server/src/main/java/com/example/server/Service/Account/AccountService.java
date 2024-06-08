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
            return true;
        }catch (Exception ex){
            System.out.println(ex.getMessage());
            return false;
        }

    }

    @Override
    public String registerAccount(AccountDTO accountDTO) {
        Optional<Account> accountNeedToCheck = iAccountRepository.findByEmail(accountDTO.getEmail());
        if(accountNeedToCheck.isPresent()){
            return "Account Exist";
        }else{
            String otp = generateOtp();
            sendEmailVerification(accountDTO.getEmail(), otp);
            httpSession.setAttribute(otp, accountDTO);
            return "OTP: " + otp;
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
    public String verifyOtp(String otp) {
        AccountDTO accountDTO = (AccountDTO)httpSession.getAttribute(otp);
        if(accountDTO == null) return "Wrong OTP";
        Account account = new Account();
        Optional<Account> checkAccountExist = iAccountRepository.findByEmail(accountDTO.getEmail());
        if(checkAccountExist.isPresent()) return "Account Exist";
        account.setEmail(accountDTO.getEmail());
            try {
                Account accountValid = iAccountRepository.save(account);
                return accountValid.getRole();
            }catch (Exception e){
                return "Create Fail";
            }
        }




}
