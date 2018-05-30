<%@ include file="/WEB-INF/views/include.jsp" %>
<link rel="stylesheet" type="text/css" href="resources/bootstrap/css/normalize.css" />
<link rel="stylesheet" type="text/css" href="resources/bootstrap/css/component.css" />
<script>
    $(document).ready(function() {
        $(".component").css("font-size","12px");
        var oTable1 = $('#tablaAceptadoCambioCli').dataTable({
            "aoColumns": [
                { "bSortable": true },
                { "bSortable": true },
                { "bSortable": true },
                { "bSortable": true },
                { "bSortable": true },
                { "bSortable": true },
                { "bSortable": true },
                { "bSortable": true }
            ],"pagingType": "full_numbers" } );


        $(".convierteClientebtn").click(function(){
            idProspecto = $(this).attr("idProspecto");
            consultaProspectoId = $(this).attr("consultaProspectoId");
            agregarCliente(idProspecto,consultaProspectoId);
        });



        agregarCliente = function(idProspecto,razonSocial){
            $.ajax("consultaProspectoId", {
                "type": "POST",
                "beforeSend": function() {
                    $("#buscaProspectodiv").css("font-size","27px");
                    $("#buscaProspectodiv").html('Cargando ...');
                },
                "success": function(result){
                    bootbox.dialog($("#CapSeg").html(result), [{
                                "label" : "Cerrar",
                                "class" : "btn-small btn-primary",
                                "callback": function() {
                                    $("#CapSeg").html("");
                                }
                            }]
                    ).css({"width":"1100px","heith":"auto","left":"450px","overflow-y": "hidden"});
                },
                "error": function(result) {
                    alert("Error al recuperar la información", result);
                    //showAlert("Error al recuperar la información", result);
                },
                "data": {id:idProspecto,razonSocial:razonSocial},
                "async": true
            });
        }


    });

</script>
<h1>Cambio a Cliente</h1>

<!--table aria-describedby="sample-table-2_info" id="sample-table-2" class="table table-striped table-bordered table-hover dataTable"-->
<div class="component">
<table aria-describedby="sample-table-2_info" id="tablaAceptadoCambioCli" class="table table-striped table-bordered table-hover dataTable">
    <thead>
    <tr role="row">
      <th align="center">CLIENTE</th>
      <th align="center" >RFC</th>
      <th align="center" >DIRECCION</th>
      <th align="center" >CONTACTO</th>
      <th align="center" >EMAIL</th>
      <th align="center" >TELEFONO</th>
      <th aling="center" >EJECUTIVO</th>
      <th align="center">CAMBIO A CLIENTE</th>
    </tr>
    </thead>
    <tbody aria-relevant="all" aria-live="polite" role="alert">
	<c:forEach items="${prospectos}" var="c">
    <tr>

      <td align="center">${c.razonSocial}</td>
      <td align="right" >${c.rfc}</td>
      <td align="right" >${c.callenum} ${c.colonia} ${c.ciudad} ${c.estado} ${c.cp}</td>
      <td align="right" >${c.contacto1}</td>
      <td align="right" >${c.email}</td>
      <td align="right" >${c.telefono}</td>
      <td align="right" >
          <c:forEach items="${ejecutivos}" var="b">
              <c:if test="${c.idEjecutivo==b.id}">
                  ${b.nombreEjecutivo}
              </c:if>
          </c:forEach>
      </td>
      <td align="center" >
          <div align="center">
          <button class="btn btn-small btn-primary convierteClientebtn" consultaProspectoId="${c.razonSocial}" idProspecto="${c.idProspecto}"><i class="icon-share-alt"></i>Cambiar</button>
          </div>
      </td>

    </tr>
</c:forEach>
    </tbody>
    <div id="loader">

    </div>
       <!--tr>
    <td colspan="2" align="center"><input type="submit" value="Consultar"></td>
    <td colspan="2" align="center"><input type="submit" value="Modificar"></td>
    <td colspan="2" align="center"><input type="submit" value="Eliminar"></td>
    </tr-->
  </table>
</div>
<div id="supdiv"><div id="CapSeg"></div></div>

