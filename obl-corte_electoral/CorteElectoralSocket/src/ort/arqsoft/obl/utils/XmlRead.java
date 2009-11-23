package ort.arqsoft.obl.utils;

//import com.sun.org.apache.xpath.internal.operations.Equals;
//import java.io.File;
//import java.io.File;
//import java.io.FileWriter;
//import java.io.IOException;
//import java.io.PrintWriter;
import java.io.StringReader;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.*;
import org.xml.sax.InputSource;
import ort.arqsoft.obl.dominio.Circuito;
import ort.arqsoft.obl.dominio.Lista;
import ort.arqsoft.obl.dominio.Voto;
import ort.arqsoft.obl.persistencia.PCircuito;
import ort.arqsoft.obl.persistencia.PEleccion;
import ort.arqsoft.obl.persistencia.PLista;
import ort.arqsoft.obl.persistencia.PVoto;

public class XmlRead {

    //public static String respuestaXML = null;

//    public XmlRead(String xml) {
//        procesarXml(xml);
//    }

    

//    public static ArrayList<Lista> procesarXmlLista(String xml) {
//        ArrayList<Lista> listas = null;
//        try {
//            //File file = new File("C:\\MyFile2.xml");
//            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
//            DocumentBuilder db = dbf.newDocumentBuilder();
//            InputSource is = new InputSource();
//            is.setCharacterStream(new StringReader(xml));
//
//            //Document document = db.parse(file);
//            Document document = db.parse(is);
//
//            NodeList node = document.getElementsByTagName("circuito");
//            Node firstNode = node.item(0);
//            Element element = (Element) firstNode;
//            NodeList tipoMsjElemntList = element.getElementsByTagName("tipo_msj");
//            Element tipoMsjElement = (Element) tipoMsjElemntList.item(0);
//            NodeList tipoMsj = tipoMsjElement.getChildNodes();
//            //System.out.println("tipo_msj antes : " + ((Node) tipoMsj.item(0)).getNodeValue());
//
//            if (((Node) tipoMsj.item(0)).getNodeValue().equals("pedir_listas")) {
//                listas = getDataFromXMLPedirLista(document);
//            } else if (((Node) tipoMsj.item(0)).getNodeValue().equals("poner_voto")) {
//                getDataFromXMLVotar(document);
//            }
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return listas;
//    }

