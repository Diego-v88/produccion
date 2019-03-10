/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.produccion.dao;

/**
 *
 * @author Diego
 */
public class DAOException extends Exception {

    public DAOException(String descripcion, Exception excepcion) {
        super(descripcion, excepcion);
    }
    
    public DAOException(String descripcion) {
        super(descripcion);
    }
    
    public DAOException(Throwable causa) { 
        super(causa); 
    } 
}
