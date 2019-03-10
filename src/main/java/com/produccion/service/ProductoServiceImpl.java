/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.produccion.service;

import com.produccion.dao.CircuitoDAO;
import com.produccion.dao.DAOException;
import com.produccion.dao.EstacionesDAO;
import com.produccion.dao.MaterialesDAO;
import com.produccion.dao.ProductoDAO;
import com.produccion.dao.RecetaDAO;
import com.produccion.datatransfers.CircuitoDTO;
import com.produccion.datatransfers.MaterialCantidadDTO;
import com.produccion.datatransfers.ProductoDTO;
import com.produccion.datatransfers.ProductosCantidadDTO;
import com.produccion.entities.Cantidadmr;
import com.produccion.entities.Circuito;
import com.produccion.entities.Estaciones;
import com.produccion.entities.Material;
import com.produccion.entities.Producto;
import com.produccion.entities.Receta;
import com.produccion.entities.Recurso;
import java.util.ArrayList;
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
public class ProductoServiceImpl implements ProductoService{
    
    @Autowired
    private ProductoDAO productodao;
    @Autowired
    private MaterialesDAO materialdao;
    @Autowired
    private RecetaDAO recetasdao;
    @Autowired
    private CircuitoDAO circuitodao;
    @Autowired
    private EstacionesDAO estacionesdao;
    
    private ModelMapper modelMapper = new ModelMapper();
    
    @Override
    public Integer addProducto(ProductoDTO productodto) throws DAOException{
        Integer id = -1;
        try {
            Producto producto = modelMapper.map(productodto, Producto.class);
            id = productodao.save(producto);
        } catch (Exception e) {
            throw new DAOException("Hubo un inconveniente y no se pudo agregar el producto solicitado", e);
        }
        return id;
    }
    
    @Override
    public void updateProducto(Integer idProducto, ProductoDTO productodto) throws DAOException{
        try {
            productodto.setIdproducto(idProducto);
            Producto producto = modelMapper.map(productodto, Producto.class);
            productodao.update(producto);
        } catch (Exception e) {
            Logger.getLogger(ProductoServiceImpl.class.getName()).log(Level.SEVERE, null, e);
            throw new DAOException("Hubo un inconveniente y no se pudo actualizar el producto solicitado", e);
        }
    }
    
    @Override
    public ProductoDTO getProducto(Integer idProducto) throws DAOException{
        ProductoDTO productodto = null;
        try {
            Producto producto = productodao.findByID(Producto.class,idProducto);
            if(producto == null){
                throw new DAOException("Producto inexistente");
            }else{
                productodto = modelMapper.map(producto, ProductoDTO.class);
            }
        } catch (Exception e) {
            
            throw new DAOException("Hubo un inconveniente y no se pudo traer el producto solicitado", e);
        }
        
        return productodto;
    }
    
    @Override
    public List<ProductoDTO> getProductos() throws DAOException{
        List<ProductoDTO> productosdto = null;
        try {
            List<Producto> productos = productodao.findAll(Producto.class);
            productosdto = MapperUtils.mapAll(productos, ProductoDTO.class);
        } catch (Exception e) {
            throw new DAOException("Hubo un inconveniente y no se pudo listar los productos", e);
        }
        return productosdto;
    }
    
        @Override
    public void deleteProducto(Integer idProducto) throws DAOException{
        try {
            productodao.deleteById(Producto.class,idProducto);
        } catch (Exception e) {
            throw new DAOException("Hubo un inconveniente y no se pudo eliminar el producto", e);
        }
    }
    
    @Override
    public Integer cantidadAProducir(Integer idProducto,Integer cantidad) throws DAOException{
        Integer nro = cantidad;
        try {
            Integer producidos = productodao.cantidadDeProducciones(idProducto);
            Integer fallos = productodao.cantidadDeFallos(idProducto);
            if(producidos!=0) nro = 1 + Math.round(fallos/producidos);
        } catch (Exception e) {
            throw new DAOException("Hubo un inconveniente y no se pudo realizar el calculo solicitado", e);
        }
        
        return (cantidad*nro);
    }
    
