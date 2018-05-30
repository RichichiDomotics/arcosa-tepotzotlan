<%@ include file="/WEB-INF/views/include.jsp" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<link rel="stylesheet" type="text/css" href="resources/bootstrap/css/clientesCaptura.css" />
<script>
	$(document).ready(function(){

		$(".veDetalleFacturabtn").click(function(){
			nofactura = $(this).attr("nofactura");
			veDetalleFacturabtn(nofactura);
		});

		veDetalleFacturabtn = function(nofactura){
			if(!$("#detCliente").length){
				$("#capturaClienteFrm").append('<div id="detCliente"></div>')
			}
			$.ajax("consultaDetalleFactura", {
				"type": "POST",
				"beforeSend": function() {
					$("#detCliente").css("font-size","27px");
					$("#detCliente").html('Cargando ...');
				},
				"success": function(result){
					$("#detCliente").css("font-size","12px");
					//$("#detCliente").html(result);
					bootbox.dialog($("#detCliente").html(result), [{
								"label" : "Cerrar",
								"class" : "btn-small btn-primary",
								"callback": function() {
									$("#detCliente").html("");
								}
							}]
					).css({"width":"1100px","heith":"auto","left":"450px"});
					return false;
				},
				"error": function(result) {
					alert("Error al recuperar la información", result);
					$("#detCliente").html("");
				},
				"data": {nofactura:nofactura},
				"async": true
			});


		};

	});

</script>
<style>
	.grupodedatosDetCliente {
		width: 1300px;
		float: left;

	}

	.grupodedatosDetCliente .bloquedata{
		display:block;
		width: 340px;
		float: left;
	}

	.grupodedatosDetCliente .bloquedata .form-group {
		width: 320px;
	}
