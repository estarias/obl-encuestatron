
package ort.discom.obl.entidades;

import java.io.Serializable;
import java.util.ArrayList;

import javax.persistence.Entity;
//import javax.persistence.GeneratedValue;
//import javax.persistence.GenerationType;
//import javax.persistence.Id;

@Entity 
public class Agente extends Usuario implements Serializable {
    private static final long serialVersionUID = 1L;

//    @Id
//    @GeneratedValue(strategy = GenerationType.AUTO)
//    private Long id;

    private ArrayList losClientes;

//    public Long getId() {
//        return id;
//    }
//
//    public void setId(Long id) {
//        this.id = id;
//    }

    public ArrayList getLosClientes() {
        return losClientes;
    }

    public void setLosClientes(ArrayList losClientes) {
        this.losClientes = losClientes;
    } 

//    @Override
//    public int hashCode() {
//        int hash = 0;
//        hash += (id != null ? id.hashCode() : 0);
//        return hash;
//    }

//    @Override
//    public boolean equals(Object object) {
//        // TODO: Warning - this method won't work in the case the id fields are not set
//        if (!(object instanceof Agente)) {
//            return false;
//        }
//        Agente other = (Agente) object;
//        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
//            return false;
//        }
//        return true;
//    }

//    @Override
//    public String toString() {
//        return "ort.discom.obl.dominio.Agente[id=" + id + "]";
//    }
}
