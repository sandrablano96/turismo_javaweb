<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %> 
        <div class="listado_elementos">
            <c:forEach var="elemento" items="${listadoElementos}">
                <div class="elemento">
                    <div class="elemento__imagen">
                        <img src="img/${elemento.detalleList[0].imagen}.jpg">
                    </div>
                    <div class="elemento__info">
                        <a href="ControladorElementosP?accion=cargarElemento&id=${elemento.id}"<h4>${elemento.nombre}</h4></a>
                        <p>${elemento.detalleList[0].descripcion}</p>
                    </div>
                        <c:if test="${usuario != null}">
                            <c:forEach var="favorito" items="${usuario.favoritoList}">
                                <c:choose>
                                    <c:when test="${favorito.favoritoPK.idElemento == elemento.id}">
                                        <a href="ControladorFavoritos?accion=like"><i class="fas fa-star"></i>Guardado</a>
                                    </c:when>  
                                    <c:otherwise>
                                        <a href="ControladorFavoritos?accion=like"><i class="far fa-star"></i>Guardar</a>
                                    </c:otherwise>
                                </c:choose>
                            </c:forEach>
                        </c:if>
                </div>
            </c:forEach>
        </div>
<script>
    //ajax para los favoritos
</script>
