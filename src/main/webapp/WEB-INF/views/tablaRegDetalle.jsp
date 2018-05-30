<%@ include file="/WEB-INF/views/include.jsp" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<script type="text/javascript" src="resources/js/salidas.js"></script>
<link rel="stylesheet" type="text/css"	href="resources/bootstrap/css/capturaDetalle.css" />
<script>var conteo=1;</script>
<table aria-describedby="sample-table-2_info" id="sample-table-2" class="table table-striped table-bordered table-hover dataTable">
        <thead>
        	<tr role="row">
              <th>Renglon</th>
              <th>Producto</th>
              <th>Marca</th>
              <th>Lote</th>
              <th>Embalaje</th>
              <th>Cantidad</th>
              <th>Peso U</th>
              <th>Caducidad</th>
           </tr>
          </thead> 
    <c:set var="sumatotal" value="0"/> 
    <c:set var="sumapesou" value="0"/> 
    <c:set var="sumatotalT" value="0"/>       
    <c:forEach items="${regEntradasAlmacenadas}" var="det">
        <tbody aria-relevant="all" aria-live="polite" role="alert"><tr class="odd">
            <td><fmt:formatNumber value="${det.renglon}" maxFractionDigits="0"/></td>
            <td>${det.descripcion}</td>
            <td>${det.marca}</td>
            <td>${det.lote}</td>
            <td>${det.embalaje}</td>
            <td><fmt:formatNumber value="${det.cantidad}" maxFractionDigits="0"/></td>
            <td><fmt:formatNumber value="${det.pesou}" maxFractionDigits="2"/></td>
            <td>${det.caducidad}</td>
        </tr>
        </tbody>
        <script>conteo++;</script>
        <c:set var="sumatotal" value="${sumatotal+det.cantidad}"/>
        <c:set var="sumapesou" value="${sumapesou+det.pesou}"/>
        <c:set var="sumatotalT" value="${sumatotalT+(det.cantidad*det.pesou)}"/>
    </c:forEach>
    <tbody aria-relevant="all" aria-live="polite" role="alert"><tr class="odd" style="color:#000">
    <tr>
        <td colspan="4"></td>
        <td class="center  sorting_1">TOTAL :</td>
        <td class="center  sorting_1"><fmt:formatNumber value="${sumatotal}" maxFractionDigits="0"/></td>
        <td class="center  sorting_1"><fmt:formatNumber value="${sumatotalT}" maxFractionDigits="2"/>(KGS)</td>
        <!-- <c:set var="sumatotalT" value="${sumatotal*sumapesou}"/>
			  <td class="center  sorting_1"><fmt:formatNumber value="${sumatotalT}" maxFractionDigits="2"/> (KGS)</td-->
        <td></td>
    </tr>
    </tbody>
</table>
<br>
<script>
   $("#RENGLONVISTA").val(conteo);
   $("#RENGLON").val(conteo);
   $("#PRODUCTO").focus();
</script>