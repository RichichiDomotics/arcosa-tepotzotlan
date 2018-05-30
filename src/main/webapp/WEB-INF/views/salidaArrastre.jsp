<form:form name="porArrastre" id="porArrastre" method="post" role="form" action="reporteArrastreSaldosres">
  <section class="grupodedatosreporte">
  	<div class="bloquedata">
  		<div class="form-group">
  			<div class="row-fluid">
				<label for="fechaInicioXFechasCliente">Recibo de Entrada</label>
			</div>
        </div>
        <div class="form-group">
           <div class="row-fluid input-append">	
             <input type='text' id="reciboEntrada" name='reciboEntrada' placeholder="RECIBO DE ENTRADA" value='' size="50" maxlength="50" required>
             <span class="add-on">
			 	<i class="icon-edit"></i>
			 </span>
             <button class="btn btn-primary btn-small busca" type="button" frmname="porArrastre"  urlenvio = "reporteArrastreSaldosres">
					<i class="icon-edit  bigger-125"></i>
					Buscar
				</button>
			</div>	
      </div>
  	</div>
  </section>	
</form:form>