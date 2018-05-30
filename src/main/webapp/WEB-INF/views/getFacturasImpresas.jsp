<%@ include file="/WEB-INF/views/include.jsp" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<link rel="stylesheet" href="resources/assets/css/reportecrm.css" />
<script src="resources/assets/js/jquery.dataTables.min.js"></script>
<script src="resources/assets/js/jquery.dataTables.bootstrap.js"></script>
<script src="resources/js/manejoTablasVentas.js"></script>
<script>
  $(document).ready(function(){
    var oTable1 = $('#sample-table-4').dataTable();
  });
</script>
<style>
  .pagination, .dataTables_info, .dataTables_length, .dataTables_filter{
    font-size: 9px;
  }
  .pagination ul > li > a, .pagination ul > li > span{
    padding: 3px 9px;
  }
  .infobox{
    width: 556px;
  }
</style>
<h3>Reporte del ${before} al ${after}</h3>
<div class="span6 widget-container-span">
  <div class="widget-box">
    <div class="widget-header">
      <h5>Facturacion Impresa y notas de cr&eacute;dito aplicados a clientes</h5>
    </div>
  <div class="widget-body">
    <div class="widget-main">
      <table aria-describedby="sample-table-2_info"  id="sample-table-2" class="table table-striped table-bordered table-hover dataTable">


        <tbody aria-relevant="all" aria-live="polite" role="alert">

          <tr>
            <td class="center  sorting_1"><strong>TOTAL DE VENTAS NETAS:</strong> </td>
            <td class="center  sorting_1">$<fmt:formatNumber pattern="###,###,###.##" value="${facturasNetas}"/></td>
          </tr>
          <tr>
            <td class="center  sorting_1"><strong>TOTAL DE FACTURAS IMPRESAS:</strong></td>
            <td class="center  sorting_1">$<fmt:formatNumber pattern="###,###,###.##" value="${facturasImpresas}"/></td>
          </tr>
          <tr>
            <td class="center  sorting_1"><strong>NOTAS DE CREDITO:</strong></td>
            <td class="center  sorting_1">$<fmt:formatNumber pattern="###,###,###.##" value="${notas}"/></td>
          </tr>
          <tr>
            <td class="center  sorting_1"><strong>TOTAL NO TIMBRADO:</strong></td>
            <td class="center  sorting_1">$<fmt:formatNumber pattern="###,###,###.##" value="${facturasTotal}"/></td>
          </tr>

        </tbody>
      </table>
    </div>
  </div>
 </div>
  <div class="infobox infobox-pink" style="float: right">
    <div class="infobox-icon">
      <i class="icon-shopping-cart"></i>
    </div>
    <div class="infobox-data">
      <span class="infobox-data-number">$ <fmt:formatNumber value="${facturasImpresas-notas}" maxFractionDigits="2"/> no incluye IVA</span>
      <div class="infobox-content"><h4>$ <fmt:formatNumber value="${facturasImpresas}" maxFractionDigits="2"/> - $ <fmt:formatNumber value="${notas}" maxFractionDigits="2"/></h4></div>
      <div class="infobox-content">Total Ventas (Facturas Impresas) - (Notas de Cr&eacute;dito)</div>
    </div>
  </div>
</div>

<div class="span6 widget-container-span">
  <div class="widget-box">
    <div class="widget-header">
      <h5>Saldos en camaras</h5>
    </div>
    <div class="widget-body">
      <div class="widget-main">
        <table aria-describedby="sample-table-2_info"  id="sample-table-3" class="table table-striped table-bordered table-hover dataTable">
          <thead>
            <tr>
              <th>Total de saldos al dia ${now}</th>
            </tr>
          </thead>
          <tbody>
            <tr>
              <td><fmt:formatNumber pattern="###,###,###.##" value="${saldos.get(0)[0]}"/> TN</td>
            </tr>
            <tr>
              <td><fmt:formatNumber pattern="###,###,###.##" value="${saldos.get(0)[1]}"/> PZAS</td>
            </tr>
          </tbody>
        </table>
      </div>
    </div>
  </div>
</div>
<div class="span6 widget-container-span">
  <div class="widget-box">
    <div class="widget-header">
      <h5>No Timbrados</h5>
    </div>
    <div class="widget-body" style="font-size: 9px">
      <div class="widget-main">
        <table aria-describedby="sample-table-2_info"  id="sample-table-4" class="table table-striped table-bordered table-hover dataTable">
          <thead>
          <tr role="row">
            <th aria-label="" style="width: 58px;" colspan="1" rowspan="1" role="columnheader" class="center">IDCLIENTE</th>
            <th aria-label="" style="width: 58px;" colspan="1" rowspan="1" role="columnheader" class="center">NOMBRE DEL CLIENTE</th>
            <th aria-label="" style="width: 58px;" colspan="1" rowspan="1" role="columnheader" class="center">SALDO</th>
            <th aria-label="" style="width: 58px;" colspan="1" rowspan="1" role="columnheader" class="center">FACTURACION</th>
            <th aria-label="" style="width: 58px;" colspan="1" rowspan="1" role="columnheader" class="center">REVISION</th>
          </tr>
          </thead>
          <tbody aria-relevant="all" aria-live="polite" role="alert">
          <c:forEach items="${pendientes}" var="f">
            <tr>
              <td class="center  sorting_1">${f[1]}</td>
              <td class="center  sorting_1">${f[3]}</td>
              <td class="center  sorting_1">$<fmt:formatNumber pattern="###,###,###.##" value="${f[0]}"/></td>
              <td class="center  sorting_1">${f[4]}</td>
              <td class="center  sorting_1">${f[2]}</td>
            </tr>
          </c:forEach>
          </tbody>
        </table>
      </div>
    </div>
  </div>
</div>