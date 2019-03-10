package com.produccion.entities;
// Generated 24/09/2018 13:27:46 by Hibernate Tools 4.3.1


import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Pedidos generated by hbm2java
 */

@Entity(name = "Pedidos")
@Table(name = "pedidos")
//@NaturalIdCache
//@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Pedidos  implements java.io.Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
     private Integer idpedido;
    @Column(name = "fechaalta")
     private Date fechaalta;
    @Column(name = "fechaesperada")
     private Date fechaesperada;
    @Column(name = "atendido")
     private boolean atendido;
    @ManyToOne
    @JoinColumn(name="idplan")
     private Planproduccion planProduccion;
    @OneToMany(mappedBy = "pedido",cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Cantidadmp> materiales = new ArrayList<>();
    
    public Pedidos() {
        this.fechaalta = new Date();
    }

    public Pedidos(Integer idpedido, Date fechaesperada, boolean atendido, Planproduccion planProduccion) {
       this.idpedido = idpedido;
       this.fechaalta = new Date();
       this.fechaesperada = fechaesperada;
       this.atendido = atendido;
       this.planProduccion = planProduccion;
    }
   
    public Integer getIdpedido() {
        return this.idpedido;
    }
    
    public void setIdpedido(Integer idpedido) {
        this.idpedido = idpedido;
    }
    public Date getFechaalta() {
        return this.fechaalta;
    }
    
    public void setFechaalta(Date fechaalta) {
        this.fechaalta = fechaalta;
    }
    public Date getFechaesperada() {
        return this.fechaesperada;
    }
    
    public void setFechaesperada(Date fechaesperada) {
        this.fechaesperada = fechaesperada;
    }
    public boolean isAtendido() {
        return this.atendido;
    }
    
    public void setAtendido(boolean atendido) {
        this.atendido = atendido;
    }

    public Planproduccion getPlanProduccion() {
        return planProduccion;
    }

    public void setPlanProduccion(Planproduccion planProduccion) {
        this.planProduccion = planProduccion;
    }

    public List<Cantidadmp> getMateriales() {
        return materiales;
    }

    public void addMateriales(Material material, Integer cantidad) {
        Cantidadmp newMaterial = new Cantidadmp(this, material, cantidad);
        this.materiales.add(newMaterial);
    }

}

