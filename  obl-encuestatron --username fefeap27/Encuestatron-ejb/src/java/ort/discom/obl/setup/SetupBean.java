/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ort.discom.obl.setup;

import javax.annotation.security.DeclareRoles;
import javax.annotation.security.RunAs;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import ort.discom.obl.entidades.Agente;
import ort.discom.obl.negocio.ManejadorAgenteRemote;
import ort.discom.obl.servicios.ManejadorUsuariosLocal;

/**
 *
 * @author Felipe
 */

@Stateless
@DeclareRoles({"ADMINISTRADOR"})
@RunAs("ADMINISTRADOR")
public class SetupBean implements SetupLocal {

    @EJB
    private ManejadorUsuariosLocal manejadorUsuarios;

    @EJB
    private ManejadorAgenteRemote manejadorAgentes;

    public void setup() {
        Agente age = new Agente();

        age.setLogin("age");
        age.setPassword("age");
        age.setNombre("Pepe");
        age.setApellido("Agent");
        age.setEmail("");
        age.setRol("Agente");
        age.setLosClientes(null);

        manejadorUsuarios.alta("admin", "admin", "Juan", "Perez", "fefeap26@gmail.com");
        manejadorAgentes.guardarAgente(age);
    }
}