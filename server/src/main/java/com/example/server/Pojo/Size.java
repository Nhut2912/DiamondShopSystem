package com.example.server.Pojo;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "size")
@Data
public class Size {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int size;
}
