package com.store.online.dao;

public interface IHibernateDao<T> {

    T get(Long id, String classname);

    void save(T obj);

    void update(T obj);

    //  public T getObjectByFieldName(String className,String fieldName, String value);


}
