<%@ include file="/WEB-INF/views/include.jsp" %>

<sec:authentication property="authorities" var="roles" scope="page" />
<c:forEach var="role" items="${roles}">
	<c:set var="rolef" value="${role}"/>
</c:forEach>
<ul class="nav nav-tabs" >
  <li role="presentation" class="dropdown">
	<a class="dropdown-toggle" href="<c:url value="home"/>" >
	 <i class="icon-home fa fa-caret-right  bigger-150"></i>
	  <span class="menu-text">Inicio</span>
	</a>
	<b class="arrow"></b>
  </li>
<c:if test="${(rolef eq 'ROLE_VIGILANTE') || (rolef eq 'ROLE_ADMIN')}">
  <li role="presentation" class="dropdown">
	<a class="dropdown-toggle" data-toggle="dropdown" href="#" role="button" aria-expanded="false">
		<i class="icon-download-alt  fa fa-caret-right bigger-150"></i>
		<span class="menu-text"> Vigilancia <span class="caret"></span></span>
		<b class="arrow fa fa-angle-down"></b>
	</a>
		<b class="arrow"></b>
		<ul class="dropdown-menu" role="menu">
			<li class="hover">
				<a href="<c:url value="v_ingresoPreVehiculo"/>">
					<i class="icon-download-alt  fa fa-caret-right"></i>
					Pre-Registro de Veh&iacute;culos
				</a>
				<b class="arrow"></b>
			</li>
			<li class="hover">
				<a href="<c:url value="v_listaPreVehiculo"/>">
					<i class="icon-download-alt  fa fa-caret-right"></i>
					Consulta de Veh&iacute;culos
				</a>
				<b class="arrow"></b>
			</li>
		</ul><div style="display: none; top: 69px; left: 183px;" class="scroll-track scroll-detached no-track scroll-thin scroll-margin scroll-visible"><div style="top: 0px;" class="scroll-bar"></div></div>
  </li>
</c:if>
<c:if test="${(rolef eq 'ROLE_ADMISION') || (rolef eq 'ROLE_ADMIN')}">
  <li role="presentation" class="dropdown">
	<a class="dropdown-toggle" data-toggle="dropdown" href="#" role="button" aria-expanded="false">
		<i class="icon-exchange  fa fa-caret-right bigger-150"></i>
		<span class="menu-text"> Ingresos y Salidas <span class="caret"></span></span>
		<b class="arrow fa fa-angle-down"></b>
	</a>
		<b class="arrow"></b>
		<ul class="dropdown-menu" role="menu">
		<c:if test="${(rolef eq 'ROLE_ADMISION') || (rolef eq 'ROLE_ADMIN')}">
			<li class="hover">
				<a href="<c:url value="adm_ingresoVehiculo"/>">
					<i class="icon-exchange fa fa-caret-right"></i>
					Registra Ingreso de Veh&iacute;culos
				</a>
				<b class="arrow"></b>
			</li>
		</c:if>
	    <c:if test="${(rolef eq 'ROLE_ALMACENISTA') || (rolef eq 'ROLE_ADMIN')}">
			<li class="hover">
				<a href="<c:url value="/alm_consultaIngresos" />">
					<i class="icon-exchange fa fa-caret-right"></i>
						Captura Ingresos
				</a>
				<b class="arrow"></b>
			</li>
		</c:if>
		<c:if test="${(rolef eq 'ROLE_ALMACENISTA') || (rolef eq 'ROLE_ADMIN')}">
			<li class="hover">
				<a href="<c:url value="/alm_consultaSalidas" />">
					<i class="icon-exchange fa fa-caret-right"></i>
						Captura Salidas
				</a>
				<b class="arrow"></b>
			</li>
		</c:if>
	    <c:if test="${(rolef eq 'ROLE_ADMISION') || (rolef eq 'ROLE_ADMIN')}">
			<li class="hover">
				<a href="<c:url value="adm_salidaVehiculo"/>">
					<i class="icon-exchange fa fa-caret-right"></i>
					Registro Salida de Veh&iacute;culos
				</a>
				<b class="arrow"></b>
			</li>
		</c:if>
		</ul><div style="display: none; top: 69px; left: 183px;" class="scroll-track scroll-detached no-track scroll-thin scroll-margin scroll-visible"><div style="top: 0px;" class="scroll-bar"></div></div>
  </li>
</c:if>
<c:if test="${(rolef eq 'ROLE_ALMACENISTA') || (rolef eq 'ROLE_ADMIN')}">
	<li role="presentation" class="dropdown">
		<a class="dropdown-toggle" data-toggle="dropdown" href="#" role="button" aria-expanded="false">
			<i class="icon-adjust  fa fa-caret-right bigger-150"></i>
			<span class="menu-text"> Ubicaciones <span class="caret"></span></span>
			<b class="arrow fa fa-angle-down"></b>
		</a>
		<b class="arrow"></b>
		<ul class="dropdown-menu" role="menu">
			<li class="hover">
				<a href="<c:url value="/alm_regUbicaciones" />">
					<i class="icon-adjust fa fa-caret-right"></i>
					Captura Ubicaci&oacute;n
				</a>
				<b class="arrow"></b>
			</li>
			<li class="hover">
				<a href="<c:url value="/alm_getUbicaciones" />">
					<i class="icon-adjust fa fa-caret-right"></i>
					Consulta Ubicaci&oacute;n
				</a>
				<b class="arrow"></b>
			</li>
		</ul><div style="display: none; top: 69px; left: 183px;" class="scroll-track scroll-detached no-track scroll-thin scroll-margin scroll-visible"><div style="top: 0px;" class="scroll-bar"></div></div>
	</li>
