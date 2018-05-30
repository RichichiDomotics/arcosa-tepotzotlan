<%@ include file="/WEB-INF/views/include.jsp" %>
<link rel="stylesheet" type="text/css" href="resources/bootstrap/css/normalize.css" />
<link rel="stylesheet" type="text/css" href="resources/bootstrap/css/component.css" />
<style>
    .titulo-nada{
        left: 200px;
    }
</style>
<script>
    $(document).ready(function() {

        var oTable1 = $('#tablaSegPros').dataTable({
            "aoColumns": [
                { "bSortable": true },
                { "bSortable": true },
                { "bSortable": true },
                { "bSortable": true },
                { "bSortable": true }
            ],"pagingType": "full_numbers" } );

        $('[data-rel=tooltip]').tooltip();
        $('[data-rel=popover]').popover({html:true});


        $(".observacion").click(function(){
            idSeguimiento = $(this).attr("idSeguimiento");
            idProspecto=$(this).attr("idProspecto");
            idEjecutivo=$(this).attr("idEjecutivo")
            bootbox.hideAll();
            veCapturaObservacion(idSeguimiento,idProspecto,idEjecutivo);
        });
        veCapturaObservacion = function(idSeguimiento,idProspecto,idEjecutivo){
            if(!$("#CapSeg").length){
                $("#supdiv").append('<div id="CapSeg"></div>')
            }
            $.ajax("CapturaObservacionJefe", {
                "type": "POST",
                "beforeSend": function() {
                    $("#CapSeg").css("font-size","27px");
                    $("#CapSeg").html('Cargando ...');
                },
                "success": function(result){
                    $("#CapSeg").css("font-size","12px");
                    //$("#detCliente").html(result);
                    bootbox.dialog($("#CapSeg").html(result), [{
                                "label" : "Cerrar",
                                "class" : "btn-small btn-primary",
                                "callback": function() {
                                    $("#CapSeg").html("");
                                }
                            }]
                    ).css({"width":"800px","heith":"660","left":"550px","overflow-y": "hidden"});

                    return false;
                },
                "error": function(result) {
                    alert("Error al recuperar la información", result);
                    $("#CapSeg").html("");
                },
                "data": {idSeguimiento:idSeguimiento,idProspecto:idProspecto,idEjecutivo:idEjecutivo},
                "async": true
            });


        };
    });
</script>
<div class="modal-header no-padding">
    <div class="table-header">
        <button type="button" class="close" data-dismiss="modal">&times;</button>
        <strong>Listado de Seguimientos</strong>
    </div>
</div>

<!--table aria-describedby="sample-table-2_info" id="sample-table-2" class="table table-striped table-bordered table-hover dataTable"-->
<c:if test="${seguimiento ne '[]'}">
<div class="component">
    <table aria-describedby="sample-table-2_info" class="table table-striped table-bordered table-hover dataTable" id="tablaSegPros">
    <thead>
    <tr role="row">
      <th align="center">PROSPECTO</th>
      <th align="center">TIPO DE SEGUIMIENTO</th>
      <th align="center" >FECHA DE SEGUIMIENTO</th>
      <th align="center" >SEGUIMIENTO</th>
      <th align="center" >OBSERVACIONES</th>

    </tr>
    </thead>
	<c:forEach items="${seguimiento}" var="c">

                <tbody aria-relevant="all" aria-live="polite" role="alert">
                <tr class="odd">
                    <td align="center">
                        ${prospecto.razonSocial}
                    </td>
                  <td align="right" >${c.tipoSeguimiento}</td>
                  <td align="right" >${c.fecha}</td>
                  <td align="right" >${c.seguimiento}</td>
                  <td align="center" >
                     <button class="btn btn-success btn-app btn-mini observacion" data-rel="tooltip" title="Observaciones" idSeguimiento="${c.id}" idProspecto="${prospecto.idProspecto}" idEjecutivo="${prospecto.idEjecutivo}">
                         <i class="icon-exclamation-sign bigger-60"></i>
                         <span class="badge badge-pink Observaciones" data-rel="tooltip" title="Observaciones">${c.numeroObservaciones}</span>
                     </button>

                  </td>
                </tr>
                </tbody>

</c:forEach>
  </table>
</div>
    <div id="supdiv"><div id="CapSeg"></div></div>
</c:if>
<c:if test="${seguimiento eq '[]'}">
    <h4 class="titulo-nada">
        NO HAY SEGUIMIENTOS PARA ESTE PROSPECTO
    </h4>
</c:if>