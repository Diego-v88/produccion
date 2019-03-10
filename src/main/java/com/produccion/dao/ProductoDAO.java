/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.produccion.dao;

import com.produccion.entities.Producto;
import java.io.Serializable;

/**
 *
 * @author Diego
 */
public interface ProductoDAO extends GenericDAO<Producto, Serializable>{
    public Integer cantidadDeFallos(Integer idProducto);
    public Integer cantidadDeProducciones(Integer idProducto);
}
