<%@ include file="/WEB-INF/views/include.jsp" %>
<link rel="stylesheet" type="text/css" href="resources/bootstrap/css/normalize.css" />
<link rel="stylesheet" type="text/css" href="resources/bootstrap/css/component.css" />
<script src="resources/js/modificaciones.js"></script>
<script type="text/javascript">
  $(document).ready(function() {
    function recargar() {
      $(".component").reload(true);
      //location.reload();
    }

    setInterval(recargar, 3000);
  });
</script>
<style>
  .component{
    max-width: 1400px;
  }
  table{
    width: 1400px;
  }
</style>
<h1>AUTORIZAR SOLICITUD DE CAMBIOS AL SISTEMA</h1>
<div class="component">
  <table>
    <thead>
      <tr>
        <th>Folio</th>
        <th>Nombre del Solicitante</th>
        <th>Area</th>
        <th>Fecha de Captura</th>
        <th>Cambio Solicitado</th>
        <th>ID Cliente</th>
        <th>Causa</th>
        <th>RD Afectado</th>
        <th>Salida Afectada</th>
        <th>Autorizar</th>
      </tr>
    </thead>
    <tbody aria-relevant="all" aria-live="polite" role="alert">
      <c:forEach items="${modificaciones}" var="m">
        <tr>
          <td align="center">${m.folio}</td>
          <td align="center">${m.nombre_solicitante}</td>
          <td align="center">${m.area_solicitante}</td>
          <td align="center">${m.fecha_captura} ${m.hora_captura}</td>
          <td align="center">${m.cambio_solicitado}</td>
          <td align="center">${m.idCliente}</td>
          <td align="center">${m.causa}</td>
          <td align="center">${m.rd_afectado}</td>
          <td align="center">${m.salida_afectada}</td>
          <td>
            <button class="btn btn-primary btn-mini ingresarModificacion" idmodificacion="${m.idModificacion}" consecutivo="${m.rd_afectado}" salida="${m.salida_afectada}" type="submit">
              <i class="icon-edit  bigger-125"></i>
              Autorizar
            </button>
            <!--button class="btn btn-danger btn-mini ingresarModificacion"  type="submit">
              <i class="icon-edit  bigger-125"></i>
              Rechazar
            </button-->
          </td>
        </tr>
      </c:forEach>
    </tbody>
  </table>
</div>
<div id="supdiv"><div id="CapSeg"></div></div>