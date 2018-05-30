<%@ include file="/WEB-INF/views/include.jsp" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<link rel="stylesheet" type="text/css" href="resources/bootstrap/css/clientesCaptura.css" />
<html>
<head>
  <title><fmt:message key="title"/>ingresa.clientes.heading</title>
  <style>
    .error { color: red; }
  </style>  
</head>
<script type="text/javascript">
<!--
$(document).ready(function(){
	$(function() {
	   $("#tabs").tabs();
	});
	
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
		 		     alert("Error al recuperar la información", result);
		 		     //showAlert("Error al recuperar la informaci�n", result);
		 		   },
		 		   "data": { numeroRd: numeroRd,idCliente : idCliente, producto : producto, caducidad : caducidad},
		 		   "async": true
		 		});
			
		}
		return false;
		
	});
	
	
});
//-->
</script>
<div id="capturaClienteFrm">
<h1><fmt:message key="ingresa.clientes.heading"/></h1>
<!-- h3><fmt:message key="consultas.clientes.subtitle"/></h3-->
<form:form method="post" commandName="Cliente" role="form" action="ae_insertarCliente">

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
    	<label for="razonSocial">RAZ&Oacute;N SOCIAL</label>
   		<input type='text' placeholder="RAZ&Oacute;N SOCIAL" id="razonSocial" name='razonSocial' value='' size="75" maxlength="60" class="form-control" required>
  	</div>
  	<div class="form-group">
    	<label for="rfc">RFC</label>
   		<input type='text' placeholder="RFC" id="rfc" name='rfc' value='' size="75" maxlength="60" class="form-control" required>
  	</div>
  	<div class="form-group">
    	<label for="callenum">CALLE NO. INT Y NO. EXT</label>
    	<input type='text' placeholder="CALLE NO. INT Y NO. EXT" id="callenum" name='callenum' value='' size="75" maxlength="255" class="form-control" required>
  	</div>
  	<div class="form-group">
   	    <label for="colonia">COLONIA</label>
    	<input type='text' placeholder="COLONIA" id="colonia" name='colonia' value='' size="75" maxlength="255" class="form-control" required>
  	</div>
  	<div class="form-group">
   	    <label for="ciudad">CIUDAD</label>
    	<input type='text' placeholder="CIUDAD" id="ciudad" name='ciudad' value='' size="75" maxlength="255" class="form-control" required>
  	</div>
  	
  </div>
  <div class="bloquedata">
  	<div class="form-group">
   	    <label for="estado">ESTADO</label>
    	<input type='text' placeholder="ESTADO" id="estado" name='estado' value='' size="75" maxlength="255" class="form-control" required>
  	</div>
  	<div class="form-group">
   	    <label for="cp">CP</label>
    	<input type='text' placeholder="CP" id="cp" name='cp' value='' size="75" maxlength="5" class="form-control">
  	</div>
  	<div class="form-group">
    	<label for="email">EMAIL</label>
   		<input type='text' placeholder="EMAIL" id="email" name='email' value='' size="75" maxlength="255" class="form-control" required>
  	</div>
  	<div class="form-group">
    	<label for="telefono">TELEFONO</label>
   		<input type='text' placeholder="TELEFONO" id="telefono" name='telefono' value='' size="75" maxlength="15" class="form-control" required>
  	</div>
  	<div class="form-group">
    	<label for="extension">EXTENSION</label>
   		<input type='text' placeholder="EXTENSION" id="extension" name='extension' value='' size="75" maxlength="15" class="form-control">
  	</div>
  </div>
  <div class="bloquedata">
    <div class="form-group">
    	<label for="contacto1">CONTACTO 1</label>
   		<input type='text' placeholder="CONTACTO 1" id="contacto1" name='contacto1' value='' size="75" maxlength="160" class="form-control" required>
  	</div>
  	<div class="form-group">
    	<label for="cargo1">CARGO 1</label>
   		<input type='text' placeholder="CARGO 1" id="cargo1" name='cargo1' value='' size="275" maxlength="160" class="form-control" required>
  	</div>
  	<div class="form-group">
    	<label for="telefono1">TELEFONO 1</label>
    	<input type='text' placeholder="TELEFONO 1" id="telefono1" name='telefono1' value='' class="form-control" maxlength="12" required>
  	</div>
  	<div class="form-group">
    	<label for="celular1">CELULAR 1</label>
    	<input type='text' placeholder="CELULAR 1" id="celular1" name='celular1' value='' class="form-control" maxlength="12" required>
  	</div>
	<div class="form-group">
		  <label for="idEjecutivo">EJECUTIVO</label>
		  <select id="idEjecutivo" name='idEjecutivo' required>
			  <option value="ALOPEZ">ABRAHAM LOPEZ</option>
			  <option value="APAEZ">ANTONIO PAEZ</option>
			  <option value="GSANTAMARIA">GERMAN SANTAMARIA</option>
			  <option value="GSERVIN">GERARDO SERVIN</option>
			  <option value="JCHAOWAH">JAIME CHAOWAH</option>
			  <option value="JMOSQUEDA">JUAN MOSQUEDA</option>
			  <option value="MCGUTIERREZ">CARMEN GUTIERREZ</option>
			  <option value="MNEGRETE">MANUEL NEGRETE</option>
			  <option value="RMONTANO">RICARDO MONTANO</option>
			  <option value="SALATORRE">SANTOS ALATORRE</option>
		  </select>
		  <input type='hidden' placeholder="" id="ejecutivo" name='ejecutivo' value='' >
	</div>
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
   		<select id="facturacion" name="facturacion" class="form-control">
    		<option value="">SELECCIONA UNA OPCI&Oacute;N</option>
        	<option value="SEMANAL">SEMANAL</option>
        	<option value="QUINCENAL">QUINCENAL</option>
        	<option value="MENSUAL">MENSUAL</option>
        	<!--option value="OTRA">OTRA</option-->
    	</select>
  	</div>
  	<div class="form-group">
    	<label for="diasCredito">DIAS DE CREDITO</label>
   		<input type='text' placeholder="DIAS DE CREDITO" id="diasCredito" name='diasCredito' value='' size="75" maxlength="3" class="form-control">
  	</div>
  	<div class="form-group">
    	<label for="email">EMAIL PAGOS</label>
   		<input type='text' placeholder="EMAIL PAGOS" id="email" name='email' value='' size="75" maxlength="255" class="form-control">
  	</div>
  </div>
  <div class="bloquedata">
  	<div class="form-group">
   	    <label for="contactoPagoNombre">CONTACTO PAGOS</label>
    	<input type='text' placeholder="CONTACTO PAGOS" id="contactoPagoNombre" name='contactoPagoNombre' value='' size="75" maxlength="255" class="form-control">
  	</div>
  	<div class="form-group">
   	    <label for="contactoPagoTelefono">TELEFONO CONTACTO PAGOS</label>
    	<input type='text' placeholder="TELEFONO CONTACTO PAGOS" id="contactoPagoTelefono" name='contactoPagoTelefono' value='' size="75" maxlength="255" class="form-control">
  	</div>
  	<div class="form-group">
   	    <label for="horario">HORARIO DE ATENCI&Oacute;N</label>
    	<input type='text' placeholder="HORARIO DE ATENCI&Oacute;N" id="horario" name='horario' value='' size="75" maxlength="255" class="form-control">
  	</div>
  </div>
  <div class="bloquedata">
    <div class="form-group">
    	<label for="diasRevision">D&Iacute;AS DE REVISI&Oacute;N DE FACTURAS</label>
    	<select id="diasRevision" name="diasRevision" class="form-control">
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
   		<select id="diasPago" name="diasPago" class="form-control">
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
    	<label for="producto">TIPO DE PRODUCTO</label>
   		<input type='text' placeholder="TIPO DE PRODUCTO" id="producto" name='producto' value='' size="75" maxlength="255" class="form-control">
  	</div>
  	<div class="form-group">
    	<label for="temperaturaRequerida">TEMPERATURA REQUERIDA</label>
   		<input type='text' placeholder="TEMPERATURA REQUERIDA" id="temperaturaRequerida" name='cuotaAlmacenaje' value='' size="75" maxlength="255" class="form-control">
  	</div>
  	<div class="form-group">
    	<label for="cuotaAlmacenaje">CUOTA ALMACENAJE</label>
   		<input type='text' placeholder="CUOTA ALMACENAJE" id="cuotaAlmacenaje" name='cuotaAlmacenaje' value='' size="75" maxlength="255" class="form-control">
  	</div>
  </div>
  <div class="bloquedata">
  	<div class="form-group">
    	<label for="minimo">MINIMO GLOBAL MENSUAL</label>
   		<input type='text' placeholder="MINIMO GLOBAL MENSUAL" id="minimo" name='minimo' value='' size="75" maxlength="255" class="form-control">
  	</div>
  	<div class="form-group">
   	    <label for="detalleMinimoGlobalMensual">DETALLE MIN. GLOBAL MENSUAL</label>
    	<input type='text' placeholder="DETALLE DEL MINIMO GLOBAL MENSUAL" id="detalleMinimoGlobalMensual" name='detalleMinimoGlobalMensual' value='' size="75" maxlength="255" class="form-control">
  	</div>
  	<div class="form-group">
   	    <label for="periodo">PERIODO DE LA CUOTA</label>
    	<input type='text' placeholder="PERIODO DE LA CUOTA" id="periodo" name='periodo' value='' size="75" maxlength="255" class="form-control">
  	</div>
  </div>
  <div class="bloquedata">
    <div class="form-group">
   	    <label for="detalleCuota">DETALLE DE LA CUOTA</label>
    	<input type='text' placeholder="DETALLE DE LA CUOTA" id="detalleCuota" name='detalleCuota' value='' size="75" maxlength="255" class="form-control">
  	</div>
    <div class="form-group">
   	    <label for="aplicaKilogramo">APLICA A KILOGRAMO</label>
    	<input type='text' placeholder="APLICA A KILOGRAMO" id="aplicaKilogramo" name='aplicaKilogramo' value='' size="75" maxlength="255" class="form-control">
  	</div>
  	<div class="form-group">
   	    <label for="valorMercancia">VALOR DE LA MERCANCIA</label>
    	<input type='text' placeholder="VALOR DE LA MERCANCIA" id="valorMercancia" name='valorMercancia' value='' size="75" maxlength="255" class="form-control">
  	</div>
  </div>
  <div class="bloquedata">
    <div class="form-group">
   	    <label for="meys">MEYS</label>
    	<input type='text' placeholder="MEYS" id="meys" name='meys' value='' size="75" maxlength="255" class="form-control">
  	</div>
    <div class="form-group">
   	    <label for="congelacionRapida">CONGELACI&Oacute;N RAPIDA</label>
    	<input type='text' placeholder="CONGELACI&Oacute;N RAPIDA" id="congelacionRapida" name='congelacionRapida' value='' size="75" maxlength="255" class="form-control">
  	</div>
  	<div class="form-group">
   	    <label for="minimoPartidaCongelacionRapida">MIN. PARTIDA CONGELACI&Oacute;N RAPIDA</label>
    	<input type='text' placeholder="MIN. PARTIDA CONGELACI&Oacute;N RAPIDA" id="minimoPartidaCongelacionRapida" name='minimoPartidaCongelacionRapida' value='' size="75" maxlength="255" class="form-control">
  	</div>
  </div>	
