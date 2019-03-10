package com.produccion.entities;
// Generated 24/09/2018 13:27:46 by Hibernate Tools 4.3.1


import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Circuito generated by hbm2java
 */

@Entity(name = "Circuito")
@Table(name = "circuito")
public class Circuito  implements java.io.Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
     private Integer idcircuito;
    @Column(name = "fechaalta")
     private Date fechaalta;
    @Column(name = "fechabaja")
     private Date fechabaja;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="idProducto")
    private Producto producto;
    @OneToMany(mappedBy = "circuito",cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Recurso> recursos = new ArrayList<>();

    public Circuito() {
    }

    public Circuito(Producto producto) {
       this.producto = producto;
       this.fechaalta = new Date();
    }
   
    public Integer getIdcircuito() {
        return this.idcircuito;
    }
    
    public void setIdcircuito(Integer idcircuito) {
        this.idcircuito = idcircuito;
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

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public List<Recurso> getRecursos() {
        return recursos;
    }

    public void addRecurso(Recurso recurso) {
        this.recursos.add(recurso);
    }

    
}


