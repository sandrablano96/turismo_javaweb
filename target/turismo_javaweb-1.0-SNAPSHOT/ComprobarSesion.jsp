<%-- 
    Document   : ComprobarSesion
    Created on : 15-feb-2022, 12:36:35
    Author     : Sandra
--%>
<%@page import="modelo.UsuarioSesion"%>
<%
UsuarioSesion us = (UsuarioSesion) session.getAttribute("usuario");
if(us == null)
{
    response.sendRedirect("ControladorElementosP");
}
%>