<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.text.SimpleDateFormat" %>
<%@page import="java.text.DateFormat" %>
<%@page import="java.util.ArrayList" %>
<%@page import="java.util.List" %>
<%@page import="ort.discom.obl.entidades.Agente" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Editando Agente</title>
        <jsp:useBean id="cliente" class="ort.discom.obl.entidades.Cliente" scope="request" />
    </head>
    <body bgcolor="#c0c0c0">
        <%@include file="../cabezal.jsp" %>
        <h2>Edici&oacute;n de Cliente</h2>
        <form action="<%=application.getContextPath()%>/ControladorCliente" method="POST">
            <%
                DateFormat myDateFormat = new SimpleDateFormat("DD/MM/yyyy");
            %>
            <input type="hidden" name="id" value='<%=cliente.getId() != 0 ? cliente.getId() : ""%>'/><br/>
            Nombre: <input type="text" name="nombre" value='<%=cliente.getNombre() != null ? cliente.getNombre() : ""%>'/><br/>
            Apellido: <input type="text" name="apellido" value='<%=cliente.getApellido() != null ? cliente.getApellido() : ""%>'/><br/>
            Teléfono: <input type="text" name="telefono" value='<%=cliente.getTelefono() != null ? cliente.getTelefono() : ""%>'/><br/>
            Celular <input type="text" name="celular" value='<%=cliente.getCelular() != null ? cliente.getCelular() : ""%>'/><br/>
            e-Mail: <input type="text" name="email" value='<%=cliente.getEmail() != null ? cliente.getEmail() : ""%>'/><br/>
            Fecha de ingreso: <input type="date" name="fecha_ingreso" value='<%=cliente.getFecha_ing() != null ? myDateFormat.parse(cliente.getFecha_ing().toString()) : ""%>'/><br/>
                        
            Agente:
            <select name="agente_login">
                <%
                    List agentes = (List) request.getAttribute("resultadoAgentes");
                    if (agentes != null) {
                        for (int i=0; i<agentes.size(); i++) {
                            Agente agente = (Agente)agentes.get(i);
                %>                
                <option value='<%= agente.getLogin()%>'><%= agente.getNombre() + ' ' + agente.getApellido() %>
                <%
                        } // Cierro el for
                    } // Cierro el if
                %>
                <option selected value='<%=cliente.getElAgente() != null ? cliente.getElAgente().getLogin() : ""%>'
            </select>

            <br/>            
            <input type="submit" value="Salvar" name="comando"><br/>
            <input type="submit" value="Cancelar" name="comando">
        </form>
              
        <%@include file="../pie.html" %>
    </body>
</html>