<%-- 
    Document   : editarEncuesta
    Created on : 05-dic-2009, 20:15:36
    Author     : Felipe
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Editando Encuesta</title>
        <jsp:useBean id="encuesta" class="ort.discom.obl.entidades.Encuesta" scope="request" />
    </head>
    <body bgcolor="#c0c0c0">
        <%@include file="../cabezal.jsp" %>
        <h2>Edici&oacute;n de Encuesta</h2>
        <form action="<%=application.getContextPath()%>/ControladorEncuesta" method="POST">
            
            <input type="hidden" name="id" value='<%=encuesta.getId() != null ? encuesta.getId() : ""%>'/><br/>
            Nombre: <input type="text" name="nombre" value='<%=encuesta.getNombre() != null ? encuesta.getNombre() : ""%>'/><br/>
            Clave: <input type="text" name="clave" value='<%=encuesta.getClave() != null ? encuesta.getClave() : ""%>'/><br/>
            Cliente: <input type="text" name="cliente" value='<%=encuesta.getCliente() != null ? encuesta.getCliente() : ""%>'/><br/>
            Agente: <input type="text" name="agente" value='<%=encuesta.getAgente() != null ? encuesta.getAgente() : ""%>'/><br/>
            Fecha de ingreso: <input type="date" name="fecha_ingreso" value='<%=encuesta.getFecha_ingreso() != null ? encuesta.getFecha_ingreso() : ""%>'/><br/>
            Fecha de modificación: <input type="date" name="fecha_modificacion" value='<%=encuesta.getFecha_modificacion() != null ? encuesta.getFecha_modificacion() : ""%>'/><br/>
            Fecha de comienzo: <input type="date" name="fecha_comienzo" value='<%=encuesta.getFecha_comienzo() != null ? encuesta.getFecha_comienzo() : ""%>'/><br/>
            Fecha de cierre: <input type="date" name="fecha_cierre" value='<%=encuesta.getFecha_cierre() != null ? encuesta.getFecha_cierre() : ""%>'/><br/>
            
            <input type="submit" value="Salvar" name="comando">
            <input type="submit" value="Cancelar" name="comando">
        </form>
        <%@include file="../pie.html" %>
    </body>
</html>
