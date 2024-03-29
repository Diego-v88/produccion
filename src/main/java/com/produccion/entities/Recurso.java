package com.produccion.entities;
// Generated 24/09/2018 13:27:46 by Hibernate Tools 4.3.1


import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Recurso generated by hbm2java
 */

@Entity(name = "Recurso")
@Table(name = "recurso")
public class Recurso implements java.io.Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
     private Integer idrecurso;
    @Column(name = "fechaalta")
     private Date fechaalta;
    @Column(name = "fechabaja")
     private Date fechabaja;
    @Column(name = "cantidadmaterial")
     private int cantidadmaterial;
    @Column(name = "orden")
     private int orden;
    @Column(name = "tiempo")
     private long tiempo;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="idCircuito")
     private Circuito circuito;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="idEstacion")
     private Estaciones estacion;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="idMaterial")
     private Material material;

    public Recurso() {
    }

    public Recurso(int cantidadmaterial, int orden, Material material, Estaciones estacion) {
       this.fechaalta = new Date();
       this.cantidadmaterial = cantidadmaterial;
       this.orden = orden;
       this.material = material;
       this.estacion = estacion;
    }
   
    public Integer getIdrecurso() {
        return this.idrecurso;
    }
    
    public void setIdrecurso(Integer idrecurso) {
        this.idrecurso = idrecurso;
    }
    public Date getFechaalta() {
        return this.fechaalta;
    }
    
    public void setFechaalta(Date fechaalta) {
        this.fechaalta = fechaalta;
    }
    public Date getFechabaja() {
        return this.fechabaja;
    }
    
    public void setFechabaja(Date fechabaja) {
        this.fechabaja = fechabaja;
    }
    public int getCantidadmaterial() {
        return this.cantidadmaterial;
    }
    
    public void setCantidadmaterial(int cantidadmaterial) {
        this.cantidadmaterial = cantidadmaterial;
    }
    public int getOrden() {
        return this.orden;
    }

    public Circuito getCircuito() {
        return circuito;
    }

    public void setCircuito(Circuito circuito) {
        this.circuito = circuito;
    }

    public Estaciones getEstaciones() {
        return estacion;
    }

    public void setEstaciones(Estaciones estaciones) {
        this.estacion = estaciones;
    }

    public Material getMaterial() {
        return material;
    }

    public void setMaterial(Material material) {
        this.material = material;
    }

    public Estaciones getEstacion() {
        return estacion;
    }

    public void setEstacion(Estaciones estacion) {
        this.estacion = estacion;
    }

    public long getTiempo() {
        return tiempo;
    }

    public void setTiempo(long tiempo) {
        this.tiempo = tiempo;
    }


}


