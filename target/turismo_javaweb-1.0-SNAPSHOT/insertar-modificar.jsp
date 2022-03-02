 <%-- 
    Document   : insertar
    Created on : 15-feb-2022, 11:59:47
    Author     : Sandra
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %> 
<jsp:include page="ComprobarSesion.jsp"></jsp:include>
<jsp:include page="ComprobarAdmin.jsp"></jsp:include>
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
            <div class="contenedor-empresa col-5  d-flex justify-content-center flex-column ps-5">
                <h1>Turismo de Ciudad Real</h1>
                <p>Un día para emocionarte<p>
            </div>
            <div class="col-6 d-flex justify-content-end">
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
            <div class="col-12 alerta">
                <p>${msg}</p>
            </div>
            <div class="col-12">
                <h2 class="mt-5"> Introduzca los datos </h2>
                <form action="ControladorAdmin?accion=${accion}" method="post" enctype="multipart/form-data" id="formularioElementos"> 
                    <input type="hidden" name="elemento" <c:if test="${elementoP != null}">value="${elementoP.id}"</c:if>>
                    <label for="nombre" class="form-label">Nombre:</label>
                    <input type="text" id="nombre" name="nombre" class="form-control mb-2" <c:if test="${elementoP != null}">value="${elementoP.nombre}"</c:if> required>
                    <label for="direccion" class="form-label">Direccion:</label>
                    <input type="text" id="direccion" name="direccion" class="form-control mb-2" <c:if test="${elementoP != null}">value="${elementoP.direccion}"</c:if> required>
                    <label for="localidad" class="form-label">Localidad:</label>
                    <input type="text" id="localidad" name="localidad" class="form-control mb-2" <c:if test="${elementoP != null}">value="${elementoP.localidad}"</c:if> required>
                    <label for="tipo" class="form-label">Tipo:</label>
                    <select id="tipo" name="tipo" required class="form-select form-select-lg mb-2">
                        <c:forEach var="tipo" items="${tipos}">
                                <c:if test="${elementoP != null &&elementoP.tipo.id == tipo.id}">
                                    <option value="${tipo.id}" selected="">${tipo.nombre}</option>
                                </c:if>
                                <option value="${tipo.id}">${tipo.nombre}</option>
                        </c:forEach>

                    </select>
                    <label for="descripcion" class="form-label">Descripcion:</label>
                    <textarea id="descripcion" name="descripcion" required class="form-control mb-2"><c:if test="${elementoP != null}">${elementoP.detalle.descripcion}</c:if></textarea>
                    <label for="horario" class="form-label">Horario</label>
                    <textarea type="text" id="horario" name="horario" class="form-control mb-2"><c:if test="${elementoP != null}">${elementoP.horario}</c:if></textarea>
                    <input type="file" name="imagen" class="form-control mb-2" <c:if test="${elementoP != null}">value="${elementoP.detalle.imagen}"</c:if>>
                    <input type="file" name="imagen2" class="form-control mb-2" <c:if test="${elementoP != null}">value="${elementoP.detalle.imagen2}"</c:if>>
                    <div class="col-12 text-center">
                        <button type="submit" class="btn btn-lg boton">Enviar</button>
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
            $("#formularioElementos").on("submit", function(evt){
                console.log(evt)
                    
                    let nombre = $("#nombre").val();
                    let direccion = $("#direccion").val();
                    let localidad = $("#localidad").val();
                    let descripcion = $("#descripcion").val();
                    let msg = "";
                    if( nombre == "" || direccion == "" || descripcion == "" || localidad == "" ){
                        evt.preventDefault();
                        msg = "Los campos nombre, direccion, descripcion y localidad no pueden quedar vacíos";
                        $(".aviso p").html(msg);
                        $('html, body').animate({
                            scrollTop: $(".aviso").offset().top
                        }, 700);
                    }
            });
        });
        
        </script>
    </body>
</html>
