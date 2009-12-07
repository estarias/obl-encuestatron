/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ort.discom.obl.controlador;

import ort.discom.obl.entidades.Agente;
import ort.discom.obl.negocio.*;

import java.io.IOException;
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
    private ManejadorAgenteRemote agenteBean;

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

        List<Agente> agentes = agenteBean.consultarTodos();

        if (comando.equals("listar")) {
            if (agentes.size() == 0) {
                mensaje = "No hay clientes definidos en el sistema";
            }
            // Ejecuto la consulta
            request.setAttribute("resultado", agentes);
            forwardTo = "/administradores/mantenedorAgentes.jsp";

        } else if (comando.equals("nuevo")) {
            mensaje = "Alta de Agente";
            forwardTo = "/administradores/editarAgente.jsp";

        } else if (comando.equals("Salvar")) {
            Agente a = new Agente();
            //u.setCelular(Integer.parseInt(request.getParameter("celular")));
                       
            a.setNombre(request.getParameter("nombre"));
            a.setApellido(request.getParameter("apellido"));
            a.setLogin(request.getParameter("login"));
            a.setPassword(request.getParameter("password"));
            a.setEmail(request.getParameter("email"));
            a.setRol("AGENTE");

            agenteBean.guardarAgente(a);
            request.setAttribute("resultado", agentes);
            mensaje = "Agente guardado";
            //forwardTo = "/administradores/mantenedorAgentes.jsp";
            forwardTo = "/ControladorAgente?comando=listar&login=" + request.getParameter("login");
        } else if (comando.equals("editar")) {
//            Long id = Long.parseLong( request.getParameter("login"));
//            Agente u = agenteBean.getAgente(id);
            String login = request.getParameter("login");
            Agente u = agenteBean.getAgente(login);
            if (u != null) {
                request.setAttribute("agente", u);
                mensaje = "Editando agente existente";
                forwardTo = "/administradores/editarAgente.jsp";
            } else {
                mensaje = "No se encontr&oacuute; el agente" + login;
                forwardTo = "/administradores/mantenedorAgentes.jsp";
            }
        } else if (comando.equals("eliminar")) {
//            Long id = Long.parseLong( request.getParameter("id"));
//            agenteBean.eliminarAgente(id);
            String login = request.getParameter("login");
            agenteBean.eliminarAgente(login);
            //if (agenteBean.eliminarAgente(id)) {
                mensaje = "Agente eliminado: " + login;
            //} else {
            //    mensaje = "No se encontr&oacuute; el agente " + id;
            //}
            request.setAttribute("resultado", agentes);
            forwardTo = "/administradores/mantenedorAgentes.jsp";
        } else if (comando.equals("Cancelar")) {
            forwardTo = "/ControladorAgente?comando=listar&login=" + request.getParameter("login");
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
