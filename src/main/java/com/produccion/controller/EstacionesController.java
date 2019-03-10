/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.produccion.controller;

import com.produccion.dao.DAOException;
import com.produccion.datatransfers.EstacionDTO;
import com.produccion.service.EstacionesService;
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
import org.springframework.web.client.HttpStatusCodeException;

/**
 *
 * @author Diego
 */
@Controller
public class EstacionesController {
    @Autowired
    private EstacionesService estacionesservice;
    
    
    @RequestMapping(value="/estaciones", produces="application/json; charset=UTF-8", method = RequestMethod.GET)
    public @ResponseBody ResponseEntity<List<EstacionDTO>> getEstacionesList(){
        List<EstacionDTO> estaciones = null;
        try {
            estaciones = estacionesservice.getEstaciones();
        } catch (DAOException ex) {
            return new ResponseEntity(ex.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(estaciones, HttpStatus.OK);
    }
    
    
    @RequestMapping(value="/estaciones/{id}", method = RequestMethod.DELETE)
    public @ResponseBody ResponseEntity deleteEstacion(@PathVariable Integer id){
        try {
            estacionesservice.deleteEstaciones(id);
        } catch (DAOException ex) {
            return new ResponseEntity(ex.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity(HttpStatus.OK);
    }
    
    
    @RequestMapping(value="/estaciones/{id}", produces="application/json; charset=UTF-8", method = RequestMethod.GET)
    public @ResponseBody ResponseEntity<EstacionDTO> getEstacion(@PathVariable Integer id){
        EstacionDTO estacion = null;
        try {
            estacion = estacionesservice.getEstacionById(id);
        } catch (DAOException ex) {
            if (estacion == null) {
                String mensaje = "El identificador ingresado es inexistente";
                return new ResponseEntity(mensaje, HttpStatus.NOT_FOUND);
            }else{
                return new ResponseEntity(ex.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
        
        return new ResponseEntity<>(estacion, HttpStatus.OK);
    }
    
    
    @RequestMapping(value="/estaciones", method = RequestMethod.POST)
    public @ResponseBody Integer addEstacion(@RequestBody EstacionDTO estacion){
        Integer id = -1;
        try {
            id = estacionesservice.addEstacion(estacion);
        } catch (DAOException ex) {
            throw new HttpStatusCodeException(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage()) {};
        }
        return id;
    }   
    
    @RequestMapping(value="/estaciones/{id}", method = RequestMethod.PUT)
    public @ResponseBody ResponseEntity updateEstacion(@PathVariable Integer id, @RequestBody EstacionDTO estacion){
        try {
            estacion.setIdestaciones(id);
            estacionesservice.updateEstacion(estacion);
        } catch (DAOException ex) {
            return new ResponseEntity(ex.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity(HttpStatus.OK);
    }   
}
