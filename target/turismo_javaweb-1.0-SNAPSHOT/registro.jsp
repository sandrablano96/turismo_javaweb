<!DOCTYPE html>
<!--
To change this license header, choose License Headers in Project Properties.
To change this template file, choose Tools | Templates
and open the template in the editor.
-->
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %> 
<! DOCTYPE html>
<html>
    <head>
        <title>Start Page</title>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
        <link rel="stylesheet" href="css/estilos.css">
        <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css" rel="stylesheet">

    </head>
    <body class="container-fluid">
        <div class="row pb-5">
        <header class="container-fluid w-100 d-flex pt-5">
            <div class="contenedor-empresa col-4  d-flex justify-content-center flex-column ps-5">
                <h1>Turismo de Ciudad Real</h1>
                <p>Un día para emocionarte<p>
            </div>
            <div class="col-7 d-flex justify-content-end">
            <c:choose>
                <c:when test="${usuario == null}">
                    <div class="contenedor-login d-flex flex-column justify-content-center pb-5">
                            <form action="ControladorUsuarios?accion=login" method="post" id="login" class="p-1">
                                <label class="form-label">Email:</label><input type="text" name="email" placeholder="Email" class="campo"  class="form-control">
                                <label class="form-label">Contraseña: </label><input type="password" name="password" placeholder="Contraseña" class="campo"  class="form-control">
                                <input type="submit" name="enviar" value="Login" class="btn botonFormulario">
                            </form>
                        <a href="registro.jsp" class="p-1">¿No tienes cuenta? <span class="registrarse">Regístrate</span></a>
                    </div>
                </c:when>
                <c:otherwise>
                    <div id="usuario" class="pb-5">
                        <div id="foto_usuario" style="background-image: url(imagenes/${usuario.imagen})"></div>
                        <p> ${usuario.nombre} </p>
                        <a href="ControladorUsuarios?accion=logout">Cerrar sesion</a>
                    </div>
                </c:otherwise>
            </c:choose>
            </div>
        </header>
                <c:choose>
                    <c:when test="${fn:containsIgnoreCase(usuario.rol, 'admin')}">
                        <nav>
                            <a href="ControladorElementosP">INICIO</a>
                            <a href="ControladorAdmin?accion=gestion">GESTION DE REGISTROS</a>
                        </nav>
                    </c:when>
                    <c:otherwise>
                        <nav>
                            <a href="ControladorElementosP">INICIO</a>
                            <a href="busquedaFiltros.jsp">Busqueda avanzada</a>
                            <c:if test="${usuario != null}">
                            <a href="ControladorUsuarios?accion=mi_perfil">Mi perfil</a>
                            </c:if>
                        </nav>
                    </c:otherwise>
                    
                </c:choose>
            </div>
    <main class="row w-75 m-auto mb-5">
        
        
        <div id="registro">
            <div class="aviso">
            <p>
                <c:if test="${msg != null}">
                    ${msg}
                </c:if>
            </p>
            </div>
            <div class="imagen">
                <img src="img/registro.png" class="imagen">
            </div>
                <form action="ControladorUsuarios?accion=registrar" enctype="multipart/form-data" id="formularioRegistro" method="post">
                    <label for="nombre" class="form-labelm">Nombre:</label>
                    <input type="text" id="nombre" name="nombre" class="cuadro">
                    <label for="apellidos" class="form-label">Apellidos:</label>
                    <input type="text" id="apellidos" name="apellidos" class="cuadro">
                    <label for="email" class="form-label">Email:</label>
                    <input type="email" id="email" name="email" class="cuadro">
                    <label for="password" class="form-label">Contraseña:</label>
                    <input type="password" id="password" name="password" class="cuadro">
                    <label for="confirmacionPass" class="form-label">Confirma la contraseña:</label>
                    <input type="password" id="confirmacionPass" name="ConfirmacionPass" class="cuadro">
                    <label for="imagen" class="form-label">Imagen de perfil</label>
                    <input type="file" id="imagen" name="imagen" class="cuadro form-control">
                    <div class="text-center">
                        <input type="submit" class="btn btn-lg botonFormulario" value="Registrarme">
                    </div>
                    
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
        <script src="https://code.jquery.com/jquery-3.6.0.min.js" integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4=" crossorigin="anonymous"></script>
        <script>
    $(document).ready(function(){
        $("#formularioRegistro").on("submit", function(evt){
                
                let nombre = $("#nombre").val();
                let apellidos = $("#apellidos").val();
                let email = $("#email").val();
                let password = $("#password").val();
                let pass = $("#confirmacionPass").val();
                let msg = "";
                let valido = true;
                if( nombre == "" || apellidos == "" || email == "" || password == "" ){
                    evt.preventDefault();
                    msg = "Los campos nombre, apellidos, email y password no pueden quedar vacíos";
                    valido= false;
                    $(".aviso p").html(msg);
                    $('html, body').animate({
                        scrollTop: $(".aviso").offset().top
                    }, 700);
                    return;
                }
                if(password != pass){
                    evt.preventDefault();
                    msg= "Las contraseñas no coinciden";
                    valido = false;
                    $(".aviso p").html(msg);
                    $('html, body').animate({
                        scrollTop: $(".aviso").offset().top
                    }, 700);
                }              
        });
    })        
            
        </script>
</body>
</html>