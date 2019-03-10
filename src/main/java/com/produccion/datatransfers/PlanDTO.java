/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.produccion.datatransfers;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Diego
 */
public class PlanDTO {
    private String ordendeproduccion;
    @JsonFormat(pattern="yyyy-MM-dd")
    private Date fechainicioestimada;
    @JsonFormat(pattern="yyyy-MM-dd")
    private Date fechafinestimada;
    private String descripcion;
    List<ProductosCantidadDTO> productos;

    public PlanDTO(String ordendeproduccion, Date fechainicioestimada, Date fechafinestimada, String descripcion, List<ProductosCantidadDTO> productos) {
        this.ordendeproduccion = ordendeproduccion;
        this.fechainicioestimada = fechainicioestimada;
        this.fechafinestimada = fechafinestimada;
        this.descripcion = descripcion;
        this.productos = productos;
    }

    public PlanDTO() {
    }
    
    public String getOrdendeproduccion() {
        return ordendeproduccion;
    }

    public void setOrdendeproduccion(String ordendeproduccion) {
        this.ordendeproduccion = ordendeproduccion;
    }

    public Date getFechainicioestimada() {
        return fechainicioestimada;
    }

    public void setFechainicioestimada(Date fechainicioestimada) {
        this.fechainicioestimada = fechainicioestimada;
    }

    public Date getFechafinestimada() {
        return fechafinestimada;
    }

    public void setFechafinestimada(Date fechafinestimada) {
        this.fechafinestimada = fechafinestimada;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public List<ProductosCantidadDTO> getProductos() {
        return productos;
    }

    public void setProductos(List<ProductosCantidadDTO> productos) {
        this.productos = productos;
    }
    
    
}
