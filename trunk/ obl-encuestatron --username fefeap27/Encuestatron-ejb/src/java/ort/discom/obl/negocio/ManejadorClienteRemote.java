package ort.discom.obl.negocio;
import java.util.List;
import ort.discom.obl.entidades.Cliente;
import javax.ejb.Remote;

@Remote
public interface ManejadorClienteRemote {

    Cliente guardarCliente (Cliente cli);

    Cliente getCliente (String id);

    void eliminarCliente(String id);

    List<Cliente>consultarTodos();
}
