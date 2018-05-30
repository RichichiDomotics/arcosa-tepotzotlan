<%@ include file="/WEB-INF/views/include.jsp" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<link rel="stylesheet" type="text/css" href="resources/bootstrap/css/entrada.css" />
<script>
	<!--
	$(document).ready(function(){
		$(".btnUpdatePosicion").click(function(){
			camara = $("#camara");
			puerta = $("#puerta");
			pasillo = $("#pasillo");
			filaCalle = $("#filaCalle");
			posicion = $("#posicion");
			nivel = $("#nivel");
			tipoTarima = $("#tipoTarima");
			consecutivo = $("#consecutivos");

			if(camara.val()==""){
			  alert("Favor de seleccionar una camara.");
			  camara.focus();
			  return false;
			}
			if(puerta.val()==""){
				alert("Favor de seleccionar una puerta.");
				puerta.focus();
				return false;
			}
			if(pasillo.val()==""){
				alert("Favor de seleccionar un pasillo.");
				pasillo.focus();
				return false;
			}
			if(filaCalle.val()==""){
				alert("Favor de ingresar una fila o calle.");
				filaCalle.focus();
				return false;
			}
			if(posicion.val()==""){
				alert("Favor de seleccionar una posicion.");
				posicion.focus();
				return false;
			}
			if(nivel.val()==""){
				alert("Favor de seleccionar un nivel.");
				nivel.focus();
				return false;
			}
			if(tipoTarima.val()==""){
				alert("Favor de seleccionar tamaño de tarima.");
				tipoTarima.focus();
				return false;
			}
			if(consecutivo.val()==""){
				alert("Favor de ingresar el RD.");
				consecutivo.focus();
				return false;
			}


			var options = {
				target:    '#tablaresultadodetalleUbica',   // target element(s) to be updated with server response
				url:       'updatePosicion',
				beforeSubmit:  showRequest,  // pre-submit callback
				success:       showResponse  // post-submit callback
			};

			// bind form using 'ajaxForm'
			$('#frmPosicion').ajaxForm(options);
			$('#frmPosicion').submit();
			return false;

		});

		showRequest = function(){
			$("#tablaresultadodetalleUbica").css("color","#000");
			$("#tablaresultadodetalleUbica").css("font-size","16px");
			$('#tablaresultadodetalleUbica').html('<div class="text-info">Actualizando ubicaci&oacute;n...</div>');
		};

		showResponse = function(){
			$("#tablaresultadodetalleUbica").css("font-size","16px");
			$('#tablaresultadodetalleUbica').html('<div class="text-info">Ubicacion Actualizada</div>');

		};

	});
	//-->
</script>
<section class="container">
	<h3 id="item_name">Ingresa los datos a modificar  <div id="tablaresultadodetalleUbica"></div></h3>
	<form:form method="post" id="frmPosicion" name="frmPosicion" commandName="formEntrada" role="form">
		<section class="grupodedatos">
			<div class="bloquedata">
				<div class="form-group">
					<label for="camara">CAMARA</label>
					<!--div class="text-info">${ubicacion.camara}</div-->
					<input type="text" value="${ubicacion.camara}" id="camara" name="camara">
					<input type="hidden" value="${ubicacion.idUbica}" id="idUbica" name="idUbica">
				</div>
				<div class="form-group">
					<label for="puerta">PUERTA</label>
					<!--div class="text-info">${ubicacion.puerta}</div-->
					<input type="text" value="${ubicacion.puerta}" id="puerta" name="puerta">
				</div>
				<div class="form-group">
					<label for="pasillo">PASILLO</label>
					<!--div class="text-info">${ubicacion.pasillo}</div-->
					<input type="text" value="${ubicacion.pasillo}" id="pasillo" name="pasillo">
				</div>
			</div>
			<div class="bloquedata">
				<div class="form-group">
					<label for="filaCalle">FILA O CALLE</label>
					<!--div class="text-info">${ubicacion.filaCalle}</div-->
					<input type="text" value="${ubicacion.filaCalle}" id="filaCalle" name="filaCalle">
				</div>
				<div class="form-group">
					<label for="posicion">POSICIONES</label>
					<!--div class="text-info">${ubicacion.posicion}</div-->
					<input type="text" value="${ubicacion.posicion}" id="posicion" name="posicion">
				</div>
				<div class="form-group">
					<label for="nivel">NIVEL</label>
						<!--div class="text-info">${ubicacion.nivel}</div-->
					<input type="text" value="${ubicacion.nivel}" id="nivel" name="nivel">
				</div>
			</div>

			<div class="bloquedata">
				<div class="form-group">
					<label for="tipoTarima">TAMA&Ntilde;O TARIMA</label>
					<input type="hidden" value="${ubicacion.tipoTarima}" id="tipoTarimaantes" name="tipoTarimaantes">
					<select id="tipoTarima" name="tipoTarima" value='' class="form-control" required>
						<option value="${ubicacion.tipoTarima}" > ${ubicacion.tipoTarima} </option>
						<option value="1.20"> <c:out value="1.20"/></option>
						<option value="1.50"> <c:out value="1.50"/></option>
					</select>
				</div>
				<div class="form-group">
					<div id="cboTipoProd">
						<label for="consecutivo">RD</label>
						<input type='text' placeholder="CONSECUTIVO" id="consecutivos" name='consecutivos' value='${ubicacion.consecutivo}' size="75" maxlength="255" class="form-control">
						<input type="hidden" value="${ubicacion.consecutivo}" id="consecutivo" name="consecutivo">
					</div>
				</div>
				<div class="form-group">
					<button class="btn btn-primary btn-small btnUpdatePosicion" type="submit">
						<i class="icon-edit  bigger-125"></i>
						Modifica Ubicacion
					</button>
				</div>
			</div>
		</section>
		<input type='hidden' name='fechaEntrada' value='${fecha}'>
		<input type='hidden' name='horaEntrada' value='${hora}'>
	</form:form>
</section>
<br>


