<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <jsp:useBean id="cliente" class="ort.discom.obl.dominio.Cliente" scope="request" />
    </head>
    <body>
        <%@include file="../cabezal.jsp" %>
        <h2>Edici&oacute;n de Cliente</h2>
        <form action="<%=application.getContextPath()%>/ControladorCliente" method="POST">
            Nombre: <input type="text" name="nombre" value="<%=cliente.getNombre()%>"/><br/>
            Tel&eacute;fono: <input type="text" name="telefono" value="<%=cliente.getTelefono()%>"/><br/>
            Celular: <input type="text" name="celular" value="<%=cliente.getCelular()%>"/><br/>
            Email falta el agente: <input type="text" name="email" value="<%=cliente.getEmail()%>"/><br/>
            <input type="hidden" name="comando" value="salvar">
            <input type="submit" value="Salvar">

        </form>
        <a href="<%=application.getContextPath()%>/ControladorCliente?comando=listar">Cerrar</a>
    </body>
</html>
