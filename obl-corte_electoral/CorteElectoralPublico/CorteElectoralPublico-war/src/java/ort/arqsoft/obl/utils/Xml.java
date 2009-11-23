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

//    public static String obtenerTipo(String xml) {
//        String tipo = null;
//        try {
//
//            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
//            DocumentBuilder db = dbf.newDocumentBuilder();
//            InputSource is = new InputSource();
//            is.setCharacterStream(new StringReader(xml));
//
//            Document document = db.parse(is);
//
//            NodeList node = document.getElementsByTagName("circuito");
//            Node firstNode = node.item(0);
//            Element element = (Element) firstNode;
//            NodeList tipoMsjElemntList = element.getElementsByTagName("tipo_msj");
//            Element tipoMsjElement = (Element) tipoMsjElemntList.item(0);
//            NodeList tipoMsj = tipoMsjElement.getChildNodes();
//
//            if (((Node) tipoMsj.item(0)).getNodeValue().equals("pedir_listas")) {
//                tipo = "pedir_listas";
//            } else if (((Node) tipoMsj.item(0)).getNodeValue().equals("poner_voto")) {
//                tipo = "poner_voto";
//            }
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return tipo;
//    }
        
    public static String getXMLSolicitarCircuito(String serie, String numero){
        XmlCreate xmlCreate = new XmlCreate();
        String xmlRespuesta = "";

        xmlRespuesta = xmlCreate.createSolicitarCircuitoXML(serie, numero);

        return xmlRespuesta;
    }

    public static Circuito getCircuitoFromXml(String xml){
        Circuito circuito = new Circuito();
        
        circuito = XmlRead.obtenerCircuito(xml);
        return circuito;
    }

     public static String getXMLSolicitarEstadoEscrutinio(){
        XmlCreate xmlCreate = new XmlCreate();
        String xmlRespuesta = "";

        xmlRespuesta = xmlCreate.createSolicitarEstadoEscrutinioXML();

        return xmlRespuesta;
    }

    public static String getEstadoEscrutinioFromXml(String xml){
        String estado = "";

        estado = XmlRead.obtenerEstadoEscrutinio(xml);

        return estado;
    }

    public static String getXMLSolicitarListasCantidadVotos(){
        XmlCreate xmlCreate = new XmlCreate();
        String xmlRespuesta = "";

        xmlRespuesta = xmlCreate.createSolicitarListasVotosXML();

        return xmlRespuesta;
    }

    public static ArrayList<Lista> getListasCantidadVotosFromXml(String xml){
        ArrayList<Lista> listas = null;

        listas = XmlRead.obtenerListasVotos(xml);

        return listas;
    }

    public static String getXMLSolicitarVotosAnulados(){
        XmlCreate xmlCreate = new XmlCreate();
        String xmlRespuesta = "";

        xmlRespuesta = xmlCreate.createSolicitarVotoAnuladosXML();

        return xmlRespuesta;
    }

    public static long getVotosAnuladosFromXml(String xml){
        long cantidad = 0;

        cantidad = XmlRead.obtenerCantidadAnulados(xml);

        return cantidad;
    }

    public static String getXMLSolicitarVotosEnBlanco(){
        XmlCreate xmlCreate = new XmlCreate();
        String xmlRespuesta = "";

        xmlRespuesta = xmlCreate.createSolicitarVotoBlancosXML();

        return xmlRespuesta;
    }
    
    public static long getVotosEnBlancoFromXml(String xml){
        long cantidad = 0;

        cantidad = XmlRead.obtenerCantidadEnBlanco(xml);

        return cantidad;
    }
}
