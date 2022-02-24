<%-- 
    Document   : busquedaFiltros
    Created on : 21-feb-2022, 13:10:26
    Author     : Sandra
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %> 
<!DOCTYPE html>
<html>
    <head>
        <title>TODO supply a title</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="css/estilos.css">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css
              ">
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
        <div class="contenedor">
            <div class="filtros">
                <p id="alerta">
                </p>
                <form action="ControladorElementosP?accion=buscarFiltros" id="formFiltros">
                    <input type="hidden" name="accion" value="buscarFiltros">
                    <label for="tipo">Filtrar por:</label>
                    <select id="tipo" name="tipo">
                        <option selected value="">Busqueda por tipo</option>
                        <option value="monumento">Monumentos</option>
                        <option value="espacio_natural">Espacios naturales</option>
                        <option value="museo">Museos</option>
                    </select>
                    <label for="nombre">Nombre: </label>
                    <input type="text" name="nombre" id="nombre">
                    <button type="submit" id="buscar">Buscar</button>
                </form>
            </div>
            <jsp:include page="listadoElementos.jsp"></jsp:include>
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
