package ort.arqsoft.obl.xml;

//import com.sun.org.apache.xpath.internal.operations.Equals;
//import java.io.File;
import ort.arqsoft.obl.utils.*;
import java.io.StringReader;
import java.util.ArrayList;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.*;
import org.xml.sax.InputSource;
import ort.arqsoft.obl.dominio.Lista;

public class XmlRead {

   // static String respuestaXML = null;

   public String obtenerTipo(String xml){
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

    public ArrayList<Lista> obtenerListas(String xml) {
        ArrayList<Lista>  Listas = null;
        
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

            if (((Node) tipoMsj.item(0)).getNodeValue().equals("envio_listas")) {
                Listas = getDataFromXMLListasPartidos(document);

                //XmlSave.setOut(".\\Listas.xml");
                XmlSave.setOut(Constants.PATH_LISTASXML);
                XmlSave.write(xml);
                XmlSave.flush();
            }
                       
        } catch (Exception e) {
            e.printStackTrace();                        
        }
        return Listas;
    }

    private ArrayList<Lista> getDataFromXMLListasPartidos(Document document) {
        ArrayList<Lista> Listas = new ArrayList<Lista>();
        Lista Lista = null;
        try {

            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();

            document.getDocumentElement().normalize();
            
            Node nodoRaiz = document.getFirstChild();

            for (int i = 1; i < nodoRaiz.getChildNodes().getLength(); i++) {
                Node firstNode = nodoRaiz.getChildNodes().item(i);

                if (firstNode.getNodeType() == Node.ELEMENT_NODE) {

                    Lista = new Lista();

                    Element element = (Element) firstNode;
                    NodeList partidoPoliticoElemntList = element.getElementsByTagName("partido_politico");
                    Element partidoPoliticoElement = (Element) partidoPoliticoElemntList.item(0);
                    String partidoPolitico = partidoPoliticoElement.getChildNodes().item(0).getNodeValue();
                    
                    NodeList nroListaElementList = element.getElementsByTagName("nro_lista");
                    Element nroListaElement = (Element) nroListaElementList.item(0);
                    long nroLista = Long.parseLong(nroListaElement.getChildNodes().item(0).getNodeValue());

                    NodeList lemaElementList = element.getElementsByTagName("lema");
                    Element lemaElement = (Element) lemaElementList.item(0);
                    String lema = lemaElement.getChildNodes().item(0).getNodeValue();

                    Lista.setPartidoPolitico(partidoPolitico);
                    Lista.setLista(nroLista);
                    Lista.setLema(lema);
                                                                                                                  
                    }
                    Listas.add(Lista);
                    Lista = null;

                }                                                     
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Listas;
    }
}