    @Override
    public boolean esPosibleProducir(List<ProductosCantidadDTO> aproducir, Date inicio, Date fin) throws DAOException{
        boolean flag = true;
        try {
            List<List> listsEstacionesLibres = new ArrayList<>();
            for (ProductosCantidadDTO produccion : aproducir) {
                Integer cantidad = this.cantidadAProducir(produccion.getIdProducto(), produccion.getCantidad());
                Producto producto = productodao.findByID(Producto.class, produccion.getIdProducto());
                int idCircuito = producto.getCircuitoActivo().getIdcircuito();
                List<Estaciones> estaciones = new ArrayList<>();
                for (Recurso recurso : producto.getCircuitoActivo().getRecursos()) {
                    estaciones.add(recurso.getEstacion());
                    boolean estado = true;
                    for (List list : listsEstacionesLibres) {
                        if (list.get(0).equals(recurso.getEstaciones().getIdestaciones()))  estado =false; break;
                    }
                    if (estado) {
                        List<Integer> temporalList = new ArrayList<>();
                        long minlibres = estacionesdao.minutosLibres(recurso.getEstacion().getIdestaciones(), inicio, fin);
                        int i = (int) minlibres;
                        temporalList.add(recurso.getEstacion().getIdestaciones());
                        temporalList.add(i);
                        listsEstacionesLibres.add(temporalList);
                    }
                }
                for (Estaciones estacion : estaciones) {
                    int minL = -1;
                    int duracion = (int) estacionesdao.tiempoDelRecurso(estacion.getIdestaciones(), idCircuito);
                    for (List listat : listsEstacionesLibres) {
                        if (listat.get(0).equals(estacion.getIdestaciones())) {
                            int disponibilidad = (int) listat.get(1);
                            minL = disponibilidad - duracion*cantidad;
                            listsEstacionesLibres.remove(listat);
                            List<Integer> temporalList = new ArrayList<>();
                            temporalList.add(estacion.getIdestaciones());
                            temporalList.add(minL);
                            listsEstacionesLibres.add(temporalList);
                            break;
                        }
                    }
                    if (minL<0) flag = false; break;
                }
            }
        } catch (Exception e) {
            Logger.getLogger(ProductoServiceImpl.class.getName()).log(Level.SEVERE, null, e);
            throw new DAOException("Hubo un inconveniente y no se pudo realizar el calculo solicitado", e);
        }
        
        return flag;
    }
    
    @Override
    public List<MaterialCantidadDTO> getReceta(Integer idproducto) throws DAOException{
        List<MaterialCantidadDTO> recetasdto = new ArrayList<>();
        try {
            Producto producto = productodao.findByID(Producto.class, idproducto);
            List<Cantidadmr> listado = producto.getRecetaActiva().getMateriales();
            for (Iterator<Cantidadmr> iterator = listado.iterator(); iterator.hasNext();) {
                Cantidadmr next = iterator.next();
                MaterialCantidadDTO receta = new MaterialCantidadDTO();
                receta.setCantidad(next.getCantidad());
                receta.setIdMaterial(next.getMaterial().getIdmaterial());
                recetasdto.add(receta);
            }
        } catch (Exception e) {
            throw new DAOException("Hubo un inconveniente y no se pudo traer la receta solicitada", e);
        }
        return recetasdto;
    }
    
    @Override
    public List<CircuitoDTO> getCircuito(Integer idproducto) throws DAOException{
        List<CircuitoDTO> circuitosdto = new ArrayList<>();
        try {
            Producto producto = productodao.findByID(Producto.class, idproducto);
            List<Recurso> listado = producto.getCircuitoActivo().getRecursos();
            for (Iterator<Recurso> iterator = listado.iterator(); iterator.hasNext();) {
                CircuitoDTO circuito = new CircuitoDTO();
                Recurso next = iterator.next();
                circuito.setTiempo((int) next.getTiempo());
                circuito.setEstacionid(next.getEstacion().getIdestaciones());
                circuito.setMaterialId(next.getMaterial().getIdmaterial());
                circuito.setOrden(next.getOrden());
                circuito.setCantidadMaterial(next.getCantidadmaterial());
                circuitosdto.add(circuito);
            }
        } catch (Exception e) {
            throw new DAOException("Hubo un inconveniente y no se pudo traer el circuito solicitado", e);
        }
        return circuitosdto;
    }
    
    @Override
    public void addReceta(Integer idproducto, List<MaterialCantidadDTO> recetas) throws DAOException{
        try {
            Producto producto = productodao.findByID(Producto.class, idproducto);
            if (!producto.getRecetaActiva().equals(null)) {
                producto.getRecetaActiva().setFechabaja(new Date());
            }
                Receta receta = new Receta(producto);
                recetasdao.save(receta);
            for (MaterialCantidadDTO next : recetas) {
                Material material = materialdao.findByID(Material.class,next.getIdMaterial());
                receta.addMaterial(material, next.getCantidad());
            }
            
        } catch (Exception e) {
            throw new DAOException("Hubo un inconveniente y no se pudo agregar la receta", e);
        }
    }
    
    @Override
    public boolean existeElProducto(Integer idproducto){
        boolean existe = false;
            Producto producto = null;
            producto = productodao.findByID(Producto.class, idproducto);
            if (producto != null) existe = true;
            
        return existe;
    }
    
    @Override
    public void addCircuitos(Integer idproducto, List<CircuitoDTO> circuitos) throws DAOException{
        try {
            Producto producto = productodao.findByID(Producto.class, idproducto);
            if (!producto.getRecetaActiva().equals(null)) {
                producto.getCircuitoActivo().setFechabaja(new Date());
            }
                Circuito circuito = new Circuito(producto);
                int id = circuitodao.save(circuito);
                circuito = circuitodao.findByID(Circuito.class, id);
            for (CircuitoDTO next: circuitos) {
                Material material = materialdao.findByID(Material.class, next.getMaterialId());
                Estaciones estacion = estacionesdao.findByID(Estaciones.class, next.getEstacionid());
                Recurso recurso = new Recurso(next.getCantidadMaterial(), next.getOrden(), material, estacion);
                recurso.setCircuito(circuito);
                circuito.addRecurso(recurso);
            }
            
        } catch (Exception e) {
            throw new DAOException("Hubo un inconveniente y no se pudo agregar el nuevo circuito", e);
        }
    }
   
}
