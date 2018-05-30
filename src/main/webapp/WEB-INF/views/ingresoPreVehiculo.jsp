<%@ include file="/WEB-INF/views/include.jsp" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<link rel="stylesheet" type="text/css" href="resources/bootstrap/css/entrada.css" />
<script>
<!--
$(document).ready(function(){
	/*$("#idCliente").change(function(){
		idCliente = $("#idCliente").val();
		if(idCliente!=""){
			$.ajax("cboTipoProducto", {
	 		   "type": "POST", //usualmente post o get
	 		   "success": function(result) {
	 			   $("#cboTipoProd").html(result);
	 		   },
	 		   "error": function(result) {
	 		     showAlert("Error al recuperar la informaci�n", result);
	 		   },
	 		   "data": {idCliente : idCliente},
	 		   "async": true
	 		});
		}	
	});*/
});	
//-->
</script>
<section class="container">
<h1 id="item_name">Registro Tr&aacute;fico</h1>
<h3 id="item_name"><fmt:message key="pre_entradas.subtitle"/></h3>
<form:form method="post" commandName="formPreRegistro" role="form" action="v_insertaVigilancia">
<section class="grupodedatos">
  <div class="bloquedata">
  	<div class="form-group">
   		<label class="control-label"  for="idCliente">CLIENTE</label>
    	<select id="idCliente" name="idCliente" value='' class="multiselect" required>
    		<option value="" > <fmt:message key="entradas.select"/> </option>
        		<c:forEach items="${clientes}" var="client">
      				<option value="${client.idCliente}"> <c:out value="${client.idCliente} ${client.nombreCliente}"/></option>
    			</c:forEach>
    	</select>
  	</div>
  	<div class="form-group">
    	<label class="control-label" for="nombreOperador">NOMBRE DEL OPERADOR</label>
   		<input type='text' placeholder="NOMBRE DEL OPERADOR" id="nombreOperador" name='nombreOperador' value='' size="75" maxlength="255" class="form-control" required>
  	</div>
  </div>
  <div class="bloquedata">
  	<div class="form-group">
    	<label for="placasVehiculo">PLACAS</label>
    	<input type='text' placeholder="PLACAS" id="placasVehiculo" name='placasVehiculo' value='' class="form-control" maxlength="12" required>
  	</div>
	<div class="form-group">
	  <div id="cboTipoProd">
    	<!--label for="tipoProducto">TIPO PRODUCTO</label>
    	<!-- select id="tipoProducto" name="tipoProducto" class="form-control" required>
    		<option value="" ><fmt:message key="entradas.select"/></option>
    	</select-->
    	<!--input type='text' placeholder="TIPO PRODUCTO" id="tipoProducto" name='tipoProducto' value='' size="75" maxlength="255" class="form-control" required-->
		  <label for="tipoProducto">VEHICULO</label>
		  <select id="tipoProducto" name="tipoProducto" value='' class="form-control" required>
			  <option value="" > <fmt:message key="entradas.select"/> </option>
			  <c:forEach items="${vehiculos}" var="v">
				  <option value="${v.descripcion}"> <c:out value="${v.descripcion}"/></option>
			  </c:forEach>
		  </select>
    	</div>
  	</div>  	
  </div>
  <div class="bloquedata">
      <div class="form-group">
          <label for="peso">CANTIDAD A INGRESAR (KGS)</label>
          <input id="peso" type='text' placeholder="CANTIDAD A INGRESAR (KGS)" name='peso' value='' size="75" maxlength="255" class="form-control" required>
      </div>
      <div class="form-group">
          <label for="maniobraCargaDescarga">MANIOBRA (Carga o Descarga)</label>
          <select id="maniobraCargaDescarga" name="maniobraCargaDescarga" value='' class="form-control" required>
    		<option value="" > <fmt:message key="entradas.select"/> </option>
        	<option value="CARGA" > CARGA </option>
        	<option value="DESCARGA" > DESCARGA </option>
    	</select>
      </div>
  	  <div class="form-group">
		<button class="btn btn-primary btn-small" type="submit">
			<i class="icon-edit  bigger-125"></i>
			Registrar
		</button>
  	  </div>
  </div>
  
  <div class="bloquedata">
  </div>	
</section>
  
  <div id="alertaSalida"></div>
 <input type='hidden' name='fechaEntrada' value='${fecha}'>
 <input type='hidden' name='horaEntrada' value='${hora}'>
 <input type='hidden' name='estatus' value='1'>
</form:form>
</section>
