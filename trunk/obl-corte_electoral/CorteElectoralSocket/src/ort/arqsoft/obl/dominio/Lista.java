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

    private long id;
    private String partidoPolitico;
    private String lista;
    private String lema;
    private long votos;

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
    public String getLista() {
        return lista;
    }

    /**
     * @param lista the lista to set
     */
    public void setLista(String lista) {
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
     * @return the id
     */
    public long getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(long id) {
        this.id = id;
    }

    /**
     * @return the votos
     */
    public long getVotos() {
        return votos;
    }

    /**
     * @param votos the votos to set
     */
    public void setVotos(long votos) {
        this.votos = votos;
    }

}
