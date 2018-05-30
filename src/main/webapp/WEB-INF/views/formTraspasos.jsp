<%@ include file="/WEB-INF/views/include.jsp" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<link rel="stylesheet" type="text/css" href="resources/bootstrap/css/entrada.css"/>

<section class="container">
<h1 id="item_name"><fmt:message key="traspasos.heading"/></h1>
<h3 id="item_name"><fmt:message key="traspasos.subtitle"/></h3>
<form:form method="post" commandName="traspasosCamara" role="form" action="ae_inserttraspasos">
<section class="grupodedatos">
  <div class="bloquedata">
      <div class="msjerror"><B>${message}</B></div>
  	<div class="form-group">
    	<label for="consecutivo">INGRESE EL RD</label>
    	<input type='text' placeholder="RD" id="consecutivo" name='consecutivo' value='' size="75" maxlength="255" class="form-control">
  	</div>
  	<div class="form-group">
   		<label for="camaraInicial">CAMARA INICIAL</label>
    	<select id="camaraInicial" name="camaraInicial" value='' class="form-control" required>
    		<option value="" > <fmt:message key="camara.select"/> </option>
    		<!--option value="1" >CAMARA 1 </option>
    		<option value="2" >CAMARA 2 </option>
			<option value="T1" >TUNEL 1 </option>
			<option value="T2" >TUNEL 2 </option>
			<option value="T3" >TUNEL 3 </option-->

			<option value="0" >CAMARA 0 </option>
			<option value="1" >CAMARA 1 </option>
			<option value="2" >CAMARA 2 </option>
			<option value="3" >CAMARA 3 </option>
			<option value="4" >CAMARA 4 </option>
			<option value="5" >CAMARA 5 </option>
			<option value="6" >CAMARA 6 </option>
			<option value="7" >CAMARA 7 </option>
			<option value="8" >CAMARA 8 </option>
			<option value="9" >CAMARA 9 </option>
			<option value="T1" >TUNEL 1 </option>
			<option value="T2" >TUNEL 2 </option>
    	</select>
  	</div>
  	<div class="form-group">
   		<label for="camaraFinal">CAMARA FINAL</label>
    	<select id="camaraFinal" name="camaraFinal" value='' class="form-control" required>
    		<option value="" > <fmt:message key="camara.select"/> </option>
    		<!--option value="1" >CAMARA 1 </option>
    		<option value="2" >CAMARA 2 </option>
			<option value="T1" >TUNEL 1 </option>
			<option value="T2" >TUNEL 2 </option>
			<option value="T3" >TUNEL 3 </option-->

			<option value="0" >CAMARA 0 </option>
			<option value="1" >CAMARA 1 </option>
			<option value="2" >CAMARA 2 </option>
			<option value="3" >CAMARA 3 </option>
			<option value="4" >CAMARA 4 </option>
			<option value="5" >CAMARA 5 </option>
			<option value="6" >CAMARA 6 </option>
			<option value="7" >CAMARA 7 </option>
			<option value="8" >CAMARA 8 </option>
			<option value="9" >CAMARA 9 </option>
			<option value="T1" >TUNEL 1 </option>
			<option value="T2" >TUNEL 2 </option>
    	</select>
  	</div>
  	<div class="form-group">
    	<label for="motivo">MOTIVO DEL CAMBIO</label>
    	<input type='text' placeholder="MOTIVO" id="motivo" name='motivo' value='' size="75" maxlength="255" class="form-control">
  	</div>
  </div>
  <div class="bloquedata">
    <div class="form-group">
    	<label for="realizadoPor">REALIZADO POR</label>
    	<input type='text' placeholder="REALIZADO POR" id="realizadoPor" name='realizadoPor' value='' size="75" maxlength="255" class="form-control">
  	</div>
  	<div class="form-group">
    	<label for="fechaIngreso">FECHA DE INGRESO</label>
    	<input type='text' placeholder="dd/mm/aaaa" id="fechaIngreso" name='fechaIngreso' value='' size="75" maxlength="255" class="form-control">
  	</div>
  	<div class="form-group">
    	<label for="fechaCambio">FECHA DE CAMBIO</label>
    	<input type='text' placeholder="dd/mm/aaaa" id="fechaCambio" name='fechaCambio' value="${now}" size="75" maxlength="255" class="form-control">
  	</div>
  	<div class="form-group">
	<button class="btn btn-primary btn-small" type="submit">
			<i class="icon-edit  bigger-125"></i>
			REALIZA TRASPASO
	</button>
  </div>
  </div>	
</section>
  
 <input type='hidden' name='fechaEntrada' value='${fecha}'>
 <input type='hidden' name='horaEntrada' value='${hora}'>
 
</form:form>
</section>
