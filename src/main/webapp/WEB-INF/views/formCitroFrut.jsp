<%@ include file="/WEB-INF/views/include.jsp" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<link rel="stylesheet" type="text/css" href="resources/bootstrap/css/inventario.css" />
<script type="text/javascript" src="resources/js/inventarios.js"></script>
<style>
  #buscainventario{
    width: 1000px;
    display: table-cell;
  }
</style>
<section class="grupodedatos">
  <h1 class="item_name">Reporte Citrofrut</h1>
  <h3 class="iten_name">(marca / lote)</h3>
  <div class="bloquedata">

      <div class="form-group">
        <label for="RD">Ingrese RD's</label>
        <textarea placeholder="1,2,3,n" id="RD" name='RD' rows="5" cols="8" class="form-control" required></textarea>
      </div>
    <br>
    <div class="form-group">
      <button class="btn btn-primary btn-small citroConsulta" type="button">
        <i class="icon-edit  bigger-125"></i>
        Consulta marca y Lote
      </button>

    </div>
    </div>
</section>
<div id="buscainventario"></div>