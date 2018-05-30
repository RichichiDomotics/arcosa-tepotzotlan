<%@ include file="/WEB-INF/views/include.jsp" %>
<link rel="stylesheet" type="text/css" href="resources/bootstrap/css/normalize.css" />
<link rel="stylesheet" type="text/css" href="resources/bootstrap/css/component.css" />
<script type="text/javascript" src="resources/js/manejoTablas.js"></script>
<script>
    $(document).ready(function() {
        var oTable1 = $('#tablaResultadoReAutoriza').dataTable({
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



    });

</script>
<h1>Prospectos a Re-Autorizar</h1>

<!--table aria-describedby="sample-table-2_info" id="sample-table-2" class="table table-striped table-bordered table-hover dataTable"-->
<div class="component">
    <table aria-describedby="sample-table-2_info" class="table table-striped table-bordered table-hover dataTable" id="tablaResultadoReAutoriza">
    <thead>
    <tr role="row">
      <th align="center">CLIENTE</th>
      <th align="center" >RFC</th>
      <th align="center" >DIRECCION</th>
      <th align="center" >CONTACTO</th>
      <th align="center" >EMAIL</th>
      <th align="center" >TELEFONO</th>
      <th aling="center" >EJECUTIVO</th>
      <th align="center">AUTORIZAR / RECHAZAR</th>
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
      <td align="center">
          <div align="center">
              <div class="btn-group">
                <button class="btn btn-mini btn-primary convierteReAutorizadobtn" consultaProspectoId="${c.razonSocial}" idProspecto="${c.idProspecto}"><i class="icon-check"></i>Autorizar</button>
                <button class="btn btn-mini btn-danger rechazarProspectoReAutorizado" consultaProspectoId2="${c.razonSocial}" idProspecto2="${c.idProspecto}"><i class="icon-external-link"></i>Rechazar</button>
              </div>
          </div>
          <input type="hidden" id="claveEjecutivo" value="${claveUsuario}">
      </td>
    </tr>
</c:forEach>
    </tbody>
    <div id="loader">

    </div>

  </table>
</div>
<div id="supdiv"><div id="CapSeg"></div></div>
