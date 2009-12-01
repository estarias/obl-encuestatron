/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ort.discom.obl.setup;

import javax.annotation.security.DeclareRoles;
import javax.annotation.security.RunAs;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import ort.discom.obl.servicios.ManejadorUsuariosLocal;

/**
 *
 * @author Felipe
 */

@Stateless
@DeclareRoles({"USUARIO"})
@RunAs("USUARIO")
public class SetupBean implements SetupLocal {

    @EJB
    private ManejadorUsuariosLocal manejadorUsuarios;

    public void setup() {
        manejadorUsuarios.alta("admin", "admin", "Juan", "Perez");
    }
}