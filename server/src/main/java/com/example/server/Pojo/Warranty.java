package com.example.server.Pojo;

import jakarta.persistence.*;

/*
 * Author: Pham Trong Hieu
 */
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

    public Warranty() {
    }

    public Warranty(Long id, boolean status, WarrantyPolicy warrantyPolicy, Product product) {
        this.id = id;
        this.status = status;
        this.warrantyPolicy = warrantyPolicy;
        this.product = product;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public WarrantyPolicy getWarrantyPolicy() {
        return warrantyPolicy;
    }

    public void setWarrantyPolicy(WarrantyPolicy warrantyPolicy) {
        this.warrantyPolicy = warrantyPolicy;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}
