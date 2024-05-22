package com.example.server.Pojo;

import jakarta.persistence.*;
import lombok.Data;

/*
 *Author: Tran Viet Hoang
 * Date: 21/5/2024
 */
@Data
@Entity
@Table(name = "ACCOUNT")
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "Email", unique = true, length = 50)
    private String email;

    @Column(name = "Password")
    private String password;

    @Column(name = "Name")
    private String name;

    @Column(name = "Phone")
    private String phone;

    @Column(name = "Address")
    private String address;

    @Column(name = "Birthday")
    private String birthday;

    @Column(name = "Role")
    private String role;

    @Column(name = "Active")
    private boolean active;

    @Column(name = "Gender")
    private boolean gender;

    @OneToOne(cascade = CascadeType.ALL,mappedBy = "account")
    private Customer customer;

    public Account() {}
    public Account(String password, Long id, String email, String phone, boolean gender, String role, String address, String birthday, boolean active, String name) {
        this.password = password;
        this.id = id;
        this.email = email;
        this.phone = phone;
        this.gender = gender;
        this.role = role;
        this.address = address;
        this.birthday = birthday;
        this.active = active;
        this.name = name;
    }


}
