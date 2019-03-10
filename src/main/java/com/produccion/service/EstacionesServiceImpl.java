/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.produccion.service;

import com.produccion.dao.DAOException;
import com.produccion.dao.EstacionesDAO;
import com.produccion.datatransfers.EstacionDTO;
import com.produccion.entities.Estaciones;
import java.util.List;
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
public class EstacionesServiceImpl implements EstacionesService{
    @Autowired
    private EstacionesDAO estacionesdao;
    
    private ModelMapper modelMapper = new ModelMapper();
    
    @Override
    public EstacionDTO getEstacionById(Integer idEstacion) throws DAOException {
        EstacionDTO estaciondto = null;
        try {
            Estaciones estacion = estacionesdao.findByID(Estaciones.class, idEstacion);
            estaciondto = modelMapper.map(estacion, EstacionDTO.class);
        } catch (Exception e) {
            throw new DAOException("Hubo un inconveniente y no se pudo traer la estacion solicitada", e);
        }
        return estaciondto;
    }
    
    @Override
    public List<EstacionDTO> getEstaciones() throws DAOException{
        List<EstacionDTO> estacionesdto = null;
        try {
            List<EstacionDTO> estaciones = estacionesdao.findAll(Estaciones.class);
            estacionesdto = MapperUtils.mapAll(estaciones, EstacionDTO.class);
        } catch (Exception e) {
            throw new DAOException("Hubo un inconveniente y no se pudo traer las estaciones solicitadas", e);
        }
        return estacionesdto;
    }
    
    @Override
    public void deleteEstaciones(Integer idEstaciones) throws DAOException{
        try {
            estacionesdao.deleteById(Estaciones.class, idEstaciones);
        } catch (Exception e) {
            throw new DAOException("Hubo un inconveniente y no se pudo listar las estaciones", e);
        }
    }
    
    @Override
    public Integer addEstacion(EstacionDTO estaciondto) throws DAOException{
        Integer id = -1;
        try {
            Estaciones estacion = modelMapper.map(estaciondto, Estaciones.class);
            id = estacionesdao.save(estacion);
        } catch (Exception e) {
            throw new DAOException("Hubo un inconveniente y no se pudo agregar la estacion", e);
        }
        return id;
    }
    
    @Override
    public void updateEstacion(EstacionDTO estaciondto) throws DAOException {
        try {
            Estaciones estacion = modelMapper.map(estaciondto, Estaciones.class);
            estacionesdao.update(estacion);
        } catch (Exception e) {
            throw new DAOException("Hubo un inconveniente y no se pudo actualizar la estacion", e);
        }
    }
    
}
