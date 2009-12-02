
package ort.discom.obl.negocio;

import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import javax.ejb.Stateless;
import ort.discom.obl.entidades.Cliente;


@Stateless
public class ClienteBean implements ClienteLocal {

    Map<Long, Cliente> clientes = Collections.synchronizedMap(new HashMap<Long, Cliente>());

    public Cliente guardarCliente(Cliente cli) {
        clientes.put(cli.getId(), cli);
        return clientes.get(cli.getId());
    }

    public Cliente getCliente(Long id) {
        return clientes.get(id);
    }

    public boolean eliminarCliente(Long id) {
        if (clientes.containsKey(id)){
                clientes.remove(id);
                return true;
            }
            return false;
    }

    public List<Cliente> buscarClientes() {
       return new ArrayList(clientes.values());
    }
    
 
 
}
