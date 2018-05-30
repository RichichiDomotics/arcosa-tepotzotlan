<%@ include file="/WEB-INF/views/include.jsp" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<link rel="stylesheet" type="text/css" href="resources/bootstrap/css/entrada.css" />
<script>
    $(document).ready(function(){
        $(".buscaClienteDetbtn").click(function(){
            idCliente = $("#idClienteConsulta").val();
            $.ajax("inventariosTif", {
                "type": "POST",
                "beforeSend": function() {
                    $("#buscaProspectodiv").css("font-size","0.875em");
                    $("#buscaProspectodiv").html('<img src="resources/img/loader.GIF"/>');
                },
                "success": function(result){
                    $("#buscaProspectodiv").css("font-size","1.0em");
                    $("#buscaProspectodiv").html(result);
                    return false;
                },
                "error": function(result) {
                    alert("Error al recuperar la información", result);
                },
                "data": { idCliente : idCliente },
                "async": true
            });
        });

    });

</script>

<section class="container">

    <h3 id="item_name2">Consulta Estatus TIF</h3>
    <section class="grupodedatosRetemes">
        <div class="bloquedata">
            <div class="form-group">
                <label for="idClienteConsulta">CLIENTE</label>
                <select id="idClienteConsulta" name="idClienteConsulta" class="form-control" required>
                    <option value="" > <fmt:message key="entradas.select"/> </option>
                    <c:forEach items="${clientes}" var="client">
                        <option value="${client.idCliente}"> <c:out value="${client.idCliente} ${client.nombreCliente}"/></option>
                    </c:forEach>
                </select>
            </div>
            <div class="form-group">
                <br>
                <button class="btn btn-primary btn-small buscaClienteDetbtn" type="button">
                    <i class="icon-edit  bigger-125"></i>
                    Consultar
                </button>
            </div>
        </div>
        <br><br>
        <div class="bloquedataResultado">
            <div id="buscaProspectodiv"></div>
        </div>
    </section>
</section>
