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
import ort.discom.obl.entidades.Usuario;
import ort.discom.obl.servicios.ManejadorUsuariosLocal;

/**
 *
 * @author Felipe
 */

public class ControladorABMUsuarios extends HttpServlet {

    @EJB
    private ManejadorUsuariosLocal manejadorUsuarios;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String comando = request.getParameter("comando");

        // Usada para determinar la vista a la que se le pasará el control al
        // terminar de procesar el pedido
        String forwardTo = null;

        // Contiene un mensaje que se le mostrará al usuario al terminar
        // de procesar el pedido (se muestra en cabezal.jsp)
        String mensaje = "";

        // Recuerdo mensajes anteriores en caso de estar recibiendo un forward
        // para que no se pierdan y poder mostrarlos
        if (request.getAttribute("mensaje") != null) {
            mensaje += request.getAttribute("mensaje") + " / ";
        }

        // Proceso el comando de búsqueda
        if (comando.equals("Buscar")) {

            // Obtengo los parámetros para la búsqueda
            String apellido = request.getParameter("apellido");

            // Uso el servicio de negocios para buscar usuarios
            List<Usuario> usuarios = manejadorUsuarios.consultar(apellido);

            if (usuarios.size() == 0) {
                mensaje += "No se encontraron resultados para su b&uacute;squeda";
                if (apellido != null && apellido.length() > 0) {
                    mensaje += " [ apellido comienza con " + apellido + " ]" ;
                }
            } else {
                if (apellido != null && apellido.length() > 0) {
                    mensaje += "Mostrando usuarios cuyo apellido comienza con: " + apellido;
                } else {
                    mensaje += "Mostrando todos los usuarios";
                }
            }

            // Guardo el modelo en el contexto de request para
            // que la vista pueda recuperarlo y buscarlo
            request.setAttribute("usuarios", usuarios);

            forwardTo = "/usuarios/listarUsuarios.jsp";

            // Recuerdo los filtros de la búsqueda
            // cuando se crea, edita o borra un usuario siempre se vuelve a la
            // busqueda y necesito saber que estaba buscando
            request.getSession().setAttribute("ultimoApellidoBuscado", apellido != null ? apellido : "");

        } else if (comando.equals("Nuevo")) {

            mensaje += "Editando nuevo usuario";

            // Le paso al editor el comando que deberá enviarme para grabar el usuario
            request.setAttribute("comando", "Alta");

            forwardTo = "/usuarios/editarUsuario.jsp";

        } else if (comando.equals("Editar")) {

            String login = (String) request.getParameter("login");
            Usuario u = manejadorUsuarios.obtener(login);

            if (u != null) {

                // Seteo el modelo de usuario en el pedido para que el editor lo
                // pueda mostrar
                request.setAttribute("usuario", u);

                // Le paso al editor el comando que deberá enviarme para modificar el usuario
                request.setAttribute("comando", "Modificar");

                mensaje += "Editando usuario existente";
                forwardTo = "/usuarios/editarUsuario.jsp";
            } else {
                mensaje += "No se encontr&oacuute; el usuario " + login;
                forwardTo = "/controladorABMUsuarios?comando=Buscar&apellido=" + request.getSession().getAttribute("ultimoApellidoBuscado");
            }

        } else if (comando.equals("Alta")) {

            String nombre = request.getParameter("nombre");
            String apellido = request.getParameter("apellido");
            String login = request.getParameter("login");
            String password = request.getParameter("password");

            manejadorUsuarios.alta(login, password, nombre, apellido);

            mensaje += "Usuario guardado";
            forwardTo = "/controladorABMUsuarios?comando=Buscar&apellido=" + request.getSession().getAttribute("ultimoApellidoBuscado");

        } else if (comando.equals("Modificar")) {

            Usuario u = new Usuario();
            u.setNombre(request.getParameter("nombre"));
            u.setApellido(request.getParameter("apellido"));
            u.setLogin(request.getParameter("login"));
            u.setPassword(request.getParameter("password"));

            manejadorUsuarios.modificar(u);

            mensaje += "Usuario guardado";
            forwardTo = "/controladorABMUsuarios?comando=Buscar&apellido=" + request.getSession().getAttribute("ultimoApellidoBuscado");

        } else if (comando.equals("Eliminar")) {

            String login = (String) request.getParameter("login");
            Usuario u = manejadorUsuarios.obtener(login);

            if (u != null) {
                manejadorUsuarios.eliminar(u);
                mensaje += "Usuario eliminado: " + login;
                forwardTo = "/controladorABMUsuarios?comando=Buscar&apellido=" + request.getSession().getAttribute("ultimoApellidoBuscado");
            } else {
                mensaje += "No se encontr&oacuute; el usuario " + login;
                forwardTo = "/controladorABMUsuarios?comando=Buscar&apellido=" + request.getSession().getAttribute("ultimoApellidoBuscado");
            }

        } else if (comando.equals("Cancelar")) {

            // Proceso el comando Cancelar del editor de usuarios

            forwardTo = "/controladorABMUsuarios?comando=Buscar&apellido=" + request.getSession().getAttribute("ultimoApellidoBuscado");
            mensaje = null;

        } else {

            mensaje += "NO SE RECONOCE EL COMANDO";
            forwardTo = "/index.jsp";

        }

        if (mensaje != null && mensaje.length() > 0) {
            // Seteo el mensaje que la vista (cabezal.jsp) le mostrará al cliente
            request.setAttribute("mensaje", mensaje);
        }

        // Le paso el control a la vista
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
