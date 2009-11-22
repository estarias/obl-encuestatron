/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ort.arqsoft.obl.persistencia;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import ort.arqsoft.obl.dominio.Voto;
import ort.arqsoft.obl.utils.ConnectionDB;

/**
 *
 * @author Felipe
 */
public class PVoto {
    private ConnectionDB conect;

    private Connection conectarDB() {
        //Crear un objeto de la clase de conexión
        conect = new ConnectionDB();
        //Obtener la conexión
        Connection con = conect.mkConection();

        return con;
    }

    private boolean desconectarDB() {
        return conect.closeConecction();
    }

      public boolean grabarVoto(Voto voto) {
        String strSQL;
        boolean result = true;
        Connection con = conectarDB();
        try {
            if (con != null) {

                //ResultSet rs = null;
                Statement stm = con.createStatement();

                strSQL = "INSERT INTO votos (nroCircuito,partido,lista,fecha) VALUES (" + voto.getNroCircuito() + ",'" + voto.getPartidoPolitico() + "'," + voto.getLista() + ",'" + voto.getFecha() + "')";
                System.out.println(strSQL);
                
                //Ejecuta la consulta SQL
                int nfilas = stm.executeUpdate(strSQL);

                //Cerrar todo
                stm.close();
                if (!desconectarDB()) {
                    //Error al cerrar la conexión
                }
            }
        }catch (SQLException ex) {
            result = false;
            Logger.getLogger(PLista.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }
}
