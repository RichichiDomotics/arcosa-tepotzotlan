<%@ include file="/WEB-INF/views/include.jsp" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<table aria-describedby="sample-table-2_info" id="sample-table-2" class="table table-striped table-bordered table-hover dataTable" style="font-size: 9px">
  <thead>
  <tr role="row">
    <th aria-label="" style="width: 58px;" colspan="1" rowspan="1" role="columnheader" class="center sorting_disabled">FOLIO</th>
    <th aria-label="" style="width: 58px;" colspan="1" rowspan="1" role="columnheader" class="center sorting_disabled">NO. NOTA</th>
    <th aria-label="" style="width: 58px;" colspan="1" rowspan="1" role="columnheader" class="center sorting_disabled">FECHA</th>
    <th aria-label="" style="width: 58px;" colspan="1" rowspan="1" role="columnheader" class="center sorting_disabled">ID CLIENTE</th>
    <th aria-label="" style="width: 58px;" colspan="1" rowspan="1" role="columnheader" class="center sorting_disabled">NO. FACTURA AFECTADA</th>
    <th aria-label="" style="width: 58px;" colspan="1" rowspan="1" role="columnheader" class="center sorting_disabled">NO. FACTURA A APLICAR</th>
    <th aria-label="" style="width: 58px;" colspan="1" rowspan="1" role="columnheader" class="center sorting_disabled">CONCEPTO</th>
    <th aria-label="" style="width: 58px;" colspan="1" rowspan="1" role="columnheader" class="center sorting_disabled">MOTIVO</th>
    <th aria-label="" style="width: 58px;" colspan="1" rowspan="1" role="columnheader" class="center sorting_disabled">SUBTOTAL</th>
    <th aria-label="" style="width: 58px;" colspan="1" rowspan="1" role="columnheader" class="center sorting_disabled">AUTORIZA</th>
    <th aria-label="" style="width: 58px;" colspan="1" rowspan="1" role="columnheader" class="center sorting_disabled">REALIZO</th>
    <th aria-label="" style="width: 58px;" colspan="1" rowspan="1" role="columnheader" class="center sorting_disabled">OBSERVACIONES</th>
    <th aria-label="" style="width: 58px;" colspan="1" rowspan="1" role="columnheader" class="center sorting_disabled">FECHA FACTURA</th>
    <th aria-label="" style="width: 58px;" colspan="1" rowspan="1" role="columnheader" class="center sorting_disabled">FECHA REFACTURA</th>
    <th aria-label="" style="width: 58px;" colspan="1" rowspan="1" role="columnheader" class="center sorting_disabled">CAMARA</th>
  </tr>
  </thead>
  <tbody aria-relevant="all" aria-live="polite" role="alert">
  <c:forEach items="${notas}" var="c">
    <tr>
      <td>${c.FOLIO}</td>
      <td>${c.NNOTA}</td>
      <td><fmt:parseDate value="${c.FECHANOTA}" var="parsedEmpDate" pattern="yyyy-MM-dd" /><fmt:formatDate pattern="dd/MM/yyyy" value="${parsedEmpDate}" /></td>
      <td>${c.ID_CLIENTE}</td>
      <td>${c.FACTURAA}</td>
      <td>${c.FACTURAS}</td>
      <td>${c.CONCEPTO}</td>
      <td>${c.MOTIVO}</td>
      <td>${c.SUBTOTAL}</td>
      <td>${c.AUTORIZA}</td>
      <td>${c.REALIZO}</td>
      <td>${c.OBSERVACIONES}</td>
      <td><fmt:parseDate value="${c.FECHAA}" var="parsedEmpDate2" pattern="yyyy-MM-dd" /><fmt:formatDate pattern="dd/MM/yyyy" value="${parsedEmpDate2}" /></td>
      <td><fmt:parseDate value="${c.FECHAS}" var="parsedEmpDate2" pattern="yyyy-MM-dd" /><fmt:formatDate pattern="dd/MM/yyyy" value="${parsedEmpDate2}" /></td></td>
      <td>${c.CAMARA}</td>
    </tr>
  </c:forEach>
  </tbody>
</table>