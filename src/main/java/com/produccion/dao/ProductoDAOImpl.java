/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.produccion.dao;

import com.produccion.entities.Producto;
import java.io.Serializable;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Diego
 */

@Repository
public class ProductoDAOImpl extends GenericDAOImpl<Producto, Serializable> implements ProductoDAO{
    
    @Override
    public Integer cantidadDeFallos(Integer idProducto) {
        Integer fallos = 0;
        String sql = "SELECT COUNT(*) FROM Lotedeproduccion lp, Productosproducidos prodP WHERE :idProducto = lp.producto.idproducto AND lp.idlote = prodP.lotedeproduccion.idlote AND prodP.desensamble = true";
        fallos = ((Number) getCurrentSession().createQuery(sql).setParameter("idProducto", idProducto).uniqueResult()).intValue();
        return fallos;
    }
    
    @Override
    public Integer cantidadDeProducciones(Integer idProducto) {
        Integer cantidad = 0;
        String sql = "SELECT COUNT(*) FROM Lotedeproduccion lp, Productosproducidos prodP WHERE :idProducto = lp.producto.idproducto AND lp.idlote = prodP.lotedeproduccion.idlote";
        cantidad = ((Number) getCurrentSession().createQuery(sql).setParameter("idProducto", idProducto).uniqueResult()).intValue();
        return cantidad;
    }
    
    
}
