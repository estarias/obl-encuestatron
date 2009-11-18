/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ort.arqsoft.obl.socket;


import java.io.IOException;
//import java.io.PrintWriter;
import java.net.ServerSocket;
//import java.net.Socket;
//import java.util.ArrayList;
//import java.util.List;
import ort.arqsoft.obl.utils.PrintAndWriterLog;
//import ort.arqsoft.obl.utils.PrintLog;


/**
 *
 * @author Felipe
 */
public class SocketServer {
    private ServerSocket server = null;
//    private PrintLog pl = null;
    //private List<SocketWorker> workers = new ArrayList<SocketWorker>();


    public SocketServer(int puerto) {
        try {
            this.server = new ServerSocket(puerto);
//            this.pl = PrintLog.getInstance();
//            this.pl.setPrefix("[server]");
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void escucharPeticiones() {
        Print("Escuchando peticiones (port: " + server.getLocalPort() + ")");
        //this.pl.printMsg("Escuchando peticiones (port: " + server.getLocalPort() + ")");
        while (true) {
            SocketWorker worker;
            try {
                worker = new SocketWorker(this, server.accept());
                Print("Cliente conectado OK!");
                //this.pl.printMsg("Cliente conectado OK!");
                //this.workers.add(worker);
                worker.start();
            } catch (IOException ex) {                
                System.out.println(ex.getMessage());
                break;
            }
        }
    }

/*
    public void enviarATodos(Socket client, String datos) {
        //for(SocketWorker worker: this.workers){
            //No se le envía el echo del server al client que envió el mensaje
            //solamente a los demás.
            //if (worker.getSocketClient().getPort() != client.getPort()){
                try {
                    //this.pl.printMsg("debe enviar al cliente: " + datos );
                    PrintWriter out = new PrintWriter(worker.getSocketClient().getOutputStream(), true);
                    out.println(datos);
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            //}
        //}
    }*/

    public void cerrar() {
        try {
            if (server != null) {
                server.close();
            }
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

        private void Print(String msj) {
        PrintAndWriterLog.setOut(System.out);
        PrintAndWriterLog.write(msj);
        PrintAndWriterLog.flush();
    }
}
