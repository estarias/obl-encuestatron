/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ort.arqsoft.obl.persistencia;

import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import ort.arqsoft.obl.dominio.Circuito;
import ort.arqsoft.obl.utils.ConnectionDB;

/**
 *
 * @author Felipe
 */
public class PCircuito {

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

    public ArrayList<Circuito> listarCircuitos() {
        ArrayList<Circuito> circuitos = new ArrayList<Circuito>();
        Circuito circuito = null;
        Connection con = conectarDB();
        try {
            if (con != null) {

                ResultSet rs = null;
                Statement stm = con.createStatement();
                String strSQL = "SELECT * FROM circuitos";
                //Ejecuta la consulta SQL
                rs = stm.executeQuery(strSQL);

                //Trabajar con el result set…
//                if (rs.next()) {
                while (rs.next()) {
                    circuito = new Circuito();
                    circuito.setId(rs.getLong("id"));
                    circuito.setNroCircuito(rs.getLong("nroCircuito"));
                    circuito.setSerie(rs.getString("serie"));
                    circuito.setDesde(rs.getLong("desde"));
                    circuito.setHasta(rs.getLong("hasta"));
                    circuito.setLocal(rs.getString("local"));
                    circuito.setDireccion(rs.getString("direccion"));
                    circuito.setActivo(rs.getInt("activo"));
                    circuitos.add(circuito);
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
        return circuitos;
    }

    public boolean grabarCircuito(Circuito circuito) {
        String strSQL;
        boolean result = true;
        Connection con = conectarDB();
        try {
            if (con != null) {

                //ResultSet rs = null;
                Statement stm = con.createStatement();

                if (circuito.getId()==0){
                    strSQL = "INSERT INTO circuitos (nroCircuito,serie,desde,hasta,local,direccion,activo) VALUES (" + circuito.getNroCircuito() + ",'" + circuito.getSerie() + "'," + circuito.getDesde() + "," + circuito.getHasta() + ",'" + circuito.getLocal() + "'," + circuito.getActivo() + ")";
                }else{
                    strSQL = "UPDATE circuitos SET nroCircuito =" + circuito.getNroCircuito() + ", serie ='" + circuito.getSerie() + "', desde =" + circuito.getDesde() + ", hasta =" + circuito.getHasta() + ",local ='" + circuito.getLocal() + "', direccion ='" + circuito.getDireccion() + "', activo = " + circuito.getActivo() + " WHERE id =" + circuito.getId() + "";
                }
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
