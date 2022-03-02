<%-- 
    Document   : ComprobarSesion
    Created on : 15-feb-2022, 12:36:35
    Author     : Sandra
--%>
<%
String email=(String)session.getAttribute("email");
if(email==null)
{
response.sendRedirect("index.html");
}
%>