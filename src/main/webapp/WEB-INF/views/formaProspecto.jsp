<%@ include file="/WEB-INF/views/include.jsp" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<link rel="stylesheet" type="text/css" href="resources/bootstrap/css/entrada.css" />
<script type="text/javascript" src="resources/js/retemes.js"></script>

<input type='hidden' id="inicial" name='inicial' value='${inicial}' class="form-control">
<section class="container">
<h1 id="item_name"><fmt:message key="prospecto.inicio.heading"/></h1>
<section class="grupodedatosRetemes">
<div class="bloquedata">
    <div class="form-group">
   		<label for="razonSocial"><strong>Buscar</strong>(raz&oacute;n social, RFC, tel&eacute;fono, correo electr&oacute;nico)</label>
		<input type='text' placeholder="BUSCAR" id="razonSocial" name='razonSocial' value='' class="form-control" required>
  	</div>
</div> 	
<div class="bloquedata">
  	<div class="form-group">	
  		<br>
  		<button class="btn btn-primary btn-small buscaProspectobtn" type="button">
			<i class="icon-edit  bigger-125"></i>
			Busca Prospecto
		</button>

		<button class="btn btn-primary btn-small agregaProspectobtn" type="button">
			<i class="icon-edit  bigger-125"></i>
			Agregar Prospecto
		</button>
  	</div> 		
</div>
<br><br>
<div class="bloquedataResultado">	
	<div id="buscaProspectodiv"></div>
</div>
</section>
</section>
<script>
$(document).ready(function(){
	$(".agregaProspectobtn").click(function(){
		agregarProspeco();
		return false;
	});

	$(".buscaProspectobtn").click(function(){
		razonSocial = $("#razonSocial").val();
		if(razonSocial==""){
			buscaProspectot();
		}else{
			buscaProspectof(razonSocial);
		}

		return false;
	});

	buscaProspectof = function(razonSocial){
		$.ajax("consultaProspectos", {
			"type": "POST",
			"beforeSend": function() {
				$("#buscaProspectodiv").css("font-size","27px");
				$("#buscaProspectodiv").html('<img src="resources/img/loader.GIF"/>');
			},
			"success": function(result){
				$("#buscaProspectodiv").css("font-size","12px");
				$("#buscaProspectodiv").html(result);
				return false;
			},
			"error": function(result) {
				alert("Error al recuperar la información", result);
				//showAlert("Error al recuperar la información", result);
			},
			"data": { razonSocial: razonSocial },
			"async": true
		});
	}

	agregarProspeco = function(){
		$.ajax("ae_formaIngresoProspecto", {
			"type": "GET",
			"beforeSend": function() {
				$("#buscaProspectodiv").css("font-size","27px");
				$("#buscaProspectodiv").html('<img src="resources/img/loader.GIF"/>');
			},
			"success": function(result){
				$("#buscaProspectodiv").css("font-size","12px");
				$("#buscaProspectodiv").html(result);
				return false;
			},
			"error": function(result) {
				alert("Error al recuperar la información", result);
				//showAlert("Error al recuperar la información", result);
			},
			"data": {},
			"async": true
		});
	}

	if ($("#inicial").val() == "1") {
		buscaProspectot();
		$("#inicial").val("");
	}

	function buscaProspectot() {
		$.ajax("consultaProspectosTodos", {
			"type": "POST",
			"beforeSend": function () {
				$("#buscaProspectodiv").css("font-size", "27px");
				$("#buscaProspectodiv").html('<img src="resources/img/loader.GIF"/>');
			},
			"success": function (result) {
				$("#buscaProspectodiv").css("font-size", "14px");
				$("#buscaProspectodiv").html(result);
				return false;
			},
			"error": function (result) {
				alert("Error al recuperar la información", result);
				//showAlert("Error al recuperar la información", result);
			},
			"data": {},
			"async": true
		});
	}

});
</script>