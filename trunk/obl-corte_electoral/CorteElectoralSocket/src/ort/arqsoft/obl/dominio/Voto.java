/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ort.arqsoft.obl.dominio;

/**
 *
 * @author Felipe
 */
public class Voto {

    private long nroCircuito;
    private String fecha;
    private String partidoPolitico;
    private long lista;

    /**
     * @return the nroCircuito
     */
    public long getNroCircuito() {
        return nroCircuito;
    }

    /**
     * @param nroCircuito the nroCircuito to set
     */
    public void setNroCircuito(long nroCircuito) {
        this.nroCircuito = nroCircuito;
    }

    /**
     * @return the fecha
     */
    public String getFecha() {
        return fecha;
    }

    /**
     * @param fecha the fecha to set
     */
    public void setFecha(String fecha) {
        this.fecha = fecha;
    }
   
    /**
     * @return the partidoPolitico
     */
    public String getPartidoPolitico() {
        return partidoPolitico;
    }

    /**
     * @param partidoPolitico the partidoPolitico to set
     */
    public void setPartidoPolitico(String partidoPolitico) {
        this.partidoPolitico = partidoPolitico;
    }

    /**
     * @return the lista
     */
    public long getLista() {
        return lista;
    }

    /**
     * @param lista the lista to set
     */
    public void setLista(long lista) {
        this.lista = lista;
    }

}
