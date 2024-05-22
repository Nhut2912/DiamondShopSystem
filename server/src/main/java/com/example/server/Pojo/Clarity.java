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
    private Set<Diamond> diamondClaritys;

    @OneToMany(cascade = CascadeType.ALL,mappedBy = "DiamondClarity")
    private Set<DiamondPriceList> diamondClarityss;

    public Clarity() {
    }

    public Clarity(Long id, String clarity, Set<Diamond> diamondClaritys, Set<DiamondPriceList> diamondClarityss) {
        Id = id;
        this.clarity = clarity;
        this.diamondClaritys = diamondClaritys;
        this.diamondClarityss = diamondClarityss;
    }

    public Clarity(Clarity cla) {
        Id = cla.Id;
        this.clarity = cla.clarity;
        this.diamondClaritys = cla.diamondClaritys;
        this.diamondClarityss = cla.diamondClarityss;
    }

    @Override
    public String toString() {
        return "Clarity{" +
                "Id=" + Id +
                ", clarity='" + clarity + '\'' +
                ", diamondClaritys=" + diamondClaritys +
                ", diamondClarityss=" + diamondClarityss +
                '}';
    }
}
