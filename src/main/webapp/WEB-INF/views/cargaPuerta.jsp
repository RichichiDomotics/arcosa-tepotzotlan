<%@ include file="/WEB-INF/views/include.jsp" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<script>
	<!--
	$(document).ready(function(){
		$("#puerta").change(function(){
			camara = $("#camara").val();
			puerta = $("#puerta").val();
			if(puerta!=""){
				$.ajax("cargaPasillo", {
					"type": "POST", //usualmente post o get
					"success": function(result) {
						$("#cboPasillo").html(result);
					},
					"error": function(result) {
						showAlert("Error al recuperar la informacion", result);
					},
					"beforeSend": function(result) {
						$("#cboPasillo").css("color","#000");
						$("#cboPasillo").html("cargando...");
					},
					"data": {Puerta : puerta, Camara : camara},
					"async": true
				});
			}
		});

	});
	//-->
</script>
<select id="puerta" name="puerta" value='' class="form-control" required>
	<option value="" > <fmt:message key="entradas.select"/> </option>
	<c:forEach items="${puertas}" var="puerta">
		<option value="${puerta}"> <c:out value="${puerta}"/></option>
	</c:forEach>
</select>
