package com.example.server.Pojo;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name ="blog")
@Data
public class Blog {
    @Id
    private long id;
    private String title;
    private String content;
    private boolean isActive;
    private String image;
}
