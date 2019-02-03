package com.store.online.dao;

import com.store.online.domain.Customer;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.Query;
import java.util.List;

@Repository("customerDao")
@Transactional
public class CustomerDao extends HibernateDaoImpl {

    public Customer getCustomerByName(String name) {
        Query query = sessionFactory.getCurrentSession().createQuery("from Customer where name = :name").setParameter("name", name);
        List<Customer> list = query.getResultList();
        if (list != null && !list.isEmpty()) {
            return list.get(0);
        }
        return null;
    }
}
