<%@ include file="/WEB-INF/views/include.jsp" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<link rel="stylesheet" type="text/css"	href="resources/bootstrap/css/capturaDetalle.css" />
<script>var conteo=1;</script>
<p>Recibo de Salida : ${salidasDetalle[0].folioSalida}
    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
    Fecha :
    <fmt:parseDate value="${salidasDetalle[0].fechaSalida}" var="parsedEmpDate" pattern="yyyy-MM-dd" />
    <fmt:formatDate pattern="dd-MM-yyyy" value="${parsedEmpDate}" />
</p>
<p>Cliente : ${cliente} &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; </p>
<p></p>
<p>RD Afectado : ${salidasDetalle[0].consecutivo}</p>
<table aria-describedby="sample-table-2_info" id="sample-table-2" class="table table-striped table-bordered table-hover dataTable">
        <thead>
        	<tr role="row">
              <th aria-label="" style="width: 58px;" colspan="1" rowspan="1" role="columnheader" class="center sorting_disabled">Renglon(RD)</th>
              <th aria-label="" style="width: 58px;" colspan="1" rowspan="1" role="columnheader" class="center sorting_disabled">Producto</th>
              <th aria-label="" style="width: 58px;" colspan="1" rowspan="1" role="columnheader" class="center sorting_disabled">Lote</th>
              <th aria-label="" style="width: 58px;" colspan="1" rowspan="1" role="columnheader" class="center sorting_disabled">Piezas</th>
              <th aria-label="" style="width: 58px;" colspan="1" rowspan="1" role="columnheader" class="center sorting_disabled">Peso U (KG)</th>
              <th aria-label="" style="width: 58px;" colspan="1" rowspan="1" role="columnheader" class="center sorting_disabled">Total de KG</th>
           </tr>
          </thead> 
    <c:set var="sumatotal" value="0"/> 
    <c:set var="sumapesou" value="0"/> 
    <c:set var="sumatotalT" value="0"/>       
    <c:forEach items="${salidasDetalle}" var="det">
    <tbody aria-relevant="all" aria-live="polite" role="alert"><tr class="odd">
        <td class="center  sorting_1"><fmt:formatNumber value="${det.renglon}" maxFractionDigits="0"/></td>
        <td class="center  sorting_1">${det.producto}</td>
        <td class="center  sorting_1">${det.lote}</td>
        <td class="center  sorting_1">${det.cantidadSalida}</td>
        <td class="center  sorting_1"><fmt:formatNumber value="${det.pesou}" maxFractionDigits="2"/></td>
        <td class="center  sorting_1"><fmt:formatNumber value="${det.pesou*det.cantidadSalida}" maxFractionDigits="2"/></td>
    </tr>
    </tbody>
    <script>conteo++;</script>
    <c:set var="sumatotal" value="${sumatotal+det.cantidadSalida}"/>
    <c:set var="sumapesou" value="${sumapesou+det.pesou}"/>
    <c:set var="sumatotalT" value="${sumatotalT+(det.cantidadSalida*det.pesou)}"/>
    </c:forEach>
    <tbody aria-relevant="all" aria-live="polite" role="alert"><tr class="odd" style="color:#000">
    <tr>
       <td colspan="3"></td>
       <td class="center  sorting_1">TOTAL :</td>
       <td class="center  sorting_1"><fmt:formatNumber value="${sumatotal}" maxFractionDigits="0"/>Pzas.</td>
       <td class="center  sorting_1"><fmt:formatNumber value="${sumatotalT}" maxFractionDigits="2"/> (KGS)</td>
       <!--c:set var="sumatotalT" value="${sumatotal*sumapesou}"/>
       <td class="center  sorting_1">${sumatotalT}</td-->
    </tr>
    </tbody>
</table>
<br>
<script>
   $("#RENGLONVISTA").val(conteo);
   $("#RENGLON").val(conteo);
   $("#PRODUCTO").focus();
</script>