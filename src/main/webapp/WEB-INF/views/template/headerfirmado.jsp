<%@ include file="/WEB-INF/views/include.jsp" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<sec:authentication property="authorities" var="roles" scope="page" />
<c:forEach var="role" items="${roles}">
	<c:set var="rolef" value="${role}"/>
</c:forEach>
<script>
$(document).ready(function(){
	veMensajeXid = function(idMensaje){
		if(!$("#CapSeg").length){
			$("#mensajeForma").append('<div id="CapSeg"></div>')
		}
		$.ajax("RecuperaMensaje", {
			"type": "POST",
			"beforeSend": function() {
				$("#CapSeg").css("font-size","27px");
				$("#CapSeg").html('Cargando ...');
			},
			"success": function(result){
				$("#CapSeg").css("font-size","14px");
				//$("#detCliente").html(result);
				bootbox.dialog($("#CapSeg").html(result), [{
							"label" : "Cerrar",
							"class" : "btn-small btn-primary",
							"callback": function() {
								$(location).attr('href','/home');
								$("#CapSeg").html("");
							}
						}]
				).css({"width":"800px","heith":"400px","left":"550px","overflow-y": "hidden"});
				return false;
			},
			"error": function(result) {
				alert("Error al recuperar la información", result);
				$("#CapSeg").html("");
			},
			"data": {idMensaje:idMensaje},
			"async": true
		});
	}

	enviaMensaje = function(){
		if(!$("#CapSeg").length){
			$("#mensajeForma").append('<div id="CapSeg"></div>')
		}
		$.ajax("CapturaMensaje", {
			"type": "POST",
			"beforeSend": function() {
				$("#CapSeg").css("font-size","27px");
				$("#CapSeg").html('Cargando ...');
			},
			"success": function(result){
				$("#CapSeg").css("font-size","14px");
				//$("#detCliente").html(result);
				bootbox.dialog($("#CapSeg").html(result), [{
							"label" : "Cerrar",
							"class" : "btn-small btn-primary",
							"callback": function() {
								$("#CapSeg").html("");
							}
						}]
				).css({"width":"800px","heith":"360px","left":"550px","overflow-y": "hidden"});

				return false;
			},
			"error": function(result) {
				alert("Error al recuperar la información", result);
				$("#CapSeg").html("");
			},
			"data": {idProspecto:0},
			"async": true
		});
	}

	marcarvisto = function(tipoAviso){
		$.ajax("cambiaVisto", {
			"type": "POST",
			"success": function(result) {
				window.location.href=window.location;
				//$(location).attr('href','/home');
			},
			"error": function(result) {
				alert("Error al Modificar como visto :" + result);
			},
			"data": {tipoAviso : tipoAviso},
			"async": true
		});
		//return false;
	}


	recTodosLosMensajes = function(){
		if(!$("#CapSeg").length){
			$("#mensajeForma").append('<div id="CapSeg"></div>')
		}else{
			$("#CapSeg").html("");
		}

		$.ajax("RecuperaTodosMensajes", {
			"type": "POST",
			"beforeSend": function() {
				$("#CapSeg").css("font-size","27px");
				$("#CapSeg").html('Cargando ...');
			},
			"success": function(result){
				$("#CapSeg").css("font-size","14px");
				//$("#detCliente").html(result);
				bootbox.dialog($("#CapSeg").html(result), [{
							"label" : "Cerrar",
							"class" : "btn-small btn-primary",
							"callback": function() {
								$(location).attr('href','/home');
								$("#CapSeg").html("");
							}
						}]
				).css({"width":"800px","heith":"350px","left":"550px","overflow-y": "hidden"});
				return false;
			},
			"error": function(result) {
				alert("Error al recuperar la información", result);
				$("#CapSeg").html("");
			},
			"data":{},
			"async": true
		});
	}


});

function recrutaInicial(){
	var RUTA_WEB_SERVIDOR = location.protocol + "//" + location.host + "/home";
	window.location.href = RUTA_WEB_SERVIDOR;
	//return RUTA_WEB_SERVIDOR;
}

