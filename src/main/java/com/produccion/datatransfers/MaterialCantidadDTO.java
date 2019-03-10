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
public class MaterialCantidadDTO {
    private Integer idMaterial;
    private Integer cantidad;

    public MaterialCantidadDTO(Integer idMaterial, Integer cantidad) {
        this.idMaterial = idMaterial;
        this.cantidad = cantidad;
    }

    public MaterialCantidadDTO() {
    }
    
    
    public Integer getIdMaterial() {
        return idMaterial;
    }

    public void setIdMaterial(Integer idMaterial) {
        this.idMaterial = idMaterial;
    }


    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }
    
    
}
