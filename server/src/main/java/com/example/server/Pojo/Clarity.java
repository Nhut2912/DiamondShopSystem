package com.example.server.Pojo;

import jakarta.persistence.*;

import java.util.Set;

@Entity
@Table(name = "CLARITY")
public class Clarity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @Column(name = "Clarity")
    private String clarity;


    @OneToMany(cascade = CascadeType.ALL,mappedBy = "DiamondClarity")
    private Set<Diamond> diamondClarity;

    @OneToMany(cascade = CascadeType.ALL,mappedBy = "DiamondClarity")
    private Set<DiamondPriceList> diamondPriceListClarity;

    public Clarity() {
    }

    public Clarity(Long id, String clarity, Set<Diamond> diamondClaritys, Set<DiamondPriceList> diamondClarityss) {
        Id = id;
        this.clarity = clarity;
        this.diamondClarity = diamondClaritys;
        this.diamondPriceListClarity = diamondClarityss;
    }

    public Clarity(Clarity cla) {
        Id = cla.Id;
        this.clarity = cla.clarity;
        this.diamondClarity = cla.diamondClarity;
        this.diamondPriceListClarity = cla.diamondPriceListClarity;
    }

    @Override
    public String toString() {
        return "Clarity{" +
                "Id=" + Id +
                ", clarity='" + clarity + '\'' +
                ", diamondClarity=" + diamondClarity +
                ", diamondPriceListClarity=" + diamondPriceListClarity +
                '}';
    }
}
