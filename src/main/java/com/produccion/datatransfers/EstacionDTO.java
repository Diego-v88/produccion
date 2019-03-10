/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.produccion.datatransfers;


/**
 *
 * @author Diego
 */
public class EstacionDTO {
    private Integer idestaciones;
     private String nombre;
     private String ubicacion;
     private String descripcion;

    public EstacionDTO() {
    }
     
     
    public EstacionDTO(Integer idestaciones, String nombre, String ubicacion, String descripcion) {
        this.idestaciones = idestaciones;
        this.nombre = nombre;
        this.ubicacion = ubicacion;
        this.descripcion = descripcion;
    }
     
     
    public Integer getIdestaciones() {
        return idestaciones;
    }

    public void setIdestaciones(Integer idestaciones) {
        this.idestaciones = idestaciones;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
     
     
}
