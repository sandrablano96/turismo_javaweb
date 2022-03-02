<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %> 

<div class="listado_elementos row">
    <div class="col-12 d-flex justify-content-end">
        <form action="ControladorElementosP?accion=mostrarOrdenados" method="post" id="formularioOrdenar" class="form-inline">
            <div class="form-group">
                <label for="ordenar" class="form-label">Ordenar por nombre:</label>
                <select id="ordenar" name="ordenar" class="form-select"> 
                    <option value="asc" <c:if test="${select == 'asc'}">selected</c:if>>Ascendente</option>
                    <option value="desc"<c:if test="${select == 'desc'}">selected</c:if>>Descendente</option>
                    </select>
                </div>
            </form>
        </div>
        <div class="col-12">   
        <c:forEach var="elemento" items="${listadoElementos}" varStatus="loop.index">
            <div class="elemento">
                <div class="elemento__imagen mb-2">
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
                        </c:otherwise>
                    </c:choose>
                    
                </div>
                <div class="elemento__info pt-3">
                    <div class="d-flex">
                        <a href="ControladorElementosP?accion=cargarElemento&id=${elemento.id}"><h3 class="mb-2" >${elemento.nombre}</h3></a>
                            <c:if test="${usuario != null && !fn:containsIgnoreCase(usuario.rol, 'admin')}">
                                <c:set var="encontrado" value="false"></c:set>
                                <c:choose>
                                    <c:when test="${fn:contains(favoritos, elemento.id) && encontrado == false}">
                                    <div><i class="fas fa-star fav" data-id="${elemento.id}" id="like"></i></div>
                                        <c:set var="encontrado" value="true"></c:set>
                                    </c:when>
                                    <c:otherwise>
                                    <div><i class="far fa-star fav" data-id="${elemento.id}" id="like"></i></div>
                                    </c:otherwise>    
                                </c:choose>
                            </c:if>
                    </div>

                    <p>${elemento.detalle.descripcion}</p>
                </div>

            </div>
        </c:forEach>
    </div> 
</div>
<script src="https://code.jquery.com/jquery-3.6.0.min.js" integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4=" crossorigin="anonymous"></script>
<script>
    $(document).on("click", "#like", function (evt) {
        let id_elemento = $(this).attr("data-id");
        let elemento = evt.target;
        let capa = $(this).parent('div');
        if ($(this).hasClass("far")) {
            var params = {
                accion: "like",
                usuario: "${usuario.id}",
                elemento: id_elemento
            }
        } else {
            var params = {
                accion: "dislike",
                usuario: "${usuario.id}",
                elemento: id_elemento
            }
        }
        console.log(params);
        console.log(elemento);
        $.ajax({
            url: "ControladorFavoritos",
            method: "POST",
            data: jQuery.param(params),
            dataType: 'text',
            success: function (response) {
                if (response == "correcto") {
                    if (params.accion == "like") {
                        $(elemento).removeClass("far");
                        $(elemento).addClass("fas");
                    } else {
                        $(elemento).removeClass("fas");
                        $(elemento).addClass("far");
                    }
                } else {
                    $(".alertas p").html("Ha habido un error, comuníquelo al administrado");
                }

            },
            error: function (status) {
                $(".alerta p").html("Ha habido un error, comuníquelo al administrado");
                console.log(status);
            },

        });


    });
    $("#ordenar").on("change", function () {
        console.log("holi");
        $("#formularioOrdenar").submit();
    });
</script>	

