/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ort.discom.obl.servicios;

import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import ort.discom.obl.entidades.Log;

/**
 *
 * @author Felipe
 */
@MessageDriven(mappedName = "jms/LoggerMDBQueue", activationConfig =  {
        @ActivationConfigProperty(propertyName = "acknowledgeMode", propertyValue = "Auto-acknowledge"),
        @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue")
    })
    
public class LoggerMDBBean implements MessageListener {

    @PersistenceContext
    private EntityManager em;

    public void onMessage(Message message) {
        try {
            TextMessage tm = (TextMessage) message;
            String mensaje = tm.getText();
            Log log = new Log();
            log.setFechaHora(new Date());
            log.setMensaje(mensaje);

            em.persist(log);
        } catch (JMSException ex) {
            Logger.getLogger(LoggerMDBBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
