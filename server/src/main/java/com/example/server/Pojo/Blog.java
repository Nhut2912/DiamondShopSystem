package com.example.server.Pojo;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name ="blog")
@Data
public class Blog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String title;

    @Column(name ="description", length =  1000000)
    private String description;

    private String tagName;

    @Column(name ="contentDetail", length =  100000000)
    private String contentDetail;

    private String image;

    private boolean isActive;
}
