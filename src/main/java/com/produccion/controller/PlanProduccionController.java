/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.produccion.controller;

import com.produccion.dao.DAOException;
import com.produccion.datatransfers.LoteDTO;
import com.produccion.datatransfers.PProducidosDTO;
import com.produccion.datatransfers.MaterialCantidadDTO;
import com.produccion.datatransfers.PedidoDTO;
import com.produccion.datatransfers.PlanDTO;
import com.produccion.datatransfers.ProductoProducidoDTO;
import com.produccion.datatransfers.ProductosCantidadDTO;
import com.produccion.entities.Ejecucion;
import com.produccion.entities.Pedidos;
import com.produccion.entities.Planproduccion;
import com.produccion.service.PProduccionService;
import com.produccion.service.ProductoService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author Diego
 */
@Controller
public class PlanProduccionController {
    @Autowired
    private PProduccionService pproduccionservice;
    @Autowired
    private ProductoService pservice;
    
    @RequestMapping(value="/planproduccion", produces="application/json; charset=UTF-8", method = RequestMethod.GET)
    public @ResponseBody ResponseEntity<List<PlanDTO>> getPlanproduccionesList(){
        List<PlanDTO> planes = null;
        try {
            planes = pproduccionservice.getPlanproducciones();
        } catch (DAOException ex) {
            return new ResponseEntity(ex.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(planes,HttpStatus.OK);
    }
    
    @RequestMapping(value="/planproduccion/{id}", method = RequestMethod.DELETE)
    public @ResponseBody ResponseEntity deletePlanproduccion(@PathVariable Integer id){
        try {
            pproduccionservice.deletePlanproduccion(id);
        } catch (DAOException ex) {
            return new ResponseEntity(ex.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity(HttpStatus.OK);
    }
    
    @RequestMapping(value="/planproduccion/{id}", produces="application/json; charset=UTF-8", method = RequestMethod.GET)
    public @ResponseBody ResponseEntity<PlanDTO> getPlanproduccion(@PathVariable Integer id){
        PlanDTO plan = null;
        try {
            plan = pproduccionservice.getPlanproduccionById(id);
        } catch (DAOException ex) {
            return new ResponseEntity(ex.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(plan, HttpStatus.OK);
    }
    
    @RequestMapping(value="/planproduccion", method = RequestMethod.POST)
    public @ResponseBody ResponseEntity addPlanproduccion(@RequestBody PlanDTO plan){
        if(plan.getFechafinestimada().before(plan.getFechainicioestimada())){
            return new ResponseEntity("Las fechas ingresadas no son correctas",HttpStatus.BAD_REQUEST);
        } else {
            plan.getProductos().removeIf(prod -> prod.getCantidad() <= 0);
            if(plan.getProductos().isEmpty()){
                return new ResponseEntity("Como minimo debe ingresar un producto",HttpStatus.BAD_REQUEST);
            } else {
                boolean flagProductos = false;
                for (ProductosCantidadDTO producto : plan.getProductos()) {
                    flagProductos = pservice.existeElProducto(producto.getIdProducto());
                    if (flagProductos == false) break;
                }
                if (flagProductos==false) {
                    return new ResponseEntity("Los productos ingresados no son correctos",HttpStatus.BAD_REQUEST);
                } else {
                    boolean flag = true;
                    try {
                        flag = pservice.esPosibleProducir(plan.getProductos(), plan.getFechainicioestimada(), plan.getFechafinestimada());
                        } catch (DAOException ex) {
                            return new ResponseEntity(ex.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
                    }
                    
                    if(!flag){
                        return new ResponseEntity("No hay recursos necesarios para crear el plan",HttpStatus.BAD_REQUEST);
                    }else{
                        try {
                            pproduccionservice.addPlanproduccion(plan);
                        } catch (DAOException ex) {
                            return new ResponseEntity(ex.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
                        }
                    }
                }
            }
        }
        return new ResponseEntity(HttpStatus.OK);
    }    

    @RequestMapping(value="/planproduccion/{idPProduccion}/pedidos", method = RequestMethod.GET)
    public @ResponseBody ResponseEntity<List<PedidoDTO>> getPedidos(@PathVariable Integer idPProduccion){
        List<PedidoDTO> lista = null;
        try {
            lista = pproduccionservice.getPedidos(idPProduccion);
        } catch (DAOException ex) {
            return new ResponseEntity(ex.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
        
        return new ResponseEntity<>(lista, HttpStatus.OK);
    }
    
    
    @RequestMapping(value="/planproduccion/{idPProduccion}/pedidos", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity newPedido(@PathVariable Integer idPProduccion, @RequestBody List<MaterialCantidadDTO> pedidos, @RequestBody Pedidos pedido){
        try {
            pproduccionservice.addPedido(idPProduccion, pedido,pedidos);
        } catch (DAOException ex) {
            return new ResponseEntity(ex.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity(HttpStatus.OK);
    } 
    
    @RequestMapping(value="/planproduccion/{idPProduccion}/pedidos/{idPedido}", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<PedidoDTO> getPedidoById(@PathVariable Integer idPedido){
        PedidoDTO pedidodto = new PedidoDTO();
        try {
            pedidodto = pproduccionservice.getPedidoById(idPedido);
        } catch (DAOException ex) {
            return new ResponseEntity(ex.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(pedidodto,HttpStatus.OK);
    }
    
    @RequestMapping(value="/planproduccion/{idPProduccion}/lotesdeproduccion", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<List<LoteDTO>> getLotesPorPlan(@PathVariable Integer idPProduccion){
        List<LoteDTO> lotes = null;
        try {
            lotes = pproduccionservice.getLotesPorPlan(idPProduccion);
        } catch (DAOException ex) {
            return new ResponseEntity(ex.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(lotes,HttpStatus.OK);
    }
    
    @RequestMapping(value="/planproduccion/{idPProduccion}/lotesdeproduccion/{idLote}/productosproducidos", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<List<PProducidosDTO>> getProductosProducidosPorLote(@PathVariable Integer idLote){
        List<PProducidosDTO> productosproducidos = null;
        try {
            productosproducidos = pproduccionservice.getProductosProducidosPorLote(idLote);
        } catch (DAOException ex) {
            return new ResponseEntity(ex.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(productosproducidos, HttpStatus.OK);
    }
    
    @RequestMapping(value="/planproduccion/{idPProduccion}/lotesdeproduccion/{idLote}/productosproducidos/{idPP}", method = RequestMethod.PUT)
    @ResponseBody
    public ResponseEntity updateProductoProducido(@PathVariable Integer idPP, @PathVariable Integer idLote,@RequestBody ProductoProducidoDTO pp){
        try {
            pproduccionservice.updateProductoProducido(idPP, pp);
        } catch (DAOException ex) {
            return new ResponseEntity(ex.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }
    
    @RequestMapping(value="/planproduccion/{idPProduccion}/lotesdeproduccion/{idLote}/ejecuciones", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<List<Ejecucion>> getEjecucionesPorLote(@PathVariable Integer idPProduccion,@PathVariable Integer idLote){
        List<Ejecucion> ejecuciones = null;
        try {
            ejecuciones = pproduccionservice.getEjecucionesPorLote(idLote);
        } catch (DAOException ex) {
            return new ResponseEntity(ex.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(ejecuciones, HttpStatus.OK);
    }

}
