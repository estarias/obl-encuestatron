package ort.discom.obl.negocio;
import java.util.List;
import ort.discom.obl.entidades.Cliente;
import javax.ejb.Remote;

@Remote
public interface ManejadorClienteRemote {

    Cliente guardarCliente (Cliente cli);

    boolean actualizarCliente (Cliente cli);
    
    Cliente getCliente (Long id);

    boolean eliminarCliente(long id);

    List<Cliente>consultarTodos();
}
