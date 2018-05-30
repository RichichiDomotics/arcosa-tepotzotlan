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
	$(".btnregistraservicio").click(function(){
		var descripcionClave = $("#clave option:selected").text();
		$("#descripcion").val(descripcionClave);
		var options = {
			target:    '#tablaServicios',   // target element(s) to be updated with server response
			url:       'insertaServicio',
			beforeSubmit:  showRequest,  // pre-submit callback
			success:       showResponse  // post-submit callback
		};

		// bind form using 'ajaxForm'
		$('#Servicio').ajaxForm(options);
		$('#Servicio').submit();
		return false;
	});

	showRequest = function(){
		$("#tablaServicios").css("color","#000");
		$("#tablaServicios").css("font-size","35px");
		$('#tablaServicios').html("Registrando Servicio...<br><br><br>");
	};

	showResponse = function(){
		$("#tablaServicios").css("font-size","14px");
	};

});
//-->
</script>
<div id="capturaClienteFrm">
<h1><fmt:message key="ingresa.clientes.heading"/></h1>
<!-- h3><fmt:message key="consultas.clientes.subtitle"/></h3-->
<form:form method="post" commandName="Servicio" role="form" action="insertaServicio">
	<input type='hidden' placeholder="descripcion" id="descripcion" name='descripcion' value='' >
	<input type='hidden' placeholder="activo" id="activo" name='activo' value='1' >
<div class="row-fluid">
	<div class="span6">
		<h3 class="header smaller lighter blue">MANIOBRAS ESPECIALES</h3>
		<div id="accordion2" class="accordion">
			<div class="accordion-group">
				<div class="accordion-heading">
					<a href="#collapseFour" data-parent="#accordion2" data-toggle="collapse" class="accordion-toggle collapsed">
						Servicios Adicionales
					</a>
				</div>
					<!-- ************************************************** -->

					<div class="accordion-body collapse" id="collapseFour">
						<div class="accordion-inner">
							<form:form id="frmServicios" name="frmServicios" method="post" commandName="insertaServicio" role="form" action="insertaServicio">
								<section class="grupodedatos">
									<div class="bloquedata">
										<div class="form-group">
											<h3 class="header smaller lighter blue">${nombreCliente}</h3>
											<input type='hidden' id="idCliente" name='idCliente' value='${idCliente}' >
											<label for="clave">SERVICIOS ADICIONALES</label>
											<select id="clave" name="clave" class="form-control">
												<option value="" >SELECCIONA UN SERVICIO</option>
												<c:forEach items="${servicios}" var="servicio">
													<option value="${servicio[0]}"> <c:out value="${servicio[1]}"/></option>
												</c:forEach>
											</select>
										</div>
										<div class="form-group">
											<label for="cuota">CUOTA</label>
											<input type='text' placeholder="CUOTA" id="cuota" name='cuota' value='' size="75" maxlength="255" class="form-control">
										</div>
									</div>
								</section>
							</form:form>
						</div>
					</div>

       </div>
	</div>
  </div>
<div class="form-group">
    	<button class="btn btn-primary btn-small btnregistraservicio" type="button">
			<i class="icon-edit  bigger-125"></i>
				Registrar Servicio
		</button> 
    </div>
    </div>
 </form:form>
	<div id="tablaServicios">
		<table aria-describedby="sample-table-2_info" id="sample-table-2" class="table table-striped table-bordered table-hover dataTable">
			<thead>
			<tr role="row">
				<th>CLAVE SERVICIO</th>
				<th>DESCRIPCION</th>
				<th>CUOTA</th>
			</tr>
			</thead>
			<c:forEach items="${serviciosRegistrados}" var="servicioReg">
				<tbody aria-relevant="all" aria-live="polite" role="alert">
				<tr class="odd">
					<td>${servicioReg.clave}</td>
					<td>${servicioReg.descripcion}</td>
					<td>${servicioReg.cuota}</td>
				</tr>
				</tbody>
			</c:forEach>
		</table>

	</div>
 </div>
