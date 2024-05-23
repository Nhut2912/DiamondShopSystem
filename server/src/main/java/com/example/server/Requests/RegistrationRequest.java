package com.example.server.Requests;

import lombok.Data;
/*
 * author : Tran Viet Hoang
 * date : 23/5/2024
 * purpose : tạo object nhận các attribute từ method của phương thức post
 *
 */
@Data
public class RegistrationRequest {
    private String email;
    private String password;
    private String name;
    private String phone;
    private String address;
    private String birthday;
    private String role;
    private boolean active;
    private boolean gender;

    private boolean verify;

}
