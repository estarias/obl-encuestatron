
package ort.discom.obl.entidades;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;

@Entity
public class Encuesta implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @OneToOne
    private Cliente propietario;
    private ArrayList lasPreguntas = new ArrayList();
    private String nombre;
    private String clave;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date fecha_ingreso;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date fecha_modificacion;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date fecha_comienzo;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date fecha_cierre;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Encuesta)) {
            return false;
        }
        Encuesta other = (Encuesta) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ort.discom.obl.dominio.Encuesta[id=" + id + "]";
    }

}
