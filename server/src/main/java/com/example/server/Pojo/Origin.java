package com.example.server.Pojo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Set;
@Data
@Entity
@Table(name = "origin")
public class Origin {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "origin",nullable = false)
    private boolean origin;

    @OneToMany(cascade = CascadeType.ALL,mappedBy = "diamondOrigin")
    @JsonIgnore
    private Set<Diamond> diamondOrigins;

    @OneToMany(cascade = CascadeType.ALL,mappedBy = "diamondOrigin")
    @JsonIgnore
    private Set<DiamondPriceList> diamondPriceList;



    @Override
    public String toString() {
        return "Origin{" +
                "Id=" + id +
                ", origin=" + origin +
                ", diamondOrigins=" + diamondOrigins +
                ", diamondOriginss=" + diamondPriceList +
                '}';
    }
}
