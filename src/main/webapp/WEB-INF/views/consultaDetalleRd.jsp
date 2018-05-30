<%@ include file="/WEB-INF/views/include.jsp" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<link rel="stylesheet" type="text/css"	href="resources/bootstrap/css/capturaDetalle.css" />
<script>var conteo=1;</script>
<p>Fecha Ingreso :
    <fmt:parseDate value="${detalleRds[0].fechaCaptura}" var="parsedEmpDate" pattern="yyyy-MM-dd" />
    <fmt:formatDate pattern="dd-MM-yyyy" value="${parsedEmpDate}" />
    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
    RD : ${detalleRds[0].consecutivo}
</p>
<p>Cliente : ${cliente} &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; Pedimento : ${detalleRds[0].impedimento} &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; Camara : ${detalleRds[0].camara}</p>
<p></p>
<p>Temperatura del Thermo king : ${detalleRds[0].thermoking} &deg;C &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; Temperatura Detectada en el anden : ${detalleRds[0].temperaturaAnden} &deg;C</p>
<table aria-describedby="sample-table-2_info" id="sample-table-2" class="table table-striped table-bordered table-hover dataTable">
        <thead>
        	<tr role="row">
              <th aria-label="" style="width: 58px;" colspan="1" rowspan="1" role="columnheader" class="center sorting_disabled">Renglon</th>
              <th aria-label="" style="width: 58px;" colspan="1" rowspan="1" role="columnheader" class="center sorting_disabled">Cantidad (pzas)</th>
              <th aria-label="" style="width: 58px;" colspan="1" rowspan="1" role="columnheader" class="center sorting_disabled">Peso U. (Kg)</th>
              <th aria-label="" style="width: 58px;" colspan="1" rowspan="1" role="columnheader" class="center sorting_disabled">Descripci&oacute;n del Producto</th>
              <th aria-label="" style="width: 58px;" colspan="1" rowspan="1" role="columnheader" class="center sorting_disabled">Embalaje</th>
              <th aria-label="" style="width: 58px;" colspan="1" rowspan="1" role="columnheader" class="center sorting_disabled">Marca</th>
              <th aria-label="" style="width: 58px;" colspan="1" rowspan="1" role="columnheader" class="center sorting_disabled">Lote</th>
              <th aria-label="" style="width: 58px;" colspan="1" rowspan="1" role="columnheader" class="center sorting_disabled">Caducidad</th>
           </tr>
          </thead> 
    <c:set var="sumatotal" value="0"/> 
    <c:set var="sumapesou" value="0"/> 
    <c:set var="sumatotalT" value="0"/>       
    <c:forEach items="${detalleRds}" var="det">
    <tbody aria-relevant="all" aria-live="polite" role="alert"><tr class="odd">
        <td class="center  sorting_1"><fmt:formatNumber value="${det.renglon}" maxFractionDigits="0"/></td>
        <td class="center  sorting_1">${det.cantidad}</td>
        <td class="center  sorting_1"><fmt:formatNumber value="${det.pesou}" maxFractionDigits="2"/></td>
        <td class="center  sorting_1">${det.descripcion}</td>
        <td class="center  sorting_1">${det.embalaje}</td>
        <td class="center  sorting_1">${det.marca}</td>
        <td class="center  sorting_1">${det.lote}</td>
        <td class="center  sorting_1">${det.caducidad}</td>
    </tr>
    </tbody>
    <script>conteo++;</script>
    <c:set var="sumatotal" value="${sumatotal+det.cantidad}"/>
    <c:set var="sumapesou" value="${sumapesou+det.pesou}"/>
    <c:set var="sumatotalT" value="${sumatotalT+(det.cantidad*det.pesou)}"/>
    </c:forEach>
    <tbody aria-relevant="all" aria-live="polite" role="alert"><tr class="odd" style="color:#000">
    <tr>
       <td>TOTAL :</td>
       <td><fmt:formatNumber value="${sumatotal}" maxFractionDigits="0"/> (PZAS)</td>
       <td><fmt:formatNumber value="${sumatotalT}" maxFractionDigits="2"/> (KGS)</td>
       <!--c:set var="sumatotalT" value="${sumatotal*sumapesou}"/>
       <td class="center  sorting_1">${sumatotalT}</td-->
        <td colspan="5"></td>
    </tr>
    </tbody>
</table>
<br>
<script>
   $("#RENGLONVISTA").val(conteo);
   $("#RENGLON").val(conteo);
   $("#PRODUCTO").focus();
</script>