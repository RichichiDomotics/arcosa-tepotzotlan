<%@ include file="/WEB-INF/views/include.jsp" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<h3 id="item_name">REPORTE DE SALDOS GENERALES POR CLIENTE</h3>
<div id="contenidodelreporte">
    <!--a class="btn btn-primary btn-small" target="_blank" href="exportSalArrastre?type=xls&reciboEntrada=${reciboEntrada}">EXPORTA XLS  <span class="glyphicon glyphicon-list-alt"></span></a>
    <a class="btn btn-primary btn-small" target="_blank" href="exportSalArrastre?type=pdf&reciboEntrada=${reciboEntrada}">EXPORTA PDF  <span class="glyphicon glyphicon-th-list"></span></a-->
    <section class="grupodedatosreportefinal">
        <div class="bloquedata">
            <div class="form-group">
                <h4 id="item_name">SALDOS GENERALES POR CLIENTE AL : ${now}</h4>
                <table aria-describedby="sample-table-2_info" id="sample-table-2" class="table table-striped table-bordered table-hover dataTable">
                    <thead>
                    <tr role="row">
                        <th>ID</th>
                        <th>NOMBRE CLIENTE</th>
                        <th>CANTIDAD (PZAS)</th>
                        <th>TOTAL (TON)</th>
                    </tr>
                    </thead>
                    <c:set var="totalpzas" value="${0}"/>
                    <c:set var="totalkilos" value="${0}"/>
                    <tbody aria-relevant="all" aria-live="polite" role="alert">
                    <c:forEach items="${clientes}" var="e">
                        <tr class="odd">
                            <td>
                                    ${e[0]}
                            </td>
                            <td>
                                    ${e[1]}
                            </td>
                            <td>
                                <fmt:formatNumber value="${e[2]}" maxFractionDigits="0" pattern="###,###,###"/>
                            </td>
                            <td>
                                <fmt:formatNumber value="${e[3]}" maxFractionDigits="2" pattern="###,###,###.##"/>
                            </td>
                            <c:set var="totalpzas" value="${totalpzas+(e[2])}"/>
                            <c:set var="totalkilos" value="${totalkilos+(e[3])}"/>
                        </tr>
                    </c:forEach>
                    </tbody>
                    <tr class="trcontenido">
                        <td colspan="2">TOTAL</td>
                        <td><b><fmt:formatNumber value="${totalpzas}" maxFractionDigits="0" pattern="###,###,###"/></b></td>
                        <td><b><fmt:formatNumber value="${totalkilos}" maxFractionDigits="2" pattern="###,###,###.##"/></b></td>
                    </tr>
                </table>
            </div>
        </div>
        <hr>
        <h4 id="item_name">TOTAL EN CAMARAS Y TUNEL</h4>
        <hr>
        <div><h3 id="item_name"><b>Total Piezas : <fmt:formatNumber value="${totalpzas}" maxFractionDigits="0"  pattern="###,###,###.##"/> PZAS.</b></h3></div>
        <div><h3 id="item_name"><b>Total Toneladas : <fmt:formatNumber value="${totalkilos}" maxFractionDigits="2"  pattern="###,###,###.##"/> TON.</b></h3></div>
        <div><h5 id="item_name">ESTE INVENTARIO SE ENCUENTRA ACTUALIZADO AL : ${now}</h5></div>
    </section>
    </div>