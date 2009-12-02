/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ort.discom.obl.negocio;

import java.util.List;
import javax.ejb.Local;
import ort.discom.obl.entidades.Agente;

/**
 *
 * @author Felipe
 */
@Local
public interface AgenteLocal {

    Agente guardarAgente (Agente age);

    Agente getAgente (Long id);

    boolean eliminarAgente(Long id);

    List<Agente>buscarAgentes();
    
}
