/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ort.arqsoft.obl.dominio;

/**
 *
 * @author Felipe
 */
public class Circuito {

    private long id;
    private long nroCircuito;
    private String serie;
    private long desde;
    private long hasta;
    private String local;
    private String direccion;
    private int activo;

    /**
     * @return the idCircuito
     */
    public long getNroCircuito() {
        return nroCircuito;
    }

    /**
     * @param idCircuito the idCircuito to set
     */
    public void setNroCircuito(long nroCircuito) {
        this.nroCircuito = nroCircuito;
    }

    /**
     * @return the serie
     */
    public String getSerie() {
        return serie;
    }

    /**
     * @param serie the serie to set
     */
    public void setSerie(String serie) {
        this.serie = serie;
    }

    /**
     * @return the desde
     */
    public long getDesde() {
        return desde;
    }

    /**
     * @param desde the desde to set
     */
    public void setDesde(long desde) {
        this.desde = desde;
    }

    /**
     * @return the hasta
     */
    public long getHasta() {
        return hasta;
    }

    /**
     * @param hasta the hasta to set
     */
    public void setHasta(long hasta) {
        this.hasta = hasta;
    }

    /**
     * @return the local
     */
    public String getLocal() {
        return local;
    }

    /**
     * @param local the local to set
     */
    public void setLocal(String local) {
        this.local = local;
    }

    /**
     * @return the direccion
     */
    public String getDireccion() {
        return direccion;
    }

    /**
     * @param direccion the direccion to set
     */
    public void setDireccion(String direccion) {
        this.direccion = direccion;
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
     * @return the activo
     */
    public int getActivo() {
        return activo;
    }

    /**
     * @param activo the activo to set
     */
    public void setActivo(int activo) {
        this.activo = activo;
    }

    @Override
    public String toString () {
        return String.valueOf(this.getNroCircuito()) + " - " + this.getLocal() + "   " + this.getDireccion() ;
    }   
}
