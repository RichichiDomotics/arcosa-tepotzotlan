<form:form name="porFolio" id="porFolio" method="post" role="form" action="reporteFolioSalidares">
  <section class="grupodedatosreporte">
    <div class="bloquedata">
      <div class="form-group">
        <div class="row-fluid">
          <label for="fechaInicioXFechasCliente">Folio de salida</label>
        </div>
      </div>
      <div class="form-group">
        <div class="row-fluid input-append">
          <input type='text' id="folioSalida" name='folioSalida' placeholder="FOLIO DE SALIDA" value='' size="50" maxlength="50" required>
             <span class="add-on">
			 	<i class="icon-edit"></i>
			 </span>
          <button class="btn btn-primary btn-small busca" type="button" frmname="porFolio"  urlenvio = "reporteFolioSalidares">
            <i class="icon-edit  bigger-125"></i>
            Buscar
          </button>
        </div>
      </div>
    </div>
  </section>
</form:form>