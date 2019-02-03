package com.store.online.domain;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "ItemOrder") //DB:Order does not work must be keyword
public class Order {
    @OneToMany(cascade = CascadeType.ALL,
            fetch = FetchType.EAGER)
    @JoinColumn(name = "order_id")
    Set<Item> orderItems = new HashSet<Item>();
    @Id
    @Column(name = "")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column
    private String status;
    @Column
    private Long OrderNo;
    @ManyToOne(cascade = CascadeType.MERGE)
    private Customer customer;
    @Column
    private Date createdDate;

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Set<Item> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(Set<Item> orderItems) {
        this.orderItems = orderItems;
    }

    public Long getOrderNo() {
        return OrderNo;
    }

    public void setOrderNo(Long orderNo) {
        OrderNo = orderNo;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
