/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ort.arqsoft.obl.webservice;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.jws.WebMethod;
import javax.jws.WebService;
import ort.arqsoft.obl.communication.SocketCommunication;
import ort.arqsoft.obl.dominio.Lista;
import ort.arqsoft.obl.xml.Xml;

/**
 *
 * @author Felipe
 */
@WebService()
public class WSEscrutinio {

    private boolean isConnected = false;
    
    /**
    * Web service operation
    */
    @WebMethod(operationName = "estadoEscrutinio")
    public String estadoEscrutinio() {
        //TODO write your implementation code here:
        String estado = "";        
        connectServer();
        if (isConnected) {
            SocketCommunication.sendData(Xml.getXMLSolicitarEstadoEscrutinio());

            esperaParaBuscarLaRespuesta();

            estado = Xml.getEstadoEscrutinioFromXml(SocketCommunication.readData());
        }        
        return estado;
    }

    /**
    * Web service operation
    */
    @WebMethod(operationName = "listasCantidadVotos")
    //public ArrayList<Lista> listasCantidadVotos() {
    public String listasCantidadVotos() {
        //TODO write your implementation code here:
        String listasVotos = "";
        ArrayList<Lista> listas = new ArrayList<Lista>();
        Lista lista = null;

        connectServer();
        if (isConnected) {
            SocketCommunication.sendData(Xml.getXMLSolicitarListasCantidadVotos());

            esperaParaBuscarLaRespuesta();

            listas = Xml.getListasCantidadVotosFromXml(SocketCommunication.readData());

            for (int i = 0; i < listas.size(); i++) {
                lista = listas.get(i);
                listasVotos += "  * PARTIDO: " + lista.getPartidoPolitico() + " - LISTA: " + lista.getLista() + " - VOTOS: " + lista.getVotos();
            }
        }
        return listasVotos;
    }

        /**
    * Web service operation
    */
    @WebMethod(operationName = "votosAnulados")
    public long votosAnulados() {
        //TODO write your implementation code here:
        long votos = 0;
        connectServer();
        if (isConnected) {
            SocketCommunication.sendData(Xml.getXMLSolicitarVotosAnulados());

            esperaParaBuscarLaRespuesta();

            votos = Xml.getVotosAnuladosFromXml(SocketCommunication.readData());
        }
        return votos;
    }

    /**
    * Web service operation
    */
    @WebMethod(operationName = "votosEnBlancos")
    public long votosEnBlancos() {
        //TODO write your implementation code here:
        long votos = 0;
        connectServer();
        if (isConnected) {
            SocketCommunication.sendData(Xml.getXMLSolicitarVotosEnBlanco());

            esperaParaBuscarLaRespuesta();

            votos = Xml.getVotosEnBlancoFromXml(SocketCommunication.readData());
        }
        return votos;
    }

    public void connectServer(){
        //if (!isConnected) {
            isConnected = SocketCommunication.connectSocket();
        //}
    }

//    public void desconnectServer(){
//        if (isConnected) {
//            SocketCommunication.disconnectSocket();
//        }
//    }

     private void esperaParaBuscarLaRespuesta() {
        try {
            Thread.sleep(3000);
        } catch (InterruptedException ex) {
            Logger.getLogger(WSEscrutinio.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
