/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.produccion.dao;

/**
 *
 * @author Diego
 */

import java.io.Serializable;
import java.util.*;

public interface GenericDAO<T, ID extends Serializable> {
    public Integer save(T entity);
    
    public void update(T entity);

    public void merge(T entity);

    public void delete(T entity);
    
    public void deleteById(Class type, Integer id);

    public List findAll(Class cls);

    public T findByID(Class cls, Integer id);
    
    public void load(Class clazz, Integer id);
}
