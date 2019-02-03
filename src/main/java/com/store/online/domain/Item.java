package com.store.online.domain;

import org.hibernate.annotations.Cascade;

import javax.persistence.*;

@Entity
@Table(name = "Item")
public class Item {
    @Id
    @Column(name = "")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column
    private double orderedQunatity;
    @Column(name = "")
    private Double totalCost;


    @OneToOne
    @Cascade(value = org.hibernate.annotations.CascadeType.SAVE_UPDATE)
    private Product product;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(Double totalCost) {
        this.totalCost = totalCost;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public double getOrderedQunatity() {
        return orderedQunatity;
    }

    public void setOrderedQunatity(double orderedQunatity) {
        this.orderedQunatity = orderedQunatity;
    }
}