</script>
<!--==============================header=================================-->
<div class="wrapper p4">
		<div class="navbar">
			<div class="navbar-inner">
				<div class="container-fluid">
					<a href='javascript:recrutaInicial();' class="brand">
						<small>
							<img class="nav-user-photo" src="resources/img/logo-arcosa.png" alt="Arcosa" />
							<i class="textocabecera icon-leaf"></i>
							Gesti&oacute;n de Inventarios Arcosa.
						</small>
					</a><!--/.brand-->

					<ul class="nav ace-nav pull-right">
<!--*************************************************************************-->
					<c:if test="${(rolef eq 'ROLE_JEFEEJE') || (rolef eq 'ROLE_EJECUTIVO')}">
						<li class="default">
							<a data-toggle="dropdown" class="dropdown-toggle" href="#">
								<i class="icon-comments"></i>
								<span class="badge badge-grey">
								   <c:if test="${MensajesTodos ne '[]'}">
								   		${MensajesTodos.size()}
								   </c:if>
								   <c:if test="${MensajesTodos eq '[]'}">
										0
								   </c:if>
								</span>
							</a>

							<ul class="pull-right dropdown-navbar navbar-grey dropdown-menu dropdown-caret dropdown-closer">
								<li class="nav-header">
									<a href="javascript:enviaMensaje();">
										<i class="icon-comments"></i>
										<span class="badge badge-grey">Enviar un Mensaje</span>
									</a>
									<i class="icon-comments"></i>
										<c:if test="${MensajesTodos ne '[]'}">
											${MensajesTodos.size()} Nuevos Mensajes
										</c:if>
										<c:if test="${MensajesTodos eq '[]'}">
											0 Nuevos Mensajes
										</c:if>


								</li>
								<c:if test="${MensajesTodos ne '[]'}">
								<c:forEach items="${MensajesTodos}" var="g">
									<!--****************************************-->
									<li>
										<a href="javascript:veMensajeXid('${g.idMensaje}')">
											<!--img src="assets/avatars/avatar.png" class="msg-photo" alt="Alex's Avatar" /-->
										<span class="msg-body">
											<span class="msg-title">
												<span class="blue">${g.usuarioEnvia} :</span>
												<!--Ciao sociis natoque penatibus et auctor ...-->
												${fn:substring(g.mensaje,0,35)}...
											</span>

											<span class="msg-time">
												<i class="icon-time"></i>
												<span>Enviado: <fmt:parseDate value="${g.fecHoraEnviado}" var="parsedEmpDate" pattern="yyyy-MM-dd H:m:s" /><fmt:formatDate pattern="dd-MM-yyyy H:m:s" value="${parsedEmpDate}" /></span>
											</span>
										</span>
										</a>
									</li>
									<!--****************************************-->
								</c:forEach>
								</c:if>
								<li>
									<sec:authentication property="principal.username" var="username" />
									<a href='javascript:recTodosLosMensajes();'>
										Ver todos los mensajes
										<i class="icon-arrow-right"></i>
									</a>
								</li>
							</ul>
						</li>
						</c:if>
						<c:if test="${(rolef eq 'ROLE_JEFEEJE')}">
						<li class="default">
							<a data-toggle="dropdown" class="dropdown-toggle" href="#">
								<i class="icon-bell-alt icon-animated-bell"></i>
								<span class="badge badge-grey">
									<c:choose>
										<c:when test="${totalAvisos > 0}">
											${totalAvisos}
										</c:when>
										<c:otherwise>
											0
										</c:otherwise>
									</c:choose>
								</span>
							</a>

							<ul class="pull-right dropdown-navbar navbar-grey dropdown-menu dropdown-caret dropdown-closer">
								<li class="nav-header">
									<i class="icon-warning-sign"></i>
									<c:choose>
										<c:when test="${totalAvisos > 0}">
											${totalAvisos}
										</c:when>
										<c:otherwise>
											0
										</c:otherwise>
									</c:choose>
								</li>
								<c:set var="linkllama" value=""/>
								<c:forEach items="${listaAvisos}" var="e">
									<c:choose>
										<c:when test="${e[1] eq 'Nuevo Prospecto'}">
											<c:set var="linkllama" value="ae_formaProspectoAutorizadoJefe"/>
										</c:when>
										<c:when test="${e[1] eq 'Cambio de Etapa'}">
											<c:set var="linkllama" value="ae_formaProspectoSeguimientoJefe"/>
										</c:when>
										<c:when test="${e[1] eq 'Nuevo Seguimiento'}">
											<c:set var="linkllama" value="ae_formaProspectoSeguimientoJefe"/>
										</c:when>
										<c:when test="${e[1] eq 'Solicitud de Reactivacion'}">
											<c:set var="linkllama" value="ae_formaProspectoReAutorizaJefe"/>
										</c:when>
										<c:when test="${e[1] eq 'Observacion Seguimiento'}">
											<c:set var="linkllama" value="ae_formaProspectoReAutorizaJefe"/>
										</c:when>
										<c:when test="${e[1] eq 'Prospecto Desasignado'}">
											<c:set var="linkllama" value="ae_formaProspectoDesasignado"/>
										</c:when>
									</c:choose>
								<li>
									<a title="Marcar como visto ${e[1]}" href="javascript:marcarvisto('${e[1]}');">
										<div class="clearfix">
										   <span class="pull-right badge badge-info">
											   <i class="pull-right badge badge-info  icon-eye-open">&nbsp;Marca Visto</i>
										   </span>
										</div>
									</a>
									<a href="<c:url value="${linkllama}" />">
										<div class="clearfix">
											<span class="pull-left">
												<i class="btn btn-mini no-hover btn-info icon-bell-alt icon-animated-bell"></i>
												${e[1]}
											</span>
											<span class="pull-right badge badge-info">+${e[0]}</span>
										</div>
									</a>
										<div>
											<table>
												<c:forEach items="${listaAvisosDetalle}" var="f">
												<c:if test="${e[1] eq f[2]}">
													<tr>
														<td><small><b>${f[1]}&nbsp;<fmt:parseDate value="${f[3]}" var="parsedEmpDatedd" pattern="yyyy-MM-dd H:m:s" /><fmt:formatDate pattern="dd-MM-yyyy H:m:s" value="${parsedEmpDatedd}" /></b></small></td>
													</tr>
												</c:if>
												</c:forEach>
											</table>
										</div>
								</li>
								</c:forEach>

							</ul>
						</li>
						</c:if>

<!--****************************************************************-->
						<li class="light-blue">
							<a data-toggle="dropdown" href="#" class="dropdown-toggle">
								<!-- img class="nav-user-photo" src="assets/avatars/user.jpg" alt="Foto de ${user}" /-->
								<span class="user-info">
									<small>Bienvenido,</small>
									<sec:authentication property="principal.username" />
								</span>
                                <i class="icon-caret-down"></i>
                                
							</a>

							<ul class="user-menu pull-right dropdown-menu dropdown-blue dropdown-caret dropdown-closer">

								<li>
									<a href="#">
										<i class="icon-cog"></i>
										<sec:authentication property="authorities" var="roles" scope="page" />
										<c:forEach var="role" items="${roles}">
											${role}
                                   		</c:forEach>
									</a>
								</li>
								
								<li class="divider"></li>

								<li>
									<a href="<c:url value="j_spring_security_logout" />">
										<i class="icon-off"></i>
										Salir
									</a>
								</li>
							</ul>
						</li>
					</ul--><!--/.ace-nav-->
				</div><!--/.container-fluid-->
			</div><!--/.navbar-inner-->
		</div><!--/.navbar-->
   </div><!--/.wrapper p4-->
<div id="mensajeForma"></div>