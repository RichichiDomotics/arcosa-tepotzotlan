<form:form id="porCamara" name="porCamara" method="post" role="form" action="reporteEntradaPorCamarares">
  <section class="grupodedatosreporte">
  	<div class="bloquedata">
  		<div class="form-group">
  			<div class="row-fluid">
				<label for="fechaInicioXFechasCliente">Reporte por Camara</label>
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
					<label for="fechaInicioXFechasCliente">Camara</label>
				</div>
             	<select id="idCamara" name="idCamara" value='' required class="form-control">
                    <option value="" > <fmt:message key="entradas.select"/> </option>
                    <c:forEach items="${camaras}" var="camara">
                        <option value="${camara.idCatalogo}"> <c:out value="${camara.descripcion}"/></option>
                    </c:forEach>
					<option value="T1">TUNEL 1</option>
					<option value="T2">TUNEL 2</option>
					<option value="T3">TUNEL 3</option>
                </select> 
                <span class="add-on">
			 		<i class="icon-download"></i>
			 	</span>
			 	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;	
                <button class="btn btn-primary btn-small busca" type="button" frmname="porCamara"  urlenvio = "reporteEntradaPorCamarares">
					<i class="icon-edit  bigger-125"></i>
					Buscar
				</button>
			</div>	
      </div>
  	</div>
  </section>	
</form:form>