<%@ include file="/WEB-INF/views/include.jsp" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<h3 id="item_name">CONSULTA DE INVENTARIOS DE PRODUCTO DE TODAS LAS CAMARAS</h3>

<div id="contenidodelreporte">
    <section class="grupodedatosreportefinal">
        <div class="bloquedata">
            <div class="form-group">
                <h4 id="item_name">REPORTE DE TODAS LAS CAMARAS AL : ${now}</h4>
                <a class="btn btn-primary btn-small" target="_blank" href="exportInventarioTodasCamaras?type=xls">EXPORTA XLS  <span class="glyphicon glyphicon-list-alt"></span></a>
                <a class="btn btn-primary btn-small" target="_blank" href="exportInventarioTodasCamaras?type=pdf">EXPORTA PDF  <span class="glyphicon glyphicon-list-alt"></span></a>
                <table aria-describedby="sample-table-2_info" id="sample-table-2"
                       class="table table-striped table-bordered table-hover dataTable">
                    <thead>
                    <tr role="row">
                        <th>CAMARA</th>
                        <th>ID</th>
                        <th>CLIENTE</th>
                        <th>RD</th>
                        <th>RENGLON</th>
                        <th>FECHA DE ENTRADA</th>
                        <th>DESCRIPCION</th>
                        <th>EMBALAJE</th>
                        <th>MARCA</th>
                        <th>CANTIDAD (PZAS)</th>
                        <th>PESO U(KGS)</th>
                        <th>PESO TOTAL(KGS)</th>
                    </tr>
                    </thead>
                    <c:forEach items="${clientes}" var="e">
                        <tbody aria-relevant="all" aria-live="polite" role="alert">
                        <tr class="odd">
                            <td>
                                    ${e[0]}
                            </td>
                            <td>
                                    ${e[1]}
                            </td>
                            <td>
                                    ${e[2]}
                            </td>
                            <td>
                                    ${e[3]}
                            </td>
                            <td>
                                <fmt:formatNumber value="${e[4]}" maxFractionDigits="0" pattern="###,###,###"/>
                            </td>
                            <td>
                                <fmt:parseDate value="${e[5]}" var="parsedEmpDate" pattern="yyyy-MM-dd"/>
                                <fmt:formatDate pattern="dd/MM/yyyy" value="${parsedEmpDate}"/>
                            </td>
                            <td>
                                    ${e[9]}
                            </td>
                            <td>
                                    ${e[10]}
                            </td>
                            <td>
                                    ${e[11]}
                            </td>
                            <td>
                                <fmt:formatNumber value="${e[6]}" maxFractionDigits="0" pattern="###,###,###"/>
                            </td>
                            <td>
                                <fmt:formatNumber value="${e[7]}" maxFractionDigits="2" pattern="###,###,###.##"/>
                            </td>
                            <td>
                                <fmt:formatNumber value="${e[8]}" maxFractionDigits="2" pattern="###,###,###.##"/>
                            </td>
                        </tr>
                        </tbody>
                    </c:forEach>
                </table>
            </div>
        </div>
        <div><h5 id="item_name_footer">ESTE INVENTARIO SE ENCUENTRA ACTUALIZADO AL : ${now}</h5></div>
    </section>
</div>