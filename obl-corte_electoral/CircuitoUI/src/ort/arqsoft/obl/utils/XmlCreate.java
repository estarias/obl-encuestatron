/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ort.arqsoft.obl.utils;

import ort.arqsoft.obl.dominio.Voto;

/**
 *
 * @author Felipe
 */
public class XmlCreate {
    
    public String createVotoXML(Voto voto){
        String xml = "";
                
        xml = "<circuito><circuito-tipo_msj><tipo_msj>poner_voto</tipo_msj></circuito-tipo_msj>";
        xml += "<circuito_data><nro_circuito>";
        xml += voto.getNroCircuito(); //(circuito)
        xml += "</nro_circuito><fecha>";
        xml += voto.getFecha(); //(fecha)
        xml += "</fecha><partido_politico>";
        xml += voto.getPartidoPolitico(); //(partidopolitico)
        xml += "</partido_politico><nro_lista>";
        xml += voto.getLista(); //(listra)
        xml += "</nro_lista></circuito_data></circuito>";        
                       
        return xml;
    }

    public String createSolicitarListasXML(){
        String xml = "";

        xml = "<circuito><circuito-tipo_msj><tipo_msj>pedir_listas</tipo_msj></circuito-tipo_msj>";
        xml += "<circuito_data><nro_circuito>";
        xml += Constants.NROCIRCUITO; //(partidopolitico)
        xml += "</nro_circuito></circuito_data></circuito>";
        
        return xml;
    }

}
