<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.ArrayList" %>
<%@page import="java.util.List" %>
<%@page import="ort.discom.obl.entidades.Agente" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Mantenimiento de agentes</title>
    </head>
    <body bgcolor="#c0c0c0">
        <%@include file="../cabezal.jsp" %>
        <h2>Agentes</h2>
        <a href="<%=application.getContextPath()%>/ControladorAgente?comando=listar">Lista de Agentes</a>&nbsp;
        <a href="<%=application.getContextPath()%>/ControladorAgente?comando=nuevo">Alta de Agente</a>
        <%
            //ArrayList agentes = (ArrayList) request.getAttribute("resultado");
            List agentes = (List) request.getAttribute("resultado");
            if (agentes != null) {
        %>
        <br/><br/>
        <table border="1">
            <caption>Agentes</caption>
            <tr>
                <th>Login</th>
                <th>Nombre</th>
                <th>Apellido</th>
                <th>e-Mail</th>
                <th>Rol</th>
                <th>Acci&oacute;n</th>
            </tr>
            <%
                    //for (Usuario usuario : usuarios) {
                    for (int i=0; i<agentes.size(); i++) {
                         Agente agente = (Agente)agentes.get(i);
            %>
            <tr>
                <td><%= agente.getLogin()%></td>
                <td><%= agente.getNombre()%></td>
                <td><%= agente.getApellido()%></td>
                <td><%= agente.getEmail()%></td>
                <td><%= agente.getRol()%></td>
                <td>
                    <a href="<%=application.getContextPath()%>/ControladorAgente?comando=editar&login=<%=agente.getLogin()%>">Editar</a>
                    <a href="<%=application.getContextPath()%>/ControladorAgente?comando=eliminar&login=<%=agente.getLogin()%>">Eliminar</a>
                </td>
            </tr>
            <%
                } // Cierro el for
%>
        </table>
        <%
            } // Cierro el if
%>
        <%@include file="../pie.html" %>
    </body>
</html>


