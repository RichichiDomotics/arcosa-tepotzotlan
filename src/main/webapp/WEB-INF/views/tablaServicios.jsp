<%@ include file="/WEB-INF/views/include.jsp" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<link rel="stylesheet" type="text/css" href="resources/bootstrap/css/clientesCaptura.css" />
		<table aria-describedby="sample-table-2_info" id="sample-table-2" class="table table-striped table-bordered table-hover dataTable">
			<thead>
			<tr role="row">
				<th>CLAVE SERVICIO</th>
				<th>DESCRIPCION</th>
				<th>CUOTA</th>
			</tr>
			</thead>
			<c:forEach items="${serviciosRegistrados}" var="servicioReg">
				<tbody aria-relevant="all" aria-live="polite" role="alert">
				<tr class="odd">
					<td>${servicioReg.clave}</td>
					<td>${servicioReg.descripcion}</td>
					<td>${servicioReg.cuota}</td>
				</tr>
				</tbody>
			</c:forEach>
		</table>