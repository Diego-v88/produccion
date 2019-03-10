/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.produccion.dao;

import com.produccion.entities.Pedidos;
import java.io.Serializable;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Diego
 */
@Repository
public class PedidoDAOImpl extends GenericDAOImpl<Pedidos, Serializable> implements PedidoDAO{

}
