/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ort.discom.obl.negocio;

import java.util.List;
import javax.ejb.Remote;
import ort.discom.obl.entidades.Encuesta;

/**
 *
 * @author Felipe
 */
@Remote
public interface ManejadorEncuestaRemote {

    Encuesta guardarEncuesta (Encuesta enc);

    Encuesta getEncuesta (long id);

    void eliminarEncuesta(long id);

    List<Encuesta>consultarTodos();

}
