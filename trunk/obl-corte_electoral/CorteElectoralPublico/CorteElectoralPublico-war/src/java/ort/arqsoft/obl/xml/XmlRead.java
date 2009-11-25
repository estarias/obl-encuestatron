package ort.arqsoft.obl.xml;

import java.io.StringReader;
import java.util.ArrayList;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.*;
import org.xml.sax.InputSource;
import ort.arqsoft.obl.dominio.Circuito;
import ort.arqsoft.obl.dominio.Lista;

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

     public static String obtenerEstadoEscrutinio(String xml) {
        String estadoE = "";
        try {
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            InputSource is = new InputSource();
            is.setCharacterStream(new StringReader(xml));

            Document document = db.parse(is);

            document.getDocumentElement().normalize();

            Node nodoRaiz = document.getFirstChild();

            for (int i = 1; i < nodoRaiz.getChildNodes().getLength(); i++) {
                Node firstNode = nodoRaiz.getChildNodes().item(i);

                if (firstNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element element = (Element) firstNode;

                    NodeList estadoElemntList = element.getElementsByTagName("estado");
                    Element estadoElement = (Element) estadoElemntList.item(0);
                    String estado = estadoElement.getChildNodes().item(0).getNodeValue();

                    estadoE = estado;
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return estadoE;
    }

 public static ArrayList<Lista> obtenerListasVotos(String xml) {
        ArrayList<Lista> listas = new ArrayList<Lista>();
        Lista lista = null;
        try {
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            InputSource is = new InputSource();
            is.setCharacterStream(new StringReader(xml));

            Document document = db.parse(is);

            document.getDocumentElement().normalize();

            Node nodoRaiz = document.getFirstChild();

            for (int i = 1; i < nodoRaiz.getChildNodes().getLength(); i++) {
                Node firstNode = nodoRaiz.getChildNodes().item(i);

                if (firstNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element element = (Element) firstNode;

                    lista = new Lista();

                    NodeList partidoPoliticoElemntList = element.getElementsByTagName("partido_politico");
                    Element partidoPoliticoElement = (Element) partidoPoliticoElemntList.item(0);
                    String partidoPolitico = partidoPoliticoElement.getChildNodes().item(0).getNodeValue();

                    NodeList listaElemntList = element.getElementsByTagName("nro_lista");
                    Element listaElement = (Element) listaElemntList.item(0);
                    String listaNro = listaElement.getChildNodes().item(0).getNodeValue();
                    
//                    NodeList lemaElemntList = element.getElementsByTagName("lema");
//                    Element lemaElement = (Element) lemaElemntList.item(0);
//                    String lema = lemaElement.getChildNodes().item(0).getNodeValue();
                    
                    NodeList votosElemntList = element.getElementsByTagName("votos");
                    Element votosElement = (Element) votosElemntList.item(0);
                    String votos = votosElement.getChildNodes().item(0).getNodeValue();
                    
                    lista.setPartidoPolitico(partidoPolitico);
                    lista.setLista(listaNro);
                    //lista.setLema(lema);
                    lista.setVotos(Long.parseLong(votos));
                    
                    listas.add(lista);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return listas;
    }

    public static long obtenerCantidadAnulados(String xml) {
        long cantidadA = 0;
        try {
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            InputSource is = new InputSource();
            is.setCharacterStream(new StringReader(xml));

            Document document = db.parse(is);

            document.getDocumentElement().normalize();

            Node nodoRaiz = document.getFirstChild();

            for (int i = 1; i < nodoRaiz.getChildNodes().getLength(); i++) {
                Node firstNode = nodoRaiz.getChildNodes().item(i);

                if (firstNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element element = (Element) firstNode;

                    NodeList estadoElemntList = element.getElementsByTagName("cantidadAnulados");
                    Element estadoElement = (Element) estadoElemntList.item(0);
                    String cantidad = estadoElement.getChildNodes().item(0).getNodeValue();

                    cantidadA = Long.parseLong(cantidad);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return cantidadA;
    }

    public static long obtenerCantidadEnBlanco(String xml) {
        long cantidadB = 0;
        try {
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            InputSource is = new InputSource();
            is.setCharacterStream(new StringReader(xml));

            Document document = db.parse(is);

            document.getDocumentElement().normalize();

            Node nodoRaiz = document.getFirstChild();

            for (int i = 1; i < nodoRaiz.getChildNodes().getLength(); i++) {
                Node firstNode = nodoRaiz.getChildNodes().item(i);

                if (firstNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element element = (Element) firstNode;

                    NodeList estadoElemntList = element.getElementsByTagName("cantidadEnBlanco");
                    Element estadoElement = (Element) estadoElemntList.item(0);
                    String cantidad = estadoElement.getChildNodes().item(0).getNodeValue();

                    cantidadB = Long.parseLong(cantidad);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return cantidadB;
    }
}