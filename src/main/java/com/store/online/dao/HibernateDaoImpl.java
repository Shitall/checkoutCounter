package com.store.online.dao;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository("hibernateDao")
@Transactional
public class HibernateDaoImpl implements IHibernateDao {


    @Autowired
    protected SessionFactory sessionFactory;

    @Override
    public Object get(Long id, String classsName) {
        sessionFactory.getCurrentSession().get(classsName, id);
        return null;
    }

    @Override
    public void save(Object obj) {

        sessionFactory.getCurrentSession().persist(obj);

    }

    @Override
    public void update(Object obj) {

        sessionFactory.getCurrentSession().update(obj);
    }

    /*
    @Override
    public Object getObjectByFieldName(String className, String fieldName, String value)
    {
        String queryStr = "from "+ className+" where "+fieldName+" = :name";
        Query query = sessionFactory.getCurrentSession().createQuery(queryStr).setParameter("name" , value);
        List list =  query.getResultList();
        if(list!=null && !list.isEmpty())
        {
            return list.get(0);
        }

        return null;
    }
    */

}
