<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Editando Agente</title>
        <jsp:useBean id="agente" class="ort.discom.obl.entidades.Agente" scope="request" />
    </head>
    <body bgcolor="#c0c0c0">
        <%@include file="../cabezal.jsp" %>
        <h2>Edici&oacute;n de Agente</h2>
        <form action="<%=application.getContextPath()%>/ControladorAgente" method="POST">
            Nombre: <input type="text" name="nombre" value='<%=agente.getNombre() != null ? agente.getNombre() : ""%>'/><br/>
            Apellido: <input type="text" name="apellido" value='<%=agente.getApellido() != null ? agente.getApellido() : ""%>'/><br/>
            e-Mail <input type="text" name="email" value='<%=agente.getEmail() != null ? agente.getEmail() : ""%>'/><br/>
            Login: <input type="text" name="login" value='<%=agente.getLogin() != null ? agente.getLogin() : ""%>'/><br/>
            Password: <input type="password" name="password" value='<%=agente.getPassword() != null ? agente.getPassword() : ""%>'/><br/><br/>
            
            <select name="menu" size="5" >
                <option value="1">maple</option>
                <option value="2">hickory</option>
                <option value="3">birch</option>
            </select>
            
            <br/><br/><br/>

            <input type="submit" value="Salvar" name="comando">
            <input type="submit" value="Cancelar" name="comando">
        </form>
        <%@include file="../pie.html" %>
    </body>
</html>