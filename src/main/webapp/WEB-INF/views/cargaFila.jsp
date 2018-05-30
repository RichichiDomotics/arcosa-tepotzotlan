<%@ include file="/WEB-INF/views/include.jsp" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<script>
	<!--
	$(document).ready(function(){
		$("#filaCallel").change(function(){
			camara = $("#camara").val();
			puerta = $("#puerta").val();
			pasillo = $("#pasillo").val();
			filaCalle = $("#filaCallel").val();

			if(puerta!=""){
				$.ajax("cargaPosicion", {
					"type": "POST", //usualmente post o get
					"success": function(result) {
						$("#cboPosicion").html(result);
					},
					"error": function(result) {
						showAlert("Error al recuperar la informacion", result);
					},
					"beforeSend": function(result) {
						$("#cboPosicion").css("color","#000");
						$("#cboPosicion").html("cargando...");
					},
					"data": {pasillo : pasillo,puerta : puerta, camara : camara, fila:filaCalle},
					"async": true
				});
			}
		});

	});
	//-->
</script>
<select id="filaCallel" name="filaCallel" value='' class="form-control" required>
	<option value="" > <fmt:message key="entradas.select"/> </option>
	<c:forEach items="${filas}" var="fila">
		<option value="${fila}"> <c:out value="${fila}"/></option>
	</c:forEach>
</select>
