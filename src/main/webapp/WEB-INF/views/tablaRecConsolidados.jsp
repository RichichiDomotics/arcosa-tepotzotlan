+6<%@ include file="/WEB-INF/views/include.jsp" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<script type="text/javascript" src="resources/js/salidas.js"></script>

<link rel="stylesheet" type="text/css"	href="resources/bootstrap/css/capturaDetalle.css" />
<c:if test="${ubicaciones=='[]'}">NO HAY REGISTROS CON LAS CONDICIONES DE BUSQUEDA INGRESADAS</c:if>
<c:if test="${ubicaciones!='[]'}">
<h3 class="item_name">UBICACIONES OCUPADAS</h3>
<section class="grupodedatosreaddetallesub">
<div class="bloquedataUbica">
 <div class="form-groupUbica">
  <table id="sample-table-2" class="table table-striped table-bordered table-hover">
        <thead align="center">
        	<tr role="row">
              <th>CAMARA</th>
              <th>OCUPADAS</th>
              <th>NUM POSICIONES</th>
           </tr>
          </thead>
    <c:forEach items="${ubicaciones}" var="det">
        <tbody aria-relevant="all" aria-live="polite" role="alert"><tr class="odd" align="center">
            <td>${det[0]}</td>
            <td>
                <c:if test="${det[1] != 0}" >
                    OCUPADA
                </c:if>
                <c:if test="${det[1] == '0'}" >
                    VACIA
                </c:if>
            </td>
            <td><fmt:formatNumber value="${det[2]}" maxFractionDigits="0"/></td>
        </tr>
        </tbody>
    </c:forEach>
  </table>
 </div>
</div>
    <h3 class="item_name">UBICACIONES VACIAS</h3>
    <table id="sample-table-2" class="table table-striped table-bordered table-hover">
        <thead>
        <tr role="row">
            <th>CAMARA</th>
            <th>VACIAS</th>
            <th>NUM POSICIONES</th>
        </tr>
        </thead>
        <c:forEach items="${ubicacionesvacias}" var="detv">
            <tbody aria-relevant="all" aria-live="polite" role="alert"><tr class="odd">
                <td>${detv[0]}</td>
                <td>
                    <c:if test="${detv[1] != 0}" >
                        OCUPADA
                    </c:if>
                    <c:if test="${detv[1] == '0'}" >
                        VACIA
                    </c:if>
                </td>
                <td><fmt:formatNumber value="${detv[2]}" maxFractionDigits="0"/></td>
            </tr>
            </tbody>
        </c:forEach>
    </table>
</section>
</c:if>
