<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.ArrayList" %>
<%@page import="java.util.List" %>
<%@page import="ort.discom.obl.entidades.Cliente" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Mantenimiento de clientes</title>
    </head>
    <body bgcolor="#c0c0c0">
        <%@include file="../cabezal.jsp" %>
        <h2>Clientes</h2>
        <a href="<%=application.getContextPath()%>/ControladorCliente?comando=listar">Lista de Clientes</a>&nbsp;
        <a href="<%=application.getContextPath()%>/ControladorCliente?comando=nuevo">Alta de Cliente</a>
        <%
            List clientes = (List) request.getAttribute("resultado");
            if (clientes != null) {
        %>
        <br/><br/>
        <table border="1">
            <caption>Clientes</caption>
            <tr>
                <th>Nombre</th>
                <th>Id</th>
                <th>Acci&oacute;n</th>
            </tr>
            <%                    
                    for (int i=0; i<clientes.size(); i++) {
                         Cliente cliente = (Cliente)clientes.get(i);
            %>
            <tr>
                <td><%= cliente.getNombre()%></td>
                <td><%= cliente.getId()%></td>
                <td>
                    <a href="<%=application.getContextPath()%>/ControladorCliente?comando=editar&id=<%=cliente.getId()%>">Editar</a>
                    <a href="<%=application.getContextPath()%>/ControladorCliente?comando=eliminar&id=<%=cliente.getId()%>">Eliminar</a>
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

