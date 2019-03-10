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
public class CircuitoDTO {
    private int estacionid;
    private int materialId;
    private int cantidadMaterial;
    private int tiempo;
    private int orden;

    public Integer getEstacionid() {
        return estacionid;
    }

    public void setEstacionid(Integer estacionid) {
        this.estacionid = estacionid;
    }

    public Integer getMaterialId() {
        return materialId;
    }

    public void setMaterialId(Integer materialId) {
        this.materialId = materialId;
    }

    public Integer getCantidadMaterial() {
        return cantidadMaterial;
    }

    public void setCantidadMaterial(Integer cantidadMaterial) {
        this.cantidadMaterial = cantidadMaterial;
    }

    public Integer getTiempo() {
        return tiempo;
    }

    public void setTiempo(Integer tiempo) {
        this.tiempo = tiempo;
    }

    public Integer getOrden() {
        return orden;
    }

    public void setOrden(Integer orden) {
        this.orden = orden;
    }

    
}
