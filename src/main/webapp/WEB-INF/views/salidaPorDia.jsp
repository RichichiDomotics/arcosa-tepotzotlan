<form:form id="frmDia" name="frmDia" method="post" role="form" action="reporteSalidaPorDiares">
  <section class="grupodedatosreporte">
  	<div class="bloquedata">
  		<div class="form-group">
				<div class="row-fluid">
					<label for="fechaInicioXdia">Total Por D&iacute;a</label>
				</div>
				<div class="row-fluid input-append">
                	<input class="span3 date-picker" data-date-format="dd/mm/yyyy" type='text' placeholder="DD/MM/AAAA" id="fechaSalida"
                       name='fechaSalida'
                       value='' size="10"
                       maxlength="10">
                       <span class="add-on">
							<i class="icon-calendar"></i>
						</span>
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;	
						<button class="btn btn-primary btn-small busca" type="button" frmname="frmDia" urlenvio = "reporteSalidaPorDiares">
							<i class="icon-edit  bigger-125"></i>
							Buscar
						</button> 
				</div>      
        </div>
  	</div>
  </section>               
</form:form>