/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.produccion.controller;

import com.produccion.dao.DAOException;
import com.produccion.datatransfers.CircuitoDTO;
import com.produccion.datatransfers.MaterialCantidadDTO;
import com.produccion.datatransfers.ProductoDTO;
import com.produccion.entities.Producto;
import com.produccion.service.ProductoService;
import java.util.ArrayList;
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
public class ProductoController {
    @Autowired
    private ProductoService productoservice;
    
    @RequestMapping(value="/productos", produces="application/json; charset=UTF-8", method = RequestMethod.GET)
    public @ResponseBody ResponseEntity<List<ProductoDTO>> getProductosList(){
        List<ProductoDTO> productos = null;
        try {
            productos = productoservice.getProductos();
            
        } catch (DAOException ex) {
            return new ResponseEntity(ex.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(productos, HttpStatus.OK);
    }

    @RequestMapping(value="/productos/{id}", produces="application/json; charset=UTF-8", method = RequestMethod.GET)
    public @ResponseBody ResponseEntity<ProductoDTO> getProducto(@PathVariable Integer id){
        ProductoDTO producto = new ProductoDTO();
        try {
            producto = productoservice.getProducto(id);
        } catch (DAOException ex) {
            if (producto.getIdproducto() == null) {
                String mensaje = "El identificador ingresado es inexistente";
                return new ResponseEntity(mensaje, HttpStatus.BAD_REQUEST);
            }else{
                return new ResponseEntity(ex.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
        return new ResponseEntity<>(producto, HttpStatus.OK);
    }

    @RequestMapping(value="/productos/{id}", method = RequestMethod.DELETE)
    public @ResponseBody ResponseEntity deleteProducto(@PathVariable Integer id){
        try {
            productoservice.deleteProducto(id);
        } catch (DAOException ex) {
            return new ResponseEntity(ex.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity(HttpStatus.OK);
    }
    
    @RequestMapping(value="/productos", method = RequestMethod.POST)
    public @ResponseBody ResponseEntity<Integer> addProducto(@RequestBody ProductoDTO producto){
        Integer id = -1;
        try {
            id = productoservice.addProducto(producto);
        } catch (DAOException ex) {
            if (id==-1) {
                String mensaje = "El producto fue ingresado incorrectamente";
                return new ResponseEntity(mensaje, HttpStatus.BAD_REQUEST);
            } else {
                return new ResponseEntity(ex.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
        
        return new ResponseEntity<>(id, HttpStatus.OK);
    }
    
    @RequestMapping(value="/productos/{id}", produces="application/json; charset=UTF-8", method = RequestMethod.PUT)
    public @ResponseBody ResponseEntity updateProducto(@PathVariable Integer id,@RequestBody ProductoDTO producto){
        try {
            productoservice.updateProducto(id, producto);
        } catch (DAOException ex) {
            return new ResponseEntity(ex.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
        
        return new ResponseEntity(HttpStatus.OK);
    }

    @RequestMapping(value="/productos/{idProducto}/receta", produces="application/json; charset=UTF-8", method = RequestMethod.GET)
    public @ResponseBody ResponseEntity<List<MaterialCantidadDTO>> getReceta(@PathVariable Integer idProducto){
        List<MaterialCantidadDTO> lista = null;
        try {
            lista = productoservice.getReceta(idProducto);
        } catch (DAOException ex) {
            if(lista == null){
                String mensaje = "No existe una receta para este producto";
                return new ResponseEntity(mensaje,HttpStatus.BAD_REQUEST);
            }else{
                return new ResponseEntity(ex.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
        return new ResponseEntity<>(lista, HttpStatus.OK);
    }
    
    
    @RequestMapping(value="/productos/{idProducto}/circuito", produces="application/json; charset=UTF-8", method = RequestMethod.GET)
    public @ResponseBody ResponseEntity<List<CircuitoDTO>> getCircuito(@PathVariable Integer idProducto){
        List<CircuitoDTO> lista = null;
        try {
            lista = productoservice.getCircuito(idProducto);
        } catch (DAOException ex) {
            if(lista == null){
                String mensaje = "No existe un circuito para este producto";
                return new ResponseEntity(mensaje,HttpStatus.BAD_REQUEST);
            }else{
                return new ResponseEntity(ex.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
        return new ResponseEntity<>(lista, HttpStatus.OK);
    }
    
    
    @RequestMapping(value="/productos/{idProducto}/receta", produces="application/json; charset=UTF-8", method = RequestMethod.POST)
    public @ResponseBody ResponseEntity newRecetas(@RequestBody List<MaterialCantidadDTO> recetas, @PathVariable Integer idProducto){
        try {
            productoservice.addReceta(idProducto, recetas);
        } catch (DAOException ex) {
            return new ResponseEntity(ex.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity(HttpStatus.OK);
    }

    @RequestMapping(value="/productos/{idProducto}/circuito", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity newCircuitos(@RequestBody List<CircuitoDTO> circuitos,  @PathVariable Integer idProducto){
        try {
            productoservice.addCircuitos(idProducto, circuitos);
        } catch (DAOException ex) {
            return new ResponseEntity(ex.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
        
        return new ResponseEntity(HttpStatus.OK);
    }
}
