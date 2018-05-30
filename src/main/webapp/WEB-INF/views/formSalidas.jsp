<%@ include file="/WEB-INF/views/include.jsp" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<script type="text/javascript" src="resources/js/salidas.js"></script>
<script>
$(document).ready(function(){
	var valorhtmllista="";
	valorhtmllista = $("#consultadetallesRd").html();
	if($.trim(valorhtmllista)==""){
		$(".buscasalidas").click();
	}
	//
});
</script>
<!-- link rel="stylesheet" type="text/css"	href="resources/bootstrap/css/entrada.css" /-->
<link rel="stylesheet" type="text/css"	href="resources/bootstrap/css/salida.css" />	
<section class="container">
<h1 class="item_name"><fmt:message key="consultas.salidasfiltra.heading"/></h1>
<h4 class="item_name">${idClienteconsultado} ${nombreCliente}</h4>
<form:form method="post" id="formsalidafiltro" commandName="formsalidabusca" role="form">
    <div>${message}</div>
<section class="grupodedatos">
  <div class="bloquedata">
	<div class="form-group">
    	<label for="numeroRd">NUMERO RD</label>
   		<input type='text' placeholder="NUMERO RD" id="numeroRd" name='numeroRd' value='' size="75" maxlength="50" class="form-control">
   		<input type='hidden' id="idCliente" name='idCliente' value="${idClienteconsultado}"/>
  	</div>
  </div>
	<div class="bloquedata">
		<div class="form-group">
			<label for="producto">NUMERO FOLIO</label>
			<input type='text' placeholder="NUMERO FOLIO" id="folioSalida" name='producto' value='' size="75" maxlength="255" class="form-control">
		</div>
	</div>
  <div class="bloquedata">
  <div class="form-group">
    	<label for="producto">PRODUCTO</label>
   		<input type='text' placeholder="PRODUCTO" id="producto" name='producto' value='' size="75" maxlength="255" class="form-control">
  </div>
  </div>
  <div class="bloquedata">
  	<div class="form-group">
    	<label for="caducidad">CADUCIDAD</label>
   		<input type='text' placeholder="CADUCIDAD" id="caducidad" name='caducidad' value='' size="75" maxlength="255" class="form-control">
  	</div>
	  <div class="form-group">
		  <label for="caducidad">ENTREGADO A</label>
		  <input type='hidden' placeholder="ENTREGADO" id="entregado" name='entregado' value='${entregado}' size="75" maxlength="255" class="form-control" disabled>
	  </div>
  	<div class="form-group">
  	<button class="btn btn-primary btn-small buscasalidas" type="submit">
		<i class="icon-edit  bigger-125"></i>
		Buscar productos
	</button>
  </div>
  </div>
</section>
  
 <input type='hidden' name='fechaEntrada' value='${fecha}'>
 <input type='hidden' name='horaEntrada' value='${hora}'>
</form:form>
<div id="consultadetallesRd"></div>
</section>

