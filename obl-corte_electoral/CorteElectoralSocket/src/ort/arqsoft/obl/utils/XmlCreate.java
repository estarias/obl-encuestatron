/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ort.arqsoft.obl.utils;

import java.util.ArrayList;
import ort.arqsoft.obl.dominio.Circuito;
import ort.arqsoft.obl.dominio.Lista;

/**
 *
 * @author Felipe
 */
public class XmlCreate {
    
    public String createListXML(ArrayList<Lista> listas){        
        String xml = "";
                
        xml = "<circuito><circuito-tipo_msj><tipo_msj>envio_listas</tipo_msj></circuito-tipo_msj>";
        
        for (int i = 0; i < listas.size(); i++) {
            xml += "<circuito_data><partido_politico>";
            xml += listas.get(i).getPartidoPolitico(); //(partidopolitico)
            xml += "</partido_politico><nro_lista>";
            xml += listas.get(i).getLista(); //(nrolista)
            xml += "</nro_lista><lema>";
            xml += listas.get(i).getLema(); //(lema)
            xml += "</lema></circuito_data>";
        }
        xml += "</circuito>";
                       
        return xml;
    }
    
    public String createCircuitoXML(Circuito circuito){
        String xml = "";

        xml = "<circuito><circuito-tipo_msj><tipo_msj>envio_circuito</tipo_msj></circuito-tipo_msj>";
        
        xml += "<solicitante_data><nro_circuito>";
        xml += circuito.getNroCircuito(); //(partidopolitico)
        xml += "</nro_circuito><serie>";
        xml += circuito.getSerie(); //(nrolista)
        xml += "</serie><desde>";
        xml += circuito.getDesde(); //(lema)
        xml += "</desde><hasta>";
        xml += circuito.getHasta(); //(nrolista)
        xml += "</hasta><local>";
        xml += circuito.getLocal(); //(nrolista)
        xml += "</local><direccion>";
        xml += circuito.getDireccion(); //(nrolista)
        xml += "</direccion></solicitante_data>";
        
        xml += "</circuito>";

        return xml;
    }

}
