/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ort.arqsoft.obl.communication;

//import com.sun.xml.internal.ws.api.message.Message;
import java.io.IOException;
//import java.util.ArrayList;
//import ort.arqsoft.obl.dominio.clsLista;
//import ort.arqsoft.obl.dominio.clsPartidoPolitico;
import java.util.logging.Level;
import java.util.logging.Logger;
import ort.arqsoft.obl.socket.SocketClient;

/**
 *
 * @author Felipe
 */
public class SocketCommunication {

    private static SocketClient SocketClient;

    public static boolean connectSocket() {
        SocketClient = new SocketClient();
        String nombreServer = "127.0.0.1";
        int puerto = Integer.parseInt("1236");
        try {
            SocketClient.conectar(nombreServer, puerto);           
            return true;
        } catch (IOException ex) {          
            return false;
        }
    }

    public static void sendData(String xml)  {
        try {
            SocketClient.enviarDatos(xml);
        } catch (IOException ex) {
            Logger.getLogger(SocketCommunication.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

     public static boolean disconnectSocket() {
        //SocketClient = new SocketClient();
        try {
            SocketClient.desconectar();
            return false;
        } catch (IOException ex) {
            return true;
        }
    }
     
     public static String readData() {
        try {
            return SocketClient.leerDatos();
        } catch (IOException ex) {
            return "";
        }
    }   
}
