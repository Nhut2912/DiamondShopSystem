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

    public Cut(Long id, String cut, Set<Diamond> diamondCuts, Set<DiamondPriceList> diamondCutss) {
        Id = id;
        this.cut = cut;
        this.diamondCuts = diamondCuts;
        this.diamondCutss = diamondCutss;
    }

    public Cut() {
    }

    public Cut(Cut c) {
        Id = c.Id;
        this.cut = c.cut;
        this.diamondCuts = c.diamondCuts;
        this.diamondCutss = c.diamondCutss;
    }

    @Override
    public String toString() {
        return "Cut{" +
                "Id=" + Id +
                ", cut='" + cut + '\'' +
                ", diamondCuts=" + diamondCuts +
                ", diamondCutss=" + diamondCutss +
                '}';
    }
}
