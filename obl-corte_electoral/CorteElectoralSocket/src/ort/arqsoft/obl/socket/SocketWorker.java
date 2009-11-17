/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ort.arqsoft.obl.socket;

import java.io.BufferedReader;
//import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import ort.arqsoft.obl.utils.LeerXml;
import ort.arqsoft.obl.utils.PrintLog;

/**
 *
 * @author Felipe
 */
public class SocketWorker extends Thread {

    private Socket client = null;
    private BufferedReader in = null;
    //private BufferedWriter out = null;
    private PrintLog pl = null;
    private SocketServer server = null;
    private LeerXml lx;

    public SocketWorker(SocketServer server, Socket client) {
        this.server = server;
        this.client = client;
        this.pl = PrintLog.getInstance();
        this.pl.setPrefix("[server]");
        this.pl.printMsg("Port para el dialogo: " + this.client.getPort());
    }

    public Socket getSocketClient() {
        return this.client;
    }

    @Override
    @SuppressWarnings("static-access")
    public void run() {
        String linea = null;
        String tipo;
        while (true) {
            linea = recibeDatos();
            if (linea != null) {
                tipo = lx.obtenerTipo(linea);

                if (tipo != null) {
                    if (tipo.equals("pedir_listas")) {
                        //respondiendo al pedido de listas...
                        lx = new LeerXml(linea);
                        this.enviaDatos(lx.respuestaXML);
                    } else if (tipo.equals("poner_voto")) {
                        //respondiendo a la votacion...
                        this.enviaDatos("votacion realizada correctamente!!");
                    }
                }
            }
        }
    }

    public String recibeDatos() {
        String datos = null;
        try {
            this.in = new BufferedReader(new InputStreamReader(this.client.getInputStream()));
            datos = this.in.readLine();
            this.pl.printMsg("datos recibidos: " + datos);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return datos;
    }

    /**
     * Delega la responsabilida de notificar a todos los clientes a la clase
     * servidor chat que tiene un Mapa con todos los clientes conectados.
     *
     * La clase SocketWorker solamente conoce a uno.
     * @param datos
     */
    public void enviaDatos(String datos) {
        this.server.enviarATodos(this.client, datos);
    //this.pl.printMsg(">>server: " + datos);
    }
}
/*
 * public class SocketWorker extends Thread {
private Socket client = null;
private BufferedReader in = null;
//private BufferedWriter out = null;
private PrintLog pl = null;
private SocketServer server = null;

public SocketWorker(SocketServer server, Socket client) {
this.server = server;
this.client = client;
this.pl = PrintLog.getInstance();
this.pl.setPrefix("[server]");
this.pl.printMsg("Port para el dialogo: " + this.client.getPort());
}

public Socket getSocketClient(){
return this.client;
}

public void run() {
String linea = null;
while (true) {
linea = leerDatoss();
if (linea != null) {

this.pl.printMsg("<<client: " + linea);
this.enviarDatos("[echo server]" + "pepepeeeee" + linea);

}
}
}

public String leerDatoss() {
String datos = null;
try {
this.in = new BufferedReader(new InputStreamReader(this.client.getInputStream()));
datos = this.in.readLine();
} catch (IOException ex) {
ex.printStackTrace();
}
return datos;
}


public void enviarDatos(String datos) {
this.server.enviarATodos(this.client, datos);
this.pl.printMsg(">>server: " + datos);
}
 */
