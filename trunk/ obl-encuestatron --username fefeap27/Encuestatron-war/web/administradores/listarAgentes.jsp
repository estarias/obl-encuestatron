<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.List" %>
<%@page import="ort.discom.obl.entidades.Usuario" %>
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
        <form method="post" action="controladorABMUsuarios">
            Apellido: <input type="text" name="apellido"/>
            <input type="submit" name="comando" value="Buscar"/>
        </form>
        <a href="<%=application.getContextPath()%>/controladorABMUsuarios?comando=Nuevo"><b>Nuevo agente</b></a>
        <%
            List<Usuario> usuarios = (List<Usuario>)request.getAttribute("usuarios");
            if (usuarios != null) {
        %>
        <br/><br/>
        <table border="1">
            <caption>Agentes</caption>
            <tr>
                <th>Nombre</th>
                <th>Apellido</th>
                <th>Login</th>
                <th>Acci&oacute;n</th>
            </tr>
            <%
                    for (Usuario usuario : usuarios) {
            %>
            <tr>
                <td><%= usuario.getNombre()%></td>
                <td><%= usuario.getApellido()%></td>
                <td><%= usuario.getLogin()%></td>
                <td>
                    <a href="<%=application.getContextPath()%>/controladorABMUsuarios?comando=Editar&login=<%=usuario.getLogin()%>">Editar</a>
                    <a href="<%=application.getContextPath()%>/controladorABMUsuarios?comando=Eliminar&login=<%=usuario.getLogin()%>">Eliminar</a>
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