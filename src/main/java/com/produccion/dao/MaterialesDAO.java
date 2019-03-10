/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.produccion.dao;

import com.produccion.entities.Material;
import java.io.Serializable;
import java.util.List;

/**
 *
 * @author Diego
 */
public interface MaterialesDAO extends GenericDAO<Material, Serializable>{
    public List<Material> getMaterialesPorReceta(Integer idReceta);
}
