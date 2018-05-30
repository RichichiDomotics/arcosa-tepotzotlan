<%@ include file="/WEB-INF/views/include.jsp" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<link rel="stylesheet" type="text/css" href="resources/bootstrap/css/entrada.css" />

<section class="contenido-forma">
<h1 class="tituloinicio alert-success"><fmt:message key="entradas.heading"/></h1>
<h3 class="tituloinicio2 alert-success"><fmt:message key="entradas.subtitle"/></h3>
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
    	<label for="idPlanta">PLANTA</label>
    	<select id="idPlanta" name="idPlanta" class="form-control">
    		<option value=-1 > <fmt:message key="entradas.select"/> </option>
        		<c:forEach items="${plantas}" var="plant">
      				<option value="${plant.idPlanta}"> <c:out value="${plant.descripcion}"/></option>
    			</c:forEach>
    	</select>
  	</div>
  	<div id="cboPuerta">
  	<div class="form-group">
   	 <label for="idPuerta">PUERTA</label>
    	<select id="idPuerta" name="idPuerta" value='' class="form-control">
    		<option value=-1 > <fmt:message key="entradas.select"/> </option>
        		<c:forEach items="${puertas}" var="p">
      				<option value="${p.idCatalogo}"> <c:out value="${p.descripcion}"/></option>
    			</c:forEach>
    	</select>
  	</div>
  </div>
  </div>
  <div class="bloquedata">
    <div class="form-group">
    	<label for="nombreOperador">NOMBRE DEL OPERADOR</label>
   		<input type='text' placeholder="NOMBRE DEL OPERADOR" id="nombreOperador" name='nombreOperador' value='' size="75" maxlength="255" class="form-control">
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
    	<label for="placasVehiculo">PLACAS</label>
    	<input type='text' placeholder="PLACAS" id="placasVehiculo" name='placasVehiculo' value='' class="form-control" required>
  	</div>
  	<div class="form-group">
    	<label for="idVehiculo">VEHICULO</label>
    	<select id="idVehiculo" name="idVehiculo" value='' class="form-control" required>
    		<option value="" > <fmt:message key="entradas.select"/> </option>
        		<c:forEach items="${vehiculos}" var="v">
      				<option value="${v.idCatalogo}"> <c:out value="${v.descripcion}"/></option>
    			</c:forEach>
    	</select>
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
    <label for="nombreCiaTransporte">COMPA�IA DE TRANSPORTE</label>
    <input id="nombreCiaTransporte" type='text' placeholder="COMPA�IA DE TRANSPORTE" name='nombreCiaTransporte' value='' size="75" maxlength="255" class="form-control">
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
  </div>
  <div class="bloquedata">
  	<div class="form-group">
    <label for="numRecibo">RECIBO DE ENTRADA U ORDEN DE SALIDA</label>
    <input id="numRecibo" type='text' placeholder="RECIBO DE ENTRADA U ORDEN DE SALIDA" name='numRecibo' value='' size="75" maxlength="255" class="form-control">
  </div>
  <div class="form-group">
    <label for="e_permisos">PERMISO PARA ELABORAR ORDEN DE SALIDA</label>
    <input id="e_permisos" type='text' placeholder="PERMISO PARA ELABORAR ORDEN DE SALIDA" name='e_permisos' value='' size="75" maxlength="255" class="form-control">
  </div>
  <div class="form-group">
	<input type="submit" class="btn btn-primary btn-sm" value="Registrar">
  </div>
  </div>	
</section>
  
 <input type='hidden' name='fechaEntrada' value='${fecha}'>
 <input type='hidden' name='horaEntrada' value='${hora}'>
 
</form:form>
</section>