</section>
					</div>
				</div>				

<!-- ************************************************** -->
				<div class="accordion-heading">
					<a href="#collapseFour" data-parent="#accordion2" data-toggle="collapse" class="accordion-toggle collapsed">
						Servicios Adicionales
					</a>
				</div>
				<div class="accordion-body collapse" id="collapseFour">
					<div class="accordion-inner">
<form:form id="frmClienteServicios" name="frmClienteServicios" method="post" commandName="ClienteServicios" role="form" action="ae_insertarClienteServicios">					
			<section class="grupodedatos">
  <div class="bloquedata">
  	<div class="form-group">
		<label for="serviciosAdicionales">SERVICIOS ADICIONALES</label>
    	<select id="serviciosAdicionales" name="serviciosAdicionales" class="form-control">
    		<option value="" >SELECCIONA UN SERVICIO</option>
        		<c:forEach items="${servicios}" var="servicio">
      				<option value="${servicio.idCatalogo}"> <c:out value="${servicio.descripcion}"/></option>
    			</c:forEach>
    	</select>
  	</div>
  	<div class="form-group">
  		<button class="btn btn-primary btn-small btnregistraservicios" type="button" frmname="frmProspecto" urlenvio="ae_insertarProspecto">
			<i class="icon-edit  bigger-125"></i>
				Agregar Servicio
		</button>
  	</div>
  </div>	
</section>	
</form:form>		 
					</div>
				</div>
				
				
			</div>
		</div>
	</div><!--/span-->
</div><!--/row-->
<div class="form-group">
    	<button class="btn btn-primary btn-small btnregistradatos" type="button" frmname="frmProspecto" urlenvio="ae_insertarProspecto">
			<i class="icon-edit  bigger-125"></i>
				Registrar
		</button> 
    </div>
    </div>
 </form:form>
 </div>
 <div id="tablaServicios"></div>
