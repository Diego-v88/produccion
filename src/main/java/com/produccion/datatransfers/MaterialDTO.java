/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.produccion.datatransfers;

import java.util.Date;

/**
 *
 * @author Diego
 */
public class MaterialDTO {
     private Integer idmaterial;
     private String nombre;
     private String unidad;
     private double cantidadminima;

    public MaterialDTO(String nombre, String unidad, double cantidadminima) {
        this.nombre = nombre;
        this.unidad = unidad;
        this.cantidadminima = cantidadminima;
    }

    public MaterialDTO() {
    }
     
    public Integer getIdmaterial() {
        return idmaterial;
    }

    public void setIdmaterial(Integer idmaterial) {
        this.idmaterial = idmaterial;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getUnidad() {
        return unidad;
    }

    public void setUnidad(String unidad) {
        this.unidad = unidad;
    }

    public double getCantidadminima() {
        return cantidadminima;
    }

    public void setCantidadminima(double cantidadminima) {
        this.cantidadminima = cantidadminima;
    }
     
     
}
