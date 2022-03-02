<%-- 
    Document   : gestion.jsp
    Created on : 26-feb-2022, 22:53:52
    Author     : Sandra
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %> 
<jsp:include page="ComprobarSesion.jsp"></jsp:include>
<jsp:include page="ComprobarAdmin.jsp"></jsp:include>
<div class="alerta">
    <p>${msg}</p>
</div>
    <div class="col-6">
        <p>Añadir un nuevo registro:</p>
        <button id="alta" class="btn btn-lg boton">Dar de alta </button>
    </div>
    <div class="col-6">
        <p>Actualizaciones y bajas:</p>
        <label for="nombre" class="form-label">Selecciona el registro a modificar o borrar:</label>
            <select id="elemento" name="elemento" class="form-select form-select-lg">
                <c:forEach var="elemento" items="${listadoElementos}">
                    <c:if test="${elementoP.id == elemento.id}">
                        <option value="${elemento.id}" selected="">${elemento.nombre}</option>
                    </c:if>
                    <option value="${elemento.id}">${elemento.nombre}</option>
                </c:forEach>
            </select>
        <div class="col-12 text-center">
            <button id="borrar" class="btn btn-lg boton">Borrar</button> <button id="editar" class="btn btn-lg boton">Editar</button>
        </div>
    
    </div>


<script src="https://code.jquery.com/jquery-3.6.0.min.js" integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4=" crossorigin="anonymous"></script>
<script>
    document.getElementById("borrar").addEventListener("click", Confirmation);
             function Confirmation(){
                 if (confirm("¿Está seguro/a de que quiere eliminarlo?")  ) {
                    alert("El registro se eliminará");
                        let id = $("#elemento").val();
                        console.log(id)
                        window.location.href = "ControladorAdmin?accion=borrar&id=" + id;
                } else {
                    alert("El registro no se eliminará");
                }
                 
    }
    $("#alta").on("click", function(){
        window.location.href = "ControladorAdmin?accion=insertar";
    });
   $("#editar").on("click", function(evt){
       let id = $("#elemento").val();
       console.log(id)
       evt.preventDefault();
       window.location.href = "ControladorAdmin?accion=elementoModificar&id=" + id;

    });


</script>
</body>
</html>
