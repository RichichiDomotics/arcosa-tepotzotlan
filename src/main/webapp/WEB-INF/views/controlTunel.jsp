<%--
  Created by IntelliJ IDEA.
  User: jolvera
  Date: 22/07/2014
  Time: 10:04 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ include file="/WEB-INF/views/include.jsp" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<link rel="stylesheet" type="text/css" href="resources/bootstrap/css/controltunel.css" />

<section class="container">
<h1 class="item_name"><fmt:message key="reserva.tunel.heading"/></h1>
<h3 class="item_name"><fmt:message key="reserva.tunel.subtitle"/></h3>
<form:form commandName="controlTunel" method="post" action="insControlTunel">
 <section class="grupodedatos">
  <div class="bloquedata">
    <div class="form-group">
        <label for="periodo">PERIODO</label>
        <select id="periodo" name="periodo" value='' class="form-control" required>
            <option value="" > <fmt:message key="entradas.select"/> </option>
            <option value="12">12</option>
            <option value="24">24</option>
            <option value="48">48</option>
        </select>
      </div>
      <div class="form-group">  
        <label for="idCliente">CLIENTE</label>
        <select id="idCliente" name="idCliente" value='' class="form-control" required>
            <option value="" > <fmt:message key="entradas.select"/> </option>
            <c:forEach items="${clientes}" var="client">
                <option value="${client.idCliente}"> <c:out value="${client.idCliente} ${client.nombreCliente}"/></option>
            </c:forEach>
        </select>
    </div>
  </div>
  <div class="bloquedata">  
    <div class="form-group">
        <label for="kilos">KILOS A ENVIAR(m&aacute;ximo 40 mil)</label>
        <input id="kilos" type='text' placeholder="KILOS A ENVIAR" name='kilos' value='' size="10" maxlength="10" class="form-control" required>
    </div>
    <div class="form-group">
        <label for="solicitante">SOLICITANTE</label>
        <input id="solicitante" type='text' placeholder="SOLICITANTE" name='solicitante' value='' size="10" maxlength="255" class="form-control" required>
    </div>
      <!--div class="form-group">
          <a href="/calendarioTunel" class="btn btn-primary btn-small">
              <i class="icon-edit  bigger-125"></i>
              Consultar calendario tunel
          </a>
      </div-->
  </div>
  <div class="bloquedata">  
    <div class="form-group">
        <label for="fechaCapturada">FECHA DE APARTADO</label>
        <input id="fechaCapturada" type='text' placeholder="FECHA dd/mm/aaaa" name='fechaCapturada' value='${fecha}' size="10" maxlength="10" class="form-control" required>
    </div>
    <div class="form-group">
        <label for="observaciones">OBSERVACIONES</label>
        <input id="observaciones" type='text' placeholder="OBSERVACIONES" name='observaciones' value='' size="75" maxlength="150" class="form-control">
    </div>
    <div class="form-group">
        <button class="btn btn-primary btn-small" type="submit">
			<i class="icon-edit  bigger-125"></i>
			Registrar
		</button>
    </div>
  </div>
 </section> 
</form:form>

<!--div id="resultadoTabla">

    <c:choose>

    <c:when test="${not empty listaTunel}">
        <table aria-describedby="sample-table-2_info" id="sample-table-2" class="table table-striped table-bordered table-hover dataTable">
        <thead>
        	<tr role="row">
            <th aria-label="" style="width: 58px;" colspan="1" rowspan="1" role="columnheader" class="center sorting_disabled">Id</th>
            <th aria-label="" style="width: 58px;" colspan="1" rowspan="1" role="columnheader" class="center sorting_disabled">Fecha de captura</th>
            <th aria-label="" style="width: 58px;" colspan="1" rowspan="1" role="columnheader" class="center sorting_disabled">Periodo</th>
            <th aria-label="" style="width: 58px;" colspan="1" rowspan="1" role="columnheader" class="center sorting_disabled">Cliente</th>
            <th aria-label="" style="width: 58px;" colspan="1" rowspan="1" role="columnheader" class="center sorting_disabled">Kilos</th>
            <th aria-label="" style="width: 58px;" colspan="1" rowspan="1" role="columnheader" class="center sorting_disabled">Solicitante</th>
            <th aria-label="" style="width: 58px;" colspan="1" rowspan="1" role="columnheader" class="center sorting_disabled">Observaciones</th>
            <th aria-label="" style="width: 58px;" colspan="1" rowspan="1" role="columnheader" class="center sorting_disabled">Fecha de apartado</th>
        </tr>
        </thead>
            <tbody aria-relevant="all" aria-live="polite" role="alert"><tr class="odd">
        <c:forEach items="${listaTunel}" var="lista">
            <td  class="center  sorting_1">${lista.folioAsignado}</td>
            <td  class="center  sorting_1">
            	<fmt:parseDate value="${lista.fecha}" var="parsedEmpDate" pattern="dd-MM-yyyy" />
                <fmt:formatDate pattern="dd/MM/yyyy" value="${parsedEmpDate}" />
            </td>
            <td  class="center  sorting_1">${lista.periodo}</td>
            <td  class="center  sorting_1">${lista.idCliente} ${lista.nombreCliente}</td>
            <td  class="center  sorting_1">${lista.kilos}</td>
            <td  class="center  sorting_1">${lista.solicitante}</td>
            <td  class="center  sorting_1">${lista.observaciones}</td>
            <td  class="center  sorting_1">
            	<fmt:parseDate value="${lista.fechaCapturada}" var="parsedEmpDate" pattern="dd/MM/yyyy" />
                <fmt:formatDate pattern="dd/MM/yyyy" value="${parsedEmpDate}" />
            </td>
        </tr>
        </c:forEach>
            </tbody>
        </table>
    </c:when>

    </c:choose>
</div-->
</section>