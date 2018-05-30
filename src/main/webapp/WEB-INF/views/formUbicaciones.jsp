<%@ include file="/WEB-INF/views/include.jsp" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<link rel="stylesheet" type="text/css" href="resources/bootstrap/css/inventario.css" />
<script type="text/javascript" src="resources/js/inventarios.js"></script>
<script>
$(document).ready(function(){
  $(".buscaUbicacionDet").click(function(){
	camara = $("#camara");
	consecutivo = $("#consecutivo");
	if(camara.val()=="" && consecutivo.val()==""){
		alert("Favor de ingresar un filtro para la consulta.");
		camara.focus();
		return false;
	}

	var options = {
		target:    '#principaldiv',   // target element(s) to be updated with server response
		url:       'tablaRecUbicacion',
		beforeSubmit:  showRequest,  // pre-submit callback
		success:       showResponse  // post-submit callback
	};

	// bind form using 'ajaxForm'
	$('#frmConsultaF').ajaxForm(options);
	$('#frmConsultaF').submit();
	return false;

	});

	showRequest = function(){
		//if(!$("#tablaresultadodetalleUbicaF").length){
		//	$("#dialog-confirm").append('<div id="tablaresultadodetalleUbicaF"></div>')
		//}
		$("#principaldiv").css("color","#000");
		$("#principaldiv").css("font-size","18px");
		$('#principaldiv').html("Buscando ubicaci&oacute;n...<br><br><br>");
	};

	showResponse = function(){
		//if(!$("#tablaresultadodetalleUbicaF").length){
		//	$("#principaldiv").append('<div id="tablaresultadodetalleUbicaF"></div>')
		//}
		$("#principaldiv").css("font-size","12px");
	};


	$(".buscaUbicacionVaciasDet").click(function(){
		camara = $("#camara");
		$("#consecutivo").val("");
		consecutivo = $("#consecutivo");
		if(camara.val()==""){
			alert("Favor de ingresar una opcion de camara.");
			camara.focus();
			return false;
		}

		var options = {
			target:    '#principaldiv',   // target element(s) to be updated with server response
			url:       'tablaRecUbicacionVacias',
			beforeSubmit:  showRequestV,  // pre-submit callback
			success:       showResponseV  // post-submit callback
		};

		// bind form using 'ajaxForm'
		$('#frmConsultaF').ajaxForm(options);
		$('#frmConsultaF').submit();
		return false;

	});

	showRequestV = function(){
		$("#principaldiv").css("color","#000");
		$("#principaldiv").css("font-size","18px");
		$('#principaldiv').html("Buscando ubicaci&oacute;n...<br><br><br>");
	};

	showResponseV = function(){
		$("#principaldiv").css("font-size","12px");
	};

	$(".buscaUbicacionConsolidado").click(function(){
		camara = $("#camara");
		$("#consecutivo").val("");
		consecutivo = $("#consecutivo");
		if(camara.val()==""){
			alert("Favor de ingresar una opcion de camara.");
			camara.focus();
			return false;
		}

		var options = {
			target:    '#principaldiv',   // target element(s) to be updated with server response
			url:       'tablaRecConsolidados',
			beforeSubmit:  showRequestC,  // pre-submit callback
			success:       showResponseC  // post-submit callback
		};

		// bind form using 'ajaxForm'
		$('#frmConsultaF').ajaxForm(options);
		$('#frmConsultaF').submit();
		return false;

	});

	showRequestC = function(){
		$("#principaldiv").css("color","#000");
		$("#principaldiv").css("font-size","18px");
		$('#principaldiv').html("Buscando ubicaci&oacute;n...<br><br><br>");
	};

	showResponseC = function(){
		$("#principaldiv").css("font-size","12px");
	};
});
</script>
<section class="container">
<h1 class="item_name"><fmt:message key="ubicaciones.heading"/></h1>
<h3 class="item_name"><fmt:message key="ubicaciones.subtitle"/></h3>
<form:form method="post" id="frmConsultaF" name="frmConsultaF">
<section class="grupodedatos">
  <div class="bloquedata">
    <div class="form-group">
		<label for="camara">CAMARA</label>
		<select id="camara" name="camara" class="form-control" required>
			<option value="">Selecciona una opcion</option>
			<option value="%">Todas las Camaras</option>
			<!--option value="1">1</option>
			<option value="2">2</option-->

			<option value="0">0</option>
			<option value="1">1</option>
			<option value="2">2</option>
			<option value="3">3</option>
			<option value="4">4</option>
			<option value="5">5</option>
			<option value="6">6</option>
			<option value="7">7</option>
			<option value="8">8</option>
			<option value="9">9</option>
		</select>
  	</div>
	<div class="form-group">
		  <br/>
		  <button class="btn btn-primary btn-small buscaUbicacionDet" type="button">
			  <i class="icon-barcode bigger-125"></i>
			  Consulta Ubicaci&oacute;n
		  </button>
	</div>
  </div>
  <div class="bloquedata">
	<div class="form-group">
		<label>RD (RECIBO DE DEPOSITO)</label>
		<input type='text' placeholder="Recibo de Deposito" id="consecutivo" name='consecutivo' value='' class="form-control" required>
	</div>
	<div class="form-group">
		<br/>
		<button class="btn btn-primary btn-small buscaUbicacionVaciasDet" type="button">
			<i class="icon-barcode bigger-125"></i>
			Consulta Ubicaciones Vacias
		</button>
	</div>
  </div>
	<div class="bloquedata">
		<div class="form-group">
			<br/>
			<button class="btn btn-primary btn-small buscaUbicacionConsolidado" type="button">
				<i class="icon-barcode bigger-125"></i>
				Consolidado de Ubicaciones
			</button>
		</div>
	</div>

</section>
 <input type='hidden' name='fechaEntrada' value='${fecha}'>
 <input type='hidden' name='horaEntrada' value='${hora}'>
</form:form>
</section>
<br>
<div id="dialog-confirm"></div>
<div id="principaldiv"></div>
