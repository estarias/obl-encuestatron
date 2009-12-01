<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Inicio</title>
    </head>
    <body bgcolor="#c0c0c0">
        <%@include file="cabezal.jsp" %>
        <h2>Bienvenido a nuestro sitio Web</h2>
        <a href="<%=application.getContextPath()%>/controladorABMUsuarios?comando=Buscar">ABM de Usuarios</a>
        <%@include file="pie.html" %>
    </body>
</html>