</c:if>
<c:if test="${(rolef eq 'ROLE_OPERACION') || (rolef eq 'ROLE_ADMIN')}">
  <li role="presentation" class="dropdown">
	<a class="dropdown-toggle" data-toggle="dropdown" href="#" role="button" aria-expanded="false">
		<i class="icon-dashboard fa fa-caret-right bigger-150"></i>
		<span class="menu-text"> Operaciones <span class="caret"></span></span>
		<b class="arrow fa fa-angle-down"></b>
	</a>
		<b class="arrow"></b>
		<ul class="dropdown-menu" role="menu">
			
			<li class="hover">
				<a href="<c:url value="ae_traspasos"/>">
					<i class="icon-dashboard fa fa-caret-right"></i>
					Traspasos Camara y T&uacute;nel
				</a>
				<b class="arrow"></b>
			</li>
			<li class="hover">
				<a href="<c:url value="ae_mandaRetemes" />">
					<i class="icon-dashboard fa fa-caret-right"></i>
					Captura Retemes
				</a>
				<b class="arrow"></b>
			</li>
			<li class="hover">
				<a href="<c:url value="ae_consultaRetemes" />">
					<i class="icon-dashboard fa fa-caret-right"></i>
					Consulta Retemes
				</a>
				<b class="arrow"></b>
			</li>
		</ul><div style="display: none; top: 69px; left: 183px;" class="scroll-track scroll-detached no-track scroll-thin scroll-margin scroll-visible"><div style="top: 0px;" class="scroll-bar"></div></div>
  </li>
</c:if>
<c:if test="${(rolef eq 'ROLE_COMERCIAL') || (rolef eq 'ROLE_ADMIN')}">
  <li role="presentation" class="dropdown">
			<a class="dropdown-toggle" data-toggle="dropdown" href="#" role="button" aria-expanded="false">
				<i class="icon-globe fa fa-caret-right bigger-150"></i>
				<span class="menu-text"> Comercial <span class="caret"></span></span>
				<b class="arrow fa fa-angle-down"></b>
			</a>
			<b class="arrow fa fa-angle-down"></b>
				<ul class="dropdown-menu" role="menu">
					<li class="hover">
						<!--a href="<c:url value="ae_formaIngresoProspecto" />"-->
						<a href="<c:url value="ae_formaProspecto" />">
							<i class="icon-globe fa fa-caret-right"></i>
							Ingreso Prospecto
						</a>
						<b class="arrow"></b>
					</li>
					<li class="hover">
						<!--a href="<c:url value="ae_formaIngresoProspecto" />"-->
						<a href="<c:url value="ae_consultaProspectoAutorizado" />">
							<i class="icon-globe fa fa-caret-right"></i>
							Prospectos Autorizados
						</a>
						<b class="arrow"></b>
					</li>
					<li class="hover">
						<!--a href="<c:url value="/ae_ListaSeguimiento" />"-->
						<a href="<c:url value="/ae_ListaSeguimiento" />">
							<i class="icon-globe fa fa-caret-right"></i>
							Lista de Seguimientos
						</a>
						<b class="arrow"></b>
					</li>
					<li class="hover">
						<a href="<c:url value="ae_formaIngresoCliente" />">
							<i class="icon-globe fa fa-caret-right"></i>
							Listado Clientes
						</a>
						<b class="arrow"></b>
					</li>
					<li class="hover">
						<a href="<c:url value="ae_controlTunel" />">
							<i class="icon-globe fa fa-caret-right"></i>
							Apartado Tunel
						</a>
						<b class="arrow"></b>
					</li>
					<li class="hover">
						<a href="<c:url value="formaConsultaClienteCompleto" />">
							<i class="icon-globe fa fa-caret-right"></i>
							Detalle Datos Cliente
						</a>
						<b class="arrow"></b>
					</li>
			</ul>
			<div style="display: none; top: 69px; left: 183px;" class="scroll-track scroll-detached no-track scroll-thin scroll-margin scroll-visible"><div style="top: 0px;" class="scroll-bar"></div></div>
		</li>
