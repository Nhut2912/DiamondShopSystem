package com.example.server.Pojo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Set;
@Data
@Entity
@Table(name = "Cut")
public class Cut {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cutId")
    private Long Id;

    @Column(name = "cut")
    private String cut;

    @OneToMany(cascade = CascadeType.ALL,mappedBy = "diamondCut")
    @JsonIgnore
    private Set<Diamond> diamondCuts;

    @OneToMany(cascade = CascadeType.ALL,mappedBy = "diamondCut")
    @JsonIgnore
    private Set<DiamondPriceList> diamondPriceList;



    @Override
    public String toString() {
        return "Cut{" +
                "Id=" + Id +
                ", cut='" + cut + '\'' +
                ", diamondCuts=" + diamondCuts +
                ", diamondCutss=" + diamondPriceList +
                '}';
    }
}
