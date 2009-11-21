package ort.discom.obl.negocio;
import java.util.List;
import ort.discom.obl.dominio.Cliente;
import javax.ejb.Local;


@Local
public interface ClienteLocal {
    Cliente guardarCliente (Cliente cli);

    Cliente getCliente (Long id);

    boolean eliminarCliente(Long id);

    List<Cliente>buscarClientes();
}
