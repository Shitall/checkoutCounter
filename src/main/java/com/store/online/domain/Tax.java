package com.store.online.domain;

import org.hibernate.annotations.Cascade;

import javax.persistence.*;

import static javax.persistence.GenerationType.AUTO;

@Entity
@Table(name = "Tax")
public class Tax {
    @Id
    @GeneratedValue(strategy = AUTO)
    private Long id;

    @Column
    private Double taxPercent;

    @OneToOne
    @Cascade(value = org.hibernate.annotations.CascadeType.SAVE_UPDATE)
    private Category category;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getTaxPercent() {
        return taxPercent;
    }

    public void setTaxPercent(Double taxPercent) {
        this.taxPercent = taxPercent;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
}
