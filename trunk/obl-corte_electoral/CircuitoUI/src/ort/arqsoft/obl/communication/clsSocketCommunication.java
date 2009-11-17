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
public class clsSocketCommunication {

    private static SocketClient SocketClient;

    public static boolean connectSocket() {
        SocketClient = new SocketClient();
        String nombreServer = "127.0.0.1";
        int puerto = Integer.parseInt("1236");
        try {
            SocketClient.conectar(nombreServer, puerto);

            sendData("<circuito><circuito-tipo_msj><tipo_msj>pedir_listas</tipo_msj></circuito-tipo_msj><circuito_data><nro_circuito>600</nro_circuito><fecha>13/11/2009</fecha><hora>22:48</hora></circuito_data></circuito>");            return true;        
        } catch (IOException ex) {          
            return false;
        }
    }

    public static void sendData(String xml)  {
        try {
            SocketClient.enviarDatos(xml);
        } catch (IOException ex) {
            Logger.getLogger(clsSocketCommunication.class.getName()).log(Level.SEVERE, null, ex);
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
