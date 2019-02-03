package com.store.online.dao;

import com.store.online.domain.Order;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.Query;
import java.util.List;

@Repository("orderDao")
@Transactional
public class OrderDao extends HibernateDaoImpl {
    public Order getCustomerRecentOrder(String name) {
        Query query = sessionFactory.getCurrentSession().createQuery("from Order order1 where order1.customer.name = :name and status = 'Active'").setParameter("name", name);
        List<Order> list = query.getResultList();
        if (list != null && !list.isEmpty()) {
            return list.get(0);
        }
        return null;
    }

}
