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
public class ProductoDTO {
     private Integer idproducto;
     private String codbarra;
     private String nombre;
     private int peso;
     private int largo;
     private int ancho;
     private int alto;
     private String descripcion;

    public ProductoDTO(String codbarra, String nombre, int peso, int largo, int ancho, int alto, String descripcion) {
        this.codbarra = codbarra;
        this.nombre = nombre;
        this.peso = peso;
        this.largo = largo;
        this.ancho = ancho;
        this.alto = alto;
        this.descripcion = descripcion;
    }
    
    public ProductoDTO(Integer idproducto, String codbarra, String nombre, int peso, int largo, int ancho, int alto, String descripcion) {
        this.idproducto = idproducto;
        this.codbarra = codbarra;
        this.nombre = nombre;
        this.peso = peso;
        this.largo = largo;
        this.ancho = ancho;
        this.alto = alto;
        this.descripcion = descripcion;
    }

    public ProductoDTO() {
    }

    public Integer getIdproducto() {
        return idproducto;
    }

    public void setIdproducto(Integer idproducto) {
        this.idproducto = idproducto;
    }
    
    public String getCodbarra() {
        return codbarra;
    }

    public void setCodbarra(String codbarra) {
        this.codbarra = codbarra;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getPeso() {
        return peso;
    }

    public void setPeso(int peso) {
        this.peso = peso;
    }

    public int getLargo() {
        return largo;
    }

    public void setLargo(int largo) {
        this.largo = largo;
    }

    public int getAncho() {
        return ancho;
    }

    public void setAncho(int ancho) {
        this.ancho = ancho;
    }

    public int getAlto() {
        return alto;
    }

    public void setAlto(int alto) {
        this.alto = alto;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
     
     
}
