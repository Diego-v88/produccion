/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.produccion.service;

import com.produccion.dao.DAOException;
import com.produccion.datatransfers.LoteDTO;
import com.produccion.datatransfers.PProducidosDTO;
import com.produccion.datatransfers.MaterialCantidadDTO;
import com.produccion.datatransfers.PedidoDTO;
import com.produccion.datatransfers.PlanDTO;
import com.produccion.datatransfers.ProductoProducidoDTO;
import com.produccion.entities.Ejecucion;
import com.produccion.entities.Pedidos;
import com.produccion.entities.Planproduccion;
import java.util.List;

/**
 *
 * @author Diego
 */
public interface PProduccionService {
    public List<PlanDTO> getPlanproducciones() throws DAOException;
    public void deletePlanproduccion(Integer idPlanproduccion) throws DAOException;
    public int addPlanproduccion(PlanDTO planProduccion) throws DAOException;
    public void updatePlanproduccion(Planproduccion planProduccion) throws DAOException;
    public PlanDTO getPlanproduccionById(Integer idPlanproduccion) throws DAOException;
    public PedidoDTO getPedidoById(Integer idPedido) throws DAOException;
    public List<PedidoDTO> getPedidos(Integer idPProduccion) throws DAOException;
    public void addPedido(Integer planproduccion, Pedidos pedido, List<MaterialCantidadDTO> pedidos) throws DAOException;
    public List<PProducidosDTO> getProductosProducidosPorLote(Integer idLote) throws DAOException;
    public List<LoteDTO> getLotesPorPlan(Integer idPProduccion) throws DAOException;
    public void updateProductoProducido(Integer id, ProductoProducidoDTO pp) throws DAOException;
    public List<Ejecucion> getEjecucionesPorLote(Integer idLote) throws DAOException;
}
