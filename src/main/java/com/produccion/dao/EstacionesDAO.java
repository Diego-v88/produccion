/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.produccion.dao;

import com.produccion.entities.Ejecucion;
import com.produccion.entities.Estaciones;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Diego
 */
public interface EstacionesDAO extends GenericDAO<Estaciones, Serializable>{
    public long minutosOcupados(Integer idEstacion, Date inicio, Date fin);
    public long minutosLibres(Integer idEstacion, Date inicio, Date fin);
    public List<Ejecucion> ejecucionesOcupadas(Integer idEstacion, Date inicio, Date fin);
    public Date ultimaEjecucion(Integer idEstacion, Date inicio, Date fin);
    public long tiempoDelRecurso(Integer idEstacion, Integer idCircuito);
}
