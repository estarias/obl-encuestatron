<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Mega sitio</title>
    </head>
    <body><table border="0" cellpadding="5" width="100%" bgcolor="#54FF4A">
            <tbody>
                <tr>
                    <td align="left">OBLIGATORIO 2009- Felipe Aguirregaray, Esteban Arias</td>
                    <td align="right">
                        <%
                        if (request.getRemoteUser() != null) {
                        %>
                            <a href="<%=application.getContextPath()%>/ControladorLogout">Cerrar sesi&oacute;n</a>
                        <%
                        }
                        %>
                    </td>
                </tr>
            </tbody>
        </table>
        <% if (request.getAttribute("mensaje") != null) {%>
        <font color="#FF1111"><%=request.getAttribute("mensaje")%></font>
        <%
                request.removeAttribute("mensaje");
            }
        %>
    </body>
</html>
