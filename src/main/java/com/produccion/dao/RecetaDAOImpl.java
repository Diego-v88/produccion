/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.produccion.dao;

import com.produccion.entities.Receta;
import java.io.Serializable;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Diego
 */
@Repository
public class RecetaDAOImpl extends GenericDAOImpl<Receta, Serializable> implements RecetaDAO{
    
}
