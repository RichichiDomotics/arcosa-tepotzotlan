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
<h1><fmt:message key="consultas.clientes.heading"/></h1>
<h3><fmt:message key="consultas.clientes.subtitle"/></h3>
<form:form method="post" commandName="formConsultaDetalle">
  <table width="95%" bgcolor="f8f8ff" border="0" cellspacing="0" cellpadding="5">
    
    <tr><td width="20%" colspan="5"></td></tr>
    <tr>
      <td align="left" width="20%">NOMBRE:</td>
      <td align="left" width="20%" colspan="3">${detalle.nombreCliente}</td>
      <td width="20%">RFC: ${detalle.rfc}</td>
    </tr>
    <tr><td width="20%" colspan="5"></td></tr>
    <tr>
      <td align="left" width="20%">DIRECCION:</td>
      <td align="left" width="20%" colspan="4">${detalle.calle}, ${detalle.colonia}</td>
    <tr><td width="20%" colspan="5"></td></tr>
    <tr>
      <td align="left" width="20%"></td>
      <td align="left" width="20%" colspan="4">${detalle.ciudad}, ${detalle.idEstado}, CP: ${detalle.cp}</td>
    </tr>
	<tr><td width="20%" colspan="5"></td></tr>
    <tr>
      	<td align="left" width="20%" colspan="5">DATOS DE CONTACTO:</td>
    </tr>
    <tr>
        <td width="20%">${detalle.contacto1}</td>
        <td width="20%">${detalle.telefono1}</td>
        <td width="20%">${detalle.email1}</td>
        <td width="20%">${detalle.cargo1}</td>
        <td width="20%">${detalle.celular1}</td>
    </tr>
    <tr>
        <td width="20%">${detalle.contacto2}</td>
        <td width="20%">${detalle.telefono2}</td>
        <td width="20%">${detalle.email2}</td>
        <td width="20%">${detalle.cargo2}</td>
        <td width="20%">${detalle.celular2}</td>
    </tr>
    <tr><td width="20%" colspan="5"></td></tr>
    <tr>
      	<td align="left" width="20%" colspan="5">DATOS DE PAGO:</td>
    </tr>
    <tr>
    	<td align="left" width="20%" colspan="3">${detalle.contactopagoNombre}</td>
        <td width="20%">${detalle.contactopagoTelefono}</td>
        <td width="20%">${detalle.contactopagoEmail}</td>
    </tr>
    <tr><td width="20%" colspan="5"></td></tr>
    <tr>
    	<td width="20%" >DIAS CREDITO: ${detalle.diasCredito}</td>
        <td align="left" width="20%" colspan="2">FACTURACION: ${detalle.facturacion}</td>
        <td width="20%">TIPO FACTURACION: ${detalle.idTipoFacturacion}</td>
        <td width="20%">TIPO PAGO: ${detalle.idPago}</td>
    </tr>
    <tr><td width="20%" colspan="5"></td></tr>
    <tr>
    	<td width="20%">DIAS REVISION: ${detalle.diasCredito}</td>
        <td width="20%">HORARIO: ${detalle.horario}</td>
        <td width="20%">FECHA INGRESO: ${detalle.fingreso}</td>
        <td width="20%">ESTATUS: ${detalle.idEstatus}</td>
        <td width="20%">SALIDA: ${detalle.salidaProducto}</td>
    </tr>
    
  </table>
  <br>
 
</form:form>
<a href="<c:url value="home"/>">Home</a>
</body>
</html>