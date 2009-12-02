<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Editando Agente</title>
        <jsp:useBean id="usuario" class="ort.discom.obl.entidades.Usuario" scope="request" />
    </head>
    <body bgcolor="#c0c0c0">
        <%@include file="../cabezal.jsp" %>
        <h2>Edici&oacute;n de Cliente</h2>
        <form action="<%=application.getContextPath()%>/controladorABMUsuarios" method="POST">
            Nombre: <input type="text" name="nombre" value='<%=usuario.getNombre() != null ? usuario.getNombre() : ""%>'/><br/>
            Apellido: <input type="text" name="apellido" value='<%=usuario.getApellido() != null ? usuario.getApellido() : ""%>'/><br/>
            Login: <input type="text" name="login" value='<%=usuario.getLogin() != null ? usuario.getLogin() : ""%>'/><br/>
            Password: <input type="password" name="password" value='<%=usuario.getPassword() != null ? usuario.getPassword() : ""%>'/><br/><br/>
            <input type="submit" value='<%=request.getAttribute("comando")%>' name="comando">
            <input type="submit" value="Cancelar" name="comando">
        </form>
        <%@include file="../pie.html" %>
    </body>
</html>