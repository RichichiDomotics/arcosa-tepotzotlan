<%@ include file="/WEB-INF/views/include.jsp" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<link rel="stylesheet" type="text/css" href="resources/bootstrap/css/inventario.css" />
<script type="text/javascript" src="resources/js/inventarios.js"></script>
<style>
  #buscainventario{
    width: 1000px;
  }
</style>
<section class="container">
  <h1 class="item_name">Consulta Recibo de Entrada</h1>
  <h3 class="item_name">Reporte por RD</h3>
  <form:form method="post" action="consultaInventario">
    <section class="grupodedatos">
      <div class="bloquedata">
        <div class="form-group">
          <label>RECIBO DE DEPOSITO(RD)</label>
          <input type='text' placeholder="Recibo de Deposito" id="consecutivo" name='consecutivo' value='' class="form-control" required>
        </div>
      </div>
      <div class="bloquedata">
        <div class="form-group">
          <br/>
          <button class="btn btn-primary btn-small buscainventariosSeguro" type="button">
            <i class="icon-edit  bigger-125"></i>
            Consulta Registro de Entrada
          </button>
        </div>
      </div>
    </section>
    <input type='hidden' name='fechaEntrada' value='${fecha}'>
    <input type='hidden' name='horaEntrada' value='${hora}'>
  </form:form>
  <div id="buscainventario"></div>
</section>
