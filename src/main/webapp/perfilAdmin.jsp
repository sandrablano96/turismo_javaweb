<%-- 
    Document   : perfilAdmin
    Created on : 14-feb-2022, 13:38:21
    Author     : Sandra
--%>

<%@page import="modelo.Usuario"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %> 
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="stylesheet" href="css/estilos.css">
    </head>
    <body>
        <header>
            <div class="contenedor-empresa">
                <h1>Turismo de Ciudad Real</h1>
                <p>Un día para emocionarte<p>
            </div>
            <c:choose>
                
                <c:when test="${usuario == null}">
                    <div class="contenedor-login">
                        <form action="ControladorUsuarios?accion=login" method="post" id="login">
                            Email:<input type="text" name="email" placeholder="Email" class="campo">
                            Contraseña: <input type="password" name="password" placeholder="Contraseña" class="campo">
                            <input type="submit" name="enviar" value="Login" class="botonFormulario">
                            <br><a href="registro.jsp">¿No tienes cuenta? Regístrate</a>
                        </form>
                    </div>
                </c:when>
                <c:otherwise>
                    <div id="usuario">
                        <div id="foto_usuario" style="background-image: url()"></div>
                        <p> ${usuario.nombre} </p>
                        <a href="ControladorUsuarios?accion=logout">Cerrar sesion</a>
                    </div>
                </c:otherwise>
            </c:choose>
        </header>
    <c:choose>
        <c:when test="${fn:containsIgnoreCase(usuario.rol, 'admin')}">
            <nav>
                <a href="index.jsp">INICIO</a>
                <a href="">INSERTAR REGISTRO</a>
                <a href="">MODIFICAR REGISTRO</a>
                <a href="">ELIMINAR REGISTRO</a>
            </nav>
        </c:when>
        <c:otherwise>
            <nav>
                <a href="index.jsp">INICIO</a>
                <a href="listadoElementosFiltros.jsp">Busqueda avanzada</a>
                <c:if test="${usuario != null}">
                    <a href="perfilUsuario.jsp">Mi perfil</a>
                </c:if>
            </nav>
        </c:otherwise>

    </c:choose>

        <main>
            <h2>Bienvenido ${usuario.nombre}</h2>
            <p>¿Que deseas hacer?</p>
            <form action="ControladorElementosP?Admin">
                <input type="button" name="accionAdmin" value="insertar">Altas
                <input type="button" name="accionAdmin" value="modificar">Actualizar
                <input type="button" name="accionAdmin" value="borrar">Borrar
                <select id="elementos">
                    <c:forEach var="elemento" items="${listadoElementos}">
                        <option value="${elemento.id}">${elemento.nombre}</option>
                    </c:forEach>
                </select>
                <input type="submit" value="Enviar">
            </form>
            
        </main>
        <footer>
            <div class="contenedor-footer">
                <div class="content-foo">
                    <h4 class="titulo-final">Telefono</h4>
                    <div id="hr"></div>
                    <p>98216496</p>
                </div>
                <div class="content-foo">
                    <h4 class="titulo-final">Email</h4>
                    <div id="hr"></div>
                    <p>98216496</p>
                </div>
                <div class="content-foo">
                    <h4 class="titulo-final">Ubicación</h4>
                    <div id="hr"></div>
                    <p>98216496</p>
                </div>
                <div class="copy">&copy; SBN Design | Sandra Blazquez
                </div>
            </div>
        </footer>
    </body>
</html>
