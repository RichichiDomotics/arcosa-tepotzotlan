<%--
  Created by IntelliJ IDEA.
  User: jolvera
  Date: 11/11/2014
  Time: 10:30 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ include file="/WEB-INF/views/include.jsp" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<link rel="stylesheet" type="text/css" href="resources/bootstrap/css/entrada.css" />
<section class="contenido-forma-vigilancia">
    <h1 id="item_name"><fmt:message key="pre_entradas_lista.heading"/></h1>
    <h3 id="item_name"><fmt:message key="pre_entradas_lista.subtitle"/></h3>
    <form:form method="post" role="form" action="ae_listaVigilancia">
        <!-- section class="grupodedatos">
            <div class="bloquedata">
                <div class="form-group">
                    <label for="idCliente">CLIENTE</label>
                    <select id="idCliente" name="idCliente" value='' class="form-control">
                        <option value="" > <fmt:message key="entradas.select"/> </option>
                        <c:forEach items="${clientes}" var="client">
                            <option value="${client.idCliente}"> <c:out value="${client.idCliente} ${client.nombreCliente}"/></option>
                        </c:forEach>
                    </select>
                </div>
            </div>
            <div class="form-group">
                <input type="submit" class="btn btn-warning btn-lg" value="Buscar">
            </div>
        </section-->
    </form:form>

    <table aria-describedby="sample-table-2_info" id="sample-table-2" class="table table-striped table-bordered table-hover dataTable">
        <thead>
        <tr role="row">
            <th>Cliente</th>
            <th>Fecha</th>
            <th>Hora</th>
            <th>Operador</th>
            <th>Placas</th>
            <th>Veh&iacute;culo</th>
            <th>Peso</th>
            <th>Maniobra</th>
        </tr>
        </thead>
        <c:forEach items="${registros}" var="e">
            <tbody aria-relevant="all" aria-live="polite" role="alert"><tr class="odd">
                <td class="center  sorting_1">${e[1].idCliente} ${e[1].nombreCliente}</td>
                <td class="center  sorting_1">
                	<fmt:parseDate value="${e[0].fechaEntrada}" var="parsedEmpDate" pattern="yyyy-MM-dd" />
      				<fmt:formatDate pattern="dd-MM-yyyy" value="${parsedEmpDate}" />
                </td>
                <td class="center  sorting_1">${e[0].horaEntrada}</td>
                <td class="center  sorting_1">${e[0].nombreOperador}</td>
                <td class="center  sorting_1">${e[0].placasVehiculo}</td>
                <td class="center  sorting_1">${e[0].tipoProducto}</td>
                <td class="center  sorting_1"><fmt:formatNumber value="${e[0].peso}" maxFractionDigits="2"/></td>
                <td class="center  sorting_1">${e[0].maniobraCargaDescarga}</td>
               </tr> 
            </tbody>
        </c:forEach>
    </table>
</section>
