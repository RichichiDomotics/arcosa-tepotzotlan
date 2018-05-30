<%@ include file="/WEB-INF/views/include.jsp" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<link rel="stylesheet" type="text/css"	href="resources/bootstrap/css/capturaDetalle.css" />
<section class="container">
<h1 class="item_name"><fmt:message key="consultas.salidas.heading"/></h1>
 <form:form method="post" commandName="ae_consultaIngresos">
      <table aria-describedby="sample-table-2_info" id="sample-table-2" class="table table-striped table-bordered table-hover dataTable">
          <thead>
          <tr role="row">
              <th aria-label="" style="width: 58px;" colspan="1" rowspan="1" role="columnheader" class="center sorting_disabled">Salidas</th>
              <th aria-label="" style="width: 58px;" colspan="1" rowspan="1" role="columnheader" class="center sorting_disabled">Fecha</th>
              <th aria-label="" style="width: 58px;" colspan="1" rowspan="1" role="columnheader" class="center sorting_disabled">Hora</th>
              <th aria-label="" style="width: 58px;" colspan="1" rowspan="1" role="columnheader" class="center sorting_disabled">ID Cliente</th>
              <th aria-label="" style="width: 58px;" colspan="1" rowspan="1" role="columnheader" class="center sorting_disabled">Cliente</th>
              <th aria-label="" style="width: 58px;" colspan="1" rowspan="1" role="columnheader" class="center sorting_disabled">Planta</th>
              <th aria-label="" style="width: 58px;" colspan="1" rowspan="1" role="columnheader" class="center sorting_disabled">Puerta</th>
      </tr>
          </thead>
          <c:if test="${registrado == 'registrado'}">
          	<div class="alert alert-block alert-danger">
                <b>Esta Salida ya est&aacute; siendo atendido por otro usuario.</b>
            </div>          
          </c:if>
          <c:forEach items="${salidas}" var="e">
     <tr>
         <td class="center  sorting_1">
      		<label><input class="radio" type="radio" name="idConsulta" value="${e.idIngresoVehiculo}"><span class="lbl"></span></label>
      	<!--<button class="btn-primary btn-xs vedetalle" idconsulta="${e.idIngresoVehiculo}">Detalle</button>-->
      </td>
         <td class="center  sorting_1">${e.fechaEntrada}</td>
         <td class="center  sorting_1">${e.horaEntrada}</td>
         <td class="center  sorting_1">${e.idCliente}</td>
         <td class="center  sorting_1">${e.nombreCliente}</td>
         <td class="center  sorting_1">${e.nombrePlanta}</td>
         <td class="center  sorting_1">${e.nombrePuerta}</td>
    </tr>
    </c:forEach>
       <tr>
    <td colspan="7" align="center">
    	<button class="btn btn-primary btn-small" type="submit">
			<i class="icon-edit  bigger-125"></i>
			Consultar
		</button>
    </td>
    </tr>
  </table>
  <br>
</form:form>
</section>