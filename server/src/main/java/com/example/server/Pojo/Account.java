package com.example.server.Pojo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;

/*
 *Author: Tran Viet Hoang
 * Date: 21/5/2024
 */
@Data
@Entity
@Table(name = "ACCOUNT")
@Builder
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "accountId")
    private Long id;

    @Column(name = "email", unique = true, length = 50)
    private String email;

    @Column(name = "password")
    private String password;

    @Column(name = "name")
    private String name;

    @Column(name = "phone")
    private String phone;

    @Column(name = "address")
    private String address;

    @Column(name = "birthday")
    private String birthday;

    @Column(name = "role")
    private String role;

    @Column(name = "active")
    private boolean active;

    @Column(name = "gender")
    private boolean gender;

    @OneToOne(cascade = CascadeType.ALL,mappedBy = "account")
    @JsonIgnore
    private Customer customer;



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
