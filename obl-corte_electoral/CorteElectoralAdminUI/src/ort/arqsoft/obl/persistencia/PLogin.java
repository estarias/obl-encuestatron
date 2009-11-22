/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ort.arqsoft.obl.persistencia;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import ort.arqsoft.obl.utils.ConnectionDB;

/**
 *
 * @author Felipe
 */
public class PLogin {

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

    public boolean loginUser(String usuario, String clave) {
        Connection con = conectarDB();
        boolean login = false;
        
            if (con != null) {
                try {
                    ResultSet rs = null;
                    Statement stm = con.createStatement();
                    String strSQL = "SELECT * FROM usuarios WHERE usuario = '" + usuario + "' AND clave = '" + clave.toString() + "'";
                    //Ejecuta la consulta SQL
                    rs = stm.executeQuery(strSQL);
                    //Trabajar con el result set…
                    if (rs.next()) {
                        login = true;
                    }
                    //Cerrar todo
                    rs.close();
                    stm.close();
                    if (!desconectarDB()) {
                        //Error al cerrar la conexión
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(PLogin.class.getName()).log(Level.SEVERE, null, ex);
                }
            }      
        return login;
    }
}
