/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ort.discom.obl.controlador;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.faces.convert.DateTimeConverter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import ort.discom.obl.entidades.Agente;
import ort.discom.obl.entidades.Cliente;
import ort.discom.obl.entidades.Encuesta;
import ort.discom.obl.negocio.ManejadorAgenteRemote;
import ort.discom.obl.negocio.ManejadorClienteRemote;
import ort.discom.obl.negocio.ManejadorEncuestaRemote;

/**
 *
 * @author Felipe
 */
public class ControladorEncuesta extends HttpServlet {
   
    @EJB
    private ManejadorEncuestaRemote encuestaBean;
    @EJB
    private ManejadorClienteRemote clienteBean;
    @EJB
    private ManejadorAgenteRemote agenteBean;

    @Override
    public void init() {
        if (getServletContext().getAttribute("encuestas") == null) {
            getServletContext().setAttribute("encuestas", encuestaBean);
        }
        if (getServletContext().getAttribute("clientes") == null) {
            getServletContext().setAttribute("clientes", clienteBean);
        }
        if (getServletContext().getAttribute("agentes") == null) {
            getServletContext().setAttribute("agentes", agenteBean);
        }
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException, ParseException {
        String comando = request.getParameter("comando");
        String forwardTo = null;
        String mensaje = null;
    
    try {

        List<Encuesta> encuestas = encuestaBean.consultarTodos();

        if (comando.equals("listar")) {
            if (encuestas.size() == 0) {
                mensaje = "No hay encuestas definidos en el sistema";
            }

            // Ejecuto la consulta
            request.setAttribute("resultado", encuestas);
            forwardTo = "/agentes/mantenedorEncuestas.jsp";

        } else if (comando.equals("nuevo")) {
            mensaje = "Alta de Encuesta";
            forwardTo = "/agentes/editarEncuesta.jsp";

        } else if (comando.equals("Salvar")) {
            Encuesta e = new Encuesta();            
            DateFormat myDateFormat = new SimpleDateFormat("dd/mm/yyyy");            
            //Date myDate = null;
            //e.setId(Long.parseLong(request.getParameter("id")));
            e.setNombre(request.getParameter("nombre"));
            e.setClave(request.getParameter("clave"));

            try {
                e.setFecha_ingreso(myDateFormat.parse(request.getParameter("fecha_ingreso")));
                e.setFecha_modificacion(myDateFormat.parse(request.getParameter("fecha_modificacion")));
                e.setFecha_comienzo(myDateFormat.parse(request.getParameter("fecha_comienzo")));
                e.setFecha_cierre(myDateFormat.parse(request.getParameter("fecha_cierre")));
            } catch (ParseException ex) {
                 System.out.println("Invalid Date Parser Exception ");
                 ex.printStackTrace();
            }
                     
            e.setAgente(agenteBean.getAgente(request.getParameter("agente")));
            e.setCliente(clienteBean.getCliente(Long.parseLong(request.getParameter("cliente"))));

            encuestaBean.guardarEncuesta(e);

            request.setAttribute("resultado", encuestas);
            mensaje = "Encuesta guardado";
            forwardTo = "/agentes/mantenedorEncuestas.jsp";

        } else if (comando.equals("editar")) {
            long id = Long.parseLong(request.getParameter("id"));
            Encuesta e = encuestaBean.getEncuesta(id);
            if (e != null) {
                request.setAttribute("encuesta", e);
                mensaje = "Editando encuesta existente";
                forwardTo = "/agentes/editarEncuesta.jsp";
            } else {
                mensaje = "No se encontr&oacuute; la encuesta" + id;
                forwardTo = "/agentes/mantenedorEncuesta.jsp";
            }
        } else if (comando.equals("eliminar")) {
            long id = Long.parseLong( request.getParameter("id"));
            encuestaBean.eliminarEncuesta(id);
            //if (agenteBean.eliminarAgente(id)) {
                mensaje = "Encuesta eliminada: " + id;
            //} else {
            //    mensaje = "No se encontr&oacuute; el agente " + id;
            //}
            request.setAttribute("resultado", encuestas);
            forwardTo = "/agentes/mantenedorEncuestas.jsp";
        } else if (comando.equals("Cancelar")) {
            forwardTo = "/ControladorEncuesta?comando=listar";
        } else {
            mensaje = "No se reconoce el comando";
            forwardTo = "/index.jsp";
        }
        if (mensaje != null) {
            request.setAttribute("mensaje", mensaje);
        }
        request.getRequestDispatcher(forwardTo).forward(request, response);

    } catch (Exception ex) {
        System.out.println(ex.getMessage());
    }
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
        try {
            processRequest(request, response);
        } catch (ParseException ex) {
            Logger.getLogger(ControladorEncuesta.class.getName()).log(Level.SEVERE, null, ex);
        }
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
        try {
            processRequest(request, response);
        } catch (ParseException ex) {
            Logger.getLogger(ControladorEncuesta.class.getName()).log(Level.SEVERE, null, ex);
        }
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
