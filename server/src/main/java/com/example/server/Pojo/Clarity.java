package com.example.server.Pojo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Set;
@Data
@Entity
@Table(name = "clarity")
public class Clarity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "clarityId")
    private Long Id;

    @Column(name = "clarity")
    private String clarity;


<<<<<<< HEAD
    @OneToMany(cascade = CascadeType.ALL,mappedBy = "diamondClarity")
    @JsonIgnore
    private Set<Diamond> diamondClarity;

    @OneToMany(cascade = CascadeType.ALL,mappedBy = "diamondClarity")
    @JsonIgnore
=======
    @OneToMany(cascade = CascadeType.ALL,mappedBy = "DiamondClarity")
    private Set<Diamond> diamondClarity;

    @OneToMany(cascade = CascadeType.ALL,mappedBy = "DiamondClarity")
>>>>>>> 66cd59eec7390c32ee0e12456e935defed9dec98
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
