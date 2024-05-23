package com.example.server.Services;

import com.example.server.Pojo.Account;
import com.example.server.Requests.RegistrationRequest;
import org.springframework.http.ResponseEntity;

/*
 * author : Tran Viet Hoang
 * date : 23/5/2024
 * purpose : tạo interface registration service
 *
 */
public interface IRegistrationServices {
    ResponseEntity<?> register(RegistrationRequest registrationRequest);
    String generateOTP();
    void sendVerificationEmail(String email,String otp);
}
