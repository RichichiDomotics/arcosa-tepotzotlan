<%@ include file="/WEB-INF/views/include.jsp" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<h3 id="item_name">REPORTE DE SALDOS DE CAMARAS Y TUNEL : ${now}</h3>
<div id="contenidodelreporte">
    <!--a class="btn btn-primary btn-small" target="_blank" href="exportSalArrastre?type=xls&reciboEntrada=${reciboEntrada}">EXPORTA XLS  <span class="glyphicon glyphicon-list-alt"></span></a>
    <a class="btn btn-primary btn-small" target="_blank" href="exportSalArrastre?type=pdf&reciboEntrada=${reciboEntrada}">EXPORTA PDF  <span class="glyphicon glyphicon-th-list"></span></a-->
<section class="grupodedatosreportefinal">
  		<div class="bloquedata">
  			<div class="form-group">
			<h4 id="item_name">SALDO POR CAMARAS AL : ${now}</h4>
                <table aria-describedby="sample-table-2_info" id="sample-table-2" class="table table-striped table-bordered table-hover dataTable">
                    <thead>
                    <tr role="row">
                        <th>CAMARA</th>
                        <th>PIEZAS (PZAS)</th>
                        <th>KILOS (KGS)</th>
                        <th>TONELADAS</th>
                    </tr>
                    </thead>
                    <c:set var="totalpzas" value="${0}"/>
                    <c:set var="totalkilos" value="${0}"/>
                    <tbody aria-relevant="all" aria-live="polite" role="alert">
                    <c:forEach items="${camaras}" var="e">
                          <tr class="odd">
                            <td>
                                ${e[0]}
                            </td>
                            <td>
                                <fmt:formatNumber value="${e[1]}" maxFractionDigits="0" pattern="###,###,###"/>
                            </td>
                            <td>
                                <fmt:formatNumber value="${e[2]}" maxFractionDigits="2" pattern="###,###,###.##"/>
                            </td>
                            <td>
                                <fmt:formatNumber value="${e[3]}" maxFractionDigits="2" pattern="###,###,###.##"/>
                            </td>
                            <c:set var="totalpzas" value="${totalpzas+(e[1])}"/>
                            <c:set var="totalkilos" value="${totalkilos+(e[2])}"/>
                        </tr>
                    </c:forEach>
                    </tbody>
                    <tr class="trcontenido">
                        <td colspan="4"><h4><b>PIEZAS EN CAMARA:<fmt:formatNumber value="${totalpzas}" maxFractionDigits="0" pattern="###,###,###"/></b></h4></td>
                    </tr>
                    <tr class="trcontenido">
                        <td colspan="4"><h4><b>KILOS EN CAMARA:<fmt:formatNumber value="${totalkilos}" maxFractionDigits="2"  pattern="###,###,###.##"/></b></h4></td>
                    </tr>
                </table>
			</div>
            <div class="form-group">
                <h4 id="item_name">SALDOS POR TUNEL AL : ${now}</h4>
                <table aria-describedby="sample-table-2_info" id="sample-table-2" class="table table-striped table-bordered table-hover dataTable">
                    <thead>
                    <tr role="row">
                        <th>TUNEL</th>
                        <th>PIEZAS (PZAS)</th>
                        <th>KILOS (KGS)</th>
                        <th>TONELADAS</th>
                    </tr>
                    </thead>
                    <c:set var="totalpzasT" value="${0}"/>
                    <c:set var="totalkilosT" value="${0}"/>
                    <tbody aria-relevant="all" aria-live="polite" role="alert">
                    <c:forEach items="${tunel}" var="e">
                        <tr class="odd">
                            <td>
                                    ${e[0]}
                            </td>
                            <td>
                                <fmt:formatNumber value="${e[1]}" maxFractionDigits="0" pattern="###,###,###"/>
                            </td>
                            <td>
                                <fmt:formatNumber value="${e[2]}" maxFractionDigits="2" pattern="###,###,###.##"/>
                            </td>
                            <td>
                                <fmt:formatNumber value="${e[3]}" maxFractionDigits="2" pattern="###,###,###.##"/>
                            </td>
                            <c:set var="totalpzasT" value="${totalpzasT+(e[1])}"/>
                            <c:set var="totalkilosT" value="${totalkilosT+(e[2])}"/>
                        </tr>
                    </c:forEach>
                    </tbody>
                    <tr class="trcontenido">
                        <td colspan="4"><h4><b>PIEZAS EN TUNEL:<fmt:formatNumber value="${totalpzasT}" maxFractionDigits="0" pattern="###,###,###"/></b></h4></td>
                    </tr>
                    <tr class="trcontenido">
                        <td colspan="4"><h4><b>KILOS EN TUNEL:<fmt:formatNumber value="${totalkilosT}" maxFractionDigits="2"  pattern="###,###,###.##"/></b></h4></td>
                    </tr>
                </table>
            </div>
		</div>
        <c:set var="totalpzasTt" value="${totalpzas+totalpzasT}"/>
        <c:set var="totalkilosTt" value="${totalkilos+totalkilosT}"/>
        <hr>
            <h4 id="item_name">TOTAL EN CAMARAS Y TUNEL</h4>
        <hr>
        <div><h3 id="item_name"><b>Total Piezas : <fmt:formatNumber value="${totalpzasTt}" maxFractionDigits="0"  pattern="###,###,###.##"/></b></h3></div>
        <div><h3 id="item_name"><b>Total Kilos : <fmt:formatNumber value="${totalkilosTt}" maxFractionDigits="2"  pattern="###,###,###.##"/></b></h3></div>
    <div><h5 id="item_name">ESTE INVENTARIO SE ENCUENTRA ACTUALIZADO AL : ${now}</h5></div>
</section>
    </div>