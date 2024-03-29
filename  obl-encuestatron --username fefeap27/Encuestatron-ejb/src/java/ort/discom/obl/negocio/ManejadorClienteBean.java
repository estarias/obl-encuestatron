
package ort.discom.obl.negocio;

import java.util.List;
import javax.ejb.Stateless;
import ort.discom.obl.entidades.Cliente;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.Query;

@Stateless
public class ManejadorClienteBean implements ManejadorClienteRemote {

    @PersistenceContext
    private EntityManager em;
    
//    @PersistenceContext
//    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpa");
//
//    @PersistenceContext
//    private EntityManager em = emf.createEntityManager();


    public Cliente guardarCliente(Cliente cli) {
        try{
            em.persist(cli);
            em.flush();

        } catch(PersistenceException ex) {
            System.out.println(ex.getMessage());
        }
        return cli;
    }

    public Cliente getCliente(Long id) {
        return em.find(Cliente.class, id);
    }
 
    public boolean eliminarCliente(long id) {
        try{            
            Cliente cliente = buscarCliente(id);
            if (cliente != null)
                em.remove(cliente);
        } catch(Exception ex) {
            return false;
        }
        return true;
    }

    public boolean actualizarCliente(Cliente cli) {                            
        try{           
//            Cliente cliente = buscarCliente(cli.getId());
//            if (cliente != null){
                em.merge(cli);
//            }
//            em.flush();
        } catch(Exception ex) {            
            return false;                    
        }
        return true;
    }

    public Cliente buscarCliente(long id) {
        return em.find(Cliente.class, id);
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
