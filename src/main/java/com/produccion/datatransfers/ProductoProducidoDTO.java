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
public class ProductoProducidoDTO {
    private int numeroserie;
    private Boolean desensamble;

    public ProductoProducidoDTO() {
    }

    public ProductoProducidoDTO(int numeroserie, Boolean desensamble) {
        this.numeroserie = numeroserie;
        this.desensamble = desensamble;
    }
    
    public int getNumeroserie() {
        return numeroserie;
    }

    public void setNumeroserie(int numeroserie) {
        this.numeroserie = numeroserie;
    }

    public Boolean getDesensamble() {
        return desensamble;
    }

    public void setDesensamble(Boolean desensamble) {
        this.desensamble = desensamble;
    }
    
    
}
