/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ort.arqsoft.obl.utils;

import java.sql.*;

/**
 *
 * @author Felipe
 */
public class ConnectionDB {

    /*Atributos*/
    private String url = "jdbc:odbc:"; 
 
    private String driver = "corte_DB";
 
    private String usr = ""; 
 
    private String pswd = ""; 
 
    private Connection con; 
 
    /*Constructor, carga puente JDBC-ODBC*/ 
 
    public ConnectionDB()
    { 
       loadDriver(); 
    } 
 
    /** 
    * Carga el driver de la conexión a la base de datos 
    */ 
    private void loadDriver() 
    { 
        try
        { 
            //Instancía de una nueva clase para el puente 
            //sun.jdbc.odbc.JdbcOdbcDriver 
            //El puente sirve entre la aplicación y el driver.     
            Class.forName( "sun.jdbc.odbc.JdbcOdbcDriver" ); 
        } 
        catch(ClassNotFoundException e) 
        { 
            Print("Error al crear el puente JDBC-ODBC");
        } 
    } 
 
    /** 
    *Obtiene una conexión con el nombre del driver especificado 
    *@param driverName Nombre del driver de la base de datos 
    *@return 
    */
    public Connection mkConection() 
    { 
        url = url + driver; 
        Print("Estableciendo conexión con " + url);
        try
        { 
            //Obtiene la conexión 
            con = DriverManager.getConnection( url,usr,pswd); 
        } 
        catch(SQLException sqle) 
        { 
            Print("No se pudo establecer la conexión");
            return null; 
        } 
 
        Print("Conexión establecida con:t " + url);
 
        //Regresa la conexión </span>
        return con; 
    } 
 
    /* Cerrar la conexión.*/
 
    public boolean closeConecction() 
    { 
        try 
        { 
            con.close(); 
        } 
        catch(SQLException sqle) 
        { 
            Print("No se cerro la conexión");
            return false; 
        } 
 
        Print("Conexión cerrada con éxito ");
        return true; 
    }


    private void Print(String msj) {
        PrintAndWriterLog.setOut(System.out);
        PrintAndWriterLog.write(msj);
        PrintAndWriterLog.flush();
    }
}
