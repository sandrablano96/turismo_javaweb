<%-- 
    Document   : listadoFavoritos
    Created on : 15-feb-2022, 13:00:32
    Author     : Sandra
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:include page="ComprobarSesion.jsp"></jsp:include>
    <div class="mis_favoritos">
        
    <c:forEach var="favorito" items="${listadoFavoritos}">
        <div class="elemento border-bottom">
            <div class="informacion mb-1">
                <div class="elemento__imagen">
                   <c:choose>
                        <c:when test="${favorito.elementoPatrimonial.detalle.imagen == null || favorito.elementoPatrimonial.detalle.imagen == ''}">
                            <c:choose>
                                <c:when test="${favorito.elementoPatrimonial.tipo.id == 1}">
                                    <img src="img/monumento.png" style="width: 200px; height: 200px">
                                </c:when>
                                <c:when test="${favorito.elementoPatrimonial.tipo.id == 2}">
                                    <img src="img/naturaleza.png" style="width: 200px; height: 200px">
                                </c:when>
                                <c:otherwise>
                                    <img src="img/museo.png" style="width: 200px; height: 200px">
                                </c:otherwise>
                            </c:choose>
                        </c:when>
                        <c:otherwise>
                            <img src="imagenes/${favorito.elementoPatrimonial.detalle.imagen}" style="width: 600px; height: 500px">
                        </c:otherwise>
                    </c:choose>
                </div>
                <div class="elemento__info pt-3">
                    <a href="ControladorElementosP?accion=cargarElemento&id=${favorito.elementoPatrimonial.id}"><h3>${favorito.elementoPatrimonial.nombre}</h3></a>
                    <p>${favorito.elementoPatrimonial.detalle.descripcion}</p>

                </div>
            </div>
            <div class="comentarios">
                <b>${favorito.usuario.nombre}</b> comenta:
                <div class="comentario mb-2">
                    <div class="w-100 d-flex justify-content-between">
                        
                        <c:if test="${favorito.comentarios == '' || favorito.comentarios == null}">
                            <p class="w-75">Todavía no has comentado nada</p>
                        </c:if>
                        <p class="w-75">${favorito.comentarios}</p><button id="borrarComentario" class="btn btn-small" data-id="${favorito.favoritoPK.idUsuario}&${favorito.favoritoPK.idElemento}"><i class="fas fa-eraser"></i></button>
                    </div>
                    
                </div>
                <div class="nuevosComentarios w-100">
                    <textarea id="comentar" class="form-control">
                      
                    </textarea>
                    <button id="enviar" class="btn btn-small boton" data-id="${favorito.favoritoPK.idUsuario}&${favorito.favoritoPK.idElemento}">Comentar</button>
                </div>



            </div>


        </div>
    </c:forEach>
    <script src="https://code.jquery.com/jquery-3.6.0.min.js" integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4=" crossorigin="anonymous"></script>
    <script>
        $(document).on("click", "#enviar", comentar_descomentar);

        $(document).on("click", "#borrarComentario", comentar_descomentar);

        function comentar_descomentar(evt) {
            let id_compuesto = $(this).attr("data-id");
            let datos = id_compuesto.split("&");
            let target = $(this).attr("id");
            if (target == "borrarComentario") {
                var params = {
                    accion: "borrarComentario",
                    usuario: datos[0],
                    elemento: datos[1],
                    comentario: ""
                }
            } else {
                var params = {
                    accion: "comentar",
                    usuario: datos[0],
                    elemento: datos[1],
                    comentario: $("#comentar").val().trim()
                }
            }

            $.ajax({
                url: "ControladorFavoritos",
                method: "POST",
                data: jQuery.param(params),
                dataType: 'text',
                success: function (response) {
                    if (response == "correcto") {
                        $(".comentario p").html(params.comentario);

                    } else {
                        console.log("error");
                    }
                },
                error: function (status) {
                    console.log(status);
                },

            });
        }
    </script>
</div>

