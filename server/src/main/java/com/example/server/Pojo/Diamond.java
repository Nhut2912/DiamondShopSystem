package com.example.server.Pojo;

import jakarta.persistence.*;

@Entity
@Table(name="DIAMOND")
public class Diamond {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @Column(name = "Code", unique = true, length =50)
    private String code;



}
