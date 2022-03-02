
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %> 
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
                    <div id="usuario">
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
            <div class="alerta row mt-5">
                <p>${msg}</p>
            </div>
            <h2 class="mt-5">Búsqueda avanzada</h2>
            <div class="col-12">
                <jsp:include page="listadoElementos.jsp"></jsp:include>
            </div>
            <div class="col-8 m-auto">
                <form action="ControladorElementosP?accion=buscarFiltros" id="formFiltros" method="post">
                    <label for="tipo" class="form-label text-center mb-2">Filtrar por:</label>
                    <select id="tipo" name="tipo" class="form-select mb-2">
                        <option selected value="">Busqueda por tipo</option>
                        <option value="monumento">Monumentos</option>
                        <option value="espacio_natural">Espacios naturales</option>
                        <option value="museo">Museos</option>
                    </select>
                    <label for="nombre" class="form-label">Nombre: </label>
                    <input type="text" name="nombre" id="nombre" class="form-control">
                    <div class="text-center">
                        <button type="submit" id="buscar" class="btn btn-lg boton col-4">Buscar</button>
                        <button type="reset" class="btn btn-lg boton col-4">Borrar</button>
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
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
        <script>
                
                document.getElementById("formFiltros").addEventListener("submit", function(event){
                    event.preventDefault();
                    let nombre = document.getElementById("nombre").value;
                    let tipo = document.getElementById("tipo").value;
                    if(tipo == "" && nombre == ""){   
                        document.getElementById("alerta").innerHTML = "No pueden ser nulos los dos campos";
                    }else if(nombre.length > 0 || tipo.length){
                        document.getElementById("formFiltros").submit();
                    }
            });
            
            
            
            
            
            
            
        </script>
    </body>
</html>
