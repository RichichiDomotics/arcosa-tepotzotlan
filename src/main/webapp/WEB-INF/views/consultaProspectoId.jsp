<%@ include file="/WEB-INF/views/include.jsp" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<link rel="stylesheet" type="text/css" href="resources/bootstrap/css/clientesCaptura.css" />
<head>
  <style>
    .error { color: red; }
  </style>  
</head>
<script type="text/javascript">
<!--
$(document).ready(function(){
	/*$(function() {
	   $("#tabs").tabs();
	});*/
	
	$(".btnregistraservicios").click(function(){
		if($("#serviciosAdicionales").val()==""){
			alert("Agrega servicios Seleccionando uno.");
			return false;
		}else{
			$.ajax("consultaSalida", {
		 		  "type": "POST", //usualmente post o get
		 		  "beforeSend": function() {
		 			   $("#tablaServicios").css("color","#fff");
		 			   $("#tablaServicios").css("font-size","45px");
		 			   $("#tablaServicios").html('Cargando ...');
		 		   },
		 		  "success": function(result) {
		 		       $("#tablaServicios").css("font-size","0.875em");
					   $("#tablaServicios").html(result);
				   },
		 		   "error": function(result) {
		 		     alert("Error al recuperar la informaci�n", result);
		 		     //showAlert("Error al recuperar la informaci�n", result);
		 		   },
		 		   "data": { numeroRd: numeroRd,idCliente : idCliente, producto : producto, caducidad : caducidad},
		 		   "async": true
		 		});
			
		}
		return false;
		
	});

	$(".btnregistradatos").click(function(){
		var idEjecutivoSel = $("#idEjecutivo option:selected").text();
		$("#nomEjecutivo").val(idEjecutivoSel);
		var options = {
			target:    '#buscaProspectodiv',   // target element(s) to be updated with server response
			url:       'ae_insertarCliente',
			beforeSubmit:  showRequest,  // pre-submit callback
			success:       showResponse  // post-submit callback
		};

		// bind form using 'ajaxForm'
		$('#Cliente').ajaxForm(options);
		$('#Cliente').submit();
		return false;
	});

	showRequest = function(){
		$("#buscaProspectodiv").css("color","#000");
		$("#buscaProspectodiv").css("font-size","35px");
		$('#buscaProspectodiv').html("Registrando Cliente...<br><br><br>");
	};

	showResponse = function(){
		$("#buscaProspectodiv").css("font-size","14px");
	};

});
//-->
</script>
<div id="capturaClienteFrm">
<h1><fmt:message key="ingresa.clientes.heading"/></h1>
<!-- h3><fmt:message key="consultas.clientes.subtitle"/></h3-->
<form:form method="post" commandName="Cliente" role="form" action="ae_insertarCliente">
	<input type='hidden' placeholder="idProspecto" id="idProspecto" name='idProspecto' value='${idProspecto}' >
	<input type='hidden' placeholder="estatus" id="estatus" name='estatus' value='ACTIVO' >
