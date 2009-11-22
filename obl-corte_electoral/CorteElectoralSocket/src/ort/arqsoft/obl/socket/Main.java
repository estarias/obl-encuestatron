/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ort.arqsoft.obl.socket;

//import java.sql.Connection;
//import java.sql.SQLException;
//import java.util.logging.Level;
//import java.util.logging.Logger;
//import ort.arqsoft.obl.utils.ConnectionDB;

/**
 *
 * @author Felipe
 */
public class Main {
        
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here    
//        //Crear un objeto de la clase de conexión
//        ConnectionDB conect = new ConnectionDB();
//        //Obtener la conexión
//        Connection con = conect.mkConection();
//        if(con != null){
                //Error al establecer la conexión
                SocketServer server = new SocketServer(1236);
                server.escucharPeticiones();
                server.cerrar();
//            try {
//                con.close();
//            } catch (SQLException ex) {
//                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
//            }
//        }
    }

}
