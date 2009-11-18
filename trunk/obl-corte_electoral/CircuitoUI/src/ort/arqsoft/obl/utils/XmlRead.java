package ort.arqsoft.obl.utils;

//import com.sun.org.apache.xpath.internal.operations.Equals;
//import java.io.File;
import java.io.StringReader;
import java.util.ArrayList;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.*;
import org.xml.sax.InputSource;
import ort.arqsoft.obl.dominio.clsLista;

public class XmlRead {

   public static String respuestaXML = null;

   public static String obtenerTipo(String xml){
        String tipo_xml = "";
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

            if (((Node) tipoMsj.item(0)).getNodeValue().equals("envio_listas")){
                tipo_xml = "envio_listas";
            }else if (((Node) tipoMsj.item(0)).getNodeValue().equals("confirma_voto")){
                tipo_xml = "confirma_voto";
            }

       }
    catch (Exception e) {
        e.printStackTrace();
    }
        return tipo_xml;
    }

    public static ArrayList<clsLista> obtenerListas(String xml) {
        ArrayList<clsLista>  Listas = null;
        
        try {

//    public static ArrayList<clsPartidoPolitico> readData(){
//        clsPartidoPolitico Partido;
//        clsLista Lista;
            //ArrayList<clsPartidoPolitico>  ListasPartidos = new ArrayList<clsPartidoPolitico>();
//            return ListasPartidos;//    }
       //     ArrayList<clsPartidoPolitico> ListasPartidos = null;
          
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

            if (((Node) tipoMsj.item(0)).getNodeValue().equals("envio_listas")) {
                Listas = getDataFromXMLListasPartidos(document);

                //XmlSave.setOut(".\\Listas.xml");
                XmlSave.setOut(Constants.PATH_LISTASXML);
                XmlSave.write(xml);
                XmlSave.flush();
            }
//            }else if (((Node) tipoMsj.item(0)).getNodeValue().equals("poner_voto")){
//                getDataFromXMLVotar(document);
//            }
                       
        } catch (Exception e) {
            e.printStackTrace();                        
        }
        return Listas;
    }

    private static ArrayList<clsLista> getDataFromXMLListasPartidos(Document document) {        
        ArrayList<clsLista> Listas = new ArrayList<clsLista>();
        clsLista Lista = null;
        try {

            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();

            document.getDocumentElement().normalize();
            
            Node nodoRaiz = document.getFirstChild();

            for (int i = 1; i < nodoRaiz.getChildNodes().getLength(); i++) {
                Node firstNode = nodoRaiz.getChildNodes().item(i);

                if (firstNode.getNodeType() == Node.ELEMENT_NODE) {

                    Lista = new clsLista();

                    Element element = (Element) firstNode;
                    NodeList partidoPoliticoElemntList = element.getElementsByTagName("partido_politico");
                    Element partidoPoliticoElement = (Element) partidoPoliticoElemntList.item(0);
                    String partidoPolitico = partidoPoliticoElement.getChildNodes().item(0).getNodeValue();
                    
                    NodeList nroListaElementList = element.getElementsByTagName("nro_lista");
                    Element nroListaElement = (Element) nroListaElementList.item(0);
                    String nroLista = nroListaElement.getChildNodes().item(0).getNodeValue();

                    Lista.setPartidoPolitico(partidoPolitico);
                    Lista.setLista(nroLista);
                    Lista.setLema("lalalaaaa");

                        //System.out.println("nro_lista : " + ((Node) nroCircuito.item(0)).getNodeValue());

//                    NodeList partidoPoliticoElementList = element.getElementsByTagName("partido_politico");
//                    Element partidoPoliticoElement = (Element) partidoPoliticoElementList.item(0);
//                    NodeList partidoPolitico = partidoPoliticoElement.getChildNodes();
//                    System.out.println("partido_politico : " + ((Node) partidoPolitico.item(0)).getNodeValue());
//
//                    NodeList nroListaElementList = element.getElementsByTagName("nro_lista");
//                    Element nroListaElement = (Element) nroListaElementList.item(0);
//                    NodeList nroLista = nroListaElement.getChildNodes();
//                    System.out.println("nro_lista : " + ((Node) nroLista.item(0)).getNodeValue());
//
//                    NodeList fechaList = element.getElementsByTagName("fecha");
//                    Element fechaElement = (Element) fechaList.item(0);
//                    NodeList fecha = fechaElement.getChildNodes();
//                    System.out.println("fecha : " + ((Node) fecha.item(0)).getNodeValue());
//
//                    NodeList horaList = element.getElementsByTagName("hora");
//                    Element horaElement = (Element) horaList.item(0);
//                    NodeList hora = horaElement.getChildNodes();
//                    System.out.println("hora : " + ((Node) hora.item(0)).getNodeValue());
                                                                                          
                    }

                    Listas.add(Lista);

                    Lista = null;
                }                               
            respuestaXML = "respuesta desde el socket getDataFromXMLVotar";
           
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Listas;
    }
//
//    private static void getDataFromXMLPedirLista(Document document) {
//       try {
//
//            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
//            DocumentBuilder db = dbf.newDocumentBuilder();
//
//            document.getDocumentElement().normalize();
//
//            NodeList node = document.getElementsByTagName("circuito");
//            System.out.println("getDataFromXMLPedirLista");
//
//            //for (int i = 0; i < node.getLength(); i++) {
//                //Node firstNode = node.item(i);
//            Node firstNode = node.item(0);
//            if (firstNode.getNodeType() == Node.ELEMENT_NODE) {
//
//                Element element = (Element) firstNode;
//                NodeList tipoMsjElemntList = element.getElementsByTagName("tipo_msj");
//                Element tipoMsjElement = (Element) tipoMsjElemntList.item(0);
//                String tipoMsj = tipoMsjElement.getFirstChild().getNodeValue();
//                //NodeList tipoMsj = tipoMsjElement.getChildNodes();
//                //System.out.println("tipo_msj : " + ((Node) tipoMsj.item(0)).getNodeValue());
//
//                NodeList nroCircuitoElementList = element.getElementsByTagName("nro_circuito");
//                Element nroCircuitoElement = (Element) nroCircuitoElementList.item(0);
//                String nroCircuito = nroCircuitoElement.getFirstChild().getNodeValue();
//                //NodeList nroCircuito = nroCircuitoElement.getChildNodes();
//                //System.out.println("nro_circuito : " + ((Node) nroCircuito.item(0)).getNodeValue());
//
//                NodeList fechaList = element.getElementsByTagName("fecha");
//                Element fechaElement = (Element) fechaList.item(0);
//                String fecha = fechaElement.getFirstChild().getNodeValue();
//                //NodeList fecha = fechaElement.getChildNodes();
//                //System.out.println("fecha : " + ((Node) fecha.item(0)).getNodeValue());
//
//                NodeList horaList = element.getElementsByTagName("hora");
//                Element horaElement = (Element) horaList.item(0);
//                String hora = horaElement.getFirstChild().getNodeValue();
//                //NodeList hora = horaElement.getChildNodes();
//                //System.out.println("hora : " + ((Node) hora.item(0)).getNodeValue());
//
//
//            }
//            //}
//
//            //respuestaXML = "respuesta desde el socket getDataFromXMLPedirLista";
//            respuestaXML = "<circuito><circuito-tipo_msj><tipo_msj>envio_listas</tipo_msj></circuito-tipo_msj><circuito_data><partido_politico>1</partido_politico><nro_lista>100</nro_lista></circuito_data><circuito_data><partido_politico>1</partido_politico><nro_lista>101</nro_lista></circuito_data><circuito_data><partido_politico>2</partido_politico><nro_lista>200</nro_lista></circuito_data><circuito_data><partido_politico>2</partido_politico><nro_lista>201</nro_lista></circuito_data><circuito_data><partido_politico>3</partido_politico><nro_lista>300</nro_lista></circuito_data><circuito_data><partido_politico>3</partido_politico><nro_lista>301</nro_lista></circuito_data><circuito_data><partido_politico>4</partido_politico><nro_lista>400</nro_lista></circuito_data><circuito_data><partido_politico>4</partido_politico><nro_lista>401</nro_lista></circuito_data><circuito_data><partido_politico>5</partido_politico><nro_lista>500</nro_lista></circuito_data><circuito_data><partido_politico>5</partido_politico><nro_lista>501</nro_lista></circuito_data></circuito>";
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//
//    private static void generateXMLLista(Document document) {

//    }
}