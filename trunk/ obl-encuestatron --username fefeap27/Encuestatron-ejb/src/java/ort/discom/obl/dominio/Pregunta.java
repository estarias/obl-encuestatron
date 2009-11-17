
package ort.discom.obl.dominio;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;

@Entity
public class Pregunta implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private ArrayList respuestas = new ArrayList();
    private String planteo;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date ultima_contestada;

    public String getPlanteo() {
        return planteo;
    }

    public void setPlanteo(String planteo) {
        this.planteo = planteo;
    }

    public ArrayList getRespuestas() {
        return respuestas;
    }

    public void setRespuestas(ArrayList respuestas) {
        this.respuestas = respuestas;
    }

    public Date getUltima_contestada() {
        return ultima_contestada;
    }

    public void setUltima_contestada(Date ultima_contestada) {
        this.ultima_contestada = ultima_contestada;
    }

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
        if (!(object instanceof Pregunta)) {
            return false;
        }
        Pregunta other = (Pregunta) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ort.discom.obl.dominio.Pregunta[id=" + id + "]";
    }

}
