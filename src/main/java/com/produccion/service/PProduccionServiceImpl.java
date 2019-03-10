/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.produccion.service;

import com.produccion.dao.DAOException;
import com.produccion.dao.EstacionesDAO;
import com.produccion.dao.LotedeproduccionDAO;
import com.produccion.dao.MaterialesDAO;
import com.produccion.dao.PProduccionDAO;
import com.produccion.dao.PedidoDAO;
import com.produccion.dao.ProductoDAO;
import com.produccion.dao.ProductosproducidosDAO;
import com.produccion.datatransfers.LoteDTO;
import com.produccion.datatransfers.PProducidosDTO;
import com.produccion.datatransfers.MaterialCantidadDTO;
import com.produccion.datatransfers.PedidoDTO;
import com.produccion.datatransfers.PlanDTO;
import com.produccion.datatransfers.ProductoProducidoDTO;
import com.produccion.datatransfers.ProductosCantidadDTO;
import com.produccion.entities.Cantidadmp;
import com.produccion.entities.Cantidadproductos;
import com.produccion.entities.Ejecucion;
import com.produccion.entities.Lotedeproduccion;
import com.produccion.entities.Material;
import com.produccion.entities.Pedidos;
import com.produccion.entities.Planproduccion;
import com.produccion.entities.Producto;
import com.produccion.entities.Productosproducidos;
import com.produccion.entities.Recurso;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import utils.MapperUtils;

/**
 *
 * @author Diego
 */
@Service
@Transactional
public class PProduccionServiceImpl implements PProduccionService{
    @Autowired
    private PProduccionDAO pproducciondao;
    @Autowired
    private MaterialesDAO materialdao;
    @Autowired
    private PedidoDAO pedidodao;
    @Autowired
    private ProductosproducidosDAO productosdao;
    @Autowired
    private EstacionesDAO estacionesdao;
    @Autowired
    private ProductoDAO productodao;
    @Autowired
    private ProductosproducidosDAO ppdao;
    @Autowired
    private ProductoService pservice;
    @Autowired
    private LotedeproduccionDAO lotedao;
    
    private ModelMapper modelMapper = new ModelMapper();
    
    @Override
    public List<PlanDTO> getPlanproducciones() throws DAOException{
        List<PlanDTO> planesdto = new ArrayList<>();
        try {
           List<Planproduccion> planes = pproducciondao.findAll(Planproduccion.class);
            for (Iterator<Planproduccion> iterator = planes.iterator(); iterator.hasNext();) {
               Planproduccion plandeproduccion = iterator.next();
               List<ProductosCantidadDTO> lista = new ArrayList<>();
               for (Cantidadproductos next : plandeproduccion.getProductos()) {
                   ProductosCantidadDTO dto = new ProductosCantidadDTO(next.getProducto().getIdproducto(),next.getCantidad());
                   lista.add(dto);
               }
               PlanDTO plandto = new PlanDTO(plandeproduccion.getOrdendeproduccion(), plandeproduccion.getFechainicioestimada(), 
               plandeproduccion.getFechafinestimada(), plandeproduccion.getDescripcion(), lista);
               planesdto.add(plandto);
            }
        } catch (Exception e) {
            throw new DAOException("Hubo un inconveniente y no se pudo listar los planes de produccion", e);
        }
        return planesdto;
    }
    
    @Override
    public void deletePlanproduccion(Integer idPlanproduccion) throws DAOException{
        try {
            pproducciondao.deleteById(Planproduccion.class, idPlanproduccion);
        } catch (Exception e) {
            throw new DAOException("Hubo un inconveniente y no se pudo eliminar el plan solicitado", e);
        }
    }
    
