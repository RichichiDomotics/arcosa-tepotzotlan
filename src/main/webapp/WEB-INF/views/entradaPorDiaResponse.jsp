<%@ include file="/WEB-INF/views/include.jsp" %>
<script>
    $(document).ready(function(){
        $(".veDetallebtn").click(function(){
            consecutivo = $(this).attr("consecutivo");
            cliente = $(this).attr("cliente");
            $.ajax("consultaDetalleRd", {
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
                "data": { cliente:cliente, consecutivo: consecutivo},
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
<div id="contenidodelreporte">
<h3 id="item_name">Reporte Entrada al D&iacute;a de : ${fechaInicio}</h3> 
<c:choose>
	<c:when test="${not empty conteoPorDia}">
        <a class="btn btn-primary btn-small" target="_blank" href="exportEntPorDia?type=xls&fechaInicio=${fechaInicio}">EXPORTA XLS <i class="icon-bar-chart"></i></a>
        <a class="btn btn-primary btn-small" target="_blank" href="exportEntPorDia?type=pdf&fechaInicio=${fechaInicio}">EXPORTA PDF <i class="icon-bar-chart"></i></a>
        <table aria-describedby="sample-table-2_info" id="sample-table-2" class="table table-striped table-bordered table-hover dataTable">
          <thead>
        	<tr role="row">
                <th>Detalle</th>
                <th>Observaciones </th>
                <th>RD</th>
                <th>Cliente</th>
                <th>Tipo de Producto</th>
                <th>Camara</th>
                <th>Posiciones</th>
                <th>Piezas</th>
                <th>Kilos que Entraron</th>
            </tr>
           </thead> 
            <c:set var="totalpzas" value="${0}"/>  
            <c:set var="totalkilos" value="${0}"/>
            <tbody aria-relevant="all" aria-live="polite" role="alert">
          <c:forEach items="${conteoPorDia}" var="e">
                <tr class="odd">
                    <td>
                        <button class="btn btn-minier btn-primary veDetallebtn" cliente="${e[4]} ${e[5]}" consecutivo="${e[3]}">Ver</button>
                    </td>
                    <td>
						    ${e[8]}
					</td>
                    <td>
                            ${e[3]}
                    </td>
                    <td>
                            ${e[4]} ${e[5]}
                    </td>
                    <td>${e[6]}</td>
                    <td>${e[7]}</td>
                    <td>${e[9]}</td>
                    <td><fmt:formatNumber value="${e[2]}" maxFractionDigits="2"/></td>
                    <td><fmt:formatNumber value="${e[1]}" maxFractionDigits="2"/> KGS</td>
					<c:set var="totalpzas" value="${totalpzas+(e[2])}"/>
               		<c:set var="totalkilos" value="${totalkilos+(e[1])}"/>
                </tr>
          </c:forEach>
                </tbody>
            	<tr class="trcontenido">
                    <td align="right" colspan="6"></td>
                    <td align="right" >TOTAL PZAS:<fmt:formatNumber value="${totalpzas}" maxFractionDigits="2"/></td>
					<td align="right" >TOTAL KILOS:<fmt:formatNumber value="${totalkilos}" maxFractionDigits="2"/></td>
                </tr>
        </table>
    </c:when>  
	<c:when test="${conteoPorDia eq '[]'}">
      <div id="mensaje_reporte_vacio"><h1 id="item_name">No existen registros con esas condiciones.</h1></div>
    </c:when>        
</c:choose>
</div>
