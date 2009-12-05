
package ort.discom.obl.controlador;


import ort.discom.obl.entidades.*;
import ort.discom.obl.negocio.*;

import javax.ejb.EJB;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class ControladorCliente extends HttpServlet {
    @EJB
    private ManejadorClienteRemote clienteBean;
    @EJB
    private ManejadorAgenteRemote agenteBean;

    @Override
    public void init() {       
        if (getServletContext().getAttribute("clientes") == null) {
            getServletContext().setAttribute("clientes", clienteBean);
        }
         if (getServletContext().getAttribute("agentes") == null) {
            getServletContext().setAttribute("agentes", agenteBean);
        }
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String comando = request.getParameter("comando");
        String forwardTo = null;
        String mensaje = null;

        List<Cliente> clientes = clienteBean.consultarTodos();

        if (comando.equals("listar")) {
            if (clientes.size() == 0) {
                mensaje = "No hay clientes definidos en el sistema";
            }
            // Ejecuto la consulta
            request.setAttribute("resultado", clientes);
            forwardTo = "/administradores/mantenedorClientes.jsp";

        } else if (comando.equals("nuevo")) {
            mensaje = "Editando nuevo cliente";
            forwardTo = "/administradores/editarCliente.jsp";

        } else if (comando.equals("salvar")) {
            Cliente c = new Cliente();
            c.setCelular(request.getParameter("celular"));

            //Long id_agente = Long.parseLong(request.getParameter("id_agente"));
            Agente age = new Agente(); //buscarAgente(id_agente)
            c.setElAgente(agenteBean.getAgente("fefe"));

            c.setEmail(request.getParameter("email"));
            Date fecha_actual = new Date();            
            c.setFecha_ing(fecha_actual);
            c.setNombre(request.getParameter("nombre"));
            c.setTelefono(request.getParameter("telefono"));

            //u.setId(Long.parseLong("1")); //autonumerico si dios quiere por ahora le pongo 1
            //u.setLasEncuestas(new ArrayList);
                                 
            clienteBean.guardarCliente(c);
            request.setAttribute("resultado", clientes);
            mensaje = "Cliente guardado";
            forwardTo = "/administradores/mantenedorClientes.jsp";

        } else if (comando.equals("editar")) {
            String id = request.getParameter("id");
            Cliente c = clienteBean.getCliente(id);
            if (c != null) {
                request.setAttribute("cliente", c);
                mensaje = "Editando cliente existente";
                forwardTo = "/administradores/editarCliente.jsp";
            } else {
                mensaje = "No se encontr&oacuute; el cliente" + id;
                forwardTo = "/administradores/mantenedorClientes.jsp";
            }
        } else if (comando.equals("eliminar")) {
            String id = request.getParameter("id");
//          if (clienteBean.eliminarCliente(id)) {
            clienteBean.eliminarCliente(id);
                mensaje = "Cliente eliminado: " + id;
//          } else {
//              mensaje = "No se encontr&oacuute; el cliente " + id;
//          }
            request.setAttribute("resultado", clientes);
            forwardTo = "/administradores/mantenedorClientes.jsp";
        } else {
            mensaje = "No se reconoce el comando";
            forwardTo = "/index.jsp";
        }
        if (mensaje != null) {
            request.setAttribute("mensaje", mensaje);
        }
        request.getRequestDispatcher(forwardTo).forward(request, response);
    }


    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /** 
     * Handles the HTTP <code>GET</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        processRequest(request, response);
    } 

    /** 
     * Handles the HTTP <code>POST</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        processRequest(request, response);
    }

    /** 
     * Returns a short description of the servlet.
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
