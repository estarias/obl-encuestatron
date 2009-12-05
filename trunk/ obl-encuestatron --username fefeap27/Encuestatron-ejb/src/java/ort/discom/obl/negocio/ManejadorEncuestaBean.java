/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ort.discom.obl.negocio;

import ort.discom.obl.entidades.Encuesta;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Felipe
 */
@Stateless
public class ManejadorEncuestaBean implements ManejadorEncuestaRemote {
    
    @PersistenceContext
    private EntityManager em;

    public Encuesta guardarEncuesta(Encuesta enc) {
        em.persist(enc);
        em.flush();

        return enc;
    }
    public Encuesta getEncuesta(long id) {
        return em.find(Encuesta.class, id);
    }

    public void eliminarEncuesta(long id) {
        Encuesta e = buscarEncuesta(id);
        em.remove(e);
    }

    public Encuesta buscarEncuesta(long id) {
        return em.find(Encuesta.class, id);
    }

    public Encuesta Encuesta(Encuesta e) {
        return em.merge(e);
    }

    public List<Encuesta> consultarTodos() {
        String jpql = "SELECT u FROM Encuesta u";
        Query q = em.createQuery(jpql);
        return (List<Encuesta>)q.getResultList();
    }

    public List<Encuesta> consultarPorNombre(String nombre) {
        String jpql = "SELECT u FROM Encuesta u WHERE u.nombre LIKE :nom";
        Query q = em.createQuery(jpql);
        q.setParameter("nom", nombre+"%");
        return (List<Encuesta>)q.getResultList();
    }
}
