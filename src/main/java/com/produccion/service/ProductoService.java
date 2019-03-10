/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.produccion.service;

import com.produccion.dao.DAOException;
import com.produccion.datatransfers.CircuitoDTO;
import com.produccion.datatransfers.MaterialCantidadDTO;
import com.produccion.datatransfers.ProductoDTO;
import com.produccion.datatransfers.ProductosCantidadDTO;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Diego
 */
public interface ProductoService {
    public Integer addProducto(ProductoDTO producto) throws DAOException;
    public boolean existeElProducto(Integer idproducto);
    public void updateProducto(Integer id, ProductoDTO producto) throws DAOException;
    public ProductoDTO getProducto(Integer idProducto) throws DAOException;
    public List<ProductoDTO> getProductos() throws DAOException;
    public void deleteProducto(Integer idProducto) throws DAOException;
    public Integer cantidadAProducir(Integer idProducto,Integer cantidad) throws DAOException;
    public List<MaterialCantidadDTO> getReceta(Integer idproducto) throws DAOException;
    public List<CircuitoDTO> getCircuito(Integer idproducto) throws DAOException;
    public void addReceta(Integer idproducto, List<MaterialCantidadDTO> recetas) throws DAOException;
    public void addCircuitos(Integer idproducto, List<CircuitoDTO> circuitos) throws DAOException;
    public boolean esPosibleProducir(List<ProductosCantidadDTO> aproducir, Date inicio, Date fin) throws DAOException;
}
