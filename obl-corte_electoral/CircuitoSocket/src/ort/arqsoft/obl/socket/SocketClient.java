/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ort.arqsoft.obl.socket;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.*;


/**
 *
 * @author Felipe
 */
public class SocketClient {

    private Socket socket = null;
    private PrintWriter out = null;
    private BufferedReader in = null;

    public SocketClient(){
        socket = new Socket();
    }

    public void conectar(String direccion, int puerto) throws IOException {
        InetSocketAddress endPoint = new InetSocketAddress(direccion, puerto);
        socket.connect(endPoint);
    }
   
    public void enviarDatos(String datos) throws IOException{
        out = new PrintWriter(socket.getOutputStream(), true);
        out.println(datos);
    }

//    public String consultarDatos(String datos) throws IOException{
//        out = new PrintWriter(socket.getOutputStream(), true);
//        out.println(datos);
//        socket.getInputStream();
//        in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
//        return in.readLine();
//    }
    
    public String leerDatos() throws IOException{
        String datos = null;
        in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        //if (!in.readLine().equals(null))
        datos = in.readLine();
        
        return datos;
    }

    public void desconectar() throws IOException{
        if (socket != null) socket.close();
        if (out != null) socket.close();
        if (in != null) socket.close();
//        if (out != null) out.close();
//        if (in != null) in.close();
    }
}
        

