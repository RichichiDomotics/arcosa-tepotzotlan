<%@ include file="/WEB-INF/views/include.jsp" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<link rel="stylesheet" type="text/css" href="resources/bootstrap/css/clientesCaptura.css" />
<style>
	.grupodedatos{
		width: 986px;
	}
</style>
<div id="capturaClienteFrm">
<!-- h3><fmt:message key="consultas.clientes.subtitle"/></h3-->
<form:form method="post" commandName="Cliente" role="form" action="ae_insertarCliente">

<div class="row-fluid">
	<div class="span6">
		<h3 class="header smaller lighter blue">Detalle Cliente</h3>
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
  	<div class="form-group " >
    	<label class="text-info" for="razonSocial">RAZ&Oacute;N SOCIAL : </label> <div class="alert alert-info">${detalle.nombreCliente}</div>
  	</div>
  	<div class="form-group">
    	<label class="text-info" for="rfc">RFC : </label> <div class="alert alert-info">${detalle.rfc}</div>
  	</div>
  	<div class="form-group">
    	<label class="text-info" for="callenum">CALLE NO. INT Y NO. EXT : </label> <div class="alert alert-info">${detalle.calle}</div>
  	</div>
  	<div class="form-group">
   	    <label class="text-info" for="colonia">COLONIA :</label> <div class="alert alert-info">${detalle.colonia}</div>
  	</div>
  	<div class="form-group">
   	    <label class="text-info" for="ciudad">CIUDAD :</label> <div class="alert alert-info">${detalle.ciudad}</div>
  	</div>
  	
  </div>
  <div class="bloquedata">
  	<div class="form-group">
   	    <label for="estado">ESTADO :</label> <div class="alert alert-info">${detalle.idEstado}</div>
  	</div>
  	<div class="form-group">
   	    <label for="cp">CP :</label>  <div class="alert alert-info">${detalle.cp}</div>
  	</div>
  	<div class="form-group">
    	<label for="email">EMAIL :</label>  <div class="alert alert-info">${detalle.email}</div>
  	</div>
  	<div class="form-group">
    	<label for="telefono">TELEFONO :</label>  <div class="alert alert-info">${detalle.telefono1}</div>
  	</div>
  	<div class="form-group">
    	<label for="extension">EXTENSION :</label>  <div class="alert alert-info">${detalle.extension}</div>
  	</div>
  </div>
  <div class="bloquedata">
    <div class="form-group">
    	<label for="contacto1">CONTACTO:</label> <div class="alert alert-info">${detalle.contacto}</div>
  	</div>
  	<div class="form-group">
    	<label for="cargo1">CARGO:</label>  <div class="alert alert-info">${detalle.contactoPagoCargo}</div>
  	</div>
  	<div class="form-group">
    	<label for="telefono1">TELEFONO:</label>  <div class="alert alert-info">${detalle.telefono1}</div>
  	</div>
  	<div class="form-group">
    	<label for="celular1">CELULAR:</label>
  	</div>
	<div class="form-group">
		  <label for="idEjecutivo">EJECUTIVO :</label>  <div class="alert alert-info">${detalle.nomEjecutivo}</div>
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
    	<label for="facturacion">FACTURACI&Oacute;N :</label> <div class="alert alert-info">${detalle.facturacion}</div>
  	</div>
  	<div class="form-group">
    	<label for="diasCredito">DIAS DE CREDITO :</label> <div class="alert alert-info">${detalle.diasCredito}</div>
  	</div>
  	<div class="form-group">
    	<label for="email">EMAIL PAGOS :</label> <div class="alert alert-info">${detalle.email}</div>
  	</div>
  </div>
  <div class="bloquedata">
  	<div class="form-group">
   	    <label for="contactoPagoNombre">CONTACTO PAGOS :</label> <div class="alert alert-info">${detalle.contactoPagoNombre}</div>
  	</div>
  	<div class="form-group">
   	    <label for="contactoPagoTelefono">TELEFONO CONTACTO PAGOS :</label> <div class="alert alert-info">${detalle.contactoPagoTelefono}</div>
  	</div>
  	<div class="form-group">
   	    <label for="horario">HORARIO DE ATENCI&Oacute;N :</label> <div class="alert alert-info">${detalle.horario}</div>
  	</div>
  </div>
  <div class="bloquedata">
    <div class="form-group">
    	<label for="diasRevision">D&Iacute;AS DE REVISI&Oacute;N DE FACTURAS :</label> <div class="alert alert-info">${detalle.diasRevision}</div>
  	</div>
  	<div class="form-group">
    	<label for="diasPago">D&Iacute;AS DE PAGO DE FACTURAS :</label> <div class="alert alert-info">${detalle.diasPago}</div>
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
						<c:forEach items="${convenios}" var="c">
						_______________________________________________________________________________________________________________________________________________________
						<section class="grupodedatos">
								<div class="bloquedata">
									<div class="form-group">
										<label for="claveProducto">CLAVE DE PRODUCTO</label> <div class="alert alert-info">${c.claveProducto}</div>
									</div>
									<div class="form-group">
										<label for="temperaturaRequerida">TUNEL</label> <div class="alert alert-info">${c.congelacion}</div>
									</div>
								</div>
								<div class="bloquedata">
									<div class="form-group">
										<label for="minimo">MINIMO GLOBAL MENSUAL</label> <div class="alert alert-info">${c.minimo}</div>
									</div>
									<div class="form-group">
										<label for="cuotaAlmacenaje">CUOTA ALMACENAJE</label> <div class="alert alert-info">${c.cuotaAlmacenaje}</div>
									</div>
								</div>

								<div class="bloquedata">
									<div class="form-group">
										<label for="detalleCuota">DETALLE DE LA CUOTA</label> <div class="alert alert-info">${c.tipo}</div>
									</div>
									<div class="form-group">
										<label for="periodo">PERIODO DE LA CUOTA</label> <div class="alert alert-info">${c.periodo}</div>
									</div>
								</div>
								<div class="bloquedata">
									<div class="form-group">
										<label for="meys">MANIOBRAS DE ENTRADA Y SALIDA</label> <div class="alert alert-info">${c.mentysal}</div>
									</div>
									<div class="form-group">
										<label for="minimoPartidaCongelacionRapida">MIN. PARTIDA CONGELACI&Oacute;N RAPIDA</label> <div class="alert alert-info">${c.partidaMinima}</div>
									</div>
								</div>
						</section>
						</c:forEach>
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
				</div>
				
				
			</div>
		</div>
	</div><!--/span-->
</div><!--/row-->
<div class="form-group">
    </div>
    </div>
 </form:form>
 </div>
 <div id="tablaServicios"></div>