</style>
<div id="capturaClienteFrm">
<div class="row-fluid">
	<div class="span6">
		<div class="tabbable">
			<ul class="nav nav-tabs" id="myTab">

				<li class="dropdown active">
					<a data-toggle="dropdown" class="dropdown-toggle" href="#">
						<span class="icon-edit"></span>  Datos Cliente
						<b class="caret"></b>
					</a>

					<ul class="dropdown-menu dropdown-info">
						<li class="active">
							<a data-toggle="tab" href="#datosCliente"><span class="icon-group"></span> Generales</a>
						</li>

						<li>
							<a data-toggle="tab" href="#datosEntregaCobro"><span class="icon-credit-card"></span> Entrega y Cobro</a>
						</li>

						<li>
							<a data-toggle="tab" href="#datosConvenios"><span class="icon-book"></span> Convenios</a>
						</li>
					</ul>
				</li>

				<li class="">
					<a data-toggle="tab" href="#datosServicios">
						<i class="icon-briefcase"></i>
						Servicios Registrados
					</a>
				</li>

				<li class="">
					<a data-toggle="tab" href="#datosInventario">
						<i class="icon-barcode"></i>
						Inventario
					</a>
				</li>

				<li class="">
					<a data-toggle="tab" href="#datosFacturacion">
						<i class="icon-download-alt"></i>
						Facturaci&oacute;n
					</a>
				</li>


			</ul>

			<div class="tab-content">
				<div id="datosCliente" class="tab-pane active">
					<section class="grupodedatosDetCliente">
						<div class="bloquedata">
							<div class="form-group " >
								<label class="text-info" for="razonSocial">RAZ&Oacute;N SOCIAL : </label><div class="text-info">${detalle.nombreCliente}</div>
							</div>
							<div class="form-group">
								<label class="text-info" for="rfc">RFC : </label><div class="text-info">${detalle.rfc}</div>
							</div>
							<div class="form-group">
								<label class="text-info" for="callenum">CALLE NO. INT Y NO. EXT : </label><div class="text-info">${detalle.calle}</div>
							</div>
							<div class="form-group">
								<label class="text-info" for="colonia">COLONIA :</label><div class="text-info">${detalle.colonia}</div>
							</div>
							<div class="form-group">
								<label class="text-info" for="ciudad">CIUDAD :</label><div class="text-info">${detalle.ciudad}</div>
							</div>

						</div>
						<div class="bloquedata">
							<div class="form-group">
								<label for="estado">ESTADO :</label><div class="text-info">${detalle.idEstado}</div>
							</div>
							<div class="form-group">
								<label for="cp">CP :</label><div class="text-info">${detalle.cp}</div>
							</div>
							<div class="form-group">
								<label for="email">EMAIL :</label><div class="text-info">${detalle.email}</div>
							</div>
							<div class="form-group">
								<label for="telefono">TELEFONO :</label><div class="text-info">${detalle.telefono1}</div>
							</div>
							<div class="form-group">
								<label for="extension">EXTENSION :</label><div class="text-info">${detalle.extension}</div>
							</div>
						</div>
						<div class="bloquedata">
							<div class="form-group">
								<label for="contacto1">CONTACTO:</label><div class="text-info">${detalle.contacto}</div>
							</div>
							<div class="form-group">
								<label for="cargo1">CARGO:</label><div class="text-info">${detalle.contactoPagoCargo}</div>
							</div>
							<div class="form-group">
								<label for="telefono1">TELEFONO:</label><div class="text-info">${detalle.telefono1}</div>
							</div>
							<div class="form-group">
								<label for="celular1">CELULAR:</label>
							</div>
							<div class="form-group">
								<label for="idEjecutivo">EJECUTIVO :</label><div class="text-info">${detalle.nomEjecutivo}</div>
							</div>
						</div>
					</section>
				</div>

				<div id="datosEntregaCobro" class="tab-pane">
					<section class="grupodedatosDetCliente">
						<div class="bloquedata">
							<div class="form-group">
								<label for="facturacion">FACTURACI&Oacute;N :</label> <div class="text-info">${detalle.facturacion}</div>
							</div>
							<div class="form-group">
								<label for="diasCredito">DIAS DE CREDITO :</label> <div class="text-info">${detalle.diasCredito}</div>
							</div>
							<div class="form-group">
								<label for="email">EMAIL PAGOS :</label> <div class="text-info">${detalle.email}</div>
							</div>
						</div>
						<div class="bloquedata">
							<div class="form-group">
								<label for="contactoPagoNombre">CONTACTO PAGOS :</label> <div class="text-info">${detalle.contactoPagoNombre}</div>
							</div>
							<div class="form-group">
								<label for="contactoPagoTelefono">TELEFONO CONTACTO PAGOS :</label> <div class="text-info">${detalle.contactoPagoTelefono}</div>
							</div>
							<div class="form-group">
								<label for="horario">HORARIO DE ATENCI&Oacute;N :</label> <div class="text-info">${detalle.horario}</div>
							</div>
						</div>
						<div class="bloquedata">
							<div class="form-group">
								<label for="diasRevision">D&Iacute;AS DE REVISI&Oacute;N DE FACTURAS :</label> <div class="text-info">${detalle.diasRevision}</div>
							</div>
							<div class="form-group">
								<label for="diasPago">D&Iacute;AS DE PAGO DE FACTURAS :</label> <div class="text-info">${detalle.diasPago}</div>
							</div>
						</div>
						<div class="bloquedata">
						</div>
					</section>
				</div>

				<div id="datosConvenios" class="tab-pane">
					<section class="grupodedatosDetCliente">
						<div class="bloquedata">
							<div class="form-group">
								<label for="claveProducto">CLAVE DE PRODUCTO</label> <div class="text-info">${convenios.claveProducto}</div>
							</div>
							<div class="form-group">
								<label for="temperaturaRequerida">TEMPERATURA REQUERIDA</label> <div class="text-info">${convenios.congelacion}</div>
							</div>
						</div>
						<div class="bloquedata">
							<div class="form-group">
								<label for="minimo">MINIMO GLOBAL MENSUAL</label> <div class="text-info">${convenios.minimo}</div>
							</div>
							<div class="form-group">
								<label for="cuotaAlmacenaje">CUOTA ALMACENAJE</label> <div class="text-info">${convenios.cuotaAlmacenaje}</div>
							</div>
						</div>

						<div class="bloquedata">
							<div class="form-group">
								<label for="detalleCuota">DETALLE DE LA CUOTA</label> <div class="text-info">${convenios.minimo}</div>
							</div>
							<div class="form-group">
								<label for="periodo">PERIODO DE LA CUOTA</label> <div class="text-info">${convenios.periodo}</div>
							</div>
						</div>
						<div class="bloquedata">
							<div class="form-group">
								<label for="meys">MEYS</label> <div class="text-info">${convenios.mentysal}</div>
							</div>
							<div class="form-group">
								<label for="minimoPartidaCongelacionRapida">MIN. PARTIDA CONGELACI&Oacute;N RAPIDA</label> <div class="text-info">${convenios.partidaMinima}</div>
							</div>
						</div>
					</section>
				</div>

				<div id="datosServicios" class="tab-pane">
					<div id="tablaServicios">
						<table aria-describedby="sample-table-1_info" id="sample-table-1" class="table table-striped table-bordered table-hover dataTable">
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

				<div id="datosInventario" class="tab-pane">
					<table aria-describedby="sample-table-2_info" id="sample-table-2" class="table table-striped table-bordered table-hover dataTable">
						<thead>
						<tr role="row">
							<th>RD</th>
							<th>Fecha Ingreso</th>
							<th>Renglon</th>
							<th>Camara</th>
							<th>Caducidad</th>
							<th>Cantidad Pza.</th>
							<th>Peso U Kg.</th>
							<th>Descripci&oacute;n</th>
							<th>Embalaje</th>
							<th>Marca</th>
							<th>Total por Renglon</th>
						</tr>
						</thead>
						<c:set var="contador" value="${0}"/>
						<c:set var="total" value="${0}"/>
						<c:set var="totalpzas" value="${0}"/>
						<c:set var="subtotalRD" value="${0}"/>
						<c:set var="totalRenglon" value="${0}"/>
						<c:forEach items="${reporte}" var="det">
							<tbody aria-relevant="all" aria-live="polite" role="alert">
							<tr class="odd">
								<td>${det.consecutivo}</td>
								<td><fmt:parseDate value="${det.fechaCaptura}" var="parsedEmpDate" pattern="dd/MM/yyyy" />
									<fmt:formatDate pattern="dd-MM-yyyy" value="${parsedEmpDate}" /></td>

								<td><fmt:formatNumber value="${det.renglon}" maxFractionDigits="0"/></td>

								<td>
									<c:if test="${det.camara eq ''}">
										Camara 12
									</c:if>

									<c:if test="${det.camara ne ''}">
										${det.camara}
									</c:if>
								</td>
								<td>${det.caducidad}</td>
								<td><fmt:formatNumber pattern="###,###,###.##" value="${det.cantidadInventario}"/></td>
								<td><fmt:formatNumber pattern="###,###,###.##" value="${det.pesou}"/></td>
								<td>${det.descripcion}</td>
								<td>${det.embalaje}</td>
								<td>${det.marca}</td>
								<!-- td></td-->
								<c:set var="contador" value="${contador+1}"/>
								<c:set var="totalRenglon" value="${(det.cantidadInventario*det.pesou)}"/>
								<c:set var="subtotalRD" value="${(subtotalRD) + (totalRenglon)}"/>
								<td><fmt:formatNumber pattern="###,###,###.##" value="${totalRenglon}"/></td>
								<c:set var="total" value="${total+(det.cantidadInventario*det.pesou)}"/>
								<c:set var="totalpzas" value="${totalpzas+(det.cantidadInventario)}"/>
							</tr>
							</tbody>
						</c:forEach>
					</table>
				</div>

				<div id="datosFacturacion" class="tab-pane">
					<div id="tablaFacturas">
						<table aria-describedby="sample-table-2_info" id="sample-table-2" class="table table-striped table-bordered table-hover dataTable">
							<thead>
							<tr role="row">
								<th>DETALLE FACTURA</th>
								<th>NO. FACTURA</th>
								<th>FECHA FACTURA</th>
								<th>KILOS</th>
								<th>TOTAL ($)</th>
							</tr>
							</thead>
							<c:forEach items="${facturaciones}" var="factura">
								<tbody aria-relevant="all" aria-live="polite" role="alert">
								<tr class="odd">
									<td>
										<button class="btn btn-minier btn-primary veDetalleFacturabtn" nofactura="${factura[1]}">Detalle</button>
									</td>
									<td>${factura[1]}</td>
									<td><fmt:parseDate value="${factura[2]}" var="parsedEmpDate" pattern="yyyy-MM-dd" />
										<fmt:formatDate pattern="dd-MM-yyyy" value="${parsedEmpDate}" /></td>
									<td><fmt:formatNumber type="number" maxFractionDigits="2" value="${factura[3]}"/></td>
									<td>$<fmt:formatNumber type="number" maxFractionDigits="2" value="${factura[4]}"/></td>
								</tr>
								</tbody>
							</c:forEach>
						</table>

					</div>
				</div>
			</div>
		</div>
	</div><!--/span-->
</div><!--/row-->
</div>

