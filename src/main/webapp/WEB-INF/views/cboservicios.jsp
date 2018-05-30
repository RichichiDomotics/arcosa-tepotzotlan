<%@ include file="/WEB-INF/views/include.jsp" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<script>
	$(document).ready(function(){
		$("#TIPOSERVICIO").change(function(){
			clave=$("#TIPOSERVICIO").val();
			idCliente=${idCliente};
			<c:forEach items="${servicios}" var="service">
				if(clave=="${service.clave}" && idCliente== ${service.idCliente}){
					$("#COMO").val("${service.como}");
				}
			</c:forEach>
		});
	});
</script>
  <div class="form-group">
   	 <label>ELIJA LA MANIOBRA ESPECIAL</label>
    	<select id="TIPOSERVICIO" name="TIPOSERVICIO" required class="form-control">
    		<option value="" ><fmt:message key="entradas.select"/></option>
    		<c:forEach items="${servicios}" var="servicio">
            	<option value="${servicio.clave}"><c:out value="${servicio.descripcion}"/></option>
            </c:forEach>
    	</select>
 </div>
	<div class="form-group">
		<label>CAPTURARLO COMO</label>
		<input type='text' placeholder="" id="COMO" name='COMO' value='' maxlength="10" class="form-control" disabled required>
	</div>
