package com.example.server.Pojo;

import jakarta.persistence.*;
import lombok.Data;

/*
 * Author: Pham Trong Hieu
 */
@Data
@Entity
@Table(name = "Warranty")
public class Warranty {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "Status", nullable = false)
    private boolean status;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "WarrantyPolicyId")
    private WarrantyPolicy warrantyPolicy;

    @OneToOne(mappedBy = "warranty")
    private Product product;

    public Warranty(Long id, boolean status, WarrantyPolicy warrantyPolicy, Product product) {
        this.id = id;
        this.status = status;
        this.warrantyPolicy = warrantyPolicy;
        this.product = product;
    }

    public Warranty() {
    }

    public Warranty(Warranty w) {
        this.id = w.id;
        this.status = w.status;
        this.warrantyPolicy = w.warrantyPolicy;
        this.product = w.product;
    }

    @Override
    public String toString() {
        return "Warranty{" +
                "id=" + id +
                ", status=" + status +
                ", warrantyPolicy=" + warrantyPolicy +
                ", product=" + product +
                '}';
    }
}
