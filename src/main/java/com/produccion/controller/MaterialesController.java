/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.produccion.controller;

import com.produccion.dao.DAOException;
import com.produccion.datatransfers.MaterialDTO;
import com.produccion.service.MaterialesService;
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
public class MaterialesController {
    @Autowired
    private MaterialesService materialesservice;
    
    @RequestMapping(value="/materiales", produces="application/json; charset=UTF-8", method = RequestMethod.GET)
    public @ResponseBody ResponseEntity<List<MaterialDTO>> getMaterialesList(){
        List<MaterialDTO> materiales = null;
        try {
            materiales = materialesservice.getMateriales();
        } catch (DAOException ex) {
            return new ResponseEntity(ex.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(materiales, HttpStatus.OK);
    }
    
    @RequestMapping(value="/materiales/{id}", method = RequestMethod.DELETE)
    public @ResponseBody ResponseEntity deleteMaterial(@PathVariable Integer id){
        try {
            materialesservice.deleteMaterial(id);
        } catch (DAOException ex) {
            return new ResponseEntity(ex.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity(HttpStatus.OK);
    }
    
    @RequestMapping(value="/materiales/{id}", produces="application/json; charset=UTF-8", method = RequestMethod.GET)
    public @ResponseBody ResponseEntity<MaterialDTO> getMaterial(@PathVariable Integer id){
        MaterialDTO material = new MaterialDTO();
        try {
            material = materialesservice.getMaterialById(id);
        } catch (DAOException ex) {
            if (material.getIdmaterial() == null) {
                String mensaje = "El identificador ingresado es inexistente";
                return new ResponseEntity(mensaje, HttpStatus.BAD_REQUEST);
            }else{
                return new ResponseEntity(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
        return new ResponseEntity<>(material, HttpStatus.OK);
    }
    
    @RequestMapping(value="/materiales", method = RequestMethod.POST)
    public @ResponseBody ResponseEntity<Integer> addMaterial(@RequestBody MaterialDTO material){
        Integer id = -1;
        try {
            id = materialesservice.addMaterial(material);
        } catch (DAOException ex) {
            if (id==-1) {
                String mensaje = "El material fue ingresado incorrectamente";
                return new ResponseEntity(mensaje, HttpStatus.BAD_REQUEST);
            } else {
                return new ResponseEntity(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
        return new ResponseEntity<>(id, HttpStatus.OK);
    }   
}
