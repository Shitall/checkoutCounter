package com.store.online.domain;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "Customer")
public class Customer {
    @OneToMany(cascade = CascadeType.ALL,
            fetch = FetchType.LAZY)
    @JoinColumn(name = "cust_id")
    Set<Address> addressColl = new HashSet<Address>();
    @OneToMany(cascade = CascadeType.ALL,
            fetch = FetchType.LAZY)

    Set<Order> orderColl = new HashSet<Order>();
    @Id
    @Column(name = "")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "")
    private String name;
    @Column(name = "")
    private String email;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Set<Order> getOrderColl() {
        return orderColl;
    }

    public void setOrderColl(Set<Order> orderColl) {
        this.orderColl = orderColl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Set<Address> getAddressColl() {
        return addressColl;
    }

    public void setAddressColl(Set<Address> addressColl) {
        this.addressColl = addressColl;
    }
}
