/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.produccion.dao;

import com.produccion.entities.Productosproducidos;
import java.io.Serializable;
import java.util.List;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Diego
 */
@Repository
public class ProductosproducidosDAOImpl extends GenericDAOImpl<Productosproducidos, Serializable> implements ProductosproducidosDAO{
    @Override
    public List<Productosproducidos> getProductosPorLote(Integer idLote) {
        String sql = "SELECT pp FROM Productosproducidos pp, Lotedeproduccion lp WHERE lp.idLote = pp.idLote AND lp.idLote = :idLote";
        return getCurrentSession().createQuery(sql).setParameter("idLote", idLote).list();
    }
}
