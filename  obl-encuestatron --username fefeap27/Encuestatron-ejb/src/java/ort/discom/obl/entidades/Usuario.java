/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ort.discom.obl.entidades;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

/**
 *
 * @author Felipe
 */               

@NamedQueries(
    @NamedQuery(name="Usuario.findByApellido",
                query="SELECT u FROM Usuario u " +
                      "WHERE LOWER(u.apellido) LIKE :apellido")
)
@Entity @Inheritance(strategy=InheritanceType.JOINED)
public class Usuario implements Serializable {

    @Id
    private String login;

    @Column(length=16, nullable=false)
    private String password;

    @Column(length=16, nullable=false)
    private String rol = "ADMINISTRADOR";

    @Column(length=32, nullable=false)
    private String nombre;

    @Column(length=32, nullable=false)
    private String apellido;

    @Column(length=32, nullable=false)
    private String email;

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

//    @Override
//    public int hashCode() {
//        int hash = 0;
//        hash += (login != null ? login.hashCode() : 0);
//        return hash;
//    }
//
//    @Override
//    public boolean equals(Object object) {
//        if (!(object instanceof Usuario)) {
//            return false;
//        }
//        Usuario other = (Usuario) object;
//        if ((this.login == null && other.login != null) || (this.login != null && !this.login.equals(other.login))) {
//            return false;
//        }
//        return true;
//    }
//
//    @Override
//    public String toString() {
//        return "ort.discom.obl.entidades.Usuario[id=" + login + "]";
//    }

}

    
     
    


