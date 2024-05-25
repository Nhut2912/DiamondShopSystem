package com.example.server.Services;

import com.example.server.Pojo.Account;
import com.example.server.Repository.IAccountRepository;
import com.example.server.Requests.RegistrationRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Random;

/*
 * author : Tran Viet Hoang
 * date : 23/5/2024
 * purpose : implement registration service
 *
 */
@Service
public class RegistrationServices implements IRegistrationServices {
    @Autowired
    private IAccountRepository accountRepository;
    @Autowired
    private IEmailService emailService;
    //verify!!!
    @Override
    public ResponseEntity<?> register(RegistrationRequest registrationRequest) {
        Account accountNeedToCheck =  (Account) accountRepository.findByEmail(registrationRequest.getEmail());
        if(accountNeedToCheck != null){
            return new ResponseEntity<>("Account Exist", HttpStatus.BAD_REQUEST);
        }
        else if(registrationRequest.isVerify()){
            Account account = Account.builder().email(registrationRequest.getEmail()).name(registrationRequest.getName()).address(registrationRequest.getAddress())
                    .active(true).birthday(registrationRequest.getBirthday()).phone(registrationRequest.getPhone()).gender(registrationRequest.isGender())
                    .role("customer").build();
            return new ResponseEntity<>(accountRepository.save(account), HttpStatus.CREATED);
        }else{
            String otp = generateOTP();
            sendVerificationEmail(registrationRequest.getEmail(), otp);
            return new ResponseEntity<>("Require verify", HttpStatus.OK);
        }
    }

    @Override
    public String generateOTP() {
        Random random = new Random();
        int otpValue = 100000 + random.nextInt(900000);
        return String.valueOf(otpValue);
    }

    @Override
    public void sendVerificationEmail(String email, String otp) {
        String subject = "Email verification";
        String body ="your verification otp is: "+otp;
        emailService.sendEmail(email,subject,body);
    }
}
