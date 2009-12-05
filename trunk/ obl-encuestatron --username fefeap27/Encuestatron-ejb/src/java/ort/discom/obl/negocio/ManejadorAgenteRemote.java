/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ort.discom.obl.negocio;

import java.util.List;
import javax.ejb.Remote;
import ort.discom.obl.entidades.Agente;

/**
 *
 * @author Felipe
 */
@Remote
public interface ManejadorAgenteRemote {

    Agente guardarAgente (Agente age);

    Agente getAgente (String id);

    void eliminarAgente(String id);

    List<Agente>consultarTodos();
    
}
