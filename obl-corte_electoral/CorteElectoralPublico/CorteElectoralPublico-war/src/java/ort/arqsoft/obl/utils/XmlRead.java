package ort.arqsoft.obl.utils;

//import com.sun.org.apache.xpath.internal.operations.Equals;
//import java.io.File;
import java.io.StringReader;
import java.util.ArrayList;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.*;
import org.xml.sax.InputSource;
import ort.arqsoft.obl.dominio.Circuito;

public class XmlRead {

   public static String respuestaXML = null;

//   public static String obtenerTipo(String xml){
//        String tipo_xml = "";
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
//            if (((Node) tipoMsj.item(0)).getNodeValue().equals("envio_listas")){
//                tipo_xml = "envio_listas";
//            }else if (((Node) tipoMsj.item(0)).getNodeValue().equals("confirma_voto")){
//                tipo_xml = "confirma_voto";
//            }
//
//       }
//    catch (Exception e) {
//        e.printStackTrace();
//    }
//        return tipo_xml;
//    }

    public static Circuito obtenerCircuito(String xml) {
         Circuito circuito = new Circuito();
        try {
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            InputSource is = new InputSource();
            is.setCharacterStream(new StringReader(xml));

            Document document = db.parse(is);

            circuito = getDataFromXMLCircuito(document);

//            NodeList node = document.getElementsByTagName("circuito");
//            Node firstNode = node.item(0);
//            Element element = (Element) firstNode;
//            NodeList tipoMsjElemntList = element.getElementsByTagName("tipo_msj");
//            Element tipoMsjElement = (Element) tipoMsjElemntList.item(0);
//            NodeList tipoMsj = tipoMsjElement.getChildNodes();
//            if (((Node) tipoMsj.item(0)).getNodeValue().equals("envio_listas")) {
//                //XmlSave.setOut(".\\Listas.xml");
//                XmlSave.setOut(Constants.PATH_LISTASXML);
//                XmlSave.write(xml);
//                XmlSave.flush();
//            }
//            }else if (((Node) tipoMsj.item(0)).getNodeValue().equals("poner_voto")){
//                getDataFromXMLVotar(document);
//            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return circuito;
    }

    private static Circuito getDataFromXMLCircuito(Document document) {
        Circuito circuito = new Circuito();
        try {
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();

            document.getDocumentElement().normalize();

            Node nodoRaiz = document.getFirstChild();

            for (int i = 1; i < nodoRaiz.getChildNodes().getLength(); i++) {
                Node firstNode = nodoRaiz.getChildNodes().item(i);

                if (firstNode.getNodeType() == Node.ELEMENT_NODE) {                    
                    Element element = (Element) firstNode;
                    NodeList nroCircuitoElemntList = element.getElementsByTagName("nro_circuito");
                    Element nroCircuitoElement = (Element) nroCircuitoElemntList.item(0);
                    String nroCircuito = nroCircuitoElement.getChildNodes().item(0).getNodeValue();

                    NodeList serieElementList = element.getElementsByTagName("serie");
                    Element serieElement = (Element) serieElementList.item(0);
                    String serie = serieElement.getChildNodes().item(0).getNodeValue();

                    NodeList desdeElementList = element.getElementsByTagName("desde");
                    Element desdeElement = (Element) desdeElementList.item(0);
                    String desde = desdeElement.getChildNodes().item(0).getNodeValue();
                    
                    NodeList hastaElementList = element.getElementsByTagName("hasta");
                    Element hastaElement = (Element) hastaElementList.item(0);
                    String hasta = hastaElement.getChildNodes().item(0).getNodeValue();
                    
                    NodeList localElementList = element.getElementsByTagName("local");
                    Element localElement = (Element) localElementList.item(0);
                    String local = localElement.getChildNodes().item(0).getNodeValue();
                    
                    NodeList direccionElementList = element.getElementsByTagName("direccion");
                    Element direccionElement = (Element) direccionElementList.item(0);
                    String direccion = direccionElement.getChildNodes().item(0).getNodeValue();

                    circuito.setNroCircuito(Long.parseLong(nroCircuito));
                    circuito.setSerie(serie);
                    circuito.setDesde(Long.parseLong(desde));
                    circuito.setHasta(Long.parseLong(hasta));
                    circuito.setLocal(local);
                    circuito.setDireccion(direccion);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return circuito;
    }
}