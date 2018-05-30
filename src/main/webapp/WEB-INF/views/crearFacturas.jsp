<%@ include file="/WEB-INF/views/include.jsp" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<style>
  input{
    width: 100px;
  }
  .modal-body{
    max-height: 650px;
  }
  .modal.fade.in {
    top: 5%;
  }
  table.dataTable tbody > tr.selected, table.dataTable tbody > tr > .selected {
    background-color: #c7cbc9;
    color: #000;
  }
  input[type=checkbox]+.lbl, input[type=radio]+.lbl {
    z-index: 0;
  }
</style>
<script>
  $(document).ready(function(){
    var table = $('#example').DataTable({
      "bPaginate": false
    });

    $('#example tbody').on( 'click', 'tr', function () {
      if ( $(this).hasClass('selected') ) {
        $(this).removeClass('selected');
      }
      else {
        //table.$('tr.selected').removeClass('selected');
        $(this).addClass('selected');
      }
    } );

    $('#borrar').click( function () {
      table.row('.selected').remove().draw( false );
    } );

    $('html').keyup(function(e){
      if(e.keyCode == 46) {
        //table.row('.selected').remove().draw( false );
        table.rows('.selected').remove().draw( false );
      }
    });

    $("#insertar").click(function(){
      var listidcliente="";
      var listrd="";
      var listiniper="";
      var listfinper="";
      var listcamara="";
      var listkilos="";
      var listservicio="";
      var listcuota="";
      var listtotal="";
      var listfechafac="";

      $("input[name=cliente]").each(function(){
        listidcliente+=$(this).val()+",";
      });
      $("input[name=rd]").each(function(){
        listrd+=$(this).val()+",";
      });
      $("input[name=iniper]").each(function(){
        listiniper+=$(this).val()+",";
      });
      $("input[name=finper]").each(function(){
        listfinper+=$(this).val()+",";
      });
      $("input[name=camara]").each(function(){
        listcamara+=$(this).val()+",";
      });
      $("input[name=kilos]").each(function(){
        listkilos+=$(this).val()+",";
      });
      $("input[name=servicio]").each(function(){
        listservicio+=$(this).val()+",";
      });
      $("input[name=congelacion]").each(function(){
        listcuota+=$(this).val()+",";
      });
      $("input[name=total]").each(function(){
        listtotal+=$(this).val()+",";
      });
      $("input[name=fechafac]").each(function(){
        listfechafac+=$(this).val()+",";
      });

      var registros=$('#example tbody tr').length;

      $.ajax("insFactura",{
        "type" : "POST",
        "beforeSend": function() {
          var loader = $('<div align="center">Cargando...<br><img src="resources/img/loader.GIF"/></div>')
          var alto=$(window).height();
          bootbox.dialog(loader).css({"width": "220px","left":"875px","margin-top":(alto/2)-30+"px"});
        },
        "success": function(result) {
          bootbox.hideAll();
          bootbox.alert("Registro Completado")
        },
        "error": function(result) {
          bootbox.alert("Error al recuperar la información", result);
          $("#CapSeg").html("");
        },
        "data": {listidcliente:listidcliente,listrd:listrd,listiniper:listiniper,listfinper:listfinper,
          listcamara:listcamara,listkilos:listkilos,listservicio:listservicio,listcuota:listcuota,
          listtotal:listtotal,listfechafac:listfechafac,registros:registros},
        "async": true
      });
    });

  });
</script>

<button class="btn btn-primary btn-small borrar" id="borrar" type="button">
  <i class="icon-bar-chart  bigger-125"></i>
  Borrar
</button>
<button class="btn btn-primary btn-small insertar" id="insertar" type="button">
  <i class="icon-bar-chart  bigger-125"></i>
  Registrar
