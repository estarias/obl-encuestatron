/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ort.discom.obl.servicios;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.annotation.security.DeclareRoles;
import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.ejb.Stateless;
import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageProducer;
import javax.jms.Queue;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import ort.discom.obl.entidades.Usuario;
/**
 *
 * @author Felipe
 */
@DeclareRoles({"USUARIO"})
@RolesAllowed({"USUARIO"})
@Stateless
@WebService
public class ManejadorUsuariosBean implements ManejadorUsuariosRemote, ManejadorUsuariosLocal {

    @Resource(name = "jms/LoggerMDBQueue")
    private Queue loggerMDBQueue;

    @Resource(name = "jms/LoggerMDBQueueFactory")
    private ConnectionFactory loggerMDBQueueFactory;
    
    @PersistenceContext
    private EntityManager em;

    public Usuario obtener(String login) {
        return em.find(Usuario.class, login);
    }

    @PermitAll
    public Usuario alta(String login, String password, String nombre, String apellido) {

        Usuario u = new Usuario();
        u.setLogin(login);
        u.setPassword(password);
        u.setNombre(nombre);
        u.setApellido(apellido);

        em.persist(u);
        em.flush();

        log("Usuario nuevo " + login);

        return u;
    }

    public Usuario modificar(Usuario u) {

        u = em.merge(u);
        em.flush();

        log("Usuario modificado " + u.getLogin());

        return u;
    }

    public void eliminar(Usuario u) {

        u = em.merge(u);
        em.remove(u);
        em.flush();

        log("Usuario eliminado " + u.getLogin());
    }

    @WebMethod
    public List<Usuario> consultar(String apellido) {

        Query q = null;
        if (apellido != null) {
            // Consulta nominada
            q = em.createNamedQuery("Usuario.findByApellido");
            q.setParameter("apellido", apellido.toLowerCase() + "%");
        } else {
            // Consulta dinámica
            q = em.createQuery("SELECT u FROM Usuario u");
        }

        return q.getResultList();
    }

    private void log(Object messageData) {

        Connection connection = null;
        Session session = null;

        try {
            connection = loggerMDBQueueFactory.createConnection();
            session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            MessageProducer messageProducer = session.createProducer(loggerMDBQueue);
            messageProducer.send(crearMensaje(session, messageData));
        } catch (JMSException ex) {
            Logger.getLogger(ManejadorUsuariosBean.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (session != null) {
                try {
                    session.close();
                } catch (JMSException e) {
                    Logger.getLogger(this.getClass().getName()).log(Level.WARNING, "Cannot close session", e);
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (JMSException ex) {
                    Logger.getLogger(ManejadorUsuariosBean.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    private Message crearMensaje(Session session, Object messageData) throws JMSException {

        TextMessage tm = session.createTextMessage();
        tm.setText(messageData.toString());
        
        return tm;
    }   
}

