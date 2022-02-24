<%-- 
    Document   : listadoFavoritos
    Created on : 15-feb-2022, 13:00:32
    Author     : Sandra
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<div class="mis_favoritos">
    <c:forEach var="favorito" items="${listadoFavoritos}">
        <div class="elemento">
            <div class="elemento__imagen">
                <img src="img/${favorito.imagen}.jpg">
            </div>
            <div class="elemento__info">
                <h4>${favorito.nombre}</h4>
                <p>${favorito.descripcion}</p>
                <p>${favorito.comentarios}</p>
            </div>
        </div>
    </c:forEach>
</div>

