<%@ include file="/WEB-INF/views/include.jsp" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<link rel="stylesheet" href="resources/assets/css/reportecrm.css" />
<script src="resources/assets/js/jquery.dataTables.min.js"></script>
<script src="resources/assets/js/jquery.dataTables.bootstrap.js"></script>
<script src="resources/js/manejoTablasVentas.js"></script>
<script>
  $(document).ready(function(){
    var oTable1 = $('#sample-table-2').dataTable();
    var oTable2 = $('#sample-table-3').dataTable();
    var oTable3 = $('#sample-table-4').dataTable();
    $(".widget-body").hide();
    $(".widget-toolbar").click(function(){
      //$(".widget-body").hide()
      numerowidget = $(this).attr("numwidget")
      if ($("."+numerowidget).is(':hidden')){
        //$(".widget-body").hide()
        $("."+numerowidget).show();
      }else{
        $("."+numerowidget).hide();
      }

    });
    $('.widget-container-span').sortable({
      connectWith: '.widget-container-span',
      items:'> .widget-box',
      opacity:0.8,
      revert:true,
      forceHelperSize:true,
      placeholder: 'widget-placeholder',
      forcePlaceholderSize:true,
      tolerance:'pointer'
    });
    $('.slim-scroll').each(function () {
      var $this = $(this);
      $this.slimScroll({
        height: $this.data('height') || 100,
        railVisible:true
      });
    });
  });
</script>
<style>
  .pagination, .dataTables_info, .dataTables_length, .dataTables_filter{
    font-size: 9px;
  }
  .pagination ul > li > a, .pagination ul > li > span{
    padding: 3px 9px;
  }
</style>
<div class="span6 widget-container-span">
  <div class="widget-box">
    <div class="widget-header">
      <h5>Detalle Venta por Concepto</h5>
      <span class="widget-toolbar"  numwidget="1">
		<a href="#" data-action="collapse">
          <i class="icon-chevron-up"></i>
        </a>
      </span>
    </div>
    <div class="widget-body 1">
      <div class="widget-main">
        <table aria-describedby="sample-table-2_info"  id="sample-table-2" class="table table-striped table-bordered table-hover dataTable">
          <thead>
            <tr>
              <th aria-label="" style="width: 58px;" colspan="1" rowspan="1" role="columnheader" class="center">CONCEPTO</th>
              <th aria-label="" style="width: 58px;" colspan="1" rowspan="1" role="columnheader" class="center">CLAVE</th>
              <th aria-label="" style="width: 58px;" colspan="1" rowspan="1" role="columnheader" class="center">MONTO</th>
            </tr>
          </thead>
          <tbody>
          <c:forEach items="${concepto}" var="c">
            <tr>
              <td>${c[0]}</td>
              <td>${c[1]}</td>
              <td>$<fmt:formatNumber pattern="###,###,###.##" value="${c[2]}"/></td>
            </tr>
          </c:forEach>
          </tbody>
        </table>
        <br>
        <p class="alert alert-info">
          TOTAL: $<fmt:formatNumber pattern="###,###,###.##" value="${total}"/>
        </p>
      </div>
    </div>
  </div>
</div>

<div class="span6 widget-container-span">
  <div class="widget-box">
    <div class="widget-header">
      <h5>Venta Clientes por Factura</h5>
      <span class="widget-toolbar"  numwidget="2">
		<a href="#" data-action="collapse">
          <i class="icon-chevron-up"></i>
        </a>
      </span>
    </div>
    <div class="widget-body 2">
      <div class="widget-main">
        <table aria-describedby="sample-table-2_info"  id="sample-table-3" class="table table-striped table-bordered table-hover dataTable">
          <thead>
          <tr>
            <th aria-label="" style="width: 58px;" colspan="1" rowspan="1" role="columnheader" class="center">FECHA</th>
            <th aria-label="" style="width: 58px;" colspan="1" rowspan="1" role="columnheader" class="center">ID</th>
            <th aria-label="" style="width: 58px;" colspan="1" rowspan="1" role="columnheader" class="center">NOMBRE DEL CLIENTE</th>
            <th aria-label="" style="width: 58px;" colspan="1" rowspan="1" role="columnheader" class="center">MONTO</th>
            <th aria-label="" style="width: 58px;" colspan="1" rowspan="1" role="columnheader" class="center">NO. FACTURA</th>
          </tr>
          </thead>
          <tbody>
          <c:forEach items="${rubros}" var="c">
            <tr>
              <td><fmt:parseDate value="${c[0]}" var="parsedEmpDate2" pattern="yyyy-MM-dd" /><fmt:formatDate pattern="dd/MM/yyyy" value="${parsedEmpDate2}" /></td>
              <td>${c[1]}</td>
              <td>${c[2]}</td>
              <td>$<fmt:formatNumber pattern="###,###,###.##" value="${c[3]}"/></td>
              <td>${c[4]}</td>
            </tr>
          </c:forEach>
          </tbody>
        </table>
        <br>
        <p class="alert alert-info">
          TOTAL: $<fmt:formatNumber pattern="###,###,###.##" value="${total}"/>
        </p>
      </div>
    </div>
  </div>
</div>

<div class="span6 widget-container-span">
  <div class="widget-box">
    <div class="widget-header">
      <h5>Venta Clientes por Factura Detalle</h5>
      <span class="widget-toolbar"  numwidget="3">
		<a href="#" data-action="collapse">
          <i class="icon-chevron-up"></i>
        </a>
      </span>
    </div>
    <div class="widget-body 3">
      <div class="widget-main">
        <table aria-describedby="sample-table-2_info"  id="sample-table-4" class="table table-striped table-bordered table-hover dataTable">
          <thead>
          <tr>
            <th aria-label="" style="width: 58px;" colspan="1" rowspan="1" role="columnheader" class="center"FECHA</th>
            <th aria-label="" style="width: 58px;" colspan="1" rowspan="1" role="columnheader" class="center">ID</th>
            <th aria-label="" style="width: 58px;" colspan="1" rowspan="1" role="columnheader" class="center">NOMBRE DEL CLIENTE</th>
            <th aria-label="" style="width: 58px;" colspan="1" rowspan="1" role="columnheader" class="center">SERVICIO</th>
            <th aria-label="" style="width: 58px;" colspan="1" rowspan="1" role="columnheader" class="center">MONTO</th>
            <th aria-label="" style="width: 58px;" colspan="1" rowspan="1" role="columnheader" class="center"">NO. FACTURA</th>
          </tr>
          </thead>
          <tbody>
          <c:forEach items="${detalle}" var="c">
            <tr>
              <td><fmt:parseDate value="${c[0]}" var="parsedEmpDate2" pattern="yyyy-MM-dd" /><fmt:formatDate pattern="dd/MM/yyyy" value="${parsedEmpDate2}" /></td>
              <td>${c[1]}</td>
              <td>${c[2]}</td>
              <td>${c[3]}</td>
              <td>$<fmt:formatNumber pattern="###,###,###.##" value="${c[4]}"/></td>
              <td>${c[5]}</td>
            </tr>
          </c:forEach>
          </tbody>
        </table>
        <br>
        <p class="alert alert-info">
          TOTAL: $<fmt:formatNumber pattern="###,###,###.##" value="${total}"/>
        </p>
      </div>
    </div>
  </div>
</div>