</c:if>
<c:if test="${(rolef eq 'ROLE_ALMACENISTA') || (rolef eq 'ROLE_ADMIN')}">
 <li role="presentation" class="dropdown">
			<a class="dropdown-toggle" data-toggle="dropdown" href="#" role="button" aria-expanded="false">
				<i class="icon-bar-chart fa fa-caret-right bigger-150"></i>
				<span class="menu-text"> Inventarios <span class="caret"></span></span>
				<b class="arrow fa fa-angle-down"></b>
			</a>
			<b class="arrow fa fa-angle-down"></b>
				<ul class="dropdown-menu" role="menu">
					<li class="hover">
						<a href="<c:url value="/ae_formInventarios" />">
							<i class="icon-bar-chart fa fa-caret-right"></i>
							Consulta Inventario
						</a>
						<b class="arrow"></b>
					</li>
					<li class="hover">
						<a href="<c:url value="/ae_formInventariosCamara" />">
							<i class="icon-bar-chart fa fa-caret-right"></i>
							Saldos por Camaras y Tunel
						</a>
						<b class="arrow"></b>
					</li>
					<li class="hover">
						<a href="<c:url value="/ae_formInventariosCliente" />">
							<i class="icon-bar-chart fa fa-caret-right"></i>
							Saldos por Cliente
						</a>
						<b class="arrow"></b>
					</li>
                    <li class="hover">
                        <a href="<c:url value="/ae_formInventariosTodaCamaras" />">
                            <i class="icon-bar-chart fa fa-caret-right"></i>
                            Inventario Todas las Camaras
                        </a>
                        <b class="arrow"></b>
                    </li>
			</ul>
			<div style="display: none; top: 69px; left: 183px;" class="scroll-track scroll-detached no-track scroll-thin scroll-margin scroll-visible"><div style="top: 0px;" class="scroll-bar"></div></div>
		</li>
</c:if>
<c:if test="${(rolef eq 'ROLE_ALMACENISTA') || (rolef eq 'ROLE_ADMIN')}">
  	<li role="presentation" class="dropdown">
			<a class="dropdown-toggle" data-toggle="dropdown" href="#" role="button" aria-expanded="false">
				<i class="icon-book fa fa-caret-right bigger-150"></i>
				<span class="menu-text"> Reportes <span class="caret"></span></span>
				<b class="arrow fa fa-angle-down"></b>
			</a>
			<b class="arrow fa fa-angle-down"></b>
				<ul class="dropdown-menu" role="menu">
					<li class="hover">
						<a href="<c:url value="aes_formReporteEntrada" />">
							<i class="icon-book fa fa-caret-right"></i>
							Reporte de Entradas
						</a>
						<b class="arrow"></b>
					</li>
					<li class="hover">
						<a href="<c:url value="aes_formReporteSalida" />">
							<i class="icon-book fa fa-caret-right"></i>
							Reporte de Salidas
						</a>
						<b class="arrow"></b>
					</li>
					<li class="hover">
						<a href="<c:url value="ae_consultaEntradasFormCompara" />">
							<i class="icon-book fa fa-caret-right"></i>
							Reporte Compara ingresos
						</a>
						<b class="arrow"></b>
					</li>
					<li class="hover">
						<a href="<c:url value="ae_InOutFormVehiculo" />">
							<i class="icon-book fa fa-caret-right"></i>
							Tiempos de Respuesta
						</a>
						<b class="arrow"></b>
					</li>
			</ul>
			<div style="display: none; top: 69px; left: 183px;" class="scroll-track scroll-detached no-track scroll-thin scroll-margin scroll-visible"><div style="top: 0px;" class="scroll-bar"></div></div>
		</li>
</c:if>
<c:if test="${(rolef eq 'ROLE_ALMACENISTA') || (rolef eq 'ROLE_ADMIN')}">
	<li role="presentation" class="dropdown">
			<a class="dropdown-toggle" data-toggle="dropdown" href="#" role="button" aria-expanded="false">
				<i class="icon-film fa fa-caret-right bigger-150"></i>
				<span class="menu-text"> Impresiones <span class="caret"></span></span>
				<b class="arrow fa fa-angle-down"></b>
			</a>
			<b class="arrow fa fa-angle-down"></b>
				<ul class="dropdown-menu" role="menu">
					<li class="hover">
						<a href="<c:url value="/alm_consultaEntradasImp" />">
							<i class="icon-film fa fa-caret-right"></i>
							Impresi&oacute;n de Entradas
						</a>
						<b class="arrow"></b>
					</li>
					<li class="hover">
						<a href="<c:url value="alm_consultaSalidasImp" />">
							<i class="icon-film fa fa-caret-right"></i>
							Impresi&oacute;n de Salidas
						</a>
						<b class="arrow"></b>
					</li>
			</ul>
			<div style="display: none; top: 69px; left: 183px;" class="scroll-track scroll-detached no-track scroll-thin scroll-margin scroll-visible"><div style="top: 0px;" class="scroll-bar"></div></div>
		</li>
</c:if>
		<li role="presentation" class="dropdown">
			<a class="dropdown-toggle" href="<c:url value="j_spring_security_logout" />" >
			 <i class="icon-off fa fa-caret-right bigger-150"></i>
			  <span class="menu-text">Salir</span>
			</a>
			<b class="arrow"></b>
 		 </li>

</ul>
