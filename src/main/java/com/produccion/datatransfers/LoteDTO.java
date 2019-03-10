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
public class LoteDTO {
    private Integer idlote;
    private Integer numerolote;

    public LoteDTO(Integer idlote, Integer numerolote) {
        this.idlote = idlote;
        this.numerolote = numerolote;
    }

    public LoteDTO() {
    }
    
    public Integer getIdlote() {
        return idlote;
    }

    public void setIdlote(Integer idlote) {
        this.idlote = idlote;
    }

    public Integer getNumerolote() {
        return numerolote;
    }

    public void setNumerolote(Integer numerolote) {
        this.numerolote = numerolote;
    }
    
    
}
