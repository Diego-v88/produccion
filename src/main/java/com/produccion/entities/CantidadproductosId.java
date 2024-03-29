package com.produccion.entities;
// Generated 24/09/2018 13:27:46 by Hibernate Tools 4.3.1

import javax.persistence.Column;
import javax.persistence.Embeddable;




/**
 * CantidadproductosId generated by hbm2java
 */
@Embeddable
public class CantidadproductosId  implements java.io.Serializable {

     @Column(name = "idproducto")
     private Integer idproducto;
     @Column(name = "idplan")
     private Integer idplan;

    public CantidadproductosId() {
    }

    public CantidadproductosId(Integer idproducto, Integer idplan) {
       this.idproducto = idproducto;
       this.idplan = idplan;
    }
   
    public Integer getIdproducto() {
        return this.idproducto;
    }
    
    public void setIdproducto(Integer idproducto) {
        this.idproducto = idproducto;
    }
    public Integer getIdplan() {
        return this.idplan;
    }
    
    public void setIdplan(Integer idplan) {
        this.idplan = idplan;
    }


   public boolean equals(Object other) {
         if ( (this == other ) ) return true;
		 if ( (other == null ) ) return false;
		 if ( !(other instanceof CantidadproductosId) ) return false;
		 CantidadproductosId castOther = ( CantidadproductosId ) other; 
         
		 return (this.getIdproducto()==castOther.getIdproducto())
 && (this.getIdplan()==castOther.getIdplan());
   }
   
   public int hashCode() {
         int result = 17;
         
         result = 37 * result + this.getIdproducto();
         result = 37 * result + this.getIdplan();
         return result;
   }   


}


