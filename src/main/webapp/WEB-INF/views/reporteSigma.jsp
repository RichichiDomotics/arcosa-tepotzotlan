<%@ include file="/WEB-INF/views/include.jsp" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<link rel="stylesheet" type="text/css" href="resources/bootstrap/css/inventario.css" />
<link rel="stylesheet" type="text/css" href="resources/bootstrap/css/normalize.css" />
<link rel="stylesheet" type="text/css" href="resources/bootstrap/css/component.css" />
<style>
    .component{
        width: 1500px;
    }
    td,th{
        padding: 0.2em 1em;
    }
</style>
<script src="resources/js/exportarExcel.js"></script>
<c:if test="${idCliente ne '' || consecutivo ne ''}">
  <div class="alert alert-block alert-info">
    <strong class="blue">
      <table border="0">
        <tr>
          <td align="right">
            <i class="icon-cogs blue"></i>
          </td>
          <td>
            CLIENTE:</td>
          <td>${reporte[0].idCliente} ${reporte[0].nomCliente}</td>
          <fmt:parseDate value="${reporte[0].fechaCaptura}" var="parsedEmpDate" pattern="dd/MM/yyyy" />
        </tr>
      </table>
    </strong>
  </div>
</c:if>
<br>
<!--form action="http://localhost:8081/Ace/ficheroExcel.php" method="post" target="_blank" id="FormularioExportacion"-->
<form method="post" target="_blank" id="FormularioExportacion">
  <button class="btn btn-primary btn-small botonExcel">Exportar a excel</button>
  <input type="hidden" id="datos_a_enviar" name="datos_a_enviar" />
</form>
<table class="component Exportar_a_Excel">
  <thead>
  <tr bgcolor="438eba" style="color: #FFFFFF;">
      <th>RD</th>
      <th>Renglon</th>
      <th>Ingreso</th>
      <th>Camara</th>
      <th>Codigo</th>
      <th>Producto</th>
      <th>Marca</th>
      <th>Lote</th>
      <th>Embalaje</th>
      <th>Peso Neto</th>
      <th>Total Peso Neto</th>
      <th>Peso Bruto</th>
      <th>Cantidad</th>
      <th>Subtotal RD</th>
      <th>Total RD</th>
    </tr>
  </thead>
  <c:set var="contador" value="${0}"/>
  <c:set var="total" value="${0}"/>
  <c:set var="totalpzas" value="${0}"/>
  <c:set var="subtotalRD" value="${0}"/>
  <c:set var="totalRenglon" value="${0}"/>
  <tbody aria-relevant="all" aria-live="polite" role="alert">
    <c:forEach items="${reporte}" var="det">
      <tr>
        <td>${det.consecutivo}</td>
        <td><fmt:formatNumber type="number" maxIntegerDigits="2" value="${det.renglon}" /></td>
        <td><fmt:parseDate value="${det.fechaCaptura}" var="parsedEmpDate" pattern="dd/MM/yyyy" />
            <fmt:formatDate pattern="dd-MM-yyyy" value="${parsedEmpDate}" /> </td>
        <td>${det.camara}</td>
        <td>${det.observaciones}</td>
        <td>${det.descripcion}</td>
        <td>${det.marca}</td>
        <td>${det.lote}</td>
        <td>${det.embalaje}</td>
        <td>${det.pesoBruto}</td>
        <td>${det.pesoBruto*det.cantidadInventario}</td>
        <td>${det.pesou}</td>
        <td>${det.cantidadInventario}</td>
          <c:set var="contador" value="${contador+1}"/>
          <c:set var="totalRenglon" value="${(det.cantidadInventario*det.pesoBruto)}"/>
          <c:set var="subtotalRD" value="${(subtotalRD) + (totalRenglon)}"/>
        <td><fmt:formatNumber  type="number" minFractionDigits="2" value="${totalRenglon}"/></td>
        <td>
            <c:if test="${det.consecutivo != reporte[contador].consecutivo}">
                <fmt:formatNumber type="number" minFractionDigits="2" value="${subtotalRD}"/>
                <c:set var="subtotalRD" value="${0}"/>
            </c:if>
        </td>
          <c:set var="total" value="${total+(det.cantidadInventario*det.pesoBruto)}"/>
          <c:set var="totalpzas" value="${totalpzas+(det.cantidadInventario)}"/>
      </tr>
      <!--script>conteo++;</script-->
    </c:forEach>
  </tbody>
</table>
<br>
<fmt:setLocale value="es_MX"/>
<div id="totalInventario">Total Kilos :<fmt:formatNumber type="number" maxFractionDigits="2" value="${total}"/></div>
<br><div id="totalInventario">Total Piezas :<fmt:formatNumber type="number" maxFractionDigits="2" value="${totalpzas}"/></div>