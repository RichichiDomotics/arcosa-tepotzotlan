<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<form:form id="frmDiaFCB" name="frmDiaFC" method="post" role="form" action="reporteSalidaPorFechasClBachocores">
  <section class="grupodedatosreporte">
    <div class="bloquedata">
      <div class="form-group">
        <div class="row-fluid">
          <label for="fechaInicioXFechasCliente">Total Por Fechas y Cliente</label>
        </div>
      </div>
      <div class="form-group">
        <div class="row-fluid input-append">
          <input class="span3 date-picker" data-date-format="dd/mm/yyyy" type='text' id="fechaInicioCliente" name='fechaInicioCliente' placeholder="DD/MM/AAAA" value='' size="10" maxlength="10">
        	 <span class="add-on">
			 	<i class="icon-calendar"></i>
			 </span>
          <input class="span3 date-picker" data-date-format="dd/mm/yyyy" type='text' id="fechaFinCliente" name='fechaFinCliente' value='' size="10" placeholder="DD/MM/AAAA" maxlength="10">
             <span class="add-on">
			 	<i class="icon-calendar"></i>
			 </span>
        </div>
        <BR>
        <div class="row-fluid input-append">
          <div class="row-fluid">
            <label for="fechaInicioXFechasCliente">Cliente</label>
          </div>
          <select id="idCliente" name="idCliente" value='' required class="form-control">
            <option value="" > <fmt:message key="entradas.select"/> </option>
            <c:forEach items="${clientes}" var="client">
              <c:if test="${client.idCliente=='109' || client.idCliente=='116' || client.idCliente=='195' || client.idCliente=='194' || client.idCliente=='396' || client.idCliente=='395' || client.idCliente=='393' || client.idCliente=='375'}">
                <option value="${client.idCliente}"> <c:out value="${client.idCliente} ${client.nombreCliente}"/></option>
              </c:if>
            </c:forEach>
          </select>
                <span class="add-on">
			 	<i class="icon-group"></i>
			 </span>
          &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
          <button class="btn btn-primary btn-small busca" type="button" frmname="frmDiaFCB"  urlenvio = "reporteSalidaPorFechasClBachocores">
            <i class="icon-edit  bigger-125"></i>
            Buscar
          </button>
        </div>
      </div>
    </div>
  </section>
</form:form>