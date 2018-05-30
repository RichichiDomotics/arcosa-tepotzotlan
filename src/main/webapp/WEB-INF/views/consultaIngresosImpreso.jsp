<%@ include file="/WEB-INF/views/include.jsp" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
  <title><fmt:message key="title"/></title>
  <style>
    .error { color: red; }
  </style>  
</head>
<body>
<div id="contenidoconsulta">
<h1 class="titulotabla alert-success"><fmt:message key="consultas.ingreso.heading"/></h1>
<form:form method="post" commandName="formConsultaIngresos">
  <table class="table table-responsive table-bordered">
    <tr class="alert-success">
      <th align="right" width="20%">Ingreso</th>
      <th align="right" width="20%">Fecha</th>
      <th align="right" width="20%">Hora</th>
      <th align="right" width="20%">ID Cliente</th>
      <th align="right" width="20%">Cliente</th>
      <th align="right" width="20%">Planta</th>
      <th align="right" width="20%">Puerta</th>
      <th align="right" width="20%">Recibo</th>
    </tr>
	<c:forEach items="${ingresos}" var="e">
     <tr>
      <td align="right" width="20%"><input type="radio" name="idConsulta" value="${e.idFormEntrada}"></td>
      <td align="right" width="20%">${e.fechaEntrada}</td>
      <td align="right" width="20%">${e.horaEntrada}</td>
      <td align="right" width="20%">${e.idCliente}</td>
      <td align="right" width="20%">${e.nombreCliente}</td>
      <td align="right" width="20%">${e.nombrePlanta}</td>
      <td align="right" width="20%">${e.nombrePuerta}</td>
      <td align="right" width="20%">${e.numRecibo}</td>
    </tr>
    </c:forEach>
       <tr>
    <td colspan="2" align="center"><input type="submit" value="Imprimir Recibo" class="btn btn-primary"></td>
    </tr>
  </table>
  <br>
</form:form>
<!--a href="<c:url value="home"/>">Home</a-->
</div>
</body>
</html>