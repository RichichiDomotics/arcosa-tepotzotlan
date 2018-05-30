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
		}else{
			$("#CapSeg").html("");
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
});

</script>
<!--div class="wrapper p4"-->
	<!--div class="navbar"-->
		<!--div class="navbar-inner">
			<!--div class="container-fluid"-->
			<c:if test="${MensajesTodosRec eq '[]'}">
				NO EXISTEN MENSAJES PARA ESTE USUARIO
			</c:if>
			<c:if test="${MensajesTodosRec ne '[]'}">
				<ul >
					<!--*************************************************************************-->
					<c:if test="${(rolef eq 'ROLE_JEFEEJE') || (rolef eq 'ROLE_EJECUTIVO')}">
					<li>

						<ul class="nav ace-nav pull-left badge-grey" >

								<c:forEach items="${MensajesTodosRec}" var="g">
									<!--****************************************-->
									<li>
										<a href="javascript:veMensajeXid('${g.idMensaje}')">
										<span class="msg-body">
											<span class="msg-title blue">
												<span class="blue">${g.usuarioEnvia} :</span>
												${fn:substring(g.mensaje,0,35)}...
											</span>

											<span class="msg-time blue">
												<i class="icon-time blue"></i>
												<span>Enviado: <fmt:parseDate value="${g.fecHoraEnviado}" var="parsedEmpDate" pattern="yyyy-MM-dd H:m:s" /><fmt:formatDate pattern="dd-MM-yyyy H:m:s" value="${parsedEmpDate}" /></span>
											</span>
										</span>
										</a>
									</li>
									<!--****************************************-->
								</c:forEach>

						</ul>
					</li>
					</c:if>
					</ul--><!--/.ace-nav-->
				</c:if>
			<!--/div><!--/.container-fluid-->
		<!--/div><!--/.navbar-inner-->
	<!--/div><!--/.navbar-->
<!--/div><!--/.wrapper p4-->
<div id="mensajeForma"></div>