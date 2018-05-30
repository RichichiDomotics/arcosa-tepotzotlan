<form:form id="frmDiaFC" name="frmDiaFC" method="post" role="form" action="reporteEntradaPorFechasClres">
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
                        <option value="${client.idCliente}"> <c:out value="${client.idCliente} ${client.nombreCliente}"/></option>
                    </c:forEach>
                </select> 
                <span class="add-on">
			 	<i class="icon-group"></i>
			 </span>
			 <button class="btn btn-primary btn-small busca" type="button" frmname="frmDiaFC"  urlenvio = "reporteEntradaPorFechasClres">
					<i class="icon-edit  bigger-125"></i>
					Buscar
				</button>
			</div>
      </div>
  	</div>
  </section>	
</form:form>