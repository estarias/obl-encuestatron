/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ort.arqsoft.obl.dominio;

/**
 *
 * @author Felipe
 */
public class Lista {

    private String partidoPolitico;
    private long lista;
    private String lema;

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

    /**
     * @return the lema
     */
    public String getLema() {
        return lema;
    }

    /**
     * @param lema the lema to set
     */
    public void setLema(String lema) {
        this.lema = lema;
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

    @Override
    public String toString () {
        return this.getPartidoPolitico() + " - " + String.valueOf(this.getLista()) + "   " + this.getLema() ;
    }
    
}
