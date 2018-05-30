<%@ include file="/WEB-INF/views/include.jsp" %>
<script>
    new Date().toLocaleDateString()
    $(document).ready(function(){
        $(".veDetallebSaltn").click(function(){
            consecutivo = $(this).attr("consecutivo");
            folioSalidaAt = $(this).attr("folioSalidaAt");
            cliente = $(this).attr("cliente");
            $.ajax("consultaSalidaDetalleDet", {
                "type": "POST",
                "beforeSend": function() {
                    $("#bootbox-options").css("font-size","27px");
                    $("#bootbox-options").html('Cargando ...');
                },
                "success": function(result){
                    $("#bootbox-options").css("font-size","14px");
                    $("#bootbox-options").html(result);
                },
                "error": function(result) {
                    alert("Error al recuperar la información", result);
                    $("#bootbox-options").close();
                    //showAlert("Error al recuperar la información", result);
                },
                "data": { cliente:cliente, consecutivo: consecutivo ,folioSalida: folioSalidaAt},
                "async": false
            });

            bootbox.dialog($("#bootbox-options").html(), [{
                        "label" : "Cerrar",
                        "class" : "btn-small btn-primary",
                        "callback": function() {
                            //$("#bootbox-options").html("");
                        }
                    }]
            ).css({"width":"850px","heith":"auto"});
            $("#bootbox-options").html("");
        });
    });
</script>
<h3 id="item_name">Reporte de Salida por Fechas y Camara : Del ${fechaInicio} al ${fechaFin}</h3>
<c:choose>
	<c:when test="${not empty conteoCamara}">
        <a class="btn btn-primary btn-small" target="_blank" href="exportSalPorCamara?type=xls&fechaInicio=${fechaInicio}&fechaFin=${fechaFin}&idCamara=${idCamara}">EXPORTA XLS  <span class="glyphicon glyphicon-list-alt"></span></a>
        <a class="btn btn-primary btn-small" target="_blank" href="exportSalPorCamara?type=pdf&fechaInicio=${fechaInicio}&fechaFin=${fechaFin}&idCamara=${idCamara}">EXPORTA PDF  <span class="glyphicon glyphicon-th-list"></span></a>
        <table aria-describedby="sample-table-2_info" id="sample-table-2" class="table table-striped table-bordered table-hover dataTable">
        	<thead>
        	<tr role="row">
                <th>DETALLE</th>
                <th>ID</th>
                <th>NOMBRE CLIENTE</th>
                <th>RD</th>
                <th>FOLIO DE SALIDA</th>
                <th>PZAS</th>
                <th>KILOS</th>
            </tr>
            </thead>
            <c:set var="totalpzas" value="${0}"/>  
            <c:set var="totalkilos" value="${0}"/>
            <tbody aria-relevant="all" aria-live="polite" role="alert"><tr class="odd">
            <c:forEach items="${conteoCamara}" var="e">
                    <td>
                        <button class="btn btn-minier btn-primary veDetallebSaltn" cliente="${e[1]}" consecutivo="${e[2]}" folioSalidaAt="${e[5]}">Ver</button>
                    </td>
                    <td align="right" width="20%">${e[0]}</td>
                    <td align="right" width="20%">
                        ${e[1]}
                    </td>
                    <td align="right" width="20%">${e[2]}</td>
                    <td align="right" width="20%">${e[5]}</td>
                    <td align="right" width="20%">${e[3]}</td>
                    <td align="right" width="20%"><fmt:formatNumber value="${e[4]}" maxFractionDigits="2"/></td>
                    <c:set var="totalpzas" value="${totalpzas+(e[3])}"/>
               		<c:set var="totalkilos" value="${totalkilos+(e[4])}"/>
                </tr>
            </c:forEach>
            </tbody>
            	<tr class="trcontenido">
                    <td align="right" width="20%" colspan="5"></td>
                    <td align="right" width="20%">TOTAL PZAS:<fmt:formatNumber value="${totalpzas}" maxFractionDigits="2"/></td>
					<td align="right" width="20%">TOTAL KILOS:<fmt:formatNumber value="${totalkilos}" maxFractionDigits="2"/></td>
                </tr>
        </table>
	</c:when>  
	<c:when test="${conteoCamara eq '[]'}">
      <div id="mensaje_reporte_vacio"><h1 id="item_name">No existen registros con esas condiciones.</h1></div>
    </c:when>        
</c:choose>