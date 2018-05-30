<%@ include file="/WEB-INF/views/include.jsp" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<link rel="stylesheet" type="text/css" href="resources/bootstrap/css/entrada.css" />
<script>
<!--
$(document).ready(function(){
	$(".solosalidas").hide();
});	
//-->
</script>
<section class="container">
<h1 id="item_name"><fmt:message key="entradas.heading"/></h1>
<h3 id="item_name"><fmt:message key="entradas.subtitle"/></h3>
<form:form method="post" commandName="formEntrada" role="form">
<section class="grupodedatos">
  <div class="bloquedata">
  	<div class="form-group">
    	<label for="idTipoRecibo">TIPO DE RECIBO</label>
    	<select id="idTipoRecibo" name="idTipoRecibo" placeholder="TIPO DE RECIBO" value='' class="form-control" required>
    		<option value="" > <fmt:message key="entradas.select"/></option>
       	 		<c:forEach items="${recibos}" var="r">
      				<option value="${r.idCatalogo}"> <c:out value="${r.descripcion}"/></option>
    			</c:forEach>
    	 </select>
  	</div>
  	<div class="form-group">
   		<label for="idCliente">CLIENTE</label>
    	<select id="idCliente" name="idCliente" value='' class="form-control" required>
    		<option value="" > <fmt:message key="entradas.select"/> </option>
        		<c:forEach items="${clientes}" var="client">
      				<option value="${client.idCliente}"> <c:out value="${client.idCliente} ${client.nombreCliente}"/></option>
    			</c:forEach>
    	</select>
  	</div>
  	<div class="form-group">
    	<label for="placasVehiculo">PLACAS (Trailer placas de la caja)</label>
    	<input type='text' placeholder="PLACAS (Trailer placas de la caja)" id="placasVehiculo" name='placasVehiculo' value='' class="form-control" maxlength="12" required>
  	</div>
  	<div class="form-group">
    	<label for="nombreOperador">NOMBRE DEL OPERADOR</label>
   		<input type='text' placeholder="NOMBRE DEL OPERADOR" id="nombreOperador" name='nombreOperador' value='' size="75" maxlength="255" class="form-control">
  	</div>
  	<div class="form-group">
    	<label for="incidenciaIngreso">INCIDENCIA AL INGRESO</label>
   		<input type='text' placeholder="INCIDENCIA AL INGRESO" id="incidenciaIngreso" name='incidenciaIngreso' value='' size="75" maxlength="60" class="form-control">
  	</div>
	<div class="form-group">
	  <div class="solosalidas">
	    <label for="entregadoA">ENTREGADO A</label>
		<input type='text' placeholder="ENTREGADO A" id="entregadoA" name='entregadoA' value='' size="75" maxlength="255" class="form-control">
	  </div>
	</div>
  </div>
  <div class="bloquedata">
  	<div class="form-group">
    	<label for="idVehiculo">VEHICULO</label>
    	<select id="idVehiculo" name="idVehiculo" value='' class="form-control" required>
    		<option value="" > <fmt:message key="entradas.select"/> </option>
        		<c:forEach items="${vehiculos}" var="v">
      				<option value="${v.idCatalogo}"> <c:out value="${v.descripcion}"/></option>
    			</c:forEach>
    	</select>
  	</div>
	<div class="form-group">
	  <div id="cboPlanta">
    	<label for="idPlanta">PLANTA</label>
    	<select id="idPlanta" name="idPlanta" class="form-control" required>
    		<option value=""> <fmt:message key="entradas.select"/> </option>
        		<c:forEach items="${plantas}" var="plant">
      				<option value="${plant.idPlanta}"> <c:out value="${plant.descripcion}"/></option>
    			</c:forEach>
    	</select>
      </div>	
  	</div>
    <div class="form-group">
     <div id="cboPuerta">
   	 <label for="idPuerta">PUERTA</label>
    	<select id="idPuerta" name="idPuerta" value='' class="form-control" required>
    		<option value="" > <fmt:message key="entradas.select"/> </option>
        		<c:forEach items="${puertas}" var="p">
      				<option value="${p.idCatalogo}"> <c:out value="${p.descripcion}"/></option>
    			</c:forEach>
    	</select>
      </div>	
  	</div>
  	<div class="form-group">
    	<label for="idManiobra">MANIOBRAS</label>
    	<select id="idManiobra" name="idManiobra" value='' class="form-control" required disabled>
    		<option value="" > <fmt:message key="entradas.select"/> </option>
        		<c:forEach items="${maniobras}" var="m">
      				<option value="${m.idCatalogo}"> <c:out value="${m.descripcion}"/></option>
    			</c:forEach>
    	</select>
  	</div>
	<div class="form-group">
	  <div id="cboTipoProd">
    	<label for="tipoProducto">TIPO PRODUCTO</label>
    	<!-- select id="tipoProducto" name="tipoProducto" class="form-control" required>
    		<option value="" ><fmt:message key="entradas.select"/></option>
    	</select-->
    	<input type='text' placeholder="TIPO PRODUCTO" id="tipoProducto" name='tipoProducto' value='' size="75" maxlength="255" class="form-control">
    	</div>
  	</div>
	<div class="form-group">
	  <div class="solosalidas">
		<label for="ordenSalidaCliente">ORDEN DE SALIDA DEL CLIENTE</label>
		<input type='text' placeholder="ORDEN DE SALIDA DEL CLIENTE" id="ordenSalidaCliente" name='ordenSalidaCliente' value='' size="75" maxlength="255" class="form-control">
	  </div>
	</div>
  </div>

  <div class="bloquedata">
  <div class="form-group">
    <label for="idTransporte">TRANSPORTE</label>
    <select id="idTransporte" name="idTransporte" value='' class="form-control">
    	<option value=-1 > <fmt:message key="entradas.select"/> </option>
        	<c:forEach items="${transportes}" var="t">
      			<option value="${t.idCatalogo}"> <c:out value="${t.descripcion}"/></option>
    		</c:forEach>
    </select>
  </div>
  <div class="form-group">
    <label for="nombreCiaTransporte">COMPA&Ntilde;IA DE TRANSPORTE</label>
    <input id="nombreCiaTransporte" type='text' placeholder="COMPA&Ntilde;IA DE TRANSPORTE" name='nombreCiaTransporte' value='' size="75" maxlength="255" class="form-control">
  </div>
  <div class="form-group">
    	<label for="idFleje">TIPO DE FLEJE</label>
    	<select id="idFleje" name="idFleje" value='' class="form-control">
    		<option value=-1 > <fmt:message key="entradas.select"/> </option>
        		<c:forEach items="${flejes}" var="f">
      				<option value="${f.idCatalogo}"> <c:out value="${f.descripcion}"/></option>
    			</c:forEach>
    	</select>
  </div>  
  <div class="form-group">
    <label for="fleje">FLEJE</label>
    <input id="fleje" type='text' placeholder="FLEJE" name='fleje' value='' size="75" maxlength="255" class="form-control">
  </div>
      <div class="form-group">
          <label for="peso">CANTIDAD INGRESO/SALIDA(KGS)</label>
          <input id="peso" type='text' placeholder="CANTIDAD INGRESO/SALIDA(KGS)" name='peso' value='' size="75" maxlength="255" class="form-control">
      </div>
  <div class="form-group">
	<button class="btn btn-primary btn-small" id="reg" type="submit">
			<i class="icon-edit  bigger-125"></i>
			Registrar
		</button>
  </div>
  </div>
</section>
  
  <div id="alertaSalida"></div>
 <input type='hidden' name='fechaEntrada' value='${fecha}'>
 <input type='hidden' name='horaEntrada' value='${hora}'>
</form:form>
</section>
