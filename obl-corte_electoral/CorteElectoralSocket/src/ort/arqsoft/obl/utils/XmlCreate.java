/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ort.arqsoft.obl.utils;

import java.util.ArrayList;
import ort.arqsoft.obl.dominio.Lista;

/**
 *
 * @author Felipe
 */
public class XmlCreate {
    
    public String createListXML(ArrayList<Lista> listas){
        Lista lista;
        String xml = "";
                
        xml = "<circuito><circuito-tipo_msj><tipo_msj>envio_listas</tipo_msj></circuito-tipo_msj>";
        
        for (int i = 0; i < listas.size(); i++) {
            xml += "<circuito_data><partido_politico>";
            xml += listas.get(i).getPartidoPolitico(); //(partidopolitico)
            xml += "</partido_politico><nro_lista>";
            xml += listas.get(i).getLista(); //(nrolista)
            xml += "</nro_lista></circuito_data>";                             
        }
        xml += "</circuito>";

//        xml = "<mensaje><servicio><valor>";
//        xml += listas.get(i);
//        xml +="</valor></servicio><respuesta><valor>";
//        xml += unDispatcher.invocarServicio(nombreServicio, params);
//        xml += "</valor></respuesta><excepcion><valor></valor></excepcion></mensaje>";
//        xml += listas.get(i).getId();
        //"<circuito><circuito-tipo_msj><tipo_msj>envio_listas</tipo_msj></circuito-tipo_msj><circuito_data><partido_politico>P1</partido_politico><nro_lista>100</nro_lista></circuito_data><circuito_data><partido_politico>P1</partido_politico><nro_lista>101</nro_lista></circuito_data><circuito_data><partido_politico>P2</partido_politico><nro_lista>200</nro_lista></circuito_data><circuito_data><partido_politico>P2</partido_politico><nro_lista>201</nro_lista></circuito_data><circuito_data><partido_politico>P3</partido_politico><nro_lista>300</nro_lista></circuito_data><circuito_data><partido_politico>P3</partido_politico><nro_lista>301</nro_lista></circuito_data><circuito_data><partido_politico>P4</partido_politico><nro_lista>400</nro_lista></circuito_data><circuito_data><partido_politico>P4</partido_politico><nro_lista>401</nro_lista></circuito_data><circuito_data><partido_politico>P5</partido_politico><nro_lista>500</nro_lista></circuito_data><circuito_data><partido_politico>P5</partido_politico><nro_lista>501</nro_lista></circuito_data></circuito>";
                       
        return xml;
    }

}
