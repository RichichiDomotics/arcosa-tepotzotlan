<%@ include file="/WEB-INF/views/include.jsp" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<link rel="stylesheet" type="text/css" href="resources/bootstrap/css/entrada.css" />
<script>
	<!--
	$(document).ready(function(){
		$("#camara").change(function(){
		 camara = $("#camara").val();
		 if(camara!=""){
		 $.ajax("cargaPuerta", {
		 "type": "POST", //usualmente post o get
		 "success": function(result) {
		 $("#cboPuerta").html(result);
		 },
		 "error": function(result) {
		 showAlert("Error al recuperar la informacion", result);
		 },
		 "beforeSend": function(result) {
			 $("#cboPuerta").css("color","#000");
			 $("#cboPuerta").html("cargando...");
		 },
		 "data": {camara : camara},
		 "async": true
		 });
		 }
		 });

		$(".btnRegistraPosicion").click(function(){
			camara = $("#camara");
			puerta = $("#puerta");
			pasillo = $("#pasillo");
			filaCallel = $("#filaCallel");
			filaCalle = $("#filaCalle");
			posicion = $("#posicion");
			nivel = $("#nivel");
			tipoTarima = $("#tipoTarima");
			consecutivo = $("#consecutivo");
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
			if(filaCallel.val()==""){
				alert("Favor de seleccionar unas filas o calles.");
				filaCallel.focus();
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
				url:       'insPosicion',
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
			$("#tablaresultadodetalleUbica").css("font-size","35px");
			$('#tablaresultadodetalleUbica').html("Registrando ubicaci&oacute;n...<br><br><br>");
		};

		showResponse = function(){
			$("#tablaresultadodetalleUbica").css("font-size","14px");
		};

	});
	//-->
</script>
<section class="container">
	<h1 id="item_name"><fmt:message key="ubicacion.heading"/></h1>
	<h3 id="item_name"><fmt:message key="ubicacion.subtitle"/></h3>
	<form:form method="post" id="frmPosicion" name="frmPosicion" commandName="formEntrada" role="form">
		<section class="grupodedatos">
			<div class="bloquedata">
				<div class="form-group">
					<label for="camara">CAMARA</label>
					<select id="camara" name="camara" placeholder="TIPO DE RECIBO" value='' class="form-control" required>
						<option value="" > <fmt:message key="entradas.select"/></option>
						<c:forEach items="${camaras}" var="camara">
							<option value="${camara}"> <c:out value="${camara}"/></option>
						</c:forEach>
					</select>
				</div>
				<div class="form-group">
					<label for="puerta">LADO</label>
					<div id="cboPuerta">
					<select id="puerta" name="puerta" value='' class="form-control" required>
						<option value="" ><fmt:message key="entradas.select"/> </option>
					</select>
					</div>
				</div>
				<div class="form-group">
					<label for="pasillo">PASILLO</label>
					<div id="cboPasillo">
					<select id="pasillo" name="pasillo" value='' class="form-control" required>
						<option value="" > <fmt:message key="entradas.select"/> </option>
					</select>
					</div>
				</div>

			</div>
			<div class="bloquedata">
				<div class="form-group">
					<label for="filaCallel">FILAS O CALLES</label>
					<div id="cboFila">
					<select id="filaCallel" name="filaCallel" value='' class="form-control" required>
						<option value="" > <fmt:message key="entradas.select"/> </option>
					</select>
					</div>
				</div>
				<div class="form-group">
					<label for="filaCalle">FILA O CALLE</label>
					<input type='text' placeholder="FILA O CALLE" id="filaCalle" name='filaCalle' value='' size="75" maxlength="255" class="form-control">
				</div>
				<div class="form-group">
					<label for="posicion">SUPERFICIE</label>
					<div id="cboPosicion">
					<select id="posicion" name="posicion" value='' class="form-control" required>
						<option value="" > <fmt:message key="entradas.select"/> </option>
					</select>
					</div>
				</div>
			</div>

			<div class="bloquedata">
				<div class="form-group">
						<label for="nivel">POSICIONES</label>
						<div id="cboNivel">
						<select id="nivel" name="nivel" value='' class="form-control" required>
							<option value="" > <fmt:message key="entradas.select"/> </option>
						</select>
						</div>
				</div>
				<div class="form-group">
					<label for="tipoTarima">TAMA&Ntilde;O TARIMA</label>
					<select id="tipoTarima" name="tipoTarima" value='' class="form-control" required>
						<option value="" > <fmt:message key="entradas.select"/> </option>
						<option value="1.20"> <c:out value="1.20"/></option>
						<option value="1.50"> <c:out value="1.50"/></option>
					</select>
				</div>
				<div class="form-group">
					<div id="cboTipoProd">
						<label for="consecutivo">RD</label>
						<input type='text' placeholder="CONSECUTIVO" id="consecutivo" name='consecutivo' value='' size="75" maxlength="255" class="form-control">
					</div>
				</div>
				<div class="form-group">
					<button class="btn btn-primary btn-small btnRegistraPosicion" type="submit">
						<i class="icon-edit  bigger-125"></i>
						Registrar Ubicacion
					</button>
				</div>
			</div>
		</section>

		<input type='hidden' name='fechaEntrada' value='${fecha}'>
		<input type='hidden' name='horaEntrada' value='${hora}'>
	</form:form>
</section>
<br>
<div id="tablaresultadodetalleUbica" class="tablaresultadodetalleUbica"></div>

