package com.store.online.dao;

import com.store.online.domain.Category;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.Query;
import java.util.List;

@Repository("categoryDao")
@Transactional
public class CategoryDao extends HibernateDaoImpl {

    public Category getCategoryByName(String name) {
        Query query = sessionFactory.getCurrentSession().createQuery("from Category where name = :name").setParameter("name", name);
        List<Category> list = query.getResultList();
        if (list != null && !list.isEmpty()) {
            return list.get(0);
        }
        return null;
    }


}
