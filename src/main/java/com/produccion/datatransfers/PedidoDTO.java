/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.produccion.datatransfers;

import java.util.Date;
import java.util.List;

/**
 *
 * @author Diego
 */
public class PedidoDTO {
    private Integer idPedido;
    private Date fechaEsperada;
    private boolean atendido;
    private List<MaterialCantidadDTO> materiales;

    public PedidoDTO() {
    }

    public PedidoDTO(Integer idPedido, Date fechaEsperada, boolean atendido, List<MaterialCantidadDTO> materiales) {
        this.idPedido = idPedido;
        this.fechaEsperada = fechaEsperada;
        this.atendido = atendido;
        this.materiales = materiales;
    }

    public Integer getIdPedido() {
        return idPedido;
    }

    public void setIdPedido(Integer idPedido) {
        this.idPedido = idPedido;
    }

    public Date getFechaEsperada() {
        return fechaEsperada;
    }

    public void setFechaEsperada(Date fechaEsperada) {
        this.fechaEsperada = fechaEsperada;
    }

    public boolean isAtendido() {
        return atendido;
    }

    public void setAtendido(boolean atendido) {
        this.atendido = atendido;
    }

    public List<MaterialCantidadDTO> getMateriales() {
        return materiales;
    }

    public void addMateriales(MaterialCantidadDTO material) {
        this.materiales.add(material);
    }
    
}
