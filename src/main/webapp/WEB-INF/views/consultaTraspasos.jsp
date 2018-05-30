<%@ include file="/WEB-INF/views/include.jsp" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<link rel="stylesheet" href="resources/assets/css/reportecrm.css" />
<script src="resources/assets/js/jquery.dataTables.min.js"></script>
<script src="resources/assets/js/jquery.dataTables.bootstrap.js"></script>
<script src="resources/js/manejoTablasVentas.js"></script>
<div id="reporte2">
  <table aria-describedby="sample-table-2_info"  id="sample-table-2" class="table table-striped table-bordered table-hover dataTable">
    <thead>
    <tr role="row">
      <th aria-label="" style="width: 58px;" colspan="1" rowspan="1" role="columnheader" class="center sorting_disabled">FOLIO</th>
      <th aria-label="" style="width: 58px;" colspan="1" rowspan="1" role="columnheader" class="center sorting_disabled">RD</th>
      <th aria-label="" style="width: 58px;" colspan="1" rowspan="1" role="columnheader" class="center sorting_disabled">CAMARA ACTUAL</th>
      <th aria-label="" style="width: 58px;" colspan="1" rowspan="1" role="columnheader" class="center sorting_disabled">CAMARA FINAL</th>
      <th aria-label="" style="width: 58px;" colspan="1" rowspan="1" role="columnheader" class="center sorting_disabled">FECHA DE INGRESO</th>
      <th aria-label="" style="width: 58px;" colspan="1" rowspan="1" role="columnheader" class="center sorting_disabled">FECHA DE CAMBIO</th>
      <th aria-label="" style="width: 58px;" colspan="1" rowspan="1" role="columnheader" class="center sorting_disabled">MOTIVO DE CAMBIO</th>
      <th aria-label="" style="width: 58px;" colspan="1" rowspan="1" role="columnheader" class="center sorting_disabled">SERA REALIZADO POR</th>
    </tr>
    </thead>
    <tbody aria-relevant="all" aria-live="polite" role="alert">
    <c:forEach items="${traspasos}" var="f">
      <tr>
        <td class="center  sorting_1">${f.id}</td>
        <td class="center  sorting_1">${f.consecutivo}</td>
        <td class="center  sorting_1">${f.camaraInicial}</td>
        <td class="center  sorting_1">${f.camaraFinal}</td>
        <td class="center  sorting_1"><fmt:parseDate value="${f.fechaIngreso}" var="parsedEmpDate" pattern="yyyy-MM-dd" /><fmt:formatDate pattern="dd/MM/yyyy" value="${parsedEmpDate}" /></td>
        <td class="center  sorting_1"><fmt:parseDate value="${f.fechaCambio}" var="parsedEmpDate2" pattern="yyyy-MM-dd" /><fmt:formatDate pattern="dd/MM/yyyy" value="${parsedEmpDate2}" /></td>
        <td class="center  sorting_1">${f.motivo}</td>
        <td class="center  sorting_1">${f.realizadoPor}</td>
      </tr>
    </c:forEach>
    </tbody>
  </table>
</div>