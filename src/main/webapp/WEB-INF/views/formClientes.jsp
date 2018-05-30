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
<form:form method="post" commandName="formClienteDetalle">
  <table width="95%" bgcolor="f8f8ff" border="0" cellspacing="0" cellpadding="5">
    
    <tr><td width="20%" colspan="5"></td></tr>
    <tr>
      <td align="left" width="20%">NOMBRE:</td>
      <td align="left" width="20%" colspan="3"><input type='text' placeholder="NOMBRE:" id="nombreCliente" name='nombreCliente' value='${detalle.nombreCliente}' class="form-control"></td>
      <td width="20%"><input type='text' placeholder="RFC:" id="rfc" name='rfc' value='${detalle.rfc}' class="form-control"></td>
    </tr>
    <tr><td width="20%" colspan="5"></td></tr>
    <tr>
      <td align="left" width="20%">DIRECCION:</td>
      <td align="left" width="20%" colspan="4"><input type='text' placeholder="CALLE:" id="calle" name='calle' value='${detalle.calle}' class="form-control">, <input type='text' placeholder="COLONIA:" id="colonia" name='colonia' value='${detalle.colonia}' class="form-control"></td>
    <tr><td width="20%" colspan="5"></td></tr>
    <tr>
      <td align="left" width="20%"></td>
      <td align="left" width="20%" colspan="4"><input type='text' placeholder="CIUDAD:" id="ciudad" name='ciudad' value='${detalle.ciudad}' class="form-control">, ${detalle.idEstado}, CP: <input type='text' placeholder="CP:" id="cp" name='cp' value='${detalle.cp}' class="form-control"></td>
    </tr>
	<tr><td width="20%" colspan="5"></td></tr>
    <tr>
      	<td align="left" width="20%" colspan="5">DATOS DE CONTACTO:</td>
    </tr>
    <tr>
        <td width="20%"><input type='text' id="contacto1" name='contacto1' value='${detalle.contacto1}' class="form-control"></td>
        <td width="20%"><input type='text' id="telefono1" name='telefono1' value='${detalle.telefono1}' class="form-control"></td>
        <td width="20%"><input type='text' id="email1" name='email1' value='${detalle.email1}' class="form-control"></td>
        <td width="20%"><input type='text' id="cargo1" name='cargo1' value='${detalle.cargo1}' class="form-control"></td>
        <td width="20%"><input type='text' id="celular1" name='celular1' value='${detalle.celular1}' class="form-control"></td>
    </tr>
    <tr>
        <td width="20%"><input type='text' id="contacto2" name='contacto2' value='${detalle.contacto2}' class="form-control"></td>
        <td width="20%"><input type='text' id="telefono2" name='telefono2' value='${detalle.telefono2}' class="form-control"></td>
        <td width="20%"><input type='text' id="email2" name='email2' value='${detalle.email2}' class="form-control"></td>
        <td width="20%"><input type='text' id="cargo2" name='cargo2' value='${detalle.cargo2}' class="form-control"></td>
        <td width="20%"><input type='text' id="celular2" name='celular2' value='${detalle.celular2}' class="form-control"></td>
    </tr>
    <tr><td width="20%" colspan="5"></td></tr>
    <tr>
      	<td align="left" width="20%" colspan="5">DATOS DE PAGO:</td>
    </tr>
    <tr>
    	<td align="left" width="20%" colspan="3"><input type='text' id="contactopagoNombre" name='contactopagoNombre' value='${detalle.contactopagoNombre}' class="form-control"></td>
        <td width="20%"><input type='text' id="contactopagoTelefono" name='contactopagoTelefono' value='${detalle.contactopagoTelefono}' class="form-control"></td>
        <td width="20%"><input type='text' id="contactopagoEmail" name='contactopagoEmail' value='${detalle.contactopagoEmail}' class="form-control"></td>
    </tr>
    <tr><td width="20%" colspan="5"></td></tr>
    <tr>
    	<td width="20%"><input type='text' placeholder="DIAS CREDITO:" id="diasCredito" name='diasCredito' value='${detalle.diasCredito}' class="form-control"></td>
        <td align="left" width="20%" colspan="2">FACTURACION: <input type='text' placeholder="FACTURACION:" id="facturacion" name='facturacion' value='${detalle.facturacion}' class="form-control"></td>
        <td width="20%">TIPO FACTURACION: ${detalle.idTipoFacturacion}</td>
        <td width="20%">TIPO PAGO: ${detalle.idPago}</td>
    </tr>
    <tr><td width="20%" colspan="5"></td></tr>
    <tr>
    	<td width="20%"><input type='text' placeholder="DIAS REVISION:" id="diasRevision" name='diasRevision' value='${detalle.diasRevision}' class="form-control"></td>
        <td width="20%"><input type='text' placeholder="HORARIO:" id="horario" name='horario' value='${detalle.horario}' class="form-control"></td>
        <td width="20%"><input type='text' placeholder="FECHA INGRESO:" id="fingreso" name='fingreso' value='${detalle.fingreso}' class="form-control"></td>
        <td width="20%">ESTATUS: ${detalle.idEstatus}</td>
        <td width="20%"><input type='text' placeholder="SALIDA:" id="salidaProducto" name='salidaProducto' value='${detalle.salidaProducto}' class="form-control"></td>
    </tr>
    
  </table>
  <br>
 
</form:form>
<a href="<c:url value="home"/>">Home</a>
</body>
</html>