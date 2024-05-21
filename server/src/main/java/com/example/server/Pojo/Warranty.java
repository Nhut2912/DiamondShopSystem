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
