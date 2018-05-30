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
      etapa=$("#ETAPA option:selected").text();
      idetapa=$("#ETAPA").val();
      seguimiento=$("#segui").val();
      tipoSeguimiento=$("#TipoSeg").val();
      if(seguimiento==""){
         alert("Ingresa un seguimiento");
        $("#segui").focus();
        return false;
      }
      if(idetapa==""){
        alert("Selecciona una etapa");
        $("#ETAPA").focus();
        return false;
      }
      if(tipoSeguimiento==""){
        alert("Selecciona un tipo seguimiento");
        $("#TipoSeg").focus();
        return false;
      }
      registraSeguimiento(idProspecto,fecha,etapa,seguimiento,tipoSeguimiento,idetapa);
      bootbox.hideAll();
      //$(location).attr("href", "/ae_consultaProspectoAutorizado");
    });

    registraSeguimiento = function(idProspecto,fecha,etapa,seguimiento,tipoSeguimiento,idetapa){

      $.ajax("ae_RegistraSeguimiento", {
        "type": "POST",

        "success": function(result){
          //$("#CapSeg").css("font-size","14px");
          //$("#detCliente").html(result);
          bootbox.alert("Informaci\u00F3n guardada satisfactoriamente",function(){
            window.location.href=window.location;

          });
          return false;
        },
        "error": function(result) {
          bootbox.alert("Ocurri\u00F3 un erro an enviar la informaci\u00F3n")
        },
        "data": {idProspecto:idProspecto,fecha:fecha,etapa:etapa,seguimiento:seguimiento,tipoSeguimiento:tipoSeguimiento,idetapa:idetapa},
        "async": true
      });


    };

    $('#ETAPA > option[value="${idEtapa}"]').attr('selected', 'selected');
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
    <strong>Ingresar Seguimiento</strong>
  </div>
</div>
<div class="bloquedata" style="display: inline-block">
    <div class="group-forma">
      <label>FECHA</label>

      <input type='text' placeholder="FECHA" id="FECHA" name='FECHA' value='${now}' class="form-control" disabled>

      <label>ETAPA</label>
      <!--input type='text' placeholder="ETAPA" id="ETAPA" name='ETAPA' value="${etapa}" class="form-control" disabled-->
      <select id="ETAPA" name="ETAPA" class="form-control" required>
        <option value="">seleccione una opci&oacute;n</option>
        <option value="1">PRESENTACION</option>
        <option value="2">PRESENTACION DE CUOTAS</option>
        <option value="3">ENVIO DE COTIZACION</option>
        <option value="4">NEGOCIACION</option>
        <option value="5">ACEPTACION</option>
        <option value="6">NEGACION</option>
      </select>

      <label>Tipo de Seguimiento</label>
      <select id="TipoSeg" name="TipoSeg" class="form-control" required>
        <option value="">seleccione una opci&oacute;n</option>
        <option value="Telefonico">Telef&oacute;nico</option>
        <option value="Visita Presencial">Visita Presencial</option>
        <option value="Via correo electronico">Via correo electr&oacute;nico</option>
      </select>
    </div>
  &nbsp;&nbsp;


    <div class="group-forma">
      <label>Seguimiento</label>
      <textarea placeholder="seguimiento" id="segui" name='segui' rows="8" cols="10" class="form-control" required></textarea>
    </div>
    <input type='hidden' class="idProspecto" name='idProspecto' value='${idProspecto}'>
  &nbsp;
  <div class="group-forma">
      <button class="btn btn-primary btn-small ingresarSegumiento" type="submit">
        <i class="icon-edit  bigger-125"></i>
        Registrar
      </button>
    </div>


</div>
<div class="loader">

</div>
