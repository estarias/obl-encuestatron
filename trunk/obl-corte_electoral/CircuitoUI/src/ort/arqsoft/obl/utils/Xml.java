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
import ort.arqsoft.obl.dominio.Lista;
import ort.arqsoft.obl.dominio.Voto;

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
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return tipo;
    }
    
//    public static String getListas(String xmlPedido){
//        ArrayList<Lista> listas = new ArrayList<Lista>();
//        XmlCreate xmlCreate = new XmlCreate();
//        String xmlRespuesta = "";
//
//        listas = XmlRead.procesarXmlLista(xmlPedido);
//        xmlRespuesta = xmlCreate.createListXML(listas);
//
//        return xmlRespuesta;
//    }

    public static String setVoto(Voto voto){
        XmlCreate xmlCreate = new XmlCreate();        
        String xmlRespuesta = "";
                        
        xmlRespuesta = xmlCreate.createVotoXML(voto);

        return xmlRespuesta;
    }
    
    public static String getSolicitarListas(){
        XmlCreate xmlCreate = new XmlCreate();
        String xmlRespuesta = "";

        xmlRespuesta = xmlCreate.createSolicitarListasXML();

        return xmlRespuesta;
    }

}
