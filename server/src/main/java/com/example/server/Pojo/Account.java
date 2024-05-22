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

<<<<<<< HEAD
//    public Account() {}
//    public Account(String password, Long id, String email, String phone, boolean gender, String role, String address, String birthday, boolean active, String name) {
//        this.password = password;
//        this.id = id;
//        this.email = email;
//        this.phone = phone;
//        this.gender = gender;
//        this.role = role;
//        this.address = address;
//        this.birthday = birthday;
//        this.active = active;
//        this.name = name;
//    }
=======
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

<<<<<<< HEAD
=======
=======
>>>>>>> eb06f5d1151c196653d36adadfb49b6001aeb71c
//=======
//>>>>>>> 367fdd224da5a4f45dd0623da5c612480fb252c3
>>>>>>> 54f5466e8e91da20e7290c5a13fe1e88a273dd0d
>>>>>>> 8992e09b990f85eef86551d145efe3bd44dfd830


    public Account() {
    }

    public Account(Long id, String email, String password, String name, String phone, String address, String birthday, String role, boolean active, boolean gender, Customer customer) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.name = name;
        this.phone = phone;
        this.address = address;
        this.birthday = birthday;
        this.role = role;
        this.active = active;
        this.gender = gender;
        this.customer = customer;
    }

    public Account(Account acc) {
        this.id = acc.id;
        this.email = acc.email;
        this.password = acc.password;
        this.name = acc.name;
        this.phone = acc.phone;
        this.address = acc.address;
        this.birthday = acc.birthday;
        this.role = acc.role;
        this.active = acc.active;
        this.gender = acc.gender;
        this.customer = acc.customer;
    }

    @Override
    public String toString() {
        return "Account{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", name='" + name + '\'' +
                ", phone='" + phone + '\'' +
                ", address='" + address + '\'' +
                ", birthday='" + birthday + '\'' +
                ", role='" + role + '\'' +
                ", active=" + active +
                ", gender=" + gender +
                ", customer=" + customer +
                '}';
    }
}