    /**
     * Crea un plan de produccion y devuelve el id o indica el tiempo faltante
     * 
     * @param plan El plan de produccion a crear
     * @return devuelve el id (numero postitivo) si se crea el plan o bien
     *         la diferencia en segundos que falta para crear el plan
     * @throws com.produccion.dao.DAOException
    */
    @Override
    public int addPlanproduccion(PlanDTO plan) throws DAOException{
        int id = 0;
        try {
            Planproduccion planproduccion = new Planproduccion(plan.getOrdendeproduccion(),plan.getFechainicioestimada(),plan.getFechafinestimada(),plan.getDescripcion());
            id = pproducciondao.save(planproduccion);
            planproduccion = pproducciondao.findByID(Planproduccion.class, id);
            for (ProductosCantidadDTO next : plan.getProductos()) {
                // Se agregan los productos y sus cantidades
                Producto producto = productodao.findByID(Producto.class, next.getIdProducto());
                planproduccion.addProducto(producto, next.getCantidad());

                // Se crea el lote de un determinado producto
                Lotedeproduccion lote = new Lotedeproduccion();
                lote.setProducto(producto);
                planproduccion.addLotedeproduccion(lote);
                lote.setPlanProduccion(planproduccion);
                for (int i = 0; i < pservice.cantidadAProducir(next.getIdProducto(), next.getCantidad()); i++) {
                    Productosproducidos pp = new Productosproducidos();
                    pp.setLotedeproduccion(lote);
                    lote.addProductosProducidos(pp);
                }

                // Se crean las ejecuciones segun los recursos
                List<Recurso> recursos = new ArrayList<>();
                recursos.addAll(producto.getCircuitoActivo().getRecursos());
                    for (Recurso recurso : recursos){
                        Date ultimaFecha = estacionesdao.ultimaEjecucion(recurso.getEstacion().getIdestaciones(), plan.getFechainicioestimada(), plan.getFechafinestimada());
                        Ejecucion ejecucion = new Ejecucion();
                        ejecucion.setRecurso(recurso);
                        ejecucion.setLotedeproduccion(lote);
                        ejecucion.setFechainicio(ultimaFecha);
                        Calendar calendar = Calendar.getInstance();
                        calendar.setTime(ultimaFecha);
                        calendar.add(Calendar.SECOND, (int) recurso.getTiempo());
                        ejecucion.setFechafin(calendar.getTime());
                        lote.getEjecuciones().add(ejecucion);
                    }

            }
        } catch (Exception e) {
            Logger.getLogger(PProduccionServiceImpl.class.getName()).log(Level.SEVERE, null, e);
            throw new DAOException("Hubo un inconveniente y no se pudo crear el plan solicitado", e);
        }
        return id;
    }
    
    @Override
    public List<PedidoDTO> getPedidos(Integer idPProduccion) throws DAOException{
        List<PedidoDTO> pedidosdto = null;
        try {
            Planproduccion planproduccion = pproducciondao.findByID(Planproduccion.class, idPProduccion);
            for (Pedidos next : planproduccion.getPedidos()) {
                List<MaterialCantidadDTO> materiales = new ArrayList<>();
                next.getMateriales().stream().map((material) -> new MaterialCantidadDTO(material.getMaterial().getIdmaterial(),material.getCantidad())).forEachOrdered((matdto) -> {
                    materiales.add(matdto);
                });
                PedidoDTO pedido = new PedidoDTO(idPProduccion, next.getFechaesperada(), next.isAtendido(), materiales);
                pedidosdto.add(pedido);
            }
        } catch (Exception e) {
            throw new DAOException("Hubo un inconveniente y no se pudo traer los pedidos solicitados", e);
        }
        return pedidosdto;
    }
    
    @Override
    public PedidoDTO getPedidoById(Integer idPedido) throws DAOException{
        PedidoDTO pedidodto = null;
        List<MaterialCantidadDTO> lista = new ArrayList<>();
        try {
            Pedidos pedido = pedidodao.findByID(Pedidos.class,idPedido);
            for (Iterator<Cantidadmp> iterator = pedido.getMateriales().iterator(); iterator.hasNext();) {
                Cantidadmp next = iterator.next();
                MaterialCantidadDTO cantidadmaterial = new MaterialCantidadDTO(next.getMaterial().getIdmaterial(), next.getCantidad());
                lista.add(cantidadmaterial);
            }
            pedidodto = new PedidoDTO(idPedido,pedido.getFechaesperada(),pedido.isAtendido(),lista);
        } catch (Exception e) {
            throw new DAOException("Hubo un inconveniente y no se pudo traer el pedido solicitado", e);
        }
        return pedidodto;
    }
    
