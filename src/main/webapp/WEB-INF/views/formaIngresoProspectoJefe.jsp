<%@ include file="/WEB-INF/views/include.jsp" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<link rel="stylesheet" type="text/css" href="resources/bootstrap/css/clientesCaptura.css" />
<html>
<head>
  <title><fmt:message key="title"/></title>
  <style>
    .error { color: red; }
	.bloqueforma{
		display:block;
		width: 300px;
		float: left;;
	}
  </style>  
</head>
<script type="text/javascript">
<!--
$(document).ready(function(){
	$(".otroRegistro").hide();

	$(".otroRegistro").click(function(){
		$("#capturaClienteFrm").toggle();
		$('#frmProspecto').each (function(){
			  this.reset();
			});
		$("#registroProspecto").html("");
		$(".otroRegistro").toggle();
	});

	function buscaProspectoByNombre(nombre) {
		$.ajax("consultaExistentesByNombre", {
			"type": "POST",
			"beforeSend": function () {
				$("#existentes").css("font-size", "27px");
				$("#existentes").html('<img src="resources/img/loader.GIF"/>');
			},
			"success": function (result) {
				$("#existentes").css("font-size", "12px");
				$("#existentes").html(result);
				return false;
			},
			"error": function (result) {
				alert("Error al recuperar la información", result);
				//showAlert("Error al recuperar la información", result);
			},
			"data": {nombre:nombre},
			"async": false
		});
	}

	//$(".registra").click(function(){
	continuaRegistro = function(urlenvio,frmname){
		var idEjecutivoSel = $("#idEjecutivo option:selected").text();
		var idEjecutivo=$("#idEjecutivo").val();
		$("#ejecutivo").val(idEjecutivoSel);
		$("#registroProspecto").html("");
		$(".otroRegistro").show();
		   var options = {
			        target:    '#buscaProspectodiv',   // target element(s) to be updated with server response
			        url:       urlenvio,
			   		data:  {idEjecutivo:idEjecutivo},
			        beforeSubmit:  showRequest,  // pre-submit callback
			        success:       showResponse  // post-submit callback
			    };

			    // bind form using 'ajaxForm'
			    form = $("#frmProspecto");
			    form.validate();
				if(form.valid()){
					$("#frmProspecto").ajaxForm(options);
					$("#frmProspecto").submit();
					$("#capturaClienteFrm").toggle();
					return false;
				}

	   }

	   showRequest = function() {

	   };

	showResponse = function(responseText, statusText, xhr, $form){
		   $(window).scrollTop(0);
		   $("#buscaProspectodiv").html(responseText);
	   };


	$(".registraValida").click(function(){
		form = $("#frmProspecto");
		form.validate();
		if(form.valid()) {
			nombre = $(".razonSocial").val();
			buscaProspectoByNombre(nombre);

			if ($("#existentes").html().trim() != "0") {
				bootbox.dialog($("#existentes").html(), [{
							"label": "Continuar el registro!",
							"class": "btn-small btn-success",
							"callback": function () {
								urlenvio = $(".registraValida").attr("urlenvio");
								frmname = $(".registraValida").attr("frmname");
								continuaRegistro(urlenvio, frmname);
							}
						}, {
							"label": "Cancelar el Registro!",
							"class": "btn-small btn-danger",
							"callback": function () {
								//$("#buscaProspectodiv").html("<h3>No se realizo ninun registro.</h3>");
								$("#existentes").html("");
								bootbox.hideAll();
							}
						}]
				);
			} else {
				urlenvio = $(".registraValida").attr("urlenvio");
				frmname = $(".registraValida").attr("frmname");
				continuaRegistro(urlenvio, frmname);
			}
		}
	});

});
//-->
</script>
<body>
<div id="capturaClienteFrm">
	<h1><fmt:message key="ingresa.prospecto_clientes.heading"/></h1>
	<!-- h3><fmt:message key="consultas.clientes.subtitle"/></h3-->
	<form:form name="frmProspecto" id="frmProspecto" method="post" commandName="Prospecto" role="form" action="ae_insertarProspecto">
	<!--/*inicio de acordion***************************************************************************************/-->
	<div class="row-fluid">
		<div class="span6">
			<h3 class="header smaller lighter blue">INGRESA PROSPECTO</h3>

			<div class="tabbable">
				<ul class="nav nav-tabs" id="myTab">
					<li class="active">
						<a href="#forma-ingreso"  data-toggle="tab">
							Datos del Cliente
						</a>
					</li>
				</ul>

				<div class="tab-content">
					<div id="forma-ingreso" class="tab-pane in active">
						<div class="bloqueforma">
							<div class="form-group">
								<label for="razonSocial">RAZ&Oacute;N SOCIAL *</label>
								<input type='text' placeholder="RAZ&Oacute;N SOCIAL" id="razonSocial" name='razonSocial' size="75" maxlength="60" class="form-control razonSocial" required>
							</div>
							<div class="form-group">
								<label for="rfc">RFC</label>
								<input type='text' placeholder="RFC" id="rfc" name='rfc' value='' size="75" maxlength="60" class="form-control">
							</div>
							<div class="form-group">
								<label for="callenum">CALLE NO. INT Y NO. EXT *</label>
								<input type='text' placeholder="CALLE NO. INT Y NO. EXT" id="callenum" name='callenum' value='' size="75" maxlength="255" class="form-control" required>
							</div>
							<div class="form-group">
								<label for="colonia">COLONIA *</label>
								<input type='text' placeholder="COLONIA" id="colonia" name='colonia' value='' size="75" maxlength="255" class="form-control" required>
							</div>
							<div class="form-group">
								<label for="ciudad">CIUDAD *</label>
								<input type='text' placeholder="CIUDAD" id="ciudad" name='ciudad' value='' size="75" maxlength="255" class="form-control" required>
							</div>
							<div class="form-group">
								<button class="btn btn-primary btn-small registraValida" type="button" frmname="frmProspecto" urlenvio="ae_insertarProspecto">
									<i class="icon-edit  bigger-125"></i>
									Registrar
								</button>
							</div>
						</div>


						<div class="bloqueforma">
							<div class="form-group">
								<label for="estado">ESTADO</label>
								<input type='text' placeholder="ESTADO" id="estado" name='estado' value='' size="75" maxlength="255" class="form-control">
							</div>
							<div class="form-group">
								<label for="cp">CP</label>
								<input type='text' placeholder="CP" id="cp" name='cp' value='' size="75" maxlength="5" class="form-control">
							</div>
							<div class="form-group">
								<label for="email">EMAIL *</label>
								<input type='text' placeholder="EMAIL" id="email" name='email' value='' size="75" maxlength="255" class="form-control" required>
							</div>
							<div class="form-group">
								<label for="telefono">TELEFONO *</label>
								<input type='text' placeholder="TELEFONO" id="telefono" name='telefono' value='' size="75" maxlength="15" class="form-control" required>
							</div>
							<div class="form-group">
								<label for="extension">EXTENSION</label>
								<input type='text' placeholder="EXTENSION" id="extension" name='extension' value='' size="75" maxlength="15" class="form-control">
							</div>
							<div class="form-group">
								<label for="extension">DIVISION DE NEGOCIO *</label>
								<input type='text' placeholder="DIVISION DE NEGOCIO" id="divisionNegocio" name='divisionNegocio' value='' size="75" maxlength="255" class="form-control" required>
							</div>
						</div>
						<div class="bloqueforma">
							<div class="form-group">
								<label for="contacto1">CONTACTO *</label>
								<input type='text' placeholder="CONTACTO" id="contacto1" name='contacto1' value='' size="75" maxlength="160" class="form-control" required>
							</div>
							<div class="form-group">
								<label for="cargo1">CARGO</label>
								<input type='text' placeholder="CARGO" id="cargo1" name='cargo1' value='' size="275" maxlength="160" class="form-control">
							</div>
							<div class="form-group">
								<label for="telefono1">TELEFONO *</label>
								<input type='text' placeholder="TELEFONO" id="telefono1" name='telefono1' value='' class="form-control" maxlength="12" required>
							</div>
							<div class="form-group">
								<label for="celular1">CELULAR</label>
								<input type='text' placeholder="CELULAR" id="celular1" name='celular1' value='' class="form-control" maxlength="12">
								<input type='hidden' placeholder="" id="activo" name='activo' value='0' >
							</div>
							<div class="form-group">
								<label for="producto">PRODUCTO *</label>
								<input type='text' placeholder="PRODUCTO" id="producto" name='producto' value='' size="75" maxlength="255" class="form-control" required>
							</div>
							<div class="form-group">
								<label for="idEjecutivo">EJECUTIVO *</label>
								<select id="idEjecutivo" name='idEjecutivo' required>
									<c:forEach items="${ejecutivos}" var="e">
										<option value="${e.id}">${e.nombreEjecutivo}</option>
									</c:forEach>
								</select>
								<input type='hidden' placeholder="" id="ejecutivo" name='ejecutivo' value='' >
							</div>
						</div>
					</div>
				</div>




			</div><!--/span-->
		</div><!--/row-->
	</div>
	</form:form>
	<!-- /*fin de acordion***************************************************************************************/-->
	<div id="existentes" class="existentes"></div>
	<a class="btn btn-primary btn-small otroRegistro" href="#" >
		Otro Registro
		<i class="icon-exchange"></i>
	</a>
	<div id="registroProspecto" class="registroProspecto"></div>