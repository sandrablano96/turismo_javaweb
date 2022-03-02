<%-- 
    Document   : elemento
    Created on : 16-feb-2022, 13:19:01
    Author     : Sandra
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %> 
<jsp:include page="ComprobarSesion.jsp"></jsp:include>
<!DOCTYPE html>
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
            <div class="elemento">
                <h2>${elemento.nombre}</h2>
                <div class="elemento__imagen" class="d-flex">
                    <c:choose>
                        <c:when test="${elemento.detalle.imagen == null || elemento.detalle.imagen == ''}">
                            <c:choose>
                                <c:when test="${elemento.tipo.id == 1}">
                                    <img src="img/monumento.png" style="width: 200px; height: 200px">
                                </c:when>
                                <c:when test="${elemento.tipo.id == 2}">
                                    <img src="img/naturaleza.png" style="width: 200px; height: 200px">
                                </c:when>
                                <c:otherwise>
                                    <img src="img/museo.png" style="width: 200px; height: 200px">
                                </c:otherwise>
                            </c:choose>
                        </c:when>
                        <c:otherwise>
                            <img src="imagenes/${elemento.detalle.imagen}" style="width: 600px; height: 500px">
                            <c:choose>
                                <c:when test="${elemento.detalle.imagen2 == null || elemento.detalle.imagen2 == '' }">
                                    
                                </c:when>
                                <c:otherwise>
                                    <img src="imagenes/${elemento.detalle.imagen2}" style="width: 600px; height: 500px;">
                                </c:otherwise>
                            </c:choose>
                        </c:otherwise>
                    </c:choose>
                </div>
                <div class="elemento__info">
                    <p>${detalle.descripcion}</p>
                    <p>${elemento.direccion}</p>
                    <p>${elemento.horario}</p>
                </div>

                <c:if test="${usuario != null}">

                    <c:choose>
                        <c:when test="${favorito == elemento.id}">
                            <div id="dislike" data-id="${elemento.id}"><i class="fas fa-star"></i>Guardado</div>
                        </c:when>  
                        <c:otherwise>
                            <div id="like" data-id="${elemento.id}"><i class="far fa-star"></i>Guardar</div>
                        </c:otherwise>
                    </c:choose>

                </c:if>
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
            $(document).on("click", "#like", function () {
                let id_elemento = $(this).attr("data-id");
                console.log(id_elemento);
                var params = {
                    accion: "like",
                    usuario: "${usuario.id}",
                    elemento: id_elemento
                };
                $.post("ControladorFavoritos", $.param(params), function (response) {
                    if (response == 1) {
                        $("#like").css("display", "none");
                        $("#dislike").html('<i class="fas fa-star"></i>Guardado');
                    } else {
                        console.log(error);
                    }
                });
            });
            $(document).on("click", "#dislike", function () {
                let id_elemento = $(this).attr("data-id");
                console.log(id_elemento);
                var params = {
                    accion: "dislike",
                    usuario: "${usuario.id}",
                    elemento: id_elemento
                };
                $.post("ControladorFavoritos", $.param(params), function (response) {
                    if (response == 1) {
                        $("#dislike").css("display", "none");
                        $("#like").html('<i class="far fa-star"></i>Guardar');
                    } else {
                        console.log(error);
                    }
                });
            });

        </script>	
    </body>
</html>
