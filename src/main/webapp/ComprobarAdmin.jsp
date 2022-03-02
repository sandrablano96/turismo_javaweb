<%-- 
    Document   : ComprobarAdmin
    Created on : 27-feb-2022, 19:30:44
    Author     : Sandra
--%>

<%@page import="modelo.UsuarioSesion"%>
<%
UsuarioSesion us = (UsuarioSesion) session.getAttribute("usuario");
if(us == null || !us.getRol().equalsIgnoreCase("admin") )
{
    response.sendRedirect("ControladorElementosP");
}
%>
