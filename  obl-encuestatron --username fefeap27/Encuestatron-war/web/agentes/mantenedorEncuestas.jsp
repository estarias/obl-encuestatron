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
    <body>
        <%@include file="../cabezal.jsp" %>
        <h2>Encuestas</h2>
        <a href="<%=application.getContextPath()%>/ControladorEncuesta?comando=listar"><b>Lista de Encuestas</b></a>&nbsp;
        <a href="<%=application.getContextPath()%>/ControladorEncuesta?comando=nuevo"><b>Alta de Encuesta</b></a>
        <%
            //ArrayList agentes = (ArrayList) request.getAttribute("resultado");
            List encuestas = (List) request.getAttribute("resultado");
            if (encuestas != null) {
        %>
        <br/><br/>
        <table border="1">
            <caption>Encuestas</caption>
            <tr>
            
                <th>Id</th>
                <th>Nombre</th>

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

                <td>
                    <a href="<%=application.getContextPath()%>/ControladorEncuesta?comando=editar&login=<%=encuesta.getId()%>">Editar</a>
                    <a href="<%=application.getContextPath()%>/ControladorEncuesta?comando=eliminar&login=<%=encuesta.getId()%>">Eliminar</a>
                </td>
            </tr>
            <%
                } // Cierro el for
%>
        </table>
        <%
            } // Cierro el if
%>
    </body>
</html>


