<%@ include file="/WEB-INF/views/include.jsp" %>
<sec:authentication property="authorities" var="roles" scope="page" />
<c:forEach var="role" items="${roles}">
	<c:set var="rolef" value="${role}"/>
</c:forEach>
<script>
	$(document).ready(function(){
		$("#facturas2").click(function(){
			if($("#facturacion").length){
				$("#supdiv").append('<div id="facturacion"></div>')
			}
			//bootbox.dialog($("#facturacion").html("<iframe src='http://"+window.location.hostname+"/facturacion' width='100%' height='900px' frameborder='no' tabindex='0'></iframe>")
			bootbox.dialog($("#facturacion").html("<iframe src='http://"+window.location.hostname+":8087/login.php' width='100%' height='900px' frameborder='no' tabindex='0'></iframe>")
					, [{
						"label" : "Cerrar",
						"class" : "btn-small btn-primary",
						"callback": function() {
							$("#facturacion").html("");
						}
					}]
			).css({"width":"1320px","height":"auto","left":"290px","overflow-y": "hidden"});
		});
	});
</script>
<div class="inicioPrincipal">
	<div class="alert alert-block alert-info">
		<i class="icon-cogs blue"></i>
		Bienvenido al
		<strong class="blue">
			GIA
			<small>(v2.0.15)</small>
		</strong>, Gesti&oacute;n de Inventarios Arcosa.
	</div>
	<div class="row-fluid">
		<div class="span7 infobox-container">
			<c:if test="${(rolef eq 'ROLE_FACTURACION') || (rolef eq 'ROLE_OPERACION') ||(rolef eq 'ROLE_VIGILANTE') || (rolef eq 'ROLE_ADMIN')}">
				<div class="infobox infobox-blue">
					<div class="infobox-icon">
						<i class="icon-download-alt"></i>
					</div>
					<div class="infobox-data">
						<div class="infobox-content">Vigilancia</div>
						<span class="infobox-data-number"></span>
						<div class="infobox-content">
							<ul>
								<li><a href="<c:url value="v_ingresoPreVehiculo"/>">
									<i class="icon-download-alt fa fa-caret-right"></i>
									Pre-Registro de Vehiculos
								</a>
								</li>
								<li><a href="<c:url value="v_listaPreVehiculo"/>">
									<i class="icon-download-alt fa fa-caret-right"></i>
									Consulta de Vehiculos
								</a>
								</li>
								<li><a href="<c:url value="ae_salidaPreVehiculo"/>">
									<i class="icon-download-alt fa fa-caret-right"></i>
									Salida Veh&iacute;culos
								</a>
								</li>
							</ul>
						</div>
					</div>
				</div>
			</c:if>

			<c:if test="${(rolef eq 'ROLE_FACTURACION') || (rolef eq 'ROLE_OPERACION') || (rolef eq 'ROLE_ADMISION') || (rolef eq 'ROLE_ADMIN')}">
				<div class="infobox infobox-blue">
					<div class="infobox-icon">
						<i class="icon-exchange"></i>
					</div>
					<div class="infobox-data">
						<div class="infobox-content">Ingresos y Salidas</div>
						<span class="infobox-data-number"></span>
						<div class="infobox-content">
							<ul>
								<li><a href="<c:url value="adm_ingresoVehiculo"/>">
									<i class="icon-exchange fa fa-caret-right"></i>
									Registra Ingreso de Vehiculos
								</a>
								</li>
								<li>
									<a href="<c:url value="/alm_consultaIngresos" />">
										<i class="icon-exchange fa fa-caret-right"></i>
										Captura Ingresos
									</a>
									<ul>
										<li>
											<a href="<c:url value="/formatoIngreso" />" target="_blank">
												<i class="icon-exchange fa fa-caret-right"></i>
												Formato de Ingresos
											</a>
										</li>
									</ul>
								</li>
								<li>
									<a href="<c:url value="/alm_consultaSalidas" />">
										<i class="icon-exchange fa fa-caret-right"></i>
										Captura Salidas
									</a>
								</li>
								<li>
									<a href="<c:url value="adm_salidaVehiculo"/>">
										<i class="icon-exchange fa fa-caret-right"></i>
										Registra Salida de Vehiculos
									</a>
								</li>
							</ul>
						</div>
					</div>
				</div>
			</c:if>
			<!--
			<c:if test="${(rolef eq 'ROLE_FACTURACION') || (rolef eq 'ROLE_OPERACION') || (rolef eq 'ROLE_ALMACENISTA') || (rolef eq 'ROLE_ADMIN')}">
				<div class="infobox infobox-blue">
					<div class="infobox-icon">
						<i class="icon-adjust"></i>
					</div>
					<div class="infobox-data">
						<div class="infobox-content">Ubicaciones</div>
						<span class="infobox-data-number"></span>
						<div class="infobox-content">
							<ul>
								<li><a href="<c:url value="alm_regUbicaciones"/>">
									<i class="icon-adjust fa fa-caret-right"></i>
									Captura Ubicaci&oacute;n</a>
								</li>
								<li><a href="<c:url value="alm_getUbicaciones"/>">
									<i class="icon-adjust fa fa-caret-right"></i>
									Consulta Ubicaci&oacute;n</a>
								</li>
							</ul>
						</div>
					</div>
				</div>
			</c:if>
			-->
			<c:if test="${(rolef eq 'ROLE_FACTURACION') || (rolef eq 'ROLE_OPERACION') || (rolef eq 'ROLE_ADMIN') || (rolef eq 'ROLE_TIF') || (rolef eq 'ROLE_AUDITORIA')}">
				<div class="infobox infobox-blue">
					<div class="infobox-icon">
						<i class="icon-dashboard"></i>
					</div>
					<div class="infobox-data">
						<div class="infobox-content">Operaciones</div>
						<span class="infobox-data-number"></span>
						<div class="infobox-content">
							<ul>
								<c:if test="${(rolef eq 'ROLE_TIF')}">
									<li>
										<a href="<c:url value="ae_mandaRetemesTIF" />">
											<i class="icon-dashboard fa fa-caret-right"></i>
											Captura Retemes TIF
										</a>
									</li>
									<li>
										<a href="<c:url value="formaConsultaClienteFichaTecnica"/>">
											<i class="icon-dashboard fa fa-caret-right"></i>
											Ficha T&eacute;cnica del Cliente
										</a>
									</li>
									<li><a href="<c:url value="ae_consultaRetemes"/>">
										<i class="icon-dashboard fa fa-caret-right"></i>
										Consulta Retemes
									</a>
									</li>
								</c:if>
								<c:if test="${(rolef eq 'ROLE_AUDITORIA')}">
								<li><a href="<c:url value="ae_consultaRetemes"/>">
									<i class="icon-dashboard fa fa-caret-right"></i>
									Consulta Retemes
								</a>
								</li>
								</c:if>
								<c:if test="${(rolef eq 'ROLE_OPERACION') || (rolef eq 'ROLE_ADMIN') || (rolef eq 'ROLE_FACTURACION')}">
								<li>
									<a href="<c:url value="formaConsultaClienteFichaTecnica"/>">
										<i class="icon-dashboard fa fa-caret-right"></i>
										Ficha T&eacute;cnica del Cliente
									</a>
								</li>
								<li><a href="<c:url value="ae_traspasos"/>">
									<i class="icon-dashboard fa fa-caret-right"></i>
									Traspasos Camara y T&uacute;nel
								</a>
								</li>
								<li>
									<a href="<c:url value="/formConsultaTraspasos"/>">
										<i class="icon-dashboard fa fa-caret-right"></i>
										Consulta Traspasos Camara y T&uacute;nel
									</a>
								</li>
								<li><a href="<c:url value="ae_mandaRetemes"/>">
									<i class="icon-dashboard fa fa-caret-right"></i>
									Captura Retemes</a>
								</li>

								<li><a href="<c:url value="ae_consultaRetemes"/>">
									<i class="icon-dashboard fa fa-caret-right"></i>
									Consulta Retemes
								</a>
								</li>

								<li>
									<a href="<c:url value="solicitudModificacion" />">
										<i class="icon-dashboard fa fa-caret-right"></i>
										Solicitud de Modificacion al Sistema
									</a>
								</li>
									</c:if>
								<c:if test="${rolef eq 'ROLE_ADMIN'}">
									<li>
										<a href="<c:url value="autorizarModificaciones" />">
											<i class="icon-dashboard fa fa-caret-right"></i>
											Atencion Modificaciones al Sistema
										</a>
									</li>
								</c:if>
							</ul>
						</div>
					</div>
				</div>
			</c:if>

			<c:if test="${(rolef eq 'ROLE_FACTURACION') || (rolef eq 'ROLE_OPERACION') ||(rolef eq 'ROLE_COMERCIAL') ||(rolef eq 'ROLE_COMERCIAL_GERENTE') || (rolef eq 'ROLE_ADMIN') || rolef eq 'ROLE_EJECUTIVO' || (rolef eq 'ROLE_AUDITORIA')|| (rolef eq 'ROLE_MAQUILA') || (rolef eq 'ROLE_TIF')}">
			<div class="infobox infobox-blue">
				<div class="infobox-icon">
					<i class="icon-globe"></i>
				</div>
				<div class="infobox-data">
					<div class="infobox-content">Comercial</div>
					<span class="infobox-data-number"></span>
					<div class="infobox-content">
						<ul>
							<!--
						  <c:if test="${rolef eq 'ROLE_EJECUTIVO' || (rolef eq 'ROLE_ADMIN') }">
							<li>
								<a href="<c:url value="ae_formaProspecto" />">
								<i class="icon-globe fa fa-caret-right"></i>
								Ingreso Prospecto
								</a>	
							</li>
							<li><a href="<c:url value="ae_formaProspectoAutorizado" />">
								<i class="icon-globe fa fa-caret-right"></i>
								Prospectos Autorizados
								</a>
							</li>
							<li><a href="<c:url value="/ae_formaProspectoRechazado" />">
								<i class="icon-globe fa fa-caret-right"></i>
								Prospectos Rechazados
							</a>
							</li>
							<li><a href="<c:url value="/ae_formaProspectoSeguimiento" />">
								<i class="icon-globe fa fa-caret-right"></i>
								Revisar Seguimientos
							</a>
							</li>
						  </c:if>
						  <c:if test="${rolef eq 'ROLE_JEFEEJE' || (rolef eq 'ROLE_ADMIN') }">
								<li>
									<a href="<c:url value="ae_formaProspectoJefe" />">
										<i class="icon-globe fa fa-caret-right"></i>
										Ingreso Prospecto
									</a>
								</li>
							    <li><a href="<c:url value="/ae_formaProspectoAutorizadoJefe" />">
								   <i class="icon-globe fa fa-caret-right"></i>
								   Autorizar Prospectos
							      </a>
							    </li>
								<li><a href="<c:url value="/ae_formaProspectoSeguimientoJefe" />">
									<i class="icon-globe fa fa-caret-right"></i>
									Revisar Seguimientos
								</a>
								</li>
							    <li><a href="<c:url value="/ae_formaProspectoReAutorizaJefe" />">
								    <i class="icon-globe fa fa-caret-right"></i>
								    Solicitud de Re-Autorizaci&oacute;n
							        </a>
							    </li>
								<li><a href="<c:url value="ae_formaProspectoDesasignado" />">
								  <i class="icon-globe fa fa-caret-right"></i>
								  Desasignados
							      </a>
							    </li>
							  	<li><a href="<c:url value="/ae_consultaProspectoAceptado" />">
								  <i class="icon-globe fa fa-caret-right"></i>
								  Cambio a cliente
							      </a>
							  	</li>
						  </c:if>
						  -->
							<c:if test="${rolef eq 'ROLE_FACTURACION'||rolef eq 'ROLE_COMERCIAL' || (rolef eq 'ROLE_ADMIN') || (rolef eq 'ROLE_COMERCIAL_GERENTE') || (rolef eq 'ROLE_OPERACION') || (rolef eq 'ROLE_AUDITORIA') || (rolef eq 'ROLE_MAQUILA') || (rolef eq 'ROLE_TIF')}">
								<li><a href="<c:url value="ae_formaIngresoCliente" />">
									<i class="icon-globe fa fa-caret-right"></i>
									Listado Clientes
								</a>
								</li>
								<c:if test="${(rolef eq 'ROLE_COMERCIAL') || (rolef eq 'ROLE_ADMIN') || (rolef eq 'ROLE_OPERACION') || (rolef eq 'ROLE_COMERCIAL_GERENTE') || rolef eq 'ROLE_ALMACENISTA' || rolef eq 'ROLE_FACTURACION'}">
									<li>
										<a href="<c:url value="calendarioTunel" />">
											<i class="icon-globe fa fa-caret-right"></i>
											Apartado Tunel
										</a>
									</li>
									<li>
										<a href="<c:url value="formaConsultaClienteCompleto" />">
											<i class="icon-globe fa fa-caret-right"></i>
											Detalle Datos Cliente
										</a>
									</li>
								</c:if>
							</c:if>
						</ul>
					</div>
				</div>
			</div>
		</c:if>
			<c:if test="${(rolef eq 'ROLE_FACTURACION')}">
				<div class="infobox infobox-blue">
					<div class="infobox-icon">
						<i class="icon-barcode"></i>
					</div>
					<div class="infobox-data">
						<div class="infobox-content">Facturacion</div>
						<span class="infobox-data-number"></span>
						<div class="infobox-content">
							<ul>
								<li>
									<a href="<c:url value="bloqueoSalidas"/>">
										<i class="icon-barcode fa fa-caret-right"></i>
										Bloqueo de Salidas
									</a>
								</li>
								<li>
									<a id="facturas2">
										<i class="icon-barcode fa fa-caret-right"></i>
										Creaci&oacute;n de facturas
									</a>
								</li>
								<li>
									<a href="<c:url value="/formEntradasSeguro"/>">
										<i class="icon-barcode fa fa-caret-right"></i>
										Revision de Seguros por RD
									</a>
								</li>
							</ul>
						</div>
					</div>
				</div>
			</c:if>
			<c:if test="${(rolef eq 'ROLE_ALMACENISTA') || (rolef eq 'ROLE_ADMIN') || (rolef eq 'ROLE_OPERACION') || (rolef eq 'ROLE_COMERCIAL') ||(rolef eq 'ROLE_COMERCIAL_GERENTE') || (rolef eq 'ROLE_AUDITORIA') || (rolef eq 'ROLE_FACTURACION') || (rolef eq 'ROLE_MAQUILA') || (rolef eq 'ROLE_TIF')}">
				<div class="infobox infobox-blue">
					<div class="infobox-icon">
						<i class="icon-bar-chart"></i>
					</div>
					<div class="infobox-data">
						<div class="infobox-content">Inventarios</div>
						<span class="infobox-data-number"></span>
						<div class="infobox-content">
							<ul>
								<c:if test="${(rolef eq 'ROLE_COMERCIAL') }">
									<li>
										<a href="<c:url value="/ae_formInventariosComercial" />">
											<i class="icon-bar-chart fa fa-caret-right"></i>
											Consulta Inventario
										</a>
									</li>
								</c:if>
								<c:if test="${(rolef eq 'ROLE_COMERCIAL_GERENTE') }">
									<li>
										<a href="<c:url value="/ae_formInventariosComercial" />">
											<i class="icon-bar-chart fa fa-caret-right"></i>
											Consulta Inventario
										</a>
									</li>
									<li>
										<a href="<c:url value="/ae_formInventarioRD" />">
											<i class="icon-bar-chart fa fa-caret-right"></i>
											Consulta Recibo de Entrada
										</a>
									</li>
									<li>
										<a href="<c:url value="/ae_formInventariosCamara" />">
											<i class="icon-bar-chart fa fa-caret-right"></i>
											Saldos por Camaras y Tunel
										</a>
									</li>
									<li>
										<a href="<c:url value="/ae_formInventariosCliente" />">
											<i class="icon-bar-chart fa fa-caret-right"></i>
											Saldos por Cliente
										</a>
									</li>
									<li>
										<a href="<c:url value="/ae_formInventariosTodaCamaras" />">
											<i class="icon-bar-chart fa fa-caret-right"></i>
											Inventario Todas las Camaras
										</a>
									</li>
								</c:if>
								<c:if test="${(rolef eq 'ROLE_ALMACENISTA') || (rolef eq 'ROLE_ADMIN') || (rolef eq 'ROLE_MAQUILA') || (rolef eq 'ROLE_OPERACION') || (rolef eq 'ROLE_AUDITORIA') || (rolef eq 'ROLE_FACTURACION') || (rolef eq 'ROLE_TIF')}">
									<li>
										<a href="<c:url value="/ae_formInventarios" />">
											<i class="icon-bar-chart fa fa-caret-right"></i>
											Consulta Inventario
										</a>
									</li>
									<li>
										<a href="<c:url value="/ae_formInventarioRD" />">
											<i class="icon-bar-chart fa fa-caret-right"></i>
											Consulta Recibo de Entrada
										</a>
									</li>
									<li>
										<a href="<c:url value="/formInventariosTif" />">
											<i class="icon-bar-chart fa fa-caret-right"></i>
											Consulta Inventario TIF
										</a>
									</li>
								<c:if test="${(rolef eq 'ROLE_ALMACENISTA') || (rolef eq 'ROLE_ADMIN') || (rolef eq 'ROLE_OPERACION')  || (rolef eq 'ROLE_FACTURACION') || (rolef eq 'ROLE_AUDITORIA') || (rolef eq 'ROLE_MAQUILA')}">
									<li>
										<a href="<c:url value="/ae_formFormatosUnicos" />">
											<i class="icon-bar-chart fa fa-caret-right"></i>
											Formatos Unicos
										</a>
									</li>
									</c:if>
								<c:if test="${(rolef eq 'ROLE_ALMACENISTA') || (rolef eq 'ROLE_ADMIN') || (rolef eq 'ROLE_OPERACION') || (rolef eq 'ROLE_AUDITORIA') || (rolef eq 'ROLE_FACTURACION') || (rolef eq 'ROLE_MAQUILA')|| (rolef eq 'ROLE_AUDITORIA')}">
									<c:if test="${(rolef eq 'ROLE_ALMACENISTA') || (rolef eq 'ROLE_ADMIN') || (rolef eq 'ROLE_OPERACION') || (rolef eq 'ROLE_AUDITORIA') || (rolef eq 'ROLE_FACTURACION') || (rolef eq 'ROLE_MAQUILA')}">
									<li>
										<a href="<c:url value="/ae_formCitroFrut" />">
											<i class="icon-bar-chart fa fa-caret-right"></i>
											Consultar Marca y Lote Citrofrut
										</a>
									</li>
										</c:if>
									<li>
										<a href="<c:url value="/ae_formInventariosCamara" />">
											<i class="icon-bar-chart fa fa-caret-right"></i>
											Saldos por Camaras y Tunel
										</a>
									</li>
									<li>
										<a href="<c:url value="/ae_formInventariosCliente" />">
											<i class="icon-bar-chart fa fa-caret-right"></i>
											Saldos por Cliente
										</a>
									</li>
									<li>
										<a href="<c:url value="/ae_formInventariosTodaCamaras" />">
											<i class="icon-bar-chart fa fa-caret-right"></i>
											Inventario Todas las Camaras
										</a>
									</li>
									</c:if>
								</c:if>
							</ul>
						</div>
					</div>
				</div>
			</c:if>

			<c:if test="${(rolef eq 'ROLE_FACTURACION') ||(rolef eq 'ROLE_ALMACENISTA') || (rolef eq 'ROLE_OPERACION') || (rolef eq 'ROLE_ADMIN') ||(rolef eq 'ROLE_COMERCIAL_GERENTE') || (rolef eq 'ROLE_AUDITORIA') || (rolef eq 'ROLE_TIF')}">
				<div class="infobox infobox-blue">
					<div class="infobox-icon">
						<i class="icon-book"></i>
					</div>
					<div class="infobox-data">
						<div class="infobox-content">Reportes</div>
						<span class="infobox-data-number"></span>
						<div class="infobox-content">
							<ul>
								<c:if test="${(rolef eq 'ROLE_COMERCIAL_GERENTE') }">
									<li>
										<a href="<c:url value="aes_formReporteEntrada" />">
											<i class="icon-book fa fa-caret-right"></i>
											Reporte de Entradas
										</a>
									</li>
									<li>
										<a href="<c:url value="aes_formReporteSalida" />">
											<i class="icon-book fa fa-caret-right"></i>
											Reporte de Salidas
										</a>
									</li>
									<li class="hover">
										<a href="<c:url value="ae_reporteVentasEjecutivo" />">
											<i class="icon-book fa fa-caret-right"></i>
											Reporte de Ventas por Ejecutivo
										</a>
									</li>
									<li>
										<a href="<c:url value="ae_reporteVentasEjecutivoMes" />">
											<i class="icon-book fa fa-caret-right"></i>
											Reporte de Ventas por Mes
										</a>
									</li>
								</c:if>
								<c:if test="${(rolef eq 'ROLE_FACTURACION') || (rolef eq 'ROLE_OPERACION') ||(rolef eq 'ROLE_ALMACENISTA') || (rolef eq 'ROLE_ADMIN') || (rolef eq 'ROLE_AUDITORIA')|| (rolef eq 'ROLE_TIF')}">
								<li>
									<a href="<c:url value="aes_formReporteEntrada" />">
										<i class="icon-book fa fa-caret-right"></i>
										Reporte de Entradas
									</a>
								</li>
								<li>
									<a href="<c:url value="aes_formReporteSalida" />">
										<i class="icon-book fa fa-caret-right"></i>
										Reporte de Salidas
									</a>
								</li>
									<c:if test="${(rolef eq 'ROLE_FACTURACION') || (rolef eq 'ROLE_OPERACION') ||(rolef eq 'ROLE_ALMACENISTA') || (rolef eq 'ROLE_ADMIN') }">
								<li>
									<a href="<c:url value="ae_consultaEntradasFormCompara" />">
										<i class="icon-book fa fa-caret-right"></i>
										Reporte Compara ingresos
									</a>
								</li>
								</c:if>
									<c:if test="${(rolef eq 'ROLE_COMERCIAL') }">
										<li>
											<a href="<c:url value="aes_formReporteEntrada" />">
												Reporte de Entradas
											</a>
											<b class="arrow"></b>
										</li>
										<li>
											<a href="<c:url value="aes_formReporteSalida" />">
												Reporte de Salidas
											</a>
											<b class="arrow"></b>
										</li>
										<li>
											<a href="<c:url value="ae_reporteVentasEjecutivoId" />">
												Reporte de Ventas por Ejecutivo
											</a>
											<b class="arrow"></b>
										</li>
									</c:if>
								<c:if test="${(rolef eq 'ROLE_ADMIN') || (rolef eq 'ROLE_FACTURACION')}">
									<li class="hover">
										<a href="<c:url value="ae_reporteVentasEjecutivo" />">
											<i class="icon-book fa fa-caret-right"></i>
											Reporte de Ventas por Ejecutivo
										</a>
									</li>
									<li>
										<a href="<c:url value="ae_reporteVentasEjecutivoMes" />">
											<i class="icon-book fa fa-caret-right"></i>
											Reporte de Ventas por Mes
										</a>
									</li>
									<li>
										<a href="<c:url value="/formgetFacturasRD" />">
											<i class="icon-book fa fa-caret-right"></i>
											Reporte de Facturas por RD
										</a>
									</li>
									<li>
										<a href="<c:url value="/formgetFacturasFecha" />">
											<i class="icon-book fa fa-caret-right"></i>
											Reporte de Facturas por D&iacute;a
										</a>
									</li>

									<li>
										<a href="<c:url value="/formFacturasDiarias" />">
											<i class="icon-book fa fa-caret-right"></i>
											Reporte Facturas por Rubro
										</a>
									</li>
								</c:if>
									<c:if test="${(rolef eq 'ROLE_FACTURACION') || (rolef eq 'ROLE_OPERACION') ||(rolef eq 'ROLE_ALMACENISTA') || (rolef eq 'ROLE_ADMIN') || (rolef eq 'ROLE_AUDITORIA')}">
										<c:if test="${(rolef eq 'ROLE_FACTURACION')|| (rolef eq 'ROLE_ADMIN') || (rolef eq 'ROLE_AUDITORIA')}">
										<li>
											<a href="<c:url value="/formFacturasImpresas" />">
												<i class="icon-book fa fa-caret-right"></i>
												Reporte de Facturacion Impresa
											</a>
										</li>
											</c:if>
										<c:if test="${(rolef eq 'ROLE_FACTURACION')|| (rolef eq 'ROLE_ADMIN')}">
								<li>
									<a href="<c:url value="ae_InOutFormVehiculo" />">
										<i class="icon-book fa fa-caret-right"></i>
										Tiempos de Respuesta
									</a>
								</li>
											</c:if>
										</c:if>
								</c:if>
							</ul>
						</div>
					</div>
				</div>
			</c:if>

			<c:if test="${(rolef eq 'ROLE_FACTURACION') ||(rolef eq 'ROLE_ALMACENISTA') || (rolef eq 'ROLE_ADMIN') || (rolef eq 'ROLE_OPERACION')}">
				<div class="infobox infobox-blue">
					<div class="infobox-icon">
						<i class="icon-film"></i>
					</div>
					<div class="infobox-data">
						<div class="infobox-content">Impresiones</div>
						<span class="infobox-data-number"></span>
						<div class="infobox-content">
							<ul>
								<li>
									<a href="<c:url value="/alm_consultaEntradasImp" />">
										<i class="icon-film fa fa-caret-right"></i>
										Impresi&oacute;n de Entradas
									</a>
								</li>
								<li>
									<a href="<c:url value="alm_consultaSalidasImp" />">
										<i class="icon-film fa fa-caret-right"></i>
										Impresi&oacute;n de Salidas
									</a>
								</li>
							</ul>
						</div>
					</div>
				</div>
			</c:if>

			<div class="infobox infobox-blue">
				<div class="infobox-icon">
					<i class="icon-off"></i>
				</div>
				<div class="infobox-data">
					<div class="infobox-content">Salir</div>
					<span class="infobox-data-number"></span>
					<div class="infobox-content">
						<a class="dropdown-toggle" href="<c:url value="j_spring_security_logout" />" >
							<i class="icon-off fa fa-caret-right bigger-150"></i>
							<span class="menu-text">Salir</span>
						</a>
					</div>
				</div>
			</div>
		</div>
	</div>
