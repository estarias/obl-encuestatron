<%-- 
    Document   : mantenedorEncuestas
    Created on : 05-dic-2009, 10:39:46
    Author     : Felipe
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.ArrayList" %>
<%@page import="java.util.List" %>
<%@page import="ort.discom.obl.entidades.Encuesta" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Mantenimiento de encuestas</title>
    </head>
    <body bgcolor="#c0c0c0">
        <%@include file="../cabezal.jsp" %>
        <h2>Encuestas</h2>
        <a href="<%=application.getContextPath()%>/ControladorEncuesta?comando=listar">Lista de Encuestas</a>&nbsp;
        <a href="<%=application.getContextPath()%>/ControladorEncuesta?comando=nuevo">Alta de Encuesta</a>&nbsp;
        <%
            //ArrayList agentes = (ArrayList) request.getAttribute("resultado");
            List encuestas = (List) request.getAttribute("resultado");
            if (encuestas != null) {
        %>
        <br/><br/>
        <table border="2" align="center">
            <caption>Encuestas</caption>
            <tr>
            
                <th>Id</th>
                <th>Nombre</th>
                <th>Cliente</th>
                <th>Fecha de ingreso</th>
                <th>Fecha de modificación</th>
                <th>Fecha de comienzo</th>
                <th>Fecha de cierre</th>
                <th>Agente</th>

                <th>Acci&oacute;n</th>
            </tr>
            <%
                    //for (Usuario usuario : usuarios) {
                    for (int i=0; i<encuestas.size(); i++) {
                         Encuesta encuesta = (Encuesta)encuestas.get(i);
            %>
            <tr>

                <td><%= encuesta.getId()%></td>
                <td><%= encuesta.getNombre()%></td>
                <td><%= encuesta.getCliente()%></td>
                <td><%= encuesta.getFecha_ingreso()%></td>
                <td><%= encuesta.getFecha_modificacion()%></td>
                <td><%= encuesta.getFecha_comienzo()%></td>
                <td><%= encuesta.getFecha_cierre()%></td>
                <td><%= encuesta.getAgente()%></td>

                <td>
                    <a href="<%=application.getContextPath()%>/ControladorEncuesta?comando=editar&id=<%=encuesta.getId()%>">Editar</a>
                    <a href="<%=application.getContextPath()%>/ControladorEncuesta?comando=eliminar&id=<%=encuesta.getId()%>">Eliminar</a>
                </td>
            </tr>
            <%
                } // Cierro el for
%>
        </table>
        <%
            } // Cierro el if
%>
        <br/><br/><br/>
        <a href="<%=application.getContextPath()%>/agentes/mantenedorPreguntas.jsp">Alta de Preguntas - Respuestas</a>
        
        <%@include file="../pie.html" %>
    </body>
</html>


