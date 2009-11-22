<%-- 
    Document   : ConsultaCredencial
    Created on : 21-nov-2009, 18:22:33
    Author     : Felipe
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>        
        <h2>Complete los datos solicitados para realizar la b&uacute;squeda</h2>
        <form action="<%=application.getContextPath()%>/Consultas?comando=busqueda" method="POST">
            Serie: <input type="text" name="serie" maxlength="3" align="middle" value=""/>
            N&uacute;mero: <input type="text" name="numero" maxlength="6" value=""/><br/>
            <input type="hidden" name="comando" value="busqueda">
            <input type="submit" value="busqueda">
        </form>        
    </body>
</html>
