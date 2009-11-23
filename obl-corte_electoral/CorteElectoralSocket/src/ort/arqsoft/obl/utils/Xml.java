/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ort.arqsoft.obl.utils;

import java.io.StringReader;
import java.util.ArrayList;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import ort.arqsoft.obl.dominio.Circuito;
import ort.arqsoft.obl.dominio.Lista;

/**
 *
 * @author Felipe
 */
public class Xml {

    public static String obtenerTipo(String xml) {
        String tipo = null;
        try {

            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            InputSource is = new InputSource();
            is.setCharacterStream(new StringReader(xml));

            Document document = db.parse(is);

            NodeList node = document.getElementsByTagName("circuito");            
            Node firstNode = node.item(0);
            Element element = (Element) firstNode;
            NodeList tipoMsjElemntList = element.getElementsByTagName("tipo_msj");
            Element tipoMsjElement = (Element) tipoMsjElemntList.item(0);
            NodeList tipoMsj = tipoMsjElement.getChildNodes();

            if (((Node) tipoMsj.item(0)).getNodeValue().equals("pedir_listas")) {
                tipo = "pedir_listas";
            } else if (((Node) tipoMsj.item(0)).getNodeValue().equals("poner_voto")) {
                tipo = "poner_voto";
            } else if (((Node) tipoMsj.item(0)).getNodeValue().equals("pedir_circuito")) {
                tipo = "pedir_circuito";
            }else if (((Node) tipoMsj.item(0)).getNodeValue().equals("pedir_estadoEscrutinio")) {
                tipo = "pedir_estadoEscrutinio";
            }else if (((Node) tipoMsj.item(0)).getNodeValue().equals("pedir_listasVotos")) {
                tipo = "pedir_listasVotos";
            }else if (((Node) tipoMsj.item(0)).getNodeValue().equals("pedir_votosAnulados")) {
                tipo = "pedir_votosAnulados";
            }else if (((Node) tipoMsj.item(0)).getNodeValue().equals("pedir_votosBlancos")) {
                tipo = "pedir_votosBlancos";
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return tipo;
    }
    
    public static String getListas(String xmlPedido){
        ArrayList<Lista> listas = new ArrayList<Lista>();
        XmlCreate xmlCreate = new XmlCreate();
        String xmlRespuesta = "";

        listas = XmlRead.procesarXmlLista(xmlPedido);
        xmlRespuesta = xmlCreate.createListXML(listas);

        return xmlRespuesta;
    }

    public static String getCircuito(String xmlPedido){
        Circuito circuito = new Circuito();
        XmlCreate xmlCreate = new XmlCreate();
        String xmlRespuesta = "";

        circuito = XmlRead.procesarXmlCircuito(xmlPedido);
        xmlRespuesta = xmlCreate.createCircuitoXML(circuito);

        return xmlRespuesta;
    }

    public static String getEstadoEscrutinio(String xmlPedido){
        String estado = "";
        XmlCreate xmlCreate = new XmlCreate();
        String xmlRespuesta = "";

        estado = XmlRead.procesarXmlEstadoEscrutinio(xmlPedido);
        xmlRespuesta = xmlCreate.createEstadoEscrutinioXML(estado);

        return xmlRespuesta;
    }

    public static void setVoto(String xmlVoto){
        //ArrayList<Lista> listas = new ArrayList<Lista>();
        //XmlCreate xmlCreate = new XmlCreate();
        //String xmlRespuesta = "";
        if(!xmlVoto.equals(""))
            XmlRead.procesarXmlVoto(xmlVoto);
        //xmlRespuesta = xmlCreate.createListXML(listas);

        //return xmlRespuesta;
    }

    public static String getListasCantidadVotos(String xmlPedido){
        ArrayList<Lista> listas = new ArrayList<Lista>();
        XmlCreate xmlCreate = new XmlCreate();
        String xmlRespuesta = "";

        listas = XmlRead.procesarXmlListaCantidadVotos(xmlPedido);
        xmlRespuesta = xmlCreate.createListaCantidadVotosXML(listas);

        return xmlRespuesta;
    }

    public static String getCantidadVotosAnulados(String xmlPedido){
        long cantidad = 0;
        XmlCreate xmlCreate = new XmlCreate();
        String xmlRespuesta = "";

        cantidad = XmlRead.procesarXmlCantidadVotosAnulados(xmlPedido);
        xmlRespuesta = xmlCreate.createCantidadVotosAnuladosXML(cantidad);

        return xmlRespuesta;
    }

     public static String getCantidadVotosEnBlanco(String xmlPedido){
        long cantidad = 0;
        XmlCreate xmlCreate = new XmlCreate();
        String xmlRespuesta = "";

        cantidad = XmlRead.procesarXmlCantidadVotosEnBlanco(xmlPedido);
        xmlRespuesta = xmlCreate.createCantidadVotosEnBlancoXML(cantidad);

        return xmlRespuesta;
    }

}
