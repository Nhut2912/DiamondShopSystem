package com.example.server.Pojo;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

/*
 *Author: Tran Viet Hoang
 * Date: 21/5/2024
 */
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "PointMember")
    private int pointMember;

    @Column(name = "AccountId")
    private int accountId;

    public Customer() {
    }

    public Customer(Long id, int pointMember, int accountId) {
        this.id = id;
        this.pointMember = pointMember;
        this.accountId = accountId;
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

    public int getAccountId() {
        return accountId;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }

}
