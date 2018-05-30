<%@ include file="/WEB-INF/views/include.jsp" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<script type="text/javascript" src="resources/js/salidasImp.js"></script>
<script>
$(document).ready(function(){
	var valorhtmllista="";
	valorhtmllista = $("#consultadetallesRd").html();
	if($.trim(valorhtmllista)==""){
		$(".buscasalidasImp").click();
	}
	//
});
</script>
<!-- link rel="stylesheet" type="text/css"	href="resources/bootstrap/css/entrada.css" /-->
<link rel="stylesheet" type="text/css"	href="resources/bootstrap/css/salidaImp.css" />	
<section class="container">
<h1 class="item_name"><fmt:message key="consultas.salidasImprime.heading"/></h1>
<h4 class="item_name">${idCliente} ${nombreCliente}</h4>
<form:form method="post" id="formsalidafiltro" commandName="formsalidabusca" role="form">
<section class="grupodedatos">
  <div class="bloquedata">
	<div class="form-group">
    	<label for="numeroRd">FOLIO SALIDA</label>
   		<input type='text' placeholder="FOLIO SALIDA" id="folioSalida" name='folioSalida' value='' size="75" maxlength="50" class="form-control">
   		<input type='hidden' placeholder="PRODUCTO" id="producto" name='producto' value='' size="75" maxlength="255" class="form-control">
   		<input type='hidden' placeholder="CADUCIDAD" id="caducidad" name='caducidad' value='' size="75" maxlength="255" class="form-control">
   		<input type='hidden' id="idCliente" name='idCliente' value="${idClienteconsultado}"/>
  	</div>
  	<div class="form-group">
		<button class="btn btn-primary btn-small buscasalidasImp" type="submit">
			<i class="icon-edit  bigger-125"></i>
			Buscar salidas
		</button>
    </div>
  </div>
  <!--div class="bloquedata">
  <div class="form-group">
    	<label for="producto">PRODUCTO</label>
  </div>
  </div-->
  <!--div class="bloquedata">
  	<div class="form-group">
    	<label for="caducidad">CADUCIDAD</label>
  	</div>
  </div-->	
</section>
  
 <input type='hidden' name='fechaEntrada' value='${fecha}'>
 <input type='hidden' name='horaEntrada' value='${hora}'>
</form:form>
<div id="consultadetallesRd"></div>
<div id="bootbox-options"></div>
</section>

