<%@ include file="/WEB-INF/views/include.jsp" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<link rel="stylesheet" type="text/css" href="resources/bootstrap/css/clientesCaptura.css" />
<link rel="stylesheet" type="text/css" href="resources/bootstrap/css/loader-barra.css" />
<script>
  $(document).ready(function() {
    $(".loader").hide();

    $(".ingresarSegumiento").click(function(){
      idProspecto = $(".idProspecto").val();
      fecha=$("#FECHA").val();
      etapa=$("#ETAPA").val();
      seguimiento=$("#segui").val();
      tipoSeguimiento=$("#TipoSeg").val();
      registraSeguimiento(idProspecto,fecha,etapa,seguimiento,tipoSeguimiento);
    });

    registraSeguimiento = function(idProspecto,fecha,etapa,seguimiento,tipoSeguimiento){

      $.ajax("ae_RegistraSeguimiento", {
        "type": "POST",

        "success": function(result){
          //$("#CapSeg").css("font-size","14px");
          //$("#detCliente").html(result);
          bootbox.alert("Informaci\u00F3n guardada satisfactoriamente");
          return false;
        },
        "error": function(result) {
          bootbox.alert("Ocurri\u00F3 un erro an enviar la informaci\u00F3n")
        },
        "data": {idProspecto:idProspecto,fecha:fecha,etapa:etapa,seguimiento:seguimiento,tipoSeguimiento:tipoSeguimiento},
        "async": true
      });


    };
  });
</script>
<style>
  .group-forma{
    width: 200px;
    text-align: left;
    display: -moz-groupbox;
    /*display: table-row;*/
  }
</style>
<div class="modal-header no-padding">
  <div class="table-header">
    <button type="button" class="close" data-dismiss="modal">&times;</button>
    Ingresar Seguimiento
  </div>
</div>
<div class="bloquedata" style="display: inline-block">
    <div class="group-forma">
      <label>FECHA</label>

      <input type='text' placeholder="FECHA" id="FECHA" name='FECHA' value='${now}' class="form-control" disabled>

      <label>ETAPA</label>
      <input type='text' placeholder="ETAPA" id="ETAPA" name='ETAPA' value="${etapa}" class="form-control" disabled>


      <label>Tipo de Seguimiento</label>
      <input type='text' placeholder="ETAPA" id="TipoSeg" name='TipoSeg' value="${tipoSeg}" class="form-control" disabled>
    </div>
  &nbsp;&nbsp;


    <div class="group-forma">
      <label>Seguimiento</label>
      <textarea placeholder="seguimiento" id="segui" name='segui' rows="8" cols="10" class="form-control">${segui}</textarea>
    </div>
    <input type='hidden' class="idProspecto" name='idProspecto' value='${idSeguimiento}'>
  &nbsp;
  <div class="group-forma">
      <button class="btn btn-primary btn-small ingresarSegumiento" type="submit">
        <i class="icon-edit  bigger-125"></i>
        Observaciones
      </button>
    </div>


</div>
<div class="loader">

</div>
