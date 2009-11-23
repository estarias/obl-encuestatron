/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ort.arqsoft.obl.logica;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import ort.arqsoft.obl.communication.SocketCommunication;
import ort.arqsoft.obl.dominio.Circuito;
import ort.arqsoft.obl.utils.Xml;

/**
 *
 * @author Felipe
 */
public class Consultas extends HttpServlet {
   
    private boolean isConnected = false;

    @Override
    public void init() {
        connectServer();        
    }
    
    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {

            if (request.getParameter("serie").equals("") || request.getParameter("numero").equals("")){
                out.println("<html>");
                out.println("<head>");
                out.println("<title>Servlet Consultas</title>");
                out.println("</head>");
                out.println("<body>");
                out.println("<h1>FALTAN DATOS!!</h1>");
                out.println("</body>");
                out.println("</html>");
            }else{
                
                Circuito circuito = getCircuito(request.getParameter("serie").toString(),request.getParameter("numero").toString());

                if (circuito.getNroCircuito()!=0){
                    out.println("<html>");
                    out.println("<head>");
                    out.println("<title>Consultas circuitos electorales</title>");
                    out.println("</head>");
                    out.println("<body>");
                    out.println("<h2> Numero de circuito:" + circuito.getNroCircuito() + "  - Local: " + circuito.getLocal() + "  - Dirección: " +  circuito.getDireccion() + " </h2>");
                    out.println("</body>");
                    out.println("</html>");
                }else{
                    out.println("<html>");
                    out.println("<head>");
                    out.println("<title>Consultas circuitos electorales</title>");
                    out.println("</head>");
                    out.println("<body>");
                    out.println("<h1>No fue posible encontrar el circuito para la credencial ingresada.</h1>");
                    out.println("</body>");
                    out.println("</html>");
                }
            }

        } finally { 
            out.close();
        }
    }

    private Circuito getCircuito(String serie, String numero){
        Circuito circuito = new Circuito();
                
        //ENVIO LA SOLICITUD DE LAS LISTAS
        if (isConnected) {
            SocketCommunication.sendData(Xml.getXMLSolicitarCircuito(serie,numero));
            
            esperaParaBuscarLaRespuesta();

            circuito = Xml.getCircuitoFromXml(SocketCommunication.readData());        
        }        
        return circuito;
    }
    private void connectServer(){
        //if (!isConnected) {
            isConnected = SocketCommunication.connectSocket();            
        //}
    }

    private void esperaParaBuscarLaRespuesta() {
        try {
            Thread.sleep(3500);
        } catch (InterruptedException ex) {
            Logger.getLogger(Consultas.class.getName()).log(Level.SEVERE, null, ex);
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
