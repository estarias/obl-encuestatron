
package ort.discom.obl.controlador;

import ort.discom.obl.entidades.*;
import ort.discom.obl.negocio.*;

import javax.ejb.EJB;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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
        List<Agente> agentes = agenteBean.consultarTodos();

        if (comando.equals("listar")) {
            if (clientes.size() == 0) {
                mensaje = "No hay clientes definidos en el sistema";
            }            
            // Ejecuto la consulta
            request.setAttribute("resultado", clientes);
            forwardTo = "/administradores/mantenedorClientes.jsp";

        } else if (comando.equals("nuevo")) {
            if (agentes.size() != 0) {
                request.setAttribute("resultadoAgentes", agentes);
            }            
            mensaje = "Editando nuevo cliente";
            forwardTo = "/administradores/editarCliente.jsp";

        } else if (comando.equals("Calvar")) {
            Cliente c = new Cliente();
            DateFormat myDateFormat = new SimpleDateFormat("DD/MM/yyyy");
//            Date fecha_actual = new Date();
//            c.setFecha_ing(fecha_actual);            
            if (!request.getParameter("id").equals(""))
                c.setId(Long.parseLong(request.getParameter("id")));
            c.setNombre(request.getParameter("nombre"));
            c.setApellido(request.getParameter("apellido"));
            c.setTelefono(request.getParameter("telefono"));
            c.setCelular(request.getParameter("celular"));
            c.setEmail(request.getParameter("email"));
            try {
                c.setFecha_ing((Date)myDateFormat.parse(request.getParameter("fecha_ingreso")));
            } catch (ParseException ex) {
                 System.out.println("Invalid Date Parser Exception ");
                 ex.printStackTrace();
            }
            c.setElAgente(agenteBean.getAgente(request.getParameter("agente_login")));
            if (!request.getParameter("id").equals(""))
                clienteBean.actualizarCliente(c);
            else
                clienteBean.guardarCliente(c);            
            request.setAttribute("resultado", clientes);
            mensaje = "Cliente guardado";
            forwardTo = "/administradores/mantenedorClientes.jsp";

        } else if (comando.equals("editar")) {
            long id = Long.parseLong(request.getParameter("id"));
            Cliente c = clienteBean.getCliente(id);
            if (c != null) {
                if (agentes.size() != 0) {
                    request.setAttribute("resultadoAgentes", agentes);
                }
                request.setAttribute("cliente", c);
                mensaje = "Editando cliente existente";
                forwardTo = "/administradores/editarCliente.jsp";
            } else {
                mensaje = "No se encontr&oacuute; el cliente" + id;
                forwardTo = "/administradores/mantenedorClientes.jsp";
            }

        } else if (comando.equals("eliminar")) {
            long id = Long.parseLong(request.getParameter("id"));
            if (clienteBean.eliminarCliente(id)) {
                mensaje = "Cliente eliminado: " + id;
            } else {
                mensaje = "No se encontr&oacuute; el cliente " + id;
            }            
            request.setAttribute("resultado", clientes);
            forwardTo = "/administradores/mantenedorClientes.jsp";

        } else if (comando.equals("Cancelar")) {
            forwardTo = "/ControladorCliente?comando=listar";
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
