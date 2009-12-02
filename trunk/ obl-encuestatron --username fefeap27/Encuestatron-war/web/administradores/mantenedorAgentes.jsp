<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.ArrayList" %>
<%@page import="ort.discom.obl.entidades.Agente" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Mantenimiento de agentes</title>
    </head>
    <body>
        <%@include file="../cabezal.jsp" %>
        <h2>Agentes</h2>
        <a href="<%=application.getContextPath()%>/ControladorAgente?comando=listar"><b>Listar</b></a>&nbsp;
        <a href="<%=application.getContextPath()%>/ControladorAgente?comando=nuevo"><b>Nuevo Agente</b></a>
        <%
            ArrayList agentes = (ArrayList) request.getAttribute("resultado");
            if (agentes != null) {
        %>
        <br/><br/>
        <table border="1">
            <caption>Agentes</caption>
            <tr>
                <th>Nombre</th>
                <th>Id</th>
                <th>Acci&oacute;n</th>
            </tr>
            <%
                    //for (Usuario usuario : usuarios) {
                    for (int i=0; i<agentes.size(); i++) {
                         Agente agente = (Agente)agentes.get(i);
            %>
            <tr>
                <td><%= agente.getNombre()%></td>
                <td><%= agente.getLogin()%></td>
                <td>
                    <a href="<%=application.getContextPath()%>/ControladorAgente?comando=editar&id=<%=agente.getId()%>">Editar</a>
                    <a href="<%=application.getContextPath()%>/ControladorAgente?comando=eliminar&id=<%=agente.getId()%>">Eliminar</a>
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


