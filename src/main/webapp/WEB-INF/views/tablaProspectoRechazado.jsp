<%@ include file="/WEB-INF/views/include.jsp" %>
<link rel="stylesheet" type="text/css" href="resources/bootstrap/css/normalize.css" />
<link rel="stylesheet" type="text/css" href="resources/bootstrap/css/component.css" />
<script type="text/javascript" src="resources/js/manejoTablas.js"></script>
<script>
    $(document).ready(function() {
        var oTable1 = $('#tablaResultadoProspectoJefe').dataTable({
            "aoColumns": [
                { "bSortable": true },
                { "bSortable": true },
                { "bSortable": true },
                { "bSortable": true },
                { "bSortable": true },
                { "bSortable": true },
                { "bSortable": true }
            ],"pagingType": "full_numbers" } );

        $('[data-rel=tooltip]').tooltip();
        $('[data-rel=popover]').popover({html:true});

    });

</script>

<h1>Listado Prospectos Rechazados</h1>

<div class="component">
<table aria-describedby="sample-table-2_info" class="table table-striped table-bordered table-hover dataTable" id="tablaResultadoProspectoJefe">
    <thead>
    <tr role="row">
      <th align="center">PROSPECTO</th>
      <th align="center" >RFC</th>
      <th align="center" >DIRECCION</th>
      <th align="center" >CONTACTO</th>
      <th align="center" >EMAIL</th>
      <th align="center" >TELEFONO</th>
      <th align="center">SOLICITUD DE <br>RECTIVACION</th>
    </tr>
    </thead>
    <tbody aria-relevant="all" aria-live="polite" role="alert">
	<c:forEach items="${prospecto}" var="c">
    <tr>
      <td align="center">${c.razonSocial}</td>
      <td align="right" >${c.rfc}</td>
      <td align="right" >${c.callenum} ${c.colonia} ${c.ciudad} ${c.estado} ${c.cp}</td>
      <td align="right" >${c.contacto1}</td>
      <td align="right" >${c.email}</td>
      <td align="right" >${c.telefono}</td>
      <td align="center" >
          <button class="btn btn-success btn-app btn-mini convierteReactivarbtn" data-rel="tooltip" title="Reactivar" consultaProspectoId="${c.razonSocial}" idProspecto="${c.idProspecto}">
            <i class="icon-unlock"></i>
            <span class="badge badge-pink numeroSolicitudes${c.idProspecto}" data-rel="tooltip" title="Reactivar">${c.numConteo}</span>
          </button>
      </td>
    </tr>
    </tbody>
</c:forEach>

  </table>
</div>
<div id="supdiv"><div id="CapSeg"></div></div>