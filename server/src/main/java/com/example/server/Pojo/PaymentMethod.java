package com.example.server.Pojo;

import jakarta.persistence.*;

import java.util.Set;

@Entity
@Table(name = "paymentMethod")
public class PaymentMethod {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "paymentMethod")
    private Set<Payment> paymentSet;

    public PaymentMethod(Long id, String name, Set<Payment> paymentSet) {
        this.id = id;
        this.name = name;
        this.paymentSet = paymentSet;
    }

    public PaymentMethod() {
    }

    public PaymentMethod(PaymentMethod p) {
        this.id = p.id;
        this.name = p.name;
        this.paymentSet = p.paymentSet;
    }

    @Override
    public String toString() {
        return "PaymentMethod{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", paymentSet=" + paymentSet +
                '}';
    }
}
