<form:form id="frmDiafec" name="frmDiafec" method="post" role="form" action="reporteEntradaPorFechasres">
  <section class="grupodedatosreporte">
  	<div class="bloquedata">
  		<div class="form-group">
  				<div class="row-fluid">
					<label for="fechaInicioXFechas">Total Por Fechas de</label>
				</div>
				<div class="row-fluid input-append">
                	<input class="span3 date-picker" data-date-format="dd/mm/yyyy" type='text' id="fechaInicio" name='fechaInicio' placeholder="DD/MM/AAAA" value='' maxlength="10"> 
                	<span class="add-on">
						<i class="icon-calendar"></i>
					</span>
					<div class="row-fluid input-append">
					<input class="span3 date-picker" data-date-format="dd/mm/yyyy" type='text' id="fechaFin" name='fechaFin' value='' placeholder="DD/MM/AAAA" maxlength="10"> 
               	 	<span class="add-on">
						<i class="icon-calendar"></i>
					</span>
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;	
					<button class="btn btn-primary btn-small busca" type="button" frmname="frmDiafec" urlenvio = "reporteEntradaPorFechasres">
						<i class="icon-edit  bigger-125"></i>
						Buscar
					</button> 
                </div>
				</div>	
        </div>
  	</div>
  </section>  
</form:form>