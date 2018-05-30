<%@ include file="/WEB-INF/views/include.jsp" %>
<c:if test="${conteoProspectos==0}">
    0
</c:if>
<c:if test="${conteoProspectos>0}">
    <h3>Prospectos coincidentes</h3>
    <table width="150px" aria-describedby="sample-table-2_info" class="table table-striped table-bordered table-hover dataTable" id="tablaResultadoProspectoJefe">
        <thead>
        <tr role="row">
            <th>Prospectos</th>
        </tr>
        </thead>
        <tbody aria-relevant="all" aria-live="polite" role="alert">
    <c:forEach items="${prospectos}" var="c">
        <tr>
            <td>${c.razonSocial}</td>
        </tr>
    </c:forEach>
        </tbody>
    </table>
</c:if>
