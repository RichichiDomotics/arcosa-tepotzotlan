<%@ include file="/WEB-INF/views/include.jsp" %>
<link rel="stylesheet" type="text/css" href="resources/bootstrap/css/normalize.css" />
<link rel="stylesheet" type="text/css" href="resources/bootstrap/css/component.css" />
<style>
  .component{
    max-width: 1400px;
  }
</style>
<h1>SEGUROS RD</h1>
<div class="component">
  <table>
    <thead>
    <tr>
      <th>RD</th>
      <th>Fecha de Ingreso</th>
      <th>Nombre del Cliente</th>
      <th>Clave producto</th>
      <th>Cuota</th>
      <th>Valor Total</th>
      <th>KIlos de Origen</th>
    </tr>
    </thead>
    <tbody aria-relevant="all" aria-live="polite" role="alert">

      <tr>
        <td align="center">${detallesRD.get(0).consecutivo}</td>
        <td align="center"><fmt:parseDate value="${detallesRD.get(0).fechaCaptura}" var="parsedEmpDate" pattern="yyyy-MM-dd" /> <fmt:formatDate pattern="dd/MM/yyyy" value="${parsedEmpDate}" /></td>
        <td align="center">${cliente.idCliente} ${cliente.nombreCliente}</td>
        <td align="center">${detallesRD.get(0).claveProducto}</td>
        <td align="center">
          <c:forEach items="${cuota}" var="c">
            <c:if test="${c.claveProducto == detallesRD.get(0).claveProducto}">
              <fmt:formatNumber type="number" maxFractionDigits="2" value="${c.cuotaAlmacenaje}"/>
            </c:if>
          </c:forEach>
        </td>
        <c:set var="totalValor" value="0" />
        <c:forEach items="${detallesRD}" var="det">
          <c:set var="totalValor" value="${totalValor+ det.valorTotal}"/>
        </c:forEach>
        <td align="center"><fmt:formatNumber type="number" maxFractionDigits="2" value="${totalValor}"/></td>

        <c:set var="totalkilos" value="0" />
        <c:forEach items="${detallesRD}" var="det">
          <c:set var="totalValor" value="${totalkilos+ (det.cantidad*det.pesou)}"/>
        </c:forEach>
        <td align="center"><fmt:formatNumber type="number" maxFractionDigits="2" value="${totalkilos}"/></td>
      </tr>

    </tbody>
  </table>
</div>