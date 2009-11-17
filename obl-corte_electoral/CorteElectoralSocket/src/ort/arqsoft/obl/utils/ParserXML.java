/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ort.arqsoft.obl.utils;

import javax.xml.parsers.*;
import org.xml.sax.InputSource;
import org.w3c.dom.*;
import java.io.*;
import java.util.ArrayList;

/**
 *
 * @author Felipe
 */
public class ParserXML {

  public static String getCharacterDataFromElement(Element e) {
    Node child = e.getFirstChild();
    if (child instanceof CharacterData) {
       CharacterData cd = (CharacterData) child;
       return cd.getData();
    }
    return "?";
  }

  public static String obtenerNombreServicio (String xml){

       try {
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder db = dbf.newDocumentBuilder();
        InputSource is = new InputSource();
        is.setCharacterStream(new StringReader(xml));

        Document doc = db.parse(is);
        NodeList nodes = doc.getElementsByTagName("servicio");

        Element element = (Element) nodes.item(0);
        NodeList name = element.getElementsByTagName("valor");
        Element line = (Element) name.item(0);
        return getCharacterDataFromElement(line);
       }
    catch (Exception e) {
        e.printStackTrace();
        return null;
    }
  }

  public static ArrayList<String> obtenerParametrosServicio (String xml){
      ArrayList<String> lista = new ArrayList<String>();
      try {
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder db = dbf.newDocumentBuilder();
        InputSource is = new InputSource();
        is.setCharacterStream(new StringReader(xml));

        Document doc = db.parse(is);
        NodeList nodes = doc.getElementsByTagName("param");

        for (int i = 0; i < nodes.getLength(); i++) {
           Element element = (Element) nodes.item(i);

           NodeList name = element.getElementsByTagName("valor");
           Element line = (Element) name.item(0);
           lista.add(getCharacterDataFromElement(line));
        }
        return lista;
    }
    catch (Exception e) {
        e.printStackTrace();
        return null;
     }
  }

   public static String obtenerTecnologiaServicio (String xml){

       try {
//        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
//        DocumentBuilder db = dbf.newDocumentBuilder();
//        InputSource is = new InputSource();
//        is.setCharacterStream(new StringReader(xml));
//
//        Document doc = db.parse(is);
//        NodeList nodes = doc.getElementsByTagName("tecnologia");
//
//        Element element = (Element) nodes.item(0);
//        NodeList name = element.getElementsByTagName("valor");
//        Element line = (Element) name.item(0);
//        return getCharacterDataFromElement(line);
           return obtenerTexto(xml, "tecnologia");
       }
    catch (Exception e) {
        e.printStackTrace();
        return null;
    }
  }

   private static String obtenerTexto(String xml, String nombreTag){
        try {
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder db = dbf.newDocumentBuilder();
        InputSource is = new InputSource();
        is.setCharacterStream(new StringReader(xml));

        Document doc = db.parse(is);
        NodeList nodes = doc.getElementsByTagName(nombreTag);

        Element element = (Element) nodes.item(0);
        NodeList name = element.getElementsByTagName("valor");
        Element line = (Element) name.item(0);
        return getCharacterDataFromElement(line);
       }
    catch (Exception e) {
        e.printStackTrace();
        return null;
    }
   }


 }


