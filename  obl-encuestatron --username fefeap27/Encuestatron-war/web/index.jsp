<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Sistema de Encuestas</title>
    </head>   
        <%@include file="cabezal.jsp" %>

        <h2>Administrador</h2>
            <a href="administradores/mantenedorAgentes.jsp">Mantenimiento de Agentes</a><br/>
            <a href="administradores/mantenedorClientes.jsp">Mantenimiento de Clientes</a><br/>
            <a href="administradores/consultarEncuestas_adm.jsp">Consultas de Encuestas</a><br >
        <br/>

        <h2>Agente</h2>
            <a href="agentes/mantenedorEncuestas.jsp">Mantenimiento de Encuestas</a><br/>
            <a href="agentes/consultarEncuestas_age.jsp">Consultas de Encuestas</a><br/>
        <br/>

        <h2>Cliente</h2>
            <a href="clientes/consultarEncuestas_cli.jsp">Consultas de Encuestas</a><br/>
            <a href="clientes/consultarResultados_cli.jsp">Consultas de Resultados</a><br/>
        <br/>

        <h2>Bienvenido a nuestro sitio Web</h2>
        <a href="<%=application.getContextPath()%>/ControladorABMUsuarios?comando=Buscar">ABM de Usuarios</a>

        <%@include file="pie.html" %>
    </body>
</html>