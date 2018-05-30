<%@ include file="/WEB-INF/views/include.jsp" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<script>
	<!--
	$(document).ready(function(){
		$("#posicion").change(function(){
			camara = $("#camara").val();
			puerta = $("#puerta").val();
			pasillo = $("#pasillo").val();
			filaCalle = $("#filaCallel").val();
			if(puerta!=""){
				$.ajax("cargaNivel", {
					"type": "POST", //usualmente post o get
					"success": function(result) {
						$("#cboNivel").html(result);
					},
					"error": function(result) {
						showAlert("Error al recuperar la informacion", result);
					},
					"beforeSend": function(result) {
						$("#cboNivel").css("color","#000");
						$("#cboNivel").html("cargando...");
					},
					"data": {pasillo : pasillo,puerta : puerta, camara : camara, fila:filaCalle},
					"async": true
				});
			}
		});

	});
	//-->
</script>
<select id="posicion" name="posicion" class="form-control" required>
	<option value="" > <fmt:message key="entradas.select"/> </option>
	<c:forEach var="i" begin="1" end="${posiciones[0]}">
		<option value="${i}"> <c:out value="${i}"/></option>
	</c:forEach>
</select>
