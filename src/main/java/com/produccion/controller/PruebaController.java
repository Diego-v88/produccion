/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.produccion.controller;

import com.produccion.dao.DAOException;
import com.produccion.datatransfers.ProductoDTO;
import com.produccion.service.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author Diego
 */
@Controller
public class PruebaController {
    
    @Autowired
    private ProductoService productoservice;
    
   @RequestMapping(value="/a", method = RequestMethod.GET)
    public String showIndex(){
        return "index";
    }
    
    @RequestMapping(value="/", method = RequestMethod.GET)
    public String showTemplate(){
        return "add";
    }
    
    @RequestMapping(value="/add", method = RequestMethod.PUT)
    public @ResponseBody String addProducto(){

        ProductoDTO producto = new ProductoDTO("Otrooooo", "puerta", 13, 12, 45, 12, "Una puerta");

        try {
            productoservice.addProducto(producto);
        } catch (DAOException ex) {
            
        }

        return "add";
    }
    

}
