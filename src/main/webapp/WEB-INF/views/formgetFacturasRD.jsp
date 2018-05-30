<%@ include file="/WEB-INF/views/include.jsp" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<link rel="stylesheet" type="text/css" href="resources/bootstrap/css/inventario.css" />
<script type="text/javascript" src="resources/js/inventarios.js"></script>
<script>
  $(document).ready(function(){
    $(".consultaRDF").click(function(){
      consecutivo=$("#consecutivo").val();
      $.ajax("getFacturasRD",{
        "type": "POST",
        "beforeSend": function() {
          $("#buscainventario").css("font-size","0.875em");
          $("#buscainventario").html('<img src="resources/img/loader.GIF"/>');
        },
        "success": function(result){
          $("#buscainventario").html(result);
        },
        "error": function(result) {
          bootbox.alert("error al consultar las facturas por RD");
        },
        "data": {consecutivo : consecutivo},
        "async": true
      });
    });
  });
</script>
<style>
  #buscainventario{
    width: 1000px;
  }
</style>
<section class="container">
  <h1 class="item_name">Consulta Facturas por recibo de entrada</h1>
  <h3 class="item_name">Reporte Facturas por RD</h3>

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
          <button class="btn btn-primary btn-small consultaRDF" type="button">
            <i class="icon-edit  bigger-125"></i>
            Consulta Registro de Entrada
          </button>
        </div>
      </div>
    </section>

  <div id="buscainventario"></div>
</section>
