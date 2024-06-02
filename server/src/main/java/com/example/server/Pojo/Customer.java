package com.example.server.Pojo;

import jakarta.persistence.*;


import java.util.Set;

import lombok.Data;


/*
 *Author: Tran Viet Hoang
 * Date: 21/5/2024
 */


@Data
@Entity
@Table(name = "customer")
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "customerId")
    private Long id;

    @OneToOne(cascade = CascadeType.ALL, optional = false)
    @JoinColumn(name = "accountId")
    private Account account;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "customerOrder")
    private Set<Order> orders;


    public Customer() {
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", account=" + account +
                ", orders=" + orders +
                '}';
    }
}
