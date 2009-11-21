<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Sistema de Encuestas</title>
    </head>
    <body>
<%@include file="cabezal.jsp" %>
        <h2>Administrador</h2>
            <a href="adm/mantenedorAgentes.jsp">Mantenimiento de Agentes</a><br/>
            <a href="adm/mantenedorClientes.jsp">Mantenimiento de Clientes</a><br/>
            <a href="adm/consultarEncuestas_adm.jsp">Consultas de Encuestas</a><br >
        <br/>

        <h2>Agente</h2>
            <a href="age/mantenedorEncuestas.jsp">Mantenimiento de Encuestas</a><br/>
            <a href="age/consultarEncuestas_age.jsp">Consultas de Encuestas</a><br/>
        <br/>

        <h2>Cliente</h2>
            <a href="/cli/consultarEncuestas_cli.jsp">Consultas de Encuestas</a><br/>
            <a href="/cli/consultarResultados_cli.jsp">Consultas de Resultados</a><br/>
        <br/>

    </body>

   
</html>
