<%-- 
    Document   : mantenedorPreguntas
    Created on : 05-dic-2009, 16:50:32
    Author     : Felipe
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.ArrayList" %>
<%@page import="java.util.List" %>
<%@page import="ort.discom.obl.entidades.Pregunta" %>
<%@page import="ort.discom.obl.entidades.Respuesta" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Mantenimiento de preguntas - respuestas</title>
    </head>
    <body bgcolor="#c0c0c0">
        <%@include file="../cabezal.jsp" %>
        <h2>Preguntas</h2>
        <a href="<%=application.getContextPath()%>/ControladorPregunta?comando=listar">Lista de Preguntas</a>&nbsp;
        <a href="<%=application.getContextPath()%>/ControladorPregunta?comando=nuevo">Alta de Pregunta</a>
        <%
            //ArrayList agentes = (ArrayList) request.getAttribute("resultado");
            List preguntas = (List) request.getAttribute("resultado");
            if (preguntas != null) {
        %>
        <br/><br/>
        <table border="1">
            <caption>Preguntas</caption>
            <tr>

                <th>Id</th>
                <th>Nombre</th>

                <th>Acci&oacute;n</th>
            </tr>
            <%
                    //for (Usuario usuario : usuarios) {
                    for (int i=0; i<preguntas.size(); i++) {
                         Pregunta pregunta = (Pregunta)preguntas.get(i);
            %>
            <tr>

                <td><%= pregunta.getId()%></td>
                <td><%= pregunta.getPlanteo()%></td>

                <td>
                    <a href="<%=application.getContextPath()%>/ControladorPregunta?comando=editar&login=<%=pregunta.getId()%>">Editar</a>
                    <a href="<%=application.getContextPath()%>/ControladorPregunta?comando=eliminar&login=<%=pregunta.getId()%>">Eliminar</a>
                </td>
            </tr>
            <%
                } // Cierro el for
%>
        </table>
        <%
            } // Cierro el if
%>

        <h2>Respuestas</h2>
        <a href="<%=application.getContextPath()%>/ControladorRespuesta?comando=listar">Lista de Respuestas</a>&nbsp;
        <a href="<%=application.getContextPath()%>/ControladorRespuesta?comando=nuevo">Alta de Respuesta</a>
        <%
            //ArrayList agentes = (ArrayList) request.getAttribute("resultado");
            List respuestas = (List) request.getAttribute("resultado");
            if (respuestas != null) {
        %>
        <br/><br/>
        <table border="1">
            <caption>Preguntas</caption>
            <tr>

                <th>Id</th>
                <th>Nombre</th>

                <th>Acci&oacute;n</th>
            </tr>
            <%
                    //for (Usuario usuario : usuarios) {
                    for (int i=0; i<preguntas.size(); i++) {
                         Respuesta respuesta = (Respuesta)respuestas.get(i);
            %>
            <tr>

                <td><%= respuesta.getId()%></td>
                <td><%= respuesta.getRespuesta()%></td>

                <td>
                    <a href="<%=application.getContextPath()%>/ControladorRespuesta?comando=editar&login=<%=respuesta.getId()%>">Editar</a>
                    <a href="<%=application.getContextPath()%>/ControladorRespuesta?comando=eliminar&login=<%=respuesta.getId()%>">Eliminar</a>
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