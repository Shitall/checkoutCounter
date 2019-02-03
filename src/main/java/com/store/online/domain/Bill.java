package com.store.online.domain;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "Bill")
public class Bill {
    @Column
    private Date date;
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    private Order order;

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    /*public void calculateBill()
    {

        List<BillModel> billModelList = new ArrayList<BillModel>();
        BigDecimal totalCost = BigDecimal.ZERO;
        Set<Item> itemList = order.getOrderItems();
        for (Item item: itemList)
        {
            BillModel model = new BillModel();
            totalCost = totalCost.add(BigDecimal.valueOf(item.getProduct().getPrice()));
            model.setCustName(order.getCustomer().getName());
            model.setPricePerItem(item.getProduct().getPrice());
            model.setQuantity(item.getOrderedQunatity());
            //Substract this ordered quantity from product quantity

            billModelList.add(model);
        }

    }*/

}