    public static void procesarXmlVoto(String xml) {
        Voto voto = new Voto();
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        try {
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            InputSource is = new InputSource();
            is.setCharacterStream(new StringReader(xml));

            Document document = db.parse(is);
  
            NodeList node = document.getElementsByTagName("circuito");
  
            for (int i = 0; i < node.getLength(); i++) {
                Node firstNode = node.item(i);

                if (firstNode.getNodeType() == Node.ELEMENT_NODE) {

                    Element element = (Element) firstNode;
                    NodeList tipoMsjElemntList = element.getElementsByTagName("tipo_msj");
                    Element tipoMsjElement = (Element) tipoMsjElemntList.item(0);
                    NodeList tipoMsj = tipoMsjElement.getChildNodes();
                    //System.out.println("tipo_msj : " + ((Node) tipoMsj.item(0)).getNodeValue());

                    NodeList nroCircuitoElementList = element.getElementsByTagName("nro_circuito");
                    Element nroCircuitoElement = (Element) nroCircuitoElementList.item(0);
                    NodeList nroCircuito = nroCircuitoElement.getChildNodes();
                    //System.out.println("nro_circuito : " + ((Node) nroCircuito.item(0)).getNodeValue());
                    voto.setNroCircuito(Long.parseLong(nroCircuito.item(0).getNodeValue()));

                    NodeList partidoPoliticoElementList = element.getElementsByTagName("partido_politico");
                    Element partidoPoliticoElement = (Element) partidoPoliticoElementList.item(0);
                    NodeList partidoPolitico = partidoPoliticoElement.getChildNodes();
                    //System.out.println("partido_politico : " + ((Node) partidoPolitico.item(0)).getNodeValue());
                    voto.setPartidoPolitico(partidoPolitico.item(0).getNodeValue());
                            
                    NodeList nroListaElementList = element.getElementsByTagName("nro_lista");
                    Element nroListaElement = (Element) nroListaElementList.item(0);
                    NodeList nroLista = nroListaElement.getChildNodes();
                    System.out.println("nro_lista : " + ((Node) nroLista.item(0)).getNodeValue());
                    voto.setLista(Long.parseLong(nroLista.item(0).getNodeValue()));

                    NodeList fechaList = element.getElementsByTagName("fecha");
                    Element fechaElement = (Element) fechaList.item(0);
                    NodeList fecha = fechaElement.getChildNodes();
                    //System.out.println("fecha : " + ((Node) fecha.item(0)).getNodeValue());                    
                    voto.setFecha(fecha.item(0).getNodeValue());

                    PVoto pv = new PVoto();
                    pv.grabarVoto(voto);

//                    DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
//                    Date today = df.parse("20/12/2005");
//                    System.out.println("Today = " + df.format(today));



//                    NodeList horaList = element.getElementsByTagName("hora");
//                    Element horaElement = (Element) horaList.item(0);
//                    NodeList hora = horaElement.getChildNodes();
//                    System.out.println("hora : " + ((Node) hora.item(0)).getNodeValue());
                }
            }
            //respuestaXML = "<circuito><circuito-tipo_msj><tipo_msj>confirma_voto</tipo_msj></circuito-tipo_msj><circuito_data><partido_politico></partido_politico><nro_lista></nro_lista></circuito_data></circuito>";
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static ArrayList<Lista> procesarXmlLista(String xml) {
//        ArrayList<Lista> listas = new ArrayList<Lista>();
        ArrayList<Lista> listas = null;
//        Lista lista = null;
        try {

            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            InputSource is = new InputSource();
            is.setCharacterStream(new StringReader(xml));

            //Document document = db.parse(file);
            Document document = db.parse(is);

//            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
//            DocumentBuilder db = dbf.newDocumentBuilder();

            document.getDocumentElement().normalize();

            NodeList node = document.getElementsByTagName("circuito");
            //System.out.println("getDataFromXMLPedirLista");

            //for (int i = 0; i < node.getLength(); i++) {
            //Node firstNode = node.item(i);
            Node firstNode = node.item(0);
            if (firstNode.getNodeType() == Node.ELEMENT_NODE) {

                Element element = (Element) firstNode;
                NodeList tipoMsjElemntList = element.getElementsByTagName("tipo_msj");
                Element tipoMsjElement = (Element) tipoMsjElemntList.item(0);
                String tipoMsj = tipoMsjElement.getFirstChild().getNodeValue();
                //NodeList tipoMsj = tipoMsjElement.getChildNodes();
                //System.out.println("tipo_msj : " + ((Node) tipoMsj.item(0)).getNodeValue());

                NodeList nroCircuitoElementList = element.getElementsByTagName("nro_circuito");
                Element nroCircuitoElement = (Element) nroCircuitoElementList.item(0);
                String nroCircuito = nroCircuitoElement.getFirstChild().getNodeValue();
                //NodeList nroCircuito = nroCircuitoElement.getChildNodes();
                //System.out.println("nro_circuito : " + ((Node) nroCircuito.item(0)).getNodeValue());

//                NodeList fechaList = element.getElementsByTagName("fecha");
//                Element fechaElement = (Element) fechaList.item(0);
//                String fecha = fechaElement.getFirstChild().getNodeValue();
//                //NodeList fecha = fechaElement.getChildNodes();
//                //System.out.println("fecha : " + ((Node) fecha.item(0)).getNodeValue());
//
//                NodeList horaList = element.getElementsByTagName("hora");
//                Element horaElement = (Element) horaList.item(0);
//                String hora = horaElement.getFirstChild().getNodeValue();
            //NodeList hora = horaElement.getChildNodes();
            //System.out.println("hora : " + ((Node) hora.item(0)).getNodeValue());
            }
            //}
            
            //respuestaXML = "<circuito><circuito-tipo_msj><tipo_msj>envio_listas</tipo_msj></circuito-tipo_msj><circuito_data><partido_politico>P1</partido_politico><nro_lista>100</nro_lista></circuito_data><circuito_data><partido_politico>P1</partido_politico><nro_lista>101</nro_lista></circuito_data><circuito_data><partido_politico>P2</partido_politico><nro_lista>200</nro_lista></circuito_data><circuito_data><partido_politico>P2</partido_politico><nro_lista>201</nro_lista></circuito_data><circuito_data><partido_politico>P3</partido_politico><nro_lista>300</nro_lista></circuito_data><circuito_data><partido_politico>P3</partido_politico><nro_lista>301</nro_lista></circuito_data><circuito_data><partido_politico>P4</partido_politico><nro_lista>400</nro_lista></circuito_data><circuito_data><partido_politico>P4</partido_politico><nro_lista>401</nro_lista></circuito_data><circuito_data><partido_politico>P5</partido_politico><nro_lista>500</nro_lista></circuito_data><circuito_data><partido_politico>P5</partido_politico><nro_lista>501</nro_lista></circuito_data></circuito>";

            PLista pl = new PLista();
            listas = pl.listarListas();


        } catch (Exception e) {
            e.printStackTrace();
        }
        return listas;
    }

    public static Circuito procesarXmlCircuito(String xml) {
        Circuito circuito = new Circuito();
        try {

            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            InputSource is = new InputSource();
            is.setCharacterStream(new StringReader(xml));

            Document document = db.parse(is);

            document.getDocumentElement().normalize();

            NodeList node = document.getElementsByTagName("circuito");

            for (int i = 0; i < node.getLength(); i++) {
                Node firstNode = node.item(i);

                if (firstNode.getNodeType() == Node.ELEMENT_NODE) {

                    Element element = (Element) firstNode;
                    NodeList serieElemntList = element.getElementsByTagName("serie");
                    Element serieElement = (Element) serieElemntList.item(0);
                    String serie = serieElement.getFirstChild().getNodeValue();

                    NodeList numeroElementList = element.getElementsByTagName("numero");
                    Element numeroElement = (Element) numeroElementList.item(0);
                    String numero = numeroElement.getFirstChild().getNodeValue();

                    PCircuito pc = new PCircuito();
                    circuito = pc.listarCircuitos(serie, numero);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return circuito;
    }

    public static String procesarXmlEstadoEscrutinio(String xml) {
        String estado = "";
        try {

            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            InputSource is = new InputSource();
            is.setCharacterStream(new StringReader(xml));

            Document document = db.parse(is);

            document.getDocumentElement().normalize();

            NodeList node = document.getElementsByTagName("circuito");

            for (int i = 0; i < node.getLength(); i++) {
                Node firstNode = node.item(i);

                if (firstNode.getNodeType() == Node.ELEMENT_NODE) {

//                    Element element = (Element) firstNode;
//                    NodeList serieElemntList = element.getElementsByTagName("serie");
//                    Element serieElement = (Element) serieElemntList.item(0);
//                    String serie = serieElement.getFirstChild().getNodeValue();

                    PEleccion pe = new PEleccion();
                    estado = pe.estadoEscutinio();
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return estado;
    }

    public static ArrayList<Lista> procesarXmlListaCantidadVotos(String xml) {
        ArrayList<Lista> listas = null;
        try {

            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            InputSource is = new InputSource();
            is.setCharacterStream(new StringReader(xml));

            Document document = db.parse(is);

            document.getDocumentElement().normalize();

            NodeList node = document.getElementsByTagName("circuito");

            for (int i = 0; i < node.getLength(); i++) {
                Node firstNode = node.item(i);

                if (firstNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element element = (Element) firstNode;

//                    NodeList tipoMsjElemntList = element.getElementsByTagName("tipo_msj");
//                    Element tipoMsjElement = (Element) tipoMsjElemntList.item(0);
//                    String tipoMsj = tipoMsjElement.getFirstChild().getNodeValue();
//
//                    NodeList nroCircuitoElementList = element.getElementsByTagName("nro_circuito");
//                    Element nroCircuitoElement = (Element) nroCircuitoElementList.item(0);
//                    String nroCircuito = nroCircuitoElement.getFirstChild().getNodeValue();

                }
            }

            PLista pl = new PLista();
            listas = pl.listarListasCantidadVotos();


        } catch (Exception e) {
            e.printStackTrace();
        }
        return listas;
    }

    public static long procesarXmlCantidadVotosAnulados(String xml) {
        long cantidad = 0;
        try {

            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            InputSource is = new InputSource();
            is.setCharacterStream(new StringReader(xml));

            Document document = db.parse(is);

            document.getDocumentElement().normalize();

            NodeList node = document.getElementsByTagName("circuito");

            for (int i = 0; i < node.getLength(); i++) {
                Node firstNode = node.item(i);

                if (firstNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element element = (Element) firstNode;

//                    NodeList tipoMsjElemntList = element.getElementsByTagName("tipo_msj");
//                    Element tipoMsjElement = (Element) tipoMsjElemntList.item(0);
//                    String tipoMsj = tipoMsjElement.getFirstChild().getNodeValue();
//
//                    NodeList nroCircuitoElementList = element.getElementsByTagName("nro_circuito");
//                    Element nroCircuitoElement = (Element) nroCircuitoElementList.item(0);
//                    String nroCircuito = nroCircuitoElement.getFirstChild().getNodeValue();
                }
            }
            PEleccion pe = new PEleccion();
            cantidad = pe.cantidadVotosAnulados();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return cantidad;
    }

     public static long procesarXmlCantidadVotosEnBlanco(String xml) {
        long cantidad = 0;
        try {

            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            InputSource is = new InputSource();
            is.setCharacterStream(new StringReader(xml));

            Document document = db.parse(is);

            document.getDocumentElement().normalize();

            NodeList node = document.getElementsByTagName("circuito");

            for (int i = 0; i < node.getLength(); i++) {
                Node firstNode = node.item(i);

                if (firstNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element element = (Element) firstNode;

//                    NodeList tipoMsjElemntList = element.getElementsByTagName("tipo_msj");
//                    Element tipoMsjElement = (Element) tipoMsjElemntList.item(0);
//                    String tipoMsj = tipoMsjElement.getFirstChild().getNodeValue();
//
//                    NodeList nroCircuitoElementList = element.getElementsByTagName("nro_circuito");
//                    Element nroCircuitoElement = (Element) nroCircuitoElementList.item(0);
//                    String nroCircuito = nroCircuitoElement.getFirstChild().getNodeValue();
                }
            }
            PEleccion pe = new PEleccion();
            cantidad = pe.cantidadVotosEnBlanco();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return cantidad;
    }

}