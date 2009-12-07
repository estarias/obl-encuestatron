
package ort.discom.obl.entidades;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;

@Entity
public class Cliente implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String nombre;
    private String apellido;
    private String telefono;
    private String celular;
    private String email;

    @Temporal(javax.persistence.TemporalType.DATE)
    private Date fecha_ing;

    @OneToMany (mappedBy="cliente")
    private Set<Encuesta> lasEncuestas;
  
//  @OneToMany(mappedBy="departamento")
//  private Set<Empleado> empleados;

    @OneToOne
    private Agente elAgente;

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public Agente getElAgente() {
        return elAgente;
    }

    public void setElAgente(Agente elAgente) {
        this.elAgente = elAgente;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getFecha_ing() {
        return fecha_ing;
    }

    public void setFecha_ing(Date fecha_ing) {
        this.fecha_ing = fecha_ing;
    }
 
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

//    @Override
//    public int hashCode() {
//        int hash = 0;
//        hash += (id != null ? id.hashCode() : 0);
//        return hash;
//    }
//
//    @Override
//    public boolean equals(Object object) {
//        // TODO: Warning - this method won't work in the case the id fields are not set
//        if (!(object instanceof Cliente)) {
//            return false;
//        }
//        Cliente other = (Cliente) object;
//        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
//            return false;
//        }
//        return true;
//    }
//
//    @Override
//    public String toString() {
//        return "ort.discom.obl.dominio.Cliente[id=" + id + "]";
//    }

    /**
     * @return the apellido
     */
    public String getApellido() {
        return apellido;
    }

    /**
     * @param apellido the apellido to set
     */
    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    /**
     * @return the lasEncuestas
     */
    public Set<Encuesta> getLasEncuestas() {
        return lasEncuestas;
    }

    /**
     * @param lasEncuestas the lasEncuestas to set
     */
    public void setLasEncuestas(Set<Encuesta> lasEncuestas) {
        this.lasEncuestas = lasEncuestas;
    }    
}
