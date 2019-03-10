/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.produccion.service;

import com.produccion.dao.DAOException;
import com.produccion.datatransfers.EstacionDTO;
import java.util.List;

/**
 *
 * @author Diego
 */
public interface EstacionesService {
    public List<EstacionDTO> getEstaciones() throws DAOException;
    public void deleteEstaciones(Integer idEstaciones) throws DAOException;
    public Integer addEstacion(EstacionDTO estacion) throws DAOException;
    public void updateEstacion(EstacionDTO estacion) throws DAOException;
    public EstacionDTO getEstacionById(Integer idEstacion) throws DAOException;
}
