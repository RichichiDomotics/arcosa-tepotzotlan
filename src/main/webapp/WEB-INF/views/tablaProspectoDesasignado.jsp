<%@ include file="/WEB-INF/views/include.jsp" %>
<link rel="stylesheet" type="text/css" href="resources/bootstrap/css/normalize.css" />
<link rel="stylesheet" type="text/css" href="resources/bootstrap/css/component.css" />
<script type="text/javascript" src="resources/js/manejoTablas.js"></script>
<script>
    $(document).ready(function() {
        var oTable1 = $('#tablaResultadoAutoriza').dataTable({
            "aoColumns": [
                { "bSortable": true },
                { "bSortable": true },
                { "bSortable": true },
                { "bSortable": true },
                { "bSortable": true },
                { "bSortable": true },
                { "bSortable": true, "sClass": "a-center" }
            ],"pagingType": "full_numbers" } );

    });

</script>

<h1>Listado Prospectos Desasignados</h1>
<div class="component">
 <table aria-describedby="sample-table-2_info" class="table table-striped table-bordered table-hover dataTable" id="tablaResultadoAutoriza">
    <thead>
    <tr role="row">
      <th align="center">PROSPECTO</th>
      <th align="center" >RFC</th>
      <th align="center" >DIRECCION</th>
      <th align="center" >CONTACTO</th>
      <th align="center" >EMAIL</th>
      <th align="center" >TELEFONO</th>
      <th align="center">RE-ASIGNAR</th>
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
      <td align="center" ><button class="btn btn-minier btn-primary convierteReAsingnarbtn" consultaProspectoId="${c.razonSocial}" idProspecto="${c.idProspecto}"><i class="icon-edit"></i>Re-Asigar</button></td>
    </tr>
</c:forEach>
    </tbody>

  </table>
</div>
<div id="supdiv"><div id="CapSeg"></div></div>