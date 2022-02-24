<%-- 
    Document   : index
    Created on : 15-feb-2022, 12:45:35
    Author     : Sandra
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %> 
<!DOCTYPE html>
<html>
    <head>
        <title>Start Page</title>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="css/estilos.css">
        <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css" rel="stylesheet">
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
                            <a href="perfilAdmin.jsp">Mi perfil</a>
                        </nav>
                    </c:when>
                    <c:otherwise>
                        <nav>
                            <a href="index.jsp">INICIO</a>
                            <a href="busquedaFiltros.jsp">Busqueda avanzada</a>
                            <c:if test="${usuario != null}">
                            <a href="perfilUsuario.jsp">Mi perfil</a>
                            </c:if>
                        </nav>
                    </c:otherwise>
                    
                </c:choose>
        <main>  
            <a href="ControladorElementosP">Ver elementos</a>
            <jsp:include page="listadoElementos.jsp"></jsp:include>
            
            <a href="busquedaFiltros.jsp">Busqueda avanzada</a>
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
