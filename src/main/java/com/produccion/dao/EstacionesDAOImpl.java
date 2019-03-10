/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.produccion.dao;

import com.produccion.entities.Ejecucion;
import com.produccion.entities.Estaciones;
import com.produccion.entities.Recurso;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Diego
 */
@Repository
public class EstacionesDAOImpl extends GenericDAOImpl<Estaciones, Serializable> implements EstacionesDAO{
    
    
    /**
     * Devuelve la cantidad de minutos que estan ocupados en una estacion.
     * 
     * @param idEstacion id de la estacion propiamente dicha
     * @param inicio fecha de inicio a tratar
     * @param fin fecha de fin a tratar
     * @return cantidad de minutos que estan ocupado entre las fechas pasadas
    */
    @Override
    public long minutosOcupados(Integer idEstacion, Date inicio, Date fin) {
        long minutos = 0;
        String sql = "SELECT eje FROM Recurso rec, Ejecucion eje WHERE rec.idestacion = :idEstacion AND eje.idrecurso = rec.idrecurso AND eje.fechafin >= :inicio AND eje.fechainicio < :fin";
        List<Ejecucion> ejecuciones = getCurrentSession().createQuery(sql).setParameter("idEstacion", idEstacion).setParameter("inicio", inicio).setParameter("fin", fin).list();
        for (Ejecucion next : ejecuciones) {
            Date inicioEjecucion = next.getFechainicio();
            Date finEjecucion = next.getFechafin();
            if (inicioEjecucion.before(inicio) && finEjecucion.before(fin)) {
                //Comienza en inicio y finaliza en el fin de la ejecucion
                minutos = minutos + Math.abs(finEjecucion.getTime() - inicio.getTime())/60000;
                
            } else if (inicioEjecucion.after(inicio) && finEjecucion.before(fin)) {
                //Comienza en inicio de la ejecucion y finaliza en el fin de la ejecucion
                minutos = minutos + Math.abs(fin.getTime() - inicioEjecucion.getTime())/60000;
                
            }else if (inicioEjecucion.after(inicio) && finEjecucion.after(fin)){
                //Comienza en inicio de la ejecucion y finaliza en el fin
                minutos = minutos + Math.abs(fin.getTime() - inicioEjecucion.getTime())/60000;
                
            } else if (inicioEjecucion.before(inicio) && finEjecucion.after(fin)) {
                //Comienza en inicio y finaliza en el fin
                minutos = minutos + Math.abs(fin.getTime() - inicio.getTime())/60000;
            }
        }
        return minutos;
    }
    
    /**
     * Devuelve la cantidad de minutos que estan libres en una estacion.
     * 
     * @param idEstacion id de la estacion propiamente dicha
     * @param inicio fecha de inicio a tratar
     * @param fin fecha de fin a tratar
     * @return cantidad de minutos que estan libres entre las fechas pasadas
    */
    @Override
    public long minutosLibres(Integer idEstacion, Date inicio, Date fin) {
        long minutos = Math.abs(fin.getTime() - inicio.getTime())/60000;
        List<Ejecucion> ejecuciones = null;
        String sql = "SELECT eje FROM Recurso rec, Ejecucion eje WHERE rec.estacion.idestaciones = :idEstacion AND eje.recurso.idrecurso = rec.idrecurso AND eje.fechafin >= :inicio AND eje.fechainicio < :fin";
        ejecuciones = getCurrentSession().createQuery(sql).setParameter("idEstacion", idEstacion).setParameter("inicio", inicio).setParameter("fin", fin).list();
        
        if (ejecuciones != null) {
            for (Ejecucion next : ejecuciones) {
                Date inicioEjecucion = next.getFechainicio();
                Date finEjecucion = next.getFechafin();
                if (inicioEjecucion.before(inicio) && finEjecucion.before(fin)) {
                    //Comienza en inicio y finaliza en el fin de la ejecucion
                    minutos = minutos - Math.abs(finEjecucion.getTime() - inicio.getTime())/60000;

                } else if (inicioEjecucion.after(inicio) && finEjecucion.before(fin)) {
                    //Comienza en inicio de la ejecucion y finaliza en el fin de la ejecucion
                    minutos = minutos - Math.abs(fin.getTime() - inicioEjecucion.getTime())/60000;

                }else if (inicioEjecucion.after(inicio) && finEjecucion.after(fin)){
                    //Comienza en inicio de la ejecucion y finaliza en el fin
                    minutos = minutos - Math.abs(fin.getTime() - inicioEjecucion.getTime())/60000;

                } else if (inicioEjecucion.before(inicio) && finEjecucion.after(fin)) {
                    //Comienza en inicio y finaliza en el fin
                    minutos = minutos - Math.abs(fin.getTime() - inicio.getTime())/60000;
                }
                /*if (inicioEjecucion.before(inicio) && finEjecucion.before(fin)) {
                    //Comienza en inicio y finaliza en el fin de la ejecucion
                    minutos = minutos + (fin.getTime() - inicio.getTime())/60000;

                } else if (inicioEjecucion.after(inicio) && finEjecucion.before(fin)) {
                    //Comienza en inicio de la ejecucion y finaliza en el fin de la ejecucion
                    minutos = minutos + ((fin.getTime() - inicio.getTime()) + (inicioEjecucion.getTime() - inicio.getTime()))/60000;

                }else if (inicioEjecucion.after(inicio) && finEjecucion.after(fin)){
                    //Comienza en inicio de la ejecucion y finaliza en el fin
                    minutos = minutos + Math.abs(inicioEjecucion.getTime() - inicio.getTime())/60000;

                }*/
            }
        }
        return minutos;
    }
    
    @Override
    public List<Ejecucion> ejecucionesOcupadas(Integer idEstacion, Date inicio, Date fin) {
        String sql = "SELECT eje FROM Recurso rec, Ejecucion eje WHERE rec.estacion.idestacion = :idEstacion AND eje.recurso.idrecurso = rec.idrecurso AND eje.fechafin >= :inicio AND eje.fechainicio < :fin ORDER BY eje.fechaInicio";
        List<Ejecucion> ejecuciones = getCurrentSession().createQuery(sql).setParameter("idEstacion", idEstacion).setParameter("inicio", inicio).setParameter("fin", fin).list();
        return ejecuciones;
    }
    
    @Override
    public Date ultimaEjecucion(Integer idEstacion, Date inicio, Date fin) {
        Date resultado = inicio;
        String sql = "SELECT eje FROM Recurso rec, Ejecucion eje WHERE rec.estacion.idestaciones = :idEstacion AND eje.recurso.idrecurso = rec.idrecurso AND eje.fechafin >= :inicio AND eje.fechainicio < :fin ORDER BY eje.fechafin";
        Ejecucion ejecucion = (Ejecucion) getCurrentSession().createQuery(sql).setParameter("idEstacion", idEstacion).setParameter("inicio", inicio).setParameter("fin", fin).uniqueResult();
        if (ejecucion != null) {
            resultado = ejecucion.getFechafin();
        }
        return resultado;
    }
    
        @Override
    public long tiempoDelRecurso(Integer idEstacion, Integer idCircuito) {
        long resultado = 0;
        String sql = "SELECT rec FROM Recurso rec WHERE rec.circuito.idcircuito = :idCircuito AND rec.estacion.idestaciones = :idEstacion";
        Recurso recurso = (Recurso) getCurrentSession().createQuery(sql).setParameter("idEstacion", idEstacion).setParameter("idCircuito", idCircuito).uniqueResult();
        resultado = recurso.getTiempo();
        return resultado;
    }
}
