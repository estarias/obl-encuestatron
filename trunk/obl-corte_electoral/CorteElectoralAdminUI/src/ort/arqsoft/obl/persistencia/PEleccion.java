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
public class PEleccion {
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

    public boolean grabarEstado(String estado) {
        String strSQL;
        boolean result = true;
        Connection con = conectarDB();
        try {
            if (con != null) {

                //ResultSet rs = null;
                Statement stm = con.createStatement();
                
                strSQL = "UPDATE elecciones SET escrutinio ='" + estado + "'";

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

    public String estadoEscutinio() {
        String estado = "";
        Connection con = conectarDB();
        try {
            if (con != null) {

                ResultSet rs = null;
                Statement stm = con.createStatement();
                String strSQL = "SELECT * FROM elecciones WHERE id = 1";
                //Ejecuta la consulta SQL
                rs = stm.executeQuery(strSQL);

                //Trabajar con el result set…
                //while (rs.next()) {
                if (rs.next()) {
                    estado = rs.getString("escrutinio");
                }

                //Cerrar todo
                rs.close();
                stm.close();
                if (!desconectarDB()) {
                    //Error al cerrar la conexión
                }
            }
        }catch (SQLException ex) {
            Logger.getLogger(PLista.class.getName()).log(Level.SEVERE, null, ex);
        }
        return estado;
    }

}