<div class="row-fluid">
	<div class="span6">
		<h3 class="header smaller lighter blue">INGRESA CLIENTE</h3>
		<div id="accordion2" class="accordion">
			<div class="accordion-group">
				<div class="accordion-heading">
					<a href="#collapseOne" data-parent="#accordion2" data-toggle="collapse" class="accordion-toggle collapsed">
						Datos del Cliente
					</a>
				</div>
				<div class="accordion-body collapse" id="collapseOne">
					<div class="accordion-inner">
						<section class="grupodedatos">
  <div class="bloquedata">
  	<div class="form-group">
    	<label for="nombreCliente">RAZ&Oacute;N SOCIAL</label>
   		<input type='text' placeholder="RAZ&Oacute;N SOCIAL" id="nombreCliente" name='nombreCliente' value='${prospecto.getRazonSocial()}' size="75" maxlength="60" class="form-control" required>
  	</div>
  	<div class="form-group">
    	<label for="rfc">RFC</label>
   		<input type='text' placeholder="RFC" id="rfc" name='rfc' value='${prospecto.rfc}' size="75" maxlength="60" class="form-control" required>
  	</div>
  	<div class="form-group">
    	<label for="calle">CALLE NO. INT Y NO. EXT</label>
    	<input type='text' placeholder="CALLE NO. INT Y NO. EXT" id="calle" name='calle' value='${prospecto.callenum}' size="75" maxlength="255" class="form-control" required>
  	</div>
  	<div class="form-group">
   	    <label for="colonia">COLONIA</label>
    	<input type='text' placeholder="COLONIA" id="colonia" name='colonia' value='${prospecto.colonia}' size="75" maxlength="255" class="form-control" required>
  	</div>
  	<div class="form-group">
   	    <label for="ciudad">CIUDAD</label>
    	<input type='text' placeholder="CIUDAD" id="ciudad" name='ciudad' value='${prospecto.ciudad}' size="75" maxlength="255" class="form-control" required>
  	</div>
  	
  </div>
  <div class="bloquedata">
  	<div class="form-group">
   	    <label for="idEstado">ESTADO</label>
    	<input type='text' placeholder="ESTADO" id="idEstado" name='idEstado' value='${prospecto.estado}' size="75" maxlength="255" class="form-control" required>
  	</div>
  	<div class="form-group">
   	    <label for="cp">CP</label>
    	<input type='text' placeholder="CP" id="cp" name='cp' value='${prospecto.cp}' size="75" maxlength="5" class="form-control">
  	</div>
  	<div class="form-group">
    	<label for="email">EMAIL</label>
   		<input type='text' placeholder="EMAIL" id="email" name='email' value='${prospecto.email}' size="75" maxlength="255" class="form-control" required>
  	</div>
  	<div class="form-group">
    	<label for="telefono1">TELEFONO</label>
   		<input type='text' placeholder="TELEFONO" id="telefono" name='telefono' value='${prospecto.telefono}' size="75" maxlength="15" class="form-control" required>
  	</div>
  	<div class="form-group">
    	<label for="extension">EXTENSION</label>
   		<input type='text' placeholder="extension" id="extension" name='extension' value='${prospecto.extension}' size="75" maxlength="15" class="form-control">
  	</div>
  </div>
  <div class="bloquedata">
    <div class="form-group">
    	<label for="contacto">CONTACTO</label>
   		<input type='text' placeholder="CONTACTO 1" id="contacto" name='contacto' value='${prospecto.contacto1}' size="75" maxlength="160" class="form-control" required>
  	</div>
  	<div class="form-group">
    	<label for="contactoPagoCargo">CARGO</label>
   		<input type='text' placeholder="CARGO 1" id="contactoPagoCargo" name='contactoPagoCargo' value='${prospecto.cargo1}' size="275" maxlength="160" class="form-control" required>
  	</div>
  	<div class="form-group">
    	<label for="telefono1">TELEFONO</label>
    	<input type='text' placeholder="TELEFONO 1" id="telefono1" name='telefono1' value='${prospecto.telefono1}' class="form-control" maxlength="12" required>
  	</div>
	  <div class="form-group">
		  <label for="idEjecutivo">EJECUTIVO</label>
		  <select id="idEjecutivo" name='idEjecutivo' required>
			  <c:forEach items="${ejecutivos}" var="e">
				  <option value="${e.nombreEjecutivo}">${e.nombreEjecutivo}</option>
			  </c:forEach>
		  </select>
		  <input type='hidden' placeholder="" id="ejecutivo" name='ejecutivo' value='' >
	  </div>
</section>
					</div>
				</div>
				<!-- ************************************************** -->
				<div class="accordion-heading">
					<a href="#collapseTwo" data-parent="#accordion2" data-toggle="collapse" class="accordion-toggle collapsed">
						Entrega y Cobro
					</a>
				</div>
				<div class="accordion-body collapse" id="collapseTwo">
					<div class="accordion-inner">
						 <section class="grupodedatos">
  <div class="bloquedata">
  	<div class="form-group">
    	<label for="facturacion">FACTURACI&Oacute;N</label>
   		<select id="facturacion" name="facturacion" class="form-control" required="">
    		<option value="">SELECCIONA UNA OPCI&Oacute;N</option>
        	<option value="SEMANAL">SEMANAL</option>
        	<option value="QUINCENAL">QUINCENAL</option>
        	<option value="MENSUAL">MENSUAL</option>
        	<!--option value="OTRA">OTRA</option-->
    	</select>
  	</div>
  	<div class="form-group">
    	<label for="diasCredito">DIAS DE CREDITO</label>
   		<input type='text' placeholder="DIAS DE CREDITO" id="diasCredito" name='diasCredito' value='' size="75" maxlength="3" class="form-control" required>
  	</div>
	  <div class="form-group">
		  <label for="horario">HORARIO DE ATENCI&Oacute;N</label>
		  <input type='text' placeholder="HORARIO DE ATENCI&Oacute;N" id="horario" name='horario' value='' size="75" maxlength="255" class="form-control" required>
	  </div>
  	<!--div class="form-group">
    	<label for="email">EMAIL PAGOS</label>
   		<input type='text' placeholder="EMAIL PAGOS" id="email" name='email' value='' size="75" maxlength="255" class="form-control" required>
  	</div-->
  </div>
  <div class="bloquedata">
  	<div class="form-group">
   	    <label for="contactoPagoNombre">CONTACTO PAGOS</label>
    	<input type='text' placeholder="CONTACTO PAGOS" id="contactoPagoNombre" name='contactoPagoNombre' value='' size="75" maxlength="255" class="form-control" required>
  	</div>
  	<div class="form-group">
   	    <label for="contactoPagoTelefono">TELEFONO CONTACTO PAGOS</label>
    	<input type='text' placeholder="TELEFONO CONTACTO PAGOS" id="contactoPagoTelefono" name='contactoPagoTelefono' value='' size="75" maxlength="255" class="form-control" required>
  	</div>

  </div>
  <div class="bloquedata">
    <div class="form-group">
    	<label for="diasRevision">D&Iacute;AS DE REVISI&Oacute;N DE FACTURAS</label>
    	<select id="diasRevision" name="diasRevision" class="form-control" required>
    		<option value="">SELECCIONA UNA OPCI&Oacute;N</option>
        	<option value="LUNES">LUNES</option>
        	<option value="MARTES">MARTES</option>
        	<option value="MIERCOLES">MIERCOLES</option>
        	<option value="JUEVES">JUEVES</option>
        	<option value="VIERNES">VIERNES</option>
    	</select>
  	</div>
  	<div class="form-group">
    	<label for="diasPago">D&Iacute;AS DE PAGO DE FACTURAS</label>
   		<select id="diasPago" name="diasPago" class="form-control" required>
    		<option value="">SELECCIONA UNA OPCI&Oacute;N</option>
        	<option value="LUNES">LUNES</option>
        	<option value="MARTES">MARTES</option>
        	<option value="MIERCOLES">MIERCOLES</option>
        	<option value="JUEVES">JUEVES</option>
        	<option value="VIERNES">VIERNES</option>
    	</select>
  	</div>
  </div>
  <div class="bloquedata">
  </div>	
