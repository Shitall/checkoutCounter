package com.store.online.dao;

import com.store.online.domain.Product;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.Query;
import java.util.List;

@Repository("productDao")
@Transactional
public class ProductDao extends HibernateDaoImpl {

    public Product getProductByName(String name) {
        Query query = sessionFactory.getCurrentSession().createQuery("from Product where name = :name").setParameter("name", name);
        List<Product> list = query.getResultList();
        if (list != null && !list.isEmpty()) {
            return list.get(0);
        }
        return null;
    }

}
