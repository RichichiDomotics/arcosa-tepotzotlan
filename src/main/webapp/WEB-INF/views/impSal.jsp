<%@ include file="/WEB-INF/views/include.jsp" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<button class="btn btn-primary btn-mini ingresarModificacion imprimir">
    <i class="icon-key"></i>
    Imprimir
</button>
<br>
<div id="result"></div>
<script>
    $(".imprimir").click(function () {
        $.ajax("imprimirSalida",{
            "type": "Post",
            "success": function (data) {
                $("#result").html(data);
            },
            "error": function () {
                bootbox.alert("Ocurrio un problema");
            }
        });
    });
</script>
