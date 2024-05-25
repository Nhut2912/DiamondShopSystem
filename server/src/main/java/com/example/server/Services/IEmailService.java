package com.example.server.Services;
/*
 * author : Tran Viet Hoang
 * date : 23/5/2024
 * purpose : EmailService service
 *
 */
public interface IEmailService {
    public void sendEmail(String to,String subject,String body);
}
