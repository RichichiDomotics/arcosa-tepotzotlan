<%@ include file="/WEB-INF/views/include.jsp" %>
<script>
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
<h3 id="item_name">Reporte Salida por Fechas y Cliente : Del ${fechaInicio} al ${fechaFin}</h3>
<c:choose>
	<c:when test="${not empty conteoPorFechasCliente}">
        <a class="btn btn-primary btn-small" target="_blank" href="exportSalPorFechaCliente?type=xls&fechaInicio=${fechaInicio}&fechaFin=${fechaFin}&idCliente=${idCliente}&nombreCliente=${clienteDatos.idCliente} ${clienteDatos.nombreCliente}">EXPORTA XLS  <span class="glyphicon glyphicon-list-alt"></span></a>
        <a class="btn btn-primary btn-small" target="_blank" href="exportSalPorFechaCliente?type=pdf&fechaInicio=${fechaInicio}&fechaFin=${fechaFin}&idCliente=${idCliente}&nombreCliente=${clienteDatos.idCliente} ${clienteDatos.nombreCliente}">EXPORTA PDF  <span class="glyphicon glyphicon-th-list"></span></a>
        <table aria-describedby="sample-table-2_info" id="sample-table-2" class="table table-striped table-bordered table-hover dataTable">
          <thead>
        	<tr role="row">
                <td colspan="6" class="trtitulo">
                    CLIENTE: ${clienteDatos.idCliente} ${clienteDatos.nombreCliente}
                </td>
            </tr>
            <tr>
                <th>Detalle</th>
                <th>Fecha</th>
                <th>RD</th>
                <th>Folio de Salida</th>
                <th>Cantidad</th>
                <th>Total de Kilos</th>
            </tr>
           </thead> 
            <c:set var="totalpzas" value="${0}"/>  
            <c:set var="totalkilos" value="${0}"/>
            <tbody aria-relevant="all" aria-live="polite" role="alert"><tr class="odd">
            <c:forEach items="${conteoPorFechasCliente}" var="e">
                    <td>
                        <button class="btn btn-minier btn-primary veDetallebSaltn" cliente="${clienteDatos.idCliente} ${clienteDatos.nombreCliente}" consecutivo="${e[3]}" folioSalidaAt="${e[4]}">Ver</button>
                    </td>
                    <td align="right" width="20%"><fmt:parseDate value="${e[0]}" var="parsedEmpDate" pattern="yyyy-MM-dd" />
                        <fmt:formatDate pattern="dd-MM-yyyy" value="${parsedEmpDate}" /></td>
                    <td>${e[3]}</td>
                    <td>
                            ${e[4]}
                    </td>
                    <td><fmt:formatNumber value="${e[1]}" maxFractionDigits="2"/></td>
                    <td><fmt:formatNumber value="${e[2]}" maxFractionDigits="2"/> KGS</td>
               		<c:set var="totalpzas" value="${totalpzas+(e[2])}"/>
               		<c:set var="totalkilos" value="${totalkilos+(e[1])}"/>
                </tr>
            </c:forEach>
            </tbody>
            	<tr class="trcontenido">
                    <td colspan="4"></td>
					<td>TOTAL PZAS:<fmt:formatNumber value="${totalkilos}" maxFractionDigits="2"/></td>
					<td>TOTAL KILOS:<fmt:formatNumber value="${totalpzas}" maxFractionDigits="2"/></td>
                </tr>
        </table>
	</c:when>
	<c:when test="${conteoPorFechasCliente eq '[]'}">
      <div id="mensaje_reporte_vacio"><h1 id="item_name">No existen registros con esas condiciones.</h1></div>
    </c:when>        
</c:choose>