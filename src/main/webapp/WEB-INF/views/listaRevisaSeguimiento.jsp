<%@ include file="/WEB-INF/views/include.jsp" %>
<link rel="stylesheet" type="text/css" href="resources/bootstrap/css/normalize.css" />
<link rel="stylesheet" type="text/css" href="resources/bootstrap/css/component.css" />
<script type="text/javascript" src="resources/js/manejoTablas.js"></script>
<script>
    $(document).ready(function() {
        var oTable1 = $('#tablaResultado').dataTable({
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
<h1>Listado Prospectos</h1>

<div class="component">
<table aria-describedby="sample-table-2_info" class="table table-striped table-bordered table-hover dataTable" id="tablaResultado">
    <thead>
    <tr role="row">
      <th align="center">PROSPECTO</th>
      <th align="center" >RFC</th>
      <th align="center" >DIRECCION</th>
      <th align="center" >CONTACTO</th>
      <th align="center" >EMAIL</th>
      <th align="center" >TELEFONO</th>
      <th align="center">ETAPA</th>
      <th align="center">SEGUIMIENTO</th>
    </tr>
    </thead>
    <tbody aria-relevant="all" aria-live="polite" role="alert">
	<c:forEach items="${prospecto}" var="c">
                <tr>
                  <td>${c.razonSocial}</td>
                  <td>${c.rfc}</td>
                  <td>${c.callenum} ${c.colonia} ${c.ciudad} ${c.estado} ${c.cp}</td>
                  <td>${c.contacto1}</td>
                  <td>${c.email}</td>
                  <td>${c.telefono}</td>
                  <td>
                      <c:if test="${c.idEtapa==1}">
                          <span class="label label-purple arrowed-in-right">Presentaci&oacute;n</span>
                      </c:if>
                      <c:if test="${c.idEtapa==2}">
                          <span class="label label-grey arrowed-in-right arrowed-in">Presentaci&oacute;n de cuotas</span>
                      </c:if>
                      <c:if test="${c.idEtapa==4}">
                          <span class="label label-pink arrowed-right">Env&iacute;o de cotizaci&oacute;n</span>
                      </c:if>
                      <c:if test="${c.idEtapa==3}">
                          <span class="label label-yellow arrowed-in">Negociaci&oacute;n</span>
                      </c:if>
                      <c:if test="${c.idEtapa==5}">
                          <span class="label label-success arrowed-right">Aceptaci&oacute;n</span>
                      </c:if>
                      <c:if test="${c.idEtapa==6}">
                          <span class="label label-inverse arrowed-right">Negaci&oacute;n</span>
                      </c:if>
                  </td>
                  <td>
                      <button class="btn btn-success btn-app btn-mini convierteClienteSegbtn" data-rel="tooltip" title="Ver Observaciones" consultaProspectoId="${c.razonSocial}" idProspecto="${c.idProspecto}">
                          <i class="icon-eye-open"></i>
                          <span class="badge badge-pink numeroSeguimientos${c.idProspecto}" data-rel="tooltip" title="Revisar">${c.numConteo}</span>
                      </button>
                  </td>
                </tr>

</c:forEach>
    </tbody>
  </table>
</div>
<div id="supdiv"><div id="CapSeg"></div></div>