package com.example.server.Pojo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "promotion")
public class Promotion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "DateStart")
    private Date dateStart;

    @Column(name = "dateEnd")
    private Date dateEnd;

    @Column(name = "active")
    private boolean active;

    @Column(name = "promotionPrice")
    private int promotionPrice;


    public Promotion(Long id, Date dateStart, Date dateEnd, boolean active, int promotionPrice) {
        this.id = id;
        this.dateStart = dateStart;
        this.dateEnd = dateEnd;
        this.active = active;
        this.promotionPrice = promotionPrice;
    }

    public Promotion() {
    }

    public Promotion(Promotion p) {
        this.id = p.id;
        this.dateStart = p.dateStart;
        this.dateEnd = p.dateEnd;
        this.active = p.active;
    }

    @Override
    public String toString() {
        return "Promotion{" +
                "id=" + id +
                ", dateStart=" + dateStart +
                ", dateEnd=" + dateEnd +
                ", active=" + active +
                ", promotionPrice=" + promotionPrice +
                '}';
    }
}
