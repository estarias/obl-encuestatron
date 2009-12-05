
package ort.discom.obl.negocio;

import java.util.List;
import javax.ejb.Stateless;
import ort.discom.obl.entidades.Cliente;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Stateless
public class ManejadorClienteBean implements ManejadorClienteRemote {

    @PersistenceContext
    private EntityManager em;

    public Cliente guardarCliente(Cliente cli) {
        em.persist(cli);
        em.flush();

        return cli;
    }

    public Cliente getCliente(String login) {
        return em.find(Cliente.class, login);
    }

    public void eliminarCliente(String login) {
        Cliente c = buscarCliente(login);
        em.remove(c);
    }

    public Cliente buscarCliente(String login) {
        return em.find(Cliente.class, login);
    }

    public Cliente Cliente(Cliente c) {
        return em.merge(c);
    }

    public List<Cliente> consultarTodos() {
        String jpql = "SELECT u FROM Cliente u";
        Query q = em.createQuery(jpql);
        return (List<Cliente>)q.getResultList();
    }

    public List<Cliente> consultarPorNombre(String nombre) {
        String jpql = "SELECT u FROM Cliente u WHERE u.nombre LIKE :nom";
        Query q = em.createQuery(jpql);
        q.setParameter("nom", nombre+"%");
        return (List<Cliente>)q.getResultList();
    }

//    Map<Long, Cliente> clientes = Collections.synchronizedMap(new HashMap<Long, Cliente>());
//
//    public Cliente guardarCliente(Cliente cli) {
//        clientes.put(cli.getId(), cli);
//        return clientes.get(cli.getId());
//    }
//
//    public Cliente getCliente(Long id) {
//        return clientes.get(id);
//    }
//
//    public boolean eliminarCliente(Long id) {
//        if (clientes.containsKey(id)){
//                clientes.remove(id);
//                return true;
//            }
//            return false;
//    }
//
//    public List<Cliente> buscarClientes() {
//       return new ArrayList(clientes.values());
//    }
    
 
 
}
