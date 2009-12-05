<%@page contentType="text/html" pageEncoding="UTF-8"%>
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
        <form action="<%=application.getContextPath()%>/ControladorCliente?comando=salvar" method="POST">
            Nombre: <input type="text" name="nombre" value='<%=cliente.getNombre() != null ? cliente.getNombre() : ""%>'/><br/>
            Apellido: <input type="text" name="apellido" value='<%=cliente.getApellido() != null ? cliente.getApellido() : ""%>'/><br/>
            Teléfono: <input type="text" name="telefono" value='<%=cliente.getTelefono() != null ? cliente.getTelefono() : ""%>'/><br/>
            Celular <input type="text" name="celular" value='<%=cliente.getCelular() != null ? cliente.getCelular() : ""%>'/><br/>
            e-Mail: <input type="text" name="email" value='<%=cliente.getEmail() != null ? cliente.getEmail() : ""%>'/><br/>
            Fecha de ingreso: <input type="date" name="fechaIngreso" value='<%=cliente.getFecha_ing() != null ? cliente.getFecha_ing() : ""%>'/><br/>
            <input type="submit" value='<%=request.getAttribute("comando")%>' name="comando">
            <input type="submit" value="Cancelar" name="comando">
        </form>
        <%@include file="../pie.html" %>
    </body>
</html>