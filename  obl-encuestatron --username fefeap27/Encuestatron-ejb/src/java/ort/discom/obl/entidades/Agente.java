
package ort.discom.obl.entidades;

import java.io.Serializable;
import java.util.ArrayList;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


@Entity
public class Agente extends Usuario implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private ArrayList losClientes;

    public ArrayList getLosClientes() {
        return losClientes;
    }

    public void setLosClientes(ArrayList losClientes) {
        this.losClientes = losClientes;
    } 

}
