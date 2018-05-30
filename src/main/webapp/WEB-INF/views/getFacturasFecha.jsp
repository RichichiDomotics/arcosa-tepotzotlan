<%@ include file="/WEB-INF/views/include.jsp" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<link rel="stylesheet" href="resources/assets/css/reportecrm.css" />
<script src="resources/assets/js/jquery.dataTables.min.js"></script>
<script src="resources/assets/js/jquery.dataTables.bootstrap.js"></script>
<script src="resources/js/manejoTablasVentas.js"></script>
<script>
  $(document).ready(function(){
    var oTable1 = $('#sample-table-2').dataTable();
  });
</script>
<div id="reporte2">
  <h5>Facturas por D&iacute;a</h5>
  <h6><strong>IMPORTANTE: </strong>ESTE REPORTE CONTIENE LOS RECIBOS DE ENTRADA(FACTURAS EMITIDAS Y POR FACTURAR)</h6>

  <table aria-describedby="sample-table-2_info"  id="sample-table-2" class="table table-striped table-bordered table-hover dataTable">
    <thead>
    <tr role="row">
      <th aria-label="" style="width: 58px;" colspan="1" rowspan="1" role="columnheader" class="center">RD</th>
      <th aria-label="" style="width: 58px;" colspan="1" rowspan="1" role="columnheader" class="center">IDCLIENTE</th>
      <th aria-label="" style="width: 58px;" colspan="1" rowspan="1" role="columnheader" class="center">FECHA DE INGRESO</th>
      <th aria-label="" style="width: 58px;" colspan="1" rowspan="1" role="columnheader" class="center">PERIODO</th>
      <th aria-label="" style="width: 58px;" colspan="1" rowspan="1" role="columnheader" class="center">CUOTA</th>
      <th aria-label="" style="width: 58px;" colspan="1" rowspan="1" role="columnheader" class="center">KILOS</th>
      <th aria-label="" style="width: 58px;" colspan="1" rowspan="1" role="columnheader" class="center">SERVICIO</th>
      <th aria-label="" style="width: 58px;" colspan="1" rowspan="1" role="columnheader" class="center">TOTAL</th>
      <th aria-label="" style="width: 58px;" colspan="1" rowspan="1" role="columnheader" class="center">NO. FACTURA</th>
    </tr>
    </thead>
    <tbody aria-relevant="all" aria-live="polite" role="alert">
    <c:forEach items="${facturas}" var="f">
      <tr>
        <td class="center  sorting_1">${f[0]}</td>
        <td class="center  sorting_1">${f[1]}</td>
        <td class="center  sorting_1"><fmt:parseDate value="${f[9]}" var="parsedEmpDate2" pattern="yyyy-MM-dd" /><fmt:formatDate pattern="dd/MM/yyyy" value="${parsedEmpDate2}" /></td>
        <td class="center  sorting_1"><fmt:parseDate value="${f[2]}" var="parsedEmpDate" pattern="yyyy-MM-dd" /><fmt:formatDate pattern="dd/MM/yyyy" value="${parsedEmpDate}" /> - <fmt:parseDate value="${f[3]}" var="parsedEmpDate1" pattern="yyyy-MM-dd" /><fmt:formatDate pattern="dd/MM/yyyy" value="${parsedEmpDate1}" /></td>
        <td class="center  sorting_1">${f[4]}</td>
        <td class="center  sorting_1">${f[5]}</td>
        <td class="center  sorting_1">${f[6]}</td>
        <td class="center  sorting_1">${f[7]}</td>
        <td class="center  sorting_1">${f[8]}</td>
      </tr>
    </c:forEach>
    </tbody>
  </table>
</div>