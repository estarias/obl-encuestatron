/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ort.arqsoft.obl.persistencia;

import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import ort.arqsoft.obl.dominio.Lista;
import ort.arqsoft.obl.utils.ConnectionDB;

/**
 *
 * @author Felipe
 */
public class PLista {

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

    public ArrayList<Lista> listarListas() {
        ArrayList<Lista> listas = new ArrayList<Lista>();
        Lista lista = null;
        Connection con = conectarDB();
        try {
            if (con != null) {

                ResultSet rs = null;
                Statement stm = con.createStatement();
                String strSQL = "SELECT * FROM listas WHERE activo = 1";
                //Ejecuta la consulta SQL
                rs = stm.executeQuery(strSQL);

                //Trabajar con el result set…
//                if (rs.next()) {
                while (rs.next()) {
                    lista = new Lista();
                    lista.setId(rs.getLong("Id"));
                    lista.setPartidoPolitico(rs.getString("partido"));
                    lista.setLista(rs.getString("lista"));
                    lista.setLema(rs.getString("lema"));
                    listas.add(lista);                    
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
        return listas;
    }

    public ArrayList<Lista> listarListasCantidadVotos() {
        ArrayList<Lista> listas = new ArrayList<Lista>();
        Lista lista = null;
        Connection con = conectarDB();
        try {
            if (con != null) {

                ResultSet rs = null;
                Statement stm = con.createStatement();
                String strSQL = "SELECT COUNT(*) AS cantidadVotos, partido,lista FROM votos GROUP BY partido, lista";
                //Ejecuta la consulta SQL
                rs = stm.executeQuery(strSQL);

                //Trabajar con el result set…
//                if (rs.next()) {
                while (rs.next()) {
                    lista = new Lista();
                    
                    lista.setPartidoPolitico(rs.getString("partido"));
                    lista.setLista(rs.getString("lista"));
                    lista.setVotos(rs.getLong("cantidadVotos"));

                    listas.add(lista);
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
        return listas;
    }
}