</section>
					</div>
				</div>

<!-- ************************************************** -->
				<div class="accordion-heading">
					<a href="#collapseTree" data-parent="#accordion2" data-toggle="collapse" class="accordion-toggle collapsed">
						Convenios y Mercancia
					</a>
				</div>
				<div class="accordion-body collapse" id="collapseTree">
					<div class="accordion-inner">
						 <section class="grupodedatos">
  <div class="bloquedata">
  	<div class="form-group">
    	<label for="claveProducto">CLAVE DE PRODUCTO</label>
   		<input type='text' placeholder="CLAVE DE PRODUCTO" id="claveProducto" name='claveProducto' value='' size="75" maxlength="255" class="form-control" required>
  	</div>
  	<div class="form-group">
    	<label for="congelacion">TEMPERATURA REQUERIDA</label>
   		<input type='text' placeholder="TEMPERATURA REQUERIDA" id="congelacion" name='congelacion' value='' size="75" maxlength="255" class="form-control" required>
  	</div>
  </div>
  <div class="bloquedata">
  	<div class="form-group">
    	<label for="minimo">MINIMO GLOBAL MENSUAL</label>
   		<input type='text' placeholder="MINIMO GLOBAL MENSUAL" id="minimo" name='minimo' value='' size="75" maxlength="255" class="form-control" required>
  	</div>
	<div class="form-group">
	    <label for="cuotaAlmacenaje">CUOTA ALMACENAJE</label>
	    <input type='text' placeholder="CUOTA ALMACENAJE" id="cuotaAlmacenaje" name='cuotaAlmacenaje' value='' size="75" maxlength="255" class="form-control" required>
	</div>
  </div>

  <div class="bloquedata">
    <div class="form-group">
   	    <label for="detalleCuota">DETALLE DE LA CUOTA</label>
    	<input type='text' placeholder="DETALLE DE LA CUOTA" id="detalleCuota" name='detalleCuota' value='' size="75" maxlength="255" class="form-control" required>
  	</div>
	<div class="form-group">
	    <label for="periodo">PERIODO DE LA CUOTA</label>
		<input type='text' placeholder="PERIODO DE LA CUOTA" id="periodo" name='periodo' value='' size="75" maxlength="255" class="form-control" required>
	</div>
  </div>
  <div class="bloquedata">
    <div class="form-group">
   	    <label for="mentysal">MEYS</label>
    	<input type='text' placeholder="MEYS" id="mentysal" name='mentysal' value='' size="75" maxlength="255" class="form-control" required>
  	</div>
  	<div class="form-group">
   	    <label for="minimoPartidaCongelacionRapida">MIN. PARTIDA CONGELACI&Oacute;N RAPIDA</label>
    	<input type='text' placeholder="MIN. PARTIDA CONGELACI&Oacute;N RAPIDA" id="partidaMinima" name='partidaMinima' value='' size="75" maxlength="255" class="form-control" required>
  	</div>
  </div>	
</section>
</div>
</div>
         </div>
       </div>
	</div>
  </div>
<div class="form-group">
    	<button class="btn btn-primary btn-small btnregistradatos" type="button">
			<i class="icon-edit  bigger-125"></i>
				Registrar
		</button> 
    </div>
    </div>
 </form:form>
 </div>
 <div id="tablaServicios"></div>
