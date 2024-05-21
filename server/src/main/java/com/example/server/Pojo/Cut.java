package com.example.server.Pojo;

import jakarta.persistence.*;

import java.util.Set;

@Entity
@Table(name = "CUT")
public class Cut {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @Column(name = "Cut")
    private String cut;

    @OneToMany(cascade = CascadeType.ALL,mappedBy = "DiamondCut")
    private Set<Diamond> diamondCuts;

    @OneToMany(cascade = CascadeType.ALL,mappedBy = "DiamondCut")
    private Set<DiamondPriceList> diamondCutss;
}
