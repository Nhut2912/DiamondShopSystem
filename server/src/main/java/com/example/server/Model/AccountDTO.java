package com.example.server.Model;

import lombok.Data;

import java.sql.Date;

@Data
public class AccountDTO {

    private Long id;

    private String name;

    private String email;

    private String password;

    private String numberPhone;

    private String address;

    private Date birthDay;

}
