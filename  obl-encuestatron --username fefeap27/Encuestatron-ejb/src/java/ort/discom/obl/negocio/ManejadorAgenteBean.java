/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ort.discom.obl.negocio;

import java.util.List;
//import java.util.ArrayList;
//import java.util.Collections;
//import java.util.HashMap;
//import java.util.Map;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import ort.discom.obl.entidades.Agente;

/**
 *
 * @author Felipe
 */
@Stateless
public class ManejadorAgenteBean implements ManejadorAgenteRemote {

    @PersistenceContext
    private EntityManager em;
    //Map<Long, Agente> agentes = Collections.synchronizedMap(new HashMap<Long, Agente>());

    public Agente guardarAgente(Agente age) {
        em.persist(age);
        em.flush();

        return age;
//        agentes.put(age.getId(), age);
//        return agentes.get(age.getId());
    }

    public Agente getAgente(String login) {
        return em.find(Agente.class, login);
//        return agentes.get(id);
    }

    public void eliminarAgente(String login) {
        Agente a = buscarAgente(login);
        em.remove(a);
//        if (agentes.containsKey(id)){
//                agentes.remove(id);
//                return true;
//            }
//            return false;
    }

    public Agente buscarAgente(String login) {
        return em.find(Agente.class, login);
    }

    public Agente Agente(Agente a) {
        return em.merge(a);
    }
    
    public List<Agente> consultarTodos() {
        String jpql = "SELECT u FROM Agente u";
        Query q = em.createQuery(jpql);
        return (List<Agente>)q.getResultList();
    }

    public List<Agente> consultarPorNombre(String nombre) {
        String jpql = "SELECT u FROM Agente u WHERE u.nombre LIKE :nom";
        Query q = em.createQuery(jpql);
        q.setParameter("nom", nombre+"%");
        return (List<Agente>)q.getResultList();
    }
//    public List<Agente> buscarAgentes() {
//       return new ArrayList(agentes.values());
//    }
}
