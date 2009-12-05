/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ort.discom.obl.controlador;

import java.io.IOException;
import java.util.List;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import ort.discom.obl.entidades.Encuesta;
import ort.discom.obl.negocio.ManejadorEncuestaRemote;

/**
 *
 * @author Felipe
 */
public class ControladorEncuesta extends HttpServlet {
   
     @EJB
    private ManejadorEncuestaRemote encuestaBean;

    @Override
    public void init() {
        if (getServletContext().getAttribute("encuestas") == null) {
            getServletContext().setAttribute("encuestas", encuestaBean);
        }
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        String comando = request.getParameter("comando");
        String forwardTo = null;
        String mensaje = null;

        List<Encuesta> encuestas = encuestaBean.consultarTodos();

        if (comando.equals("listar")) {
            if (encuestas.size() == 0) {
                mensaje = "No hay encuestas definidos en el sistema";
            }
            // Ejecuto la consulta
            request.setAttribute("resultado", encuestas);
            forwardTo = "/administradores/mantenedorEncuesta.jsp";

        } else if (comando.equals("nuevo")) {
            mensaje = "Alta de Agente";
            forwardTo = "/administradores/editarEncuesta.jsp";

        } else if (comando.equals("salvar")) {
            Encuesta e = new Encuesta();
            //u.setCelular(Integer.parseInt(request.getParameter("celular")));

            e.setNombre(request.getParameter("nombre"));
//            a.setApellido(request.getParameter("apellido"));
//            a.setLogin(request.getParameter("login"));
//            a.setPassword(request.getParameter("password"));
//            a.setEmail(request.getParameter("email"));
//            a.setRol("AGENTE");

            encuestaBean.guardarEncuesta(e);
            request.setAttribute("resultado", encuestas);
            mensaje = "Encuesta guardado";
            forwardTo = "/administradores/mantenedorEncuestas.jsp";
        } else if (comando.equals("editar")) {
            Long id = Long.parseLong( request.getParameter("id"));
            Encuesta e = encuestaBean.getEncuesta(id);
            if (e != null) {
                request.setAttribute("encuesta", e);
                mensaje = "Editando encuesta existente";
                forwardTo = "/administradores/editarEncuesta.jsp";
            } else {
                mensaje = "No se encontr&oacuute; el encuesta" + id;
                forwardTo = "/administradores/mantenedorEncuesta.jsp";
            }
        } else if (comando.equals("eliminar")) {
            Long id = Long.parseLong( request.getParameter("id"));
//            agenteBean.eliminarAgente(id);
            encuestaBean.eliminarEncuesta(id);
            //if (agenteBean.eliminarAgente(id)) {
                mensaje = "Agente eliminado: " + id;
            //} else {
            //    mensaje = "No se encontr&oacuute; el agente " + id;
            //}
            request.setAttribute("resultado", encuestas);
            forwardTo = "/administradores/mantenedorEncuesta.jsp";
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
