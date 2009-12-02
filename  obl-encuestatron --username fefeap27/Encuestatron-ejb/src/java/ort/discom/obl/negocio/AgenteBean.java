/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ort.discom.obl.negocio;

import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import javax.ejb.Stateless;
import ort.discom.obl.entidades.Agente;

/**
 *
 * @author Felipe
 */
@Stateless
public class AgenteBean implements AgenteLocal {
    
    Map<Long, Agente> agentes = Collections.synchronizedMap(new HashMap<Long, Agente>());

    public Agente guardarAgente(Agente age) {
        agentes.put(age.getId(), age);
        return agentes.get(age.getId());
    }

    public Agente getAgente(Long id) {
        return agentes.get(id);
    }

    public boolean eliminarAgente(Long id) {
        if (agentes.containsKey(id)){
                agentes.remove(id);
                return true;
            }
            return false;
    }

    public List<Agente> buscarAgentes() {
       return new ArrayList(agentes.values());
    }
 
}
