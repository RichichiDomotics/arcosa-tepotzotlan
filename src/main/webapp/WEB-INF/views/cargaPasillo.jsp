<%@ include file="/WEB-INF/views/include.jsp" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<script>
	<!--
	$(document).ready(function(){
		$("#pasillo").change(function(){
			camara = $("#camara").val();
			puerta = $("#puerta").val();
			pasillo = $("#pasillo").val();

			if(puerta!=""){
				$.ajax("cargaFila", {
					"type": "POST", //usualmente post o get
					"success": function(result) {
						$("#cboFila").html(result);
					},
					"error": function(result) {
						showAlert("Error al recuperar la informacion", result);
					},
					"beforeSend": function(result) {
						$("#cboFila").css("color","#000");
						$("#cboFila").html("cargando...");
					},
					"data": {pasillo : pasillo,puerta : puerta, camara : camara },
					"async": true
				});
			}
		});

	});
	//-->
</script>
<select id="pasillo" name="pasillo" value='' class="form-control" required>
	<option value="" > <fmt:message key="entradas.select"/> </option>
	<c:forEach items="${pasillos}" var="pasillo">
		<option value="${pasillo}"> <c:out value="${pasillo}"/></option>
	</c:forEach>
</select>
