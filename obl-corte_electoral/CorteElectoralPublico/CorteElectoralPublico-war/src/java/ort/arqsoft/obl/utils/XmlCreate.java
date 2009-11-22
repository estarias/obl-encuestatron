/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ort.arqsoft.obl.utils;

/**
 *
 * @author Felipe
 */
public class XmlCreate {
    
//    public String createVotoXML(Voto voto){
//        String xml = "";
//
//        xml = "<circuito><circuito-tipo_msj><tipo_msj>poner_voto</tipo_msj></circuito-tipo_msj>";
//        xml += "<circuito_data><nro_circuito>";
//        xml += voto.getNroCircuito(); //(circuito)
//        xml += "</nro_circuito><fecha>";
//        xml += voto.getFecha(); //(fecha)
//        xml += "</fecha><partido_politico>";
//        xml += voto.getPartidoPolitico(); //(partidopolitico)
//        xml += "</partido_politico><nro_lista>";
//        xml += voto.getLista(); //(listra)
//        xml += "</nro_lista></circuito_data></circuito>";
//
//        return xml;
//    }
//
    public String createSolicitarCircuitoXML(String serie, String numero){
        String xml = "";

        xml = "<circuito><circuito-tipo_msj><tipo_msj>pedir_circuito</tipo_msj></circuito-tipo_msj>";
        xml += "<solicitante_data><serie>";
        xml += serie; //(serie de solicitante)
        xml += "</serie><numero>";
        xml += numero; //(nuemro de solicitante)
        xml += "</numero></solicitante_data></circuito>";

        return xml;
    }
}
