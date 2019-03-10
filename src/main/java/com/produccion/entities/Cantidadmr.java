package com.produccion.entities;
// Generated 24/09/2018 13:27:46 by Hibernate Tools 4.3.1

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;




/**
 * Cantidadmr generated by hbm2java
 */
@Entity(name = "Cantidadmr")
@Table(name = "Cantidadmr")
public class Cantidadmr  implements java.io.Serializable {

     @EmbeddedId
     private CantidadmrId id;
     @Column(name = "cantidad")
     private int cantidad;
    @ManyToOne(fetch = FetchType.EAGER)
    @MapsId("idreceta")
    private Receta receta;
    @ManyToOne(fetch = FetchType.EAGER)
    @MapsId("idmaterial")
    private Material material;

    public Cantidadmr() {
    }

    public Cantidadmr(Receta receta, Material material, int cantidad) {
       this.material = material;
       this.receta = receta;
       this.cantidad = cantidad;
       this.id = new CantidadmrId(receta.getIdreceta(), material.getIdmaterial());
    }
   
    public CantidadmrId getId() {
        return this.id;
    }
    
    public void setId(CantidadmrId id) {
        this.id = id;
    }
    public int getCantidad() {
        return this.cantidad;
    }
    
    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public Receta getReceta() {
        return receta;
    }

    public void setReceta(Receta receta) {
        this.receta = receta;
    }

    public Material getMaterial() {
        return material;
    }

    public void setMaterial(Material material) {
        this.material = material;
    }

}


