<%@ include file="/WEB-INF/views/include.jsp" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<link rel="stylesheet" type="text/css" href="resources/bootstrap/css/entrada.css" />
		<table aria-describedby="sample-table-2_info" id="sample-table-2" class="table table-striped table-bordered table-hover dataTable">
			<thead>
			<tr role="row">
				<th>RD</th>
				<th>FECHA INICIO PERIODO</th>
				<th>FECHA FIN PERIODO</th>
				<th>CAMARA</th>
				<th>TIPO SERVICIO</th>
				<th>KILOS</th>
				<th>TOTAL ($)</th>
				<th>FECHA FACTURA </th>
			</tr>
			</thead>
			<c:forEach items="${detalleFacturas}" var="factura">
				<tbody aria-relevant="all" aria-live="polite" role="alert">
				<tr class="odd">
					<td>${factura[0]}</td>
					<td><fmt:parseDate value="${factura[1]}" var="parsedEmpDate" pattern="yyyy-MM-dd" />
						<fmt:formatDate pattern="dd-MM-yyyy" value="${parsedEmpDate}" />
					</td>
					<td><fmt:parseDate value="${factura[2]}" var="parsedEmpDate" pattern="yyyy-MM-dd" />
						<fmt:formatDate pattern="dd-MM-yyyy" value="${parsedEmpDate}" />
					</td>
					<td>${factura[3]}</td>
					<td>${factura[5]}</td>
					<td><fmt:formatNumber type="number" maxFractionDigits="2" value="${factura[4]}"/></td>
					<td>$<fmt:formatNumber type="number" maxFractionDigits="2" value="${factura[7]}"/></td>
					<td><fmt:parseDate value="${factura[9]}" var="parsedEmpDate" pattern="yyyy-MM-dd" />
						<fmt:formatDate pattern="dd-MM-yyyy" value="${parsedEmpDate}" />
					</td>
				</tr>
				</tbody>
			</c:forEach>
		</table>



