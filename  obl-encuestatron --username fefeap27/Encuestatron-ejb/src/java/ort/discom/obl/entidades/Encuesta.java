
package ort.discom.obl.entidades;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;

@Entity
public class Encuesta implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

//    @OneToOne
//    private Cliente propietario;
    @ManyToOne
    @JoinColumn(name = "ID_CLIENTE")
    private Cliente cliente;


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

//    /**
//     * @return the propietario
//     */
//    public Cliente getPropietario() {
//        return propietario;
//    }
//
//    /**
//     * @param propietario the propietario to set
//     */
//    public void setPropietario(Cliente propietario) {
//        this.propietario = propietario;
//    }

    /**
     * @return the lasPreguntas
     */
    public ArrayList getLasPreguntas() {
        return lasPreguntas;
    }

    /**
     * @param lasPreguntas the lasPreguntas to set
     */
    public void setLasPreguntas(ArrayList lasPreguntas) {
        this.lasPreguntas = lasPreguntas;
    }

    /**
     * @return the nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * @param nombre the nombre to set
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * @return the clave
     */
    public String getClave() {
        return clave;
    }

    /**
     * @param clave the clave to set
     */
    public void setClave(String clave) {
        this.clave = clave;
    }

    /**
     * @return the fecha_ingreso
     */
    public Date getFecha_ingreso() {
        return fecha_ingreso;
    }

    /**
     * @param fecha_ingreso the fecha_ingreso to set
     */
    public void setFecha_ingreso(Date fecha_ingreso) {
        this.fecha_ingreso = fecha_ingreso;
    }

    /**
     * @return the fecha_modificacion
     */
    public Date getFecha_modificacion() {
        return fecha_modificacion;
    }

    /**
     * @param fecha_modificacion the fecha_modificacion to set
     */
    public void setFecha_modificacion(Date fecha_modificacion) {
        this.fecha_modificacion = fecha_modificacion;
    }

    /**
     * @return the fecha_comienzo
     */
    public Date getFecha_comienzo() {
        return fecha_comienzo;
    }

    /**
     * @param fecha_comienzo the fecha_comienzo to set
     */
    public void setFecha_comienzo(Date fecha_comienzo) {
        this.fecha_comienzo = fecha_comienzo;
    }

    /**
     * @return the fecha_cierre
     */
    public Date getFecha_cierre() {
        return fecha_cierre;
    }

    /**
     * @param fecha_cierre the fecha_cierre to set
     */
    public void setFecha_cierre(Date fecha_cierre) {
        this.fecha_cierre = fecha_cierre;
    }

    /**
     * @return the cliente
     */
    public Cliente getCliente() {
        return cliente;
    }

    /**
     * @param cliente the cliente to set
     */
    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

}
