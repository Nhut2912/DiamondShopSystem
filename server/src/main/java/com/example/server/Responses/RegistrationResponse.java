package com.example.server.Responses;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RegistrationResponse {
    private String email;
    private String password;
    private String name;
    private String phone;
    private String address;
    private String birthday;
    private String role;
    private boolean active;
    private boolean gender;
    private String otp;

    private boolean verify;
}
