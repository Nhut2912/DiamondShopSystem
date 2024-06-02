package com.example.server.Pojo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;

import java.util.Set;

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
    @Column(name = "accountId")
    private Long id;

    @Column(name = "email", unique = true)
    private String email;

    private String password;
    private String name;
    private String phone;
    private String address;
    private String birthday;
    private String role;
    private boolean active;
    private boolean gender;

    @OneToMany(cascade = CascadeType.ALL,mappedBy = "accountOrder")
    private Set<Order> orders;


    public Account() {
    }


}
