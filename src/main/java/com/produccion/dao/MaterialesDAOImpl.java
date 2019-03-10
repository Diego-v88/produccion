/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.produccion.dao;

import com.produccion.entities.Material;
import java.io.Serializable;
import java.util.List;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Diego
 */
@Repository("materialesdao")
public class MaterialesDAOImpl extends GenericDAOImpl<Material, Serializable> implements MaterialesDAO{
  

    @Override
    public List<Material> getMaterialesPorReceta(Integer idReceta) {
        String sql = "SELECT ma FROM Material ma, Cantidadmr cmr WHERE ma.idmaterial = cmr.material.idmaterial AND cmr.receta.idreceta = :idReceta";
        return getCurrentSession().createQuery(sql).setParameter("idReceta", idReceta).list();
    }
}
