package com.example.server.Pojo;

import jakarta.persistence.*;
<<<<<<< HEAD

import java.util.Set;

import lombok.Data;

=======
//<<<<<<< HEAD
import java.util.Set;
//=======
import lombok.Data;
//>>>>>>> 367fdd224da5a4f45dd0623da5c612480fb252c3
>>>>>>> 54f5466e8e91da20e7290c5a13fe1e88a273dd0d

/*
 *Author: Tran Viet Hoang
 * Date: 21/5/2024
 */
<<<<<<< HEAD

@Data

=======
//<<<<<<< HEAD
//=======
@Data
//>>>>>>> 367fdd224da5a4f45dd0623da5c612480fb252c3
>>>>>>> 54f5466e8e91da20e7290c5a13fe1e88a273dd0d
@Entity
@Table(name = "Customer")
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "PointMember")
    private int pointMember;

    @OneToOne(cascade = CascadeType.ALL, optional = false)
    @JoinColumn(name = "AccountId", referencedColumnName = "id")
    private Account account;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "customerOrder")
    private Set<Order> orders;

    @OneToMany(cascade = CascadeType.ALL,mappedBy = "customerId")
    private Set<Promotions_Customer> customers;

<<<<<<< HEAD
=======
//<<<<<<< HEAD
>>>>>>> 54f5466e8e91da20e7290c5a13fe1e88a273dd0d
    public Customer() {
    }

    public Customer(Long id, int pointMember) {
        this.id = id;
        this.pointMember = pointMember;
    }

    public Customer(Long id, int pointMember, Account account) {
        this.id = id;
        this.pointMember = pointMember;
        this.account = account;
    }

    public int getPointMember() {
        return pointMember;
    }

    public void setPointMember(int pointMember) {
        this.pointMember = pointMember;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

//    public int getAccountId() {
//        return accountId;
//    }
//
//    public void setAccountId(int accountId) {
//        this.accountId = accountId;
//    }


    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }
<<<<<<< HEAD

=======
//=======
//
//
//>>>>>>> 367fdd224da5a4f45dd0623da5c612480fb252c3
>>>>>>> 54f5466e8e91da20e7290c5a13fe1e88a273dd0d
}
