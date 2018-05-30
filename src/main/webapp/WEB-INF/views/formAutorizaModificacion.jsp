<style>
  .bloqueforma{
    display:block;
    width: 300px;
    float: left;;
  }
  .container{
    width : 950px;
  }
</style>
<script>
  $(document).ready(function(){
    $(".registrar").click(function(){
      nombre=$("#nombre").val();
      fecha=$("#fecha").val();
      hora=$("#hora").val();
      idCliente=$("#idCliente").val();
      idModificacion=$("#idModificacion").val();
      solucion=$("#cambio").val();
      tipoMod=$("#causa").val();
      consecutivo=$("#consecutivo").val();
      salida=$("#salida").val();
      bootbox.hideAll();
      registrar(nombre,fecha,hora,idCliente,idModificacion,solucion,tipoMod,consecutivo,salida);
    });
    registrar = function (nombre,fecha,hora,idCliente,idModificacion,solucion,tipoMod,consecutivo,salida) {
      $.ajax("registrarAtencion",{
        "type" : "POST",
        "success" : function (result){
          bootbox.alert("Registro Completado con exito",function(){
            window.location.href=window.location;
          });
        },
        "error": function(result){
          bootbox.alert("Ocurrio un error");
        },
        "data": {nombre:nombre,fecha:fecha,hora:hora,idCliente:idCliente,
          idModificacion:idModificacion,solucion:solucion,tipoMod:tipoMod,
          consecutivo:consecutivo,salida:salida},
        "async": true
      })
    }
  });
</script>
<div class="modal-header no-padding">
  <div class="table-header">
    <button type="button" class="close" data-dismiss="modal">&times;</button>
    <strong>Ingrese los Siguientes Datos</strong>
  </div>
</div>
<section class="container">
  <div class="bloqueforma">

    <div class="form-group">
      <label>ATENDIDO POR:</label>
      <input type="text" class="form-control" placeholder="NOMBRE" style="text-transform:uppercase;" id="nombre" name="nombre" value="${usuario}" aria-required="true" disabled>
    </div>

    <div class="form-group">
      <label>FECHA</label>
      <input type="text" class="form-control" placeholder="FECHA" id="fecha" name="fecha" value="${now}" disabled>
    </div>


    <div class="form-group">
      <label>HORA</label>
      <input type="text" class="form-control" placeholder="HORA" id="hora" name="hora" value="${time}" disabled>
    </div>

    <input type="hidden" class="form-control" id="idCliente" value="${idCliente}">

    <input type="hidden" class="form-control" id="idModificacion" value="${idModificacion}">

    <div class="form-group">
      <label>SOLUCION:</label>
           <textarea placeholder="SOLUCION" id="cambio" name='cambio' data-maxlength="200"
                     style="width: 667px; height: 156px;" class="form-control limited"></textarea>
    </div>

  </div>
  <div class="bloqueforma">

    <div class="form-group">
      <label>TIPO DE MODIFICACION</label>
      <select class="form-control" id="causa" name="causa" aria-required="true">
        <option value="">Seleccione una opci&oacute;n</option>
        <option value="CANCELACION DE LA SALIDA">CANCELACION DE LA SALIDA</option>
        <option value="EL CLIENTE NO REQUIERE EL PRODUCTO">EL CLIENTE NO REQUIERE EL PRODUCTO</option>
        <option value="SE LLEVA MENOS PRODUCTO">SE LLEVA MENOS PRODUCTO</option>
        <option value="NO ES PRODUCTO DESEADO">NO ES PRODUCTO DESEADO</option>
        <option value="ERROR DE ALMACENISTA">ERROR DE ALMACENISTA</option>
        <option value="ERROR DE MAQUILA">ERROR DE MAQUILA</option>
        <option value="ERROR DE OPERADOR">ERROR DE OPERADOR</option>
        <option value="ERROR DE SISTEMA">ERROR DE SISTEMA</option>
        <option value="SOLICITUD DEL CLIENTE">NO SE PRESENTO EL CLIENTE</option>
        <option value="FALTA DE ESPACIO EN CAMARAS">FALTA DE ESPACIO EN CAMARAS</option>
        <option value="FALTA DE DOCUMENTOS">FALTA DE DOCUMENTOS</option>
        <option value="PRODUCTO DAÑADO">PRODUCTO DA&Ntilde;ADO</option>
        <option value="TEMPERATURA FUERA DE RANGO">TEMPERATURA FUERA DE RANGO</option>
        <option value="INGRESO FUERA DE HORARIO">INGRESO FUERA DE HORARIO</option>
        <option value="NO SE PRESENTO EL CLIENTE">NO SE PRESENTO EL CLIENTE</option>
        <option value="NO PROCEDE">NO PROCEDE</option>
        <option value="OTROS">OTROS</option>
      </select>
    </div>


    <div class="form-group">
      <label>RD AFECTADO</label>
      <input type="text" class="form-control" placeholder="RD AFECTADO" id="consecutivo" name="consecutivo" value="${consecutivo}" aria-disabled="true">
    </div>


    <div class="form-group">
      <label>SALIDA AFECTADA</label>
      <input type="text" class="form-control" placeholder="SALIDA AFECTADA" id="salida" name="salida" value="${salida}" aria-disabled="true">
    </div>

    <div class="group-forma">
      <button class="btn btn-primary btn-small registrar" type="submit">
        <i class="icon-edit  bigger-125"></i>
        Registrar
      </button>
    </div>
  </div>
</section>