package com.example.server.Services;

import com.example.server.Pojo.Account;
import com.example.server.Repository.IAccountRepository;
import com.example.server.Requests.RegistrationRequest;
import com.example.server.Responses.RegistrationResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
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
    @Autowired
    private HttpSession httpSession;
    //verify!!!
    @Override
    public ResponseEntity<?> register(RegistrationRequest registrationRequest) {
        Account accountNeedToCheck = accountRepository.findByEmail(registrationRequest.getEmail());


        if(accountNeedToCheck != null){
            return new ResponseEntity<>("Account Exist", HttpStatus.BAD_REQUEST);
        }
        else if(httpSession.getAttribute(registrationRequest.getOtp()) != null){

            Account account = Account.builder().email(registrationRequest.getEmail()).password(registrationRequest.getPassword()).name(registrationRequest.getName()).address(registrationRequest.getAddress())
                    .active(true).birthday(registrationRequest.getBirthday()).phone(registrationRequest.getPhone()).gender(registrationRequest.isGender())
                    .role("customer").build();
            httpSession.removeAttribute(registrationRequest.getOtp());
            return new ResponseEntity<>(accountRepository.save(account), HttpStatus.CREATED);
        }else{
            String otp = generateOTP();
            sendVerificationEmail(registrationRequest.getEmail(), otp);
            RegistrationResponse registrationResponse = RegistrationResponse.builder().email(registrationRequest.getEmail()).password(registrationRequest.getPassword()).name(registrationRequest.getName()).address(registrationRequest.getAddress())
                    .active(true).birthday(registrationRequest.getBirthday()).phone(registrationRequest.getPhone()).gender(registrationRequest.isGender())
                    .role("CUSTOMER").otp(otp).build();
            httpSession.setAttribute(otp, registrationRequest);
            return new ResponseEntity<>(registrationResponse, HttpStatus.OK);
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
