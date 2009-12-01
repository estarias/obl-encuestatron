<%@page contentType="text/html" pageEncoding="UTF-8"%>

<table align="center" border="0" cellpadding="0" cellspacing="0" width="1240" bgcolor="#8C0202">
    <tbody>
        <tr>
            <td colspan="2" align="left">
                <img src="img/logoort.png" alt="cabezal" border="0"/>
            </td>
        </tr>
        <tr bgcolor="#B0B4B7">
            <td align="left">
                <% if (request.getAttribute("mensaje") != null) {%>
                      <font color="#FF1111"><%=request.getAttribute("mensaje")%></font>
                <%
                      request.removeAttribute("mensaje");
                   }
                %>
            </td>
            <td align="right">
                <%
                    if (request.getRemoteUser() != null) {
                %>
                <b>
                    <a href="<%=application.getContextPath()%>/logout">Cerrar sesi&oacute;n</a>
                </b>
                <%
                    }
                %>
            </td>
        </tr>
    </tbody>
</table>
<table align="center" border="0" cellpadding="0" cellspacing="0" width="1240" bgcolor="#F5F5F5">
    <tbody>
        <tr>
            <td>
