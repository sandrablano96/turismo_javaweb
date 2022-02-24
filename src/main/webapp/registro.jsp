<!DOCTYPE html>
<!--
To change this license header, choose License Headers in Project Properties.
To change this template file, choose Tools | Templates
and open the template in the editor.
-->
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %> 
<html>
    <head>
        <title>TODO supply a title</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="css/estilos.css">
    </head>
    <body>
        <header>
            <div class="contenedor-empresa">
                <h1>Turismo de Ciudad Real</h1>
                <p>Un día para emocionarte<p>
            </div>
            <c:choose>
                <c:when test="${sessionScope.email == null}">
                    <div class="contenedor-login">
                        <form action="ControladorUsuarios?accion=login" method="post" id="login">
                            Email:<input type="text" name="email" placeholder="Email" class="campo">
                            Contraseña: <input type="password" name="password" placeholder="Contraseña" class="campo">
                            <input type="submit" name="enviar" value="Login" class="botonFormulario">
                            <br><a href="registro.html">¿No tienes cuenta? Regístrate</a>
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
                <c:if test="${sessionScope.email != null}">
                    <a href="perfilUsuario.jsp">Mi perfil</a>
                </c:if>
            </nav>
        </c:otherwise>

    </c:choose>


    <main>
        <c:if test="${msg != null}">
            <p>${msg}</p>
        </c:if>
        <div id="registro">
            <div class="imagen">
                <img src="https://uklatinos.com/wp-content/uploads/2018/02/register.png" class="imagen">
            </div>
            
                <form action="ControladorUsuarios?accion=registrar" enctype="multipart/form-data" id="formularioRegistro" method="post">
                    <label for="nombre">Nombre:</label>
                    <input type="text" id="nombre" name="nombre" class="cuadro">
                    <label for="apellidos">Apellidos:</label>
                    <input type="text" id="apellidos" name="apellidos" class="cuadro">
                    <label for="email">Email:</label>
                    <input type="email" id="email" name="email" class="cuadro">
                    <label for="password">Contraseña:</label>
                    <input type="password" id="password" name="password" class="cuadro">
                    <label for="imagen">Imagen de perfil</label>
                    <input type="file" id="imagen" name="imagen" class="cuadro">
                    <input type="submit" value="Registrarme">
                </form>
            
        </div>
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