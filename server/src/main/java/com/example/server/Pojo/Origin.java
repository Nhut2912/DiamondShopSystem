package com.example.server.Pojo;

import jakarta.persistence.*;

import java.util.Set;

@Entity
@Table(name = "ORIGIN")
public class Origin {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @Column(name = "Origin",nullable = false)
    private boolean origin;

    @OneToMany(cascade = CascadeType.ALL,mappedBy = "DiamondOrigin")
    private Set<Diamond> diamondOrigins;

    @OneToMany(cascade = CascadeType.ALL,mappedBy = "DiamondOrigin")
    private Set<DiamondPriceList> diamondOriginss;

    public Origin(Long id, boolean origin, Set<Diamond> diamondOrigins, Set<DiamondPriceList> diamondOriginss) {
        Id = id;
        this.origin = origin;
        this.diamondOrigins = diamondOrigins;
        this.diamondOriginss = diamondOriginss;
    }

    public Origin() {
    }

    public Origin(Origin o) {
        Id = o.Id;
        this.origin = o.origin;
        this.diamondOrigins = o.diamondOrigins;
        this.diamondOriginss = o.diamondOriginss;
    }

    @Override
    public String toString() {
        return "Origin{" +
                "Id=" + Id +
                ", origin=" + origin +
                ", diamondOrigins=" + diamondOrigins +
                ", diamondOriginss=" + diamondOriginss +
                '}';
    }
}