    @Override
    public void addPedido(Integer idplanproduccion, Pedidos pedido, List<MaterialCantidadDTO> pedidos) throws DAOException{
        pedido.setAtendido(false);
        try {
            for (MaterialCantidadDTO next : pedidos) {
                Material material = materialdao.findByID(Material.class, next.getIdMaterial());
                pedido.addMateriales(material, idplanproduccion);
            }
            Planproduccion plan = pproducciondao.findByID(Planproduccion.class,idplanproduccion);
            plan.addPedido(pedido);
        } catch (Exception e) {
            throw new DAOException("Hubo un inconveniente y no se pudo agregar el pedido solicitado", e);
        }
    }
    
    @Override
    public void updatePlanproduccion(Planproduccion planproduccion) throws DAOException {
        try {
            pproducciondao.update(planproduccion);
        } catch (Exception e) {
            throw new DAOException("Hubo un inconveniente y no se pudo actualizar el pedido solicitado", e);
        }
    }
    
    @Override
    public PlanDTO getPlanproduccionById(Integer idPlanproduccion) throws DAOException {
        PlanDTO plandto = null;
        List<ProductosCantidadDTO> lista = new ArrayList<>();
        try {
            Planproduccion planproduccion = pproducciondao.findByID(Planproduccion.class, idPlanproduccion);
            for (Iterator<Cantidadproductos> iterator = planproduccion.getProductos().iterator(); iterator.hasNext();) {
                Cantidadproductos next = iterator.next();
                ProductosCantidadDTO dto = new ProductosCantidadDTO(next.getProducto().getIdproducto(),next.getCantidad());
                lista.add(dto);
            }
            plandto = new PlanDTO(planproduccion.getOrdendeproduccion(), planproduccion.getFechainicioestimada(), 
                    planproduccion.getFechafinestimada(), planproduccion.getDescripcion(), lista);
        } catch (Exception e) {
            throw new DAOException("Hubo un inconveniente y no se pudo traer el plan de produccion solicitado", e);
        }
        return plandto;
    }
    
    @Override
    public List<LoteDTO> getLotesPorPlan(Integer idPProduccion) throws DAOException {
        List<LoteDTO> lotes = new ArrayList<>();
        try {
            Planproduccion planproduccion = pproducciondao.findByID(Planproduccion.class, idPProduccion);
            lotes = MapperUtils.mapAll(planproduccion.getLotedeproduccion(),LoteDTO.class);
        } catch (Exception e) {
            throw new DAOException("Hubo un inconveniente y no se pudo traer los lotes solicitados", e);
        }
        
        return lotes;
    }
    
    @Override
    public List<PProducidosDTO> getProductosProducidosPorLote(Integer idLote) throws DAOException {
        List<PProducidosDTO> lotes = new ArrayList<>();
        try {
            lotes = MapperUtils.mapAll(productosdao.getProductosPorLote(idLote),PProducidosDTO.class);
        } catch (Exception e) {
            throw new DAOException("Hubo un inconveniente y no se pudo traer las producciones del lote solicitado", e);
        }
        
        return lotes;
    }
    
    @Override
    public void updateProductoProducido(Integer id, ProductoProducidoDTO pp) throws DAOException {
        try {
            Productosproducidos pproducido = modelMapper.map(pp,Productosproducidos.class);
            pproducido.setIdpproducido(id);
            ppdao.update(pproducido);
        } catch (Exception e) {
            throw new DAOException("Hubo un inconveniente y no se pudo traer las producciones del lote solicitado", e);
        }
    }
    
    @Override
    public List<Ejecucion> getEjecucionesPorLote(Integer idLote) throws DAOException {
        List<Ejecucion> ejecuciones = null;
        try {
            Lotedeproduccion lote = lotedao.findByID(Lotedeproduccion.class, idLote);
            ejecuciones = lote.getEjecuciones();
        } catch (Exception e) {
            throw new DAOException("Hubo un inconveniente y no se pudo traer las producciones del lote solicitado", e);
        }
        return ejecuciones;
    }
}
