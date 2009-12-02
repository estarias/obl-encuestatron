/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ort.discom.obl.controlador;

import ort.discom.obl.entidades.*;
import ort.discom.obl.negocio.*;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Felipe
 */
public class ControladorAgente extends HttpServlet {
   
     @EJB
    private AgenteLocal agenteBean;

    @Override
    public void init() {
        if (getServletContext().getAttribute("agentes") == null) {
            getServletContext().setAttribute("agentes", agenteBean);
        }
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {

        String comando = request.getParameter("comando");
        String forwardTo = null;
        String mensaje = null;

        List<Agente> agentes = agenteBean.buscarAgentes();

        if (comando.equals("listar")) {
            if (agentes.size() == 0) {
                mensaje = "No hay clientes definidos en el sistema";
            }
            // Ejecuto la consulta
            request.setAttribute("resultado", agentes);
            forwardTo = "/administradores/mantenedorAgentes.jsp";

        } else if (comando.equals("nuevo")) {
            mensaje = "Editando nuevo agente";
            forwardTo = "/administradores/editarAgente.jsp";

        } else if (comando.equals("salvar")) {
//            Cliente u = new Cliente();
//            u.setCelular(Integer.parseInt(request.getParameter("celular")));
//
//            //Long id_agente = Long.parseLong(request.getParameter("id_agente"));
//            Agente age = new Agente(); //buscarAgente(id_agente)
//            u.setElAgente(age);
//
//            u.setEmail(request.getParameter("email"));
//            Date fecha_actual = new Date();
//            u.setFecha_ing(fecha_actual);
//            u.setId(Long.parseLong("1")); //autonumerico si dios quiere por ahora le pongo 1
//            //u.setLasEncuestas(new ArrayList);
//            u.setNombre(request.getParameter("nombre"));
//            u.setTelefono(request.getParameter("telefono"));
//
//
//            clienteBean.guardarCliente(u);
//            request.setAttribute("resultado", clientes);
//            mensaje = "Cliente guardado";
//            forwardTo = "/administradores/mantenedorClientes.jsp";
        } else if (comando.equals("editar")) {
            Long id = Long.parseLong( request.getParameter("id"));
            Agente u = agenteBean.getAgente(id);
            if (u != null) {
                request.setAttribute("agente", u);
                mensaje = "Editando agente existente";
                forwardTo = "/administradores/editarAgente.jsp";
            } else {
                mensaje = "No se encontr&oacuute; el agente" + id;
                forwardTo = "/administradores/mantenedorAgentes.jsp";
            }
        } else if (comando.equals("eliminar")) {
            Long id = Long.parseLong( request.getParameter("id"));
            if (agenteBean.eliminarAgente(id)) {
                mensaje = "Agente eliminado: " + id;
            } else {
                mensaje = "No se encontr&oacuute; el agente " + id;
            }
            request.setAttribute("resultado", agentes);
            forwardTo = "/administradores/mantenedorAgentes.jsp";
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
