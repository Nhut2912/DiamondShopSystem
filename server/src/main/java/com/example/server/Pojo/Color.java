package com.example.server.Pojo;

import jakarta.persistence.*;

import java.util.Set;

@Entity
@Table(name = "COLOR")
public class Color {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @Column(name = "Color")
    private String color;

    @OneToMany(cascade = CascadeType.ALL,mappedBy = "DiamondColor")
    private Set<Diamond> diamondColors;

    @OneToMany(cascade = CascadeType.ALL,mappedBy = "DiamondColor")
    private Set<DiamondPriceList> diamondPriceColors;

    public Color() {
    }

    public Color(Long id, String color, Set<Diamond> diamondColors, Set<DiamondPriceList> diamondPriceColors) {
        Id = id;
        this.color = color;
        this.diamondColors = diamondColors;
        this.diamondPriceColors = diamondPriceColors;
    }

    public Color(Color c) {
        Id = c.Id;
        this.color = c.color;
        this.diamondColors = c.diamondColors;
        this.diamondPriceColors = c.diamondPriceColors;
    }

    @Override
    public String toString() {
        return "Color{" +
                "Id=" + Id +
                ", color='" + color + '\'' +
                ", diamondColors=" + diamondColors +
                ", diamondPriceColors=" + diamondPriceColors +
                '}';
    }
}
