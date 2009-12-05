<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.List" %>
<%@page import="ort.discom.obl.entidades.Agente" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Listado de Agentes</title>
    </head>
    <body bgcolor="#c0c0c0">
        <%@include file="../cabezal.jsp" %>
        <h2>Usuarios</h2>
        <form method="post" action="controladorAgente">
            Apellido: <input type="text" name="apellido"/>
            <input type="submit" name="comando" value="Buscar"/>
        </form>
        <a href="<%=application.getContextPath()%>/controladorAgente?comando=Nuevo"><b>Nuevo agente</b></a>
        <%
            List<Agente> agentes = (List<Agente>)request.getAttribute("agentes");
            if (agentes != null) {
        %>
        <br/><br/>
        <table border="1">
            <caption>Agentes</caption>
            <tr>
                <th>Nombre</th>
                <th>Apellido</th>
                <th>e-Mail</th>
                <th>Login</th>
                <th>Acci&oacute;n</th>
            </tr>
            <%
                    for (Agente agente : agentes) {
            %>
            <tr>
                <td><%= agente.getNombre()%></td>
                <td><%= agente.getApellido()%></td>
                <td><%= agente.getEmail()%></td>
                <td><%= agente.getLogin()%></td>
                <td>
                    <a href="<%=application.getContextPath()%>/controladorAgente?comando=Editar&login=<%=agente.getLogin()%>">Editar</a>
                    <a href="<%=application.getContextPath()%>/controladorAgente?comando=Eliminar&login=<%=agente.getLogin()%>">Eliminar</a>
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