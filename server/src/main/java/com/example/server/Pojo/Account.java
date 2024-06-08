package com.example.server.Pojo;

import jakarta.persistence.*;
import lombok.Data;

import java.sql.Date;
import java.util.Set;

@Entity
@Table(name ="account")
@Data
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String email;

    private String password;

    private String numberPhone;

    private String address;

    private Date birthDay;

    private String role;

    private boolean gender;

    private boolean active = true;


    @OneToMany(mappedBy = "account",cascade = CascadeType.ALL)
    private Set<Order> orders;

}