</button>
<br>
<table  id="example" class="table table-bordered table-hover dataTable">
  <thead>
  <tr>
    <th>IDCLIENTE</th>
    <th>RD</th>
    <th>FECHA INICIO PERIODO</th>
    <th>FECHA FIN PERIODO</th>
    <th>CAMARA</th>
    <th>BASE DE COBRO</th>
    <th>TIPO SERVICIO</th>
    <th>CUOTA</th>
    <th>TOTAL</th>
    <th>FECHA FACTURA</th>
  </tr>
  </thead>
  <tbody>

  <c:forEach items="${facturas}" var="c">

    <c:if test="${c ne '[]'}">
    <tr>
      <td>${c[0]} <input type="hidden" name="cliente" value="${c[0]}"/> </td>
      <td>${c[1]} <input type="hidden" name="rd" value="${c[1]}"/> </td>
      <td><input type="text" name="iniper" value="${c[2]}" class="form-control" required/></td>
      <td><input type="text" name="finper" value="${c[3]}" class="form-control" required/></td>
      <td>${c[4]}<input type="hidden" name="camara" value="${c[4]}"/></td>
      <td><input type="text" name="kilos" value="${c[5]}" class="form-control" required/></td>
      <td>${c[6]}<input type="hidden" name="servicio" value="${c[6]}"/></td>
      <td><input  type="text" name="congelacion" value="${c[7]}" class="form-control" required/></td>
      <td><input  type="text" name="total" value="${c[8]}" class="form-control" required/></td>
      <td><input  type="text" name="fechafac" value="${c[11]}" class="form-control" required/></td>
    </tr>
    </c:if>

  </c:forEach>
  <c:forEach items="${tuneles}" var="c">
    <c:if test="${c ne '[]'}">
    <tr>
      <td>${c[0]} <input type="hidden" name="cliente" value="${c[0]}"/> </td>
      <td>${c[1]} <input type="hidden" name="rd" value="${c[1]}"/> </td>
      <td><input type="text" name="iniper" value="${c[2]}" class="form-control" required/></td>
      <td><input type="text" name="finper" value="${c[3]}" class="form-control" required/></td>
      <td>${c[4]}<input type="hidden" name="camara" value="${c[4]}"/></td>
      <td><input type="text" name="kilos" value="${c[5]}" class="form-control" required/></td>
      <td>${c[6]}<input type="hidden" name="servicio" value="${c[6]}"/></td>
      <td><input  type="text" name="congelacion" value="${c[7]}" class="form-control" required/></td>
      <td><input  type="text" name="total" value="${c[8]}" class="form-control" required/></td>
      <td><input  type="text" name="fechafac" value="${c[11]}" class="form-control" required/></td>
    </tr>
    </c:if>
  </c:forEach>
  <c:forEach items="${meys}" var="c">
    <c:if test="${c ne '[]'}">
    <tr>
      <td>${c[0]} <input type="hidden" name="cliente" value="${c[0]}"/> </td>
      <td>${c[1]} <input type="hidden" name="rd" value="${c[1]}"/> </td>
      <td><input type="text" name="iniper" value="${c[2]}" class="form-control" required/></td>
      <td><input type="text" name="finper" value="${c[3]}" class="form-control" required/></td>
      <td>${c[4]}<input type="hidden" name="camara" value="${c[4]}"/></td>
      <td><input type="text" name="kilos" value="${c[5]}" class="form-control" required/></td>
      <td>${c[6]}<input type="hidden" name="servicio" value="${c[6]}"/></td>
      <td><input  type="text" name="congelacion" value="${c[7]}" class="form-control" required/></td>
      <td><input  type="text" name="total" value="${c[8]}" class="form-control" required/></td>
      <td><input  type="text" name="fechafac" value="${c[11]}" class="form-control" required/></td>
    </tr>
    </c:if>
  </c:forEach>
  <c:forEach items="${continuidades}" var="c">
    <c:if test="${c ne '[]'}">
    <tr>
      <td>${c[0]} <input type="hidden" name="cliente" value="${c[0]}"/> </td>
      <td>${c[1]} <input type="hidden" name="rd" value="${c[1]}"/> </td>
      <td><input type="text" name="iniper" value="${c[2]}" class="form-control" required/></td>
      <td><input type="text" name="finper" value="${c[3]}" class="form-control" required/></td>
      <td>${c[4]}<input type="hidden" name="camara" value="${c[4]}"/></td>
      <td><input type="text" name="kilos" value="${c[5]}" class="form-control" required/></td>
      <td>${c[6]}<input type="hidden" name="servicio" value="${c[6]}"/></td>
      <c:choose>
        <c:when test="${c[0] eq '186'}">
          <td><input  type="text" name="congelacion" value="0.17" class="form-control" required/></td>
        </c:when>
        <c:otherwise>
          <td><input  type="text" name="congelacion" value="${c[7]}" class="form-control" required/></td>
        </c:otherwise>
      </c:choose>
      <td><input  type="text" name="total" value="${c[8]}" class="form-control" required/></td>
      <td><input  type="text" name="fechafac" value="${c[11]}" class="form-control" required/></td>
    </tr>
    </c:if>
  </c:forEach>
  </tbody>
</table>
