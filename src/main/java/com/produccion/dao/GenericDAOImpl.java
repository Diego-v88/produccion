/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.produccion.dao;

import java.io.Serializable;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Diego
 */
@Repository
public abstract class GenericDAOImpl<T, ID extends Serializable> implements GenericDAO<T, ID> {
    
    @Autowired(required = true)
    private SessionFactory sessionFactory;
    
    protected Session getCurrentSession() {
      return sessionFactory.getCurrentSession();
   }
   
    @Override
    public Integer save(T entity) {
        Integer id = (Integer) this.getCurrentSession().save(entity);
        return id;
    }
    
    @Override
    public void update(T entity) {
        this.getCurrentSession().update(entity);
    }
    
    @Override
    public void merge(T entity) {
        this.getCurrentSession().merge(entity);
    }

    @Override
    public void delete(T entity) {
        this.getCurrentSession().delete(entity);
    }
    
    @Override
    public void deleteById(Class type, Integer id) {
        T entity = (T) this.getCurrentSession().get(type, id);
        this.getCurrentSession().delete(entity);
    }
    
    @Override
    public void load(Class clazz, Integer id) {
        this.getCurrentSession().load(clazz, id);
    }

    @Override
    public T findByID(Class type, Integer id) {
      return (T) this.getCurrentSession().get(type, id);
    }

    @Override
    public List findAll(Class cls) {
        List T = null;
        Query query = this.getCurrentSession().createQuery("FROM " + cls.getName());
        T = query.list();
        return T;
    }
}
