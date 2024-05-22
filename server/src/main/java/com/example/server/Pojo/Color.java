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
    private Set<DiamondPriceList> diamondColorss;

    public Color() {
    }

    public Color(Long id, String color, Set<Diamond> diamondColors, Set<DiamondPriceList> diamondColorss) {
        Id = id;
        this.color = color;
        this.diamondColors = diamondColors;
        this.diamondColorss = diamondColorss;
    }

    public Color(Color c) {
        Id = c.Id;
        this.color = c.color;
        this.diamondColors = c.diamondColors;
        this.diamondColorss = c.diamondColorss;
    }

    @Override
    public String toString() {
        return "Color{" +
                "Id=" + Id +
                ", color='" + color + '\'' +
                ", diamondColors=" + diamondColors +
                ", diamondColorss=" + diamondColorss +
                '}';
    }
}
