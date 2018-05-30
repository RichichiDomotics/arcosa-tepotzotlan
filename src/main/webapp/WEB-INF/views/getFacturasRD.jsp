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
  <h5>Facturas por RD</h5>
  <h6><strong>IMPORTANTE: </strong>ESTE REPORTE CONTIENE LOS RECIBOS DE ENTRADA(FACTURAS EMITIDAS Y POR FACTURAR)</h6>
  <div class="alert alert-block alert-info">
    <strong class="blue">
      <table border="0">
        <tr>
          <td align="right">
            <i class="icon-cogs blue"></i>
          </td>
          <td>
            RD:</td>
          <td>${facturas.get(0)[7]}</td>
          <td> </td>
          <td>Fecha de Ingreso: <fmt:parseDate value="${facturas.get(0)[10]}" var="parsedEmpDate3" pattern="yyyy-MM-dd" /><fmt:formatDate pattern="dd/MM/yyyy" value="${parsedEmpDate3}" /></td>
        </tr>
      </table>
    </strong>
  </div>
  <table aria-describedby="sample-table-2_info"  id="sample-table-2" class="table table-striped table-bordered table-hover dataTable">
    <thead>
    <tr role="row">
      <th aria-label="" style="width: 58px;" colspan="1" rowspan="1" role="columnheader" class="center sorting_disabled">PERIODO</th>
      <th aria-label="" style="width: 58px;" colspan="1" rowspan="1" role="columnheader" class="center sorting_disabled">BASE</th>
      <th aria-label="" style="width: 58px;" colspan="1" rowspan="1" role="columnheader" class="center sorting_disabled">CUOTA</th>
      <th aria-label="" style="width: 58px;" colspan="1" rowspan="1" role="columnheader" class="center sorting_disabled">SUBTOTAL</th>
      <th aria-label="" style="width: 58px;" colspan="1" rowspan="1" role="columnheader" class="center sorting_disabled">NO. FACTURA</th>
      <th aria-label="" style="width: 58px;" colspan="1" rowspan="1" role="columnheader" class="center sorting_disabled">FECHA FACTURA</th>
    </tr>
    </thead>
    <tbody aria-relevant="all" aria-live="polite" role="alert">
    <c:forEach items="${facturas}" var="f">
      <tr>
        <td class="center  sorting_1"><fmt:parseDate value="${f[0]}" var="parsedEmpDate" pattern="yyyy-MM-dd" /><fmt:formatDate pattern="dd/MM/yyyy" value="${parsedEmpDate}" /> - <fmt:parseDate value="${f[1]}" var="parsedEmpDate1" pattern="yyyy-MM-dd" /><fmt:formatDate pattern="dd/MM/yyyy" value="${parsedEmpDate1}" /></td>
        <td class="center  sorting_1">${f[2]}</td>
        <td class="center  sorting_1">${f[3]}</td>
        <td class="center  sorting_1">${f[4]}</td>
        <td class="center  sorting_1">${f[5]}</td>
        <td class="center  sorting_1"><fmt:parseDate value="${f[6]}" var="parsedEmpDate2" pattern="yyyy-MM-dd" /><fmt:formatDate pattern="dd/MM/yyyy" value="${parsedEmpDate2}" /></td>
      </tr>
    </c:forEach>
    </tbody>
  </table>
</div>

