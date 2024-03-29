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
 * Cantidadproductos generated by hbm2java
 */
@Entity(name = "Cantidadproductos")
@Table(name = "cantidadproductos")
public class Cantidadproductos  implements java.io.Serializable {

     @EmbeddedId
     private CantidadproductosId id;
     @Column(name = "cantidad")
     private int cantidad;
     
     @ManyToOne(fetch = FetchType.LAZY)
     @MapsId("idproducto")
     private Producto producto;
     @ManyToOne(fetch = FetchType.LAZY)
     @MapsId("idplan")
     private Planproduccion planProduccion;
 
    public Cantidadproductos() {
    }

    public Cantidadproductos(Planproduccion planProduccion, Producto producto, int cantidad) {
       this.id = new CantidadproductosId();
       this.producto = producto;
       this.planProduccion = planProduccion;
       this.cantidad = cantidad;
    }
   
    public CantidadproductosId getId() {
        return this.id;
    }
    
    public void setId(CantidadproductosId id) {
        this.id = id;
    }
    public int getCantidad() {
        return this.cantidad;
    }
    
    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public Planproduccion getPlanProduccion() {
        return planProduccion;
    }

    public void setPlanProduccion(Planproduccion planProduccion) {
        this.planProduccion = planProduccion;
    }




}


