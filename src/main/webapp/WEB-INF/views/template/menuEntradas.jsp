<%@ include file="/WEB-INF/views/include.jsp" %>

		<ul class="nav nav-justified">
			<li class="active"><a href="<c:url value="home"/>">Inicio</a></li>
			<li><a href="<c:url value="elaboracionRecibos" />">Elaboracion de Recibos</a></li>
			<li><a href="<c:url value="impresionRecibos" />">Impresion de Recibos</a></li>
			<li><a href="<c:url value="capturaVerificada" />">Captura Verificada de Recibos</a></li>
			<li><a href="<c:url value="j_spring_security_logout" />">Terminar sesion</a></li>
		</ul>