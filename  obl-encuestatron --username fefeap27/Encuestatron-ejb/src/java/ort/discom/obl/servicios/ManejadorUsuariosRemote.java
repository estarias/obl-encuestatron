/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ort.discom.obl.servicios;

import java.util.List;
import javax.ejb.Remote;
import ort.discom.obl.entidades.Usuario;

/**
 *
 * @author Felipe
 */
@Remote
public interface ManejadorUsuariosRemote {

    Usuario obtener(String login);

    Usuario alta(String login, String password, String nombre, String apellido, String email);

    Usuario modificar(Usuario u);

    void eliminar(Usuario u);

    List<Usuario> consultar(String apellido);

}
