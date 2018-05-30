<%@ include file="/WEB-INF/views/include.jsp" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<script src="resources/js/notasCredito.js"></script>
<link rel="stylesheet" type="text/css" href="resources/bootstrap/css/entrada.css" />
<style>
  .bloqueforma{
    display:block;
    width: 300px;
    float: left;;
  }
</style>
<script>
  $(document).ready(function(){
    /*$(".ingresarModificacion").click(function(){
      folio=$("#folio").val();
      nombre=$("#nombre").val();
      area=$("#area").val();
      fecha=$("#fecha").val();
      hora=$("#hora").val();
      idCliente=$("#idCliente").val();
      cambio=$("#cambio").val();
      causa=$("#causa").val();
      consecutivo=$("#consecutivo").val();
      salida=$("#salida").val();
      insertaModificacion(folio,nombre,area,fecha,hora,idCliente,cambio,causa,consecutivo,salida);
    });
    insertaModificacion = function(folio,nombre,area,fecha,hora,idCliente,cambio,causa,consecutivo,salida){
      $.ajax("insertarModificacion",{
        "type":"POST",
        "success":function(result){
          bootbox.alert("Informaci\u00F3n guardada satisfactoriamente",function(){
            window.location.href=window.location;

          });
        },
        "error":function(result){
          bootbox.alert("Ocurri\u00F3 un erro an enviar la informaci\u00F3n");
        },
        "data": {folio:folio,nombre:nombre,area:area,fecha:fecha,hora:hora,idCliente:idCliente,cambio:cambio,causa:causa,consecutivo:consecutivo,salida:salida},
        "async":true
      });
    };*/
  });
</script>
<section class="container">
  <h1>NOTAS DE CR&Eacute;DITO</h1>
  <div class="bloqueforma">

    <div class="form-group">
      <label>FOLIO</label>
      <input type="text" class="form-control" placeholder="FOLIO" id="folio" name="folio" value="${folio}" disabled>
    </div>

    <div class="form-group">
      <label>NO. NOTA</label>
      <input type="text" class="form-control" placeholder="NUMERO DE NOTA" id="nota" name="folio" value="" >
    </div>


    <div class="form-group">
      <label>FECHA</label>
      <input type="text" class="form-control" placeholder="fecha" id="fecha" name="fecha" value="${fecha}" aria-required="true" aria-disabled="true">
    </div>

    <div class="form-group">
      <label>ID CLIENTE</label>
      <select id="idCliente" name="idCliente" value='' class="form-control" aria-required="true">
        <option value="" > <fmt:message key="entradas.select"/> </option>
        <c:forEach items="${clientes}" var="client">
          <option value="${client.idCliente}"> <c:out value="${client.idCliente} ${client.nombreCliente}"/></option>
        </c:forEach>
      </select>
    </div>


    <div class="form-group">
      <label>NO. DE FACTURA AFECTADA</label>
      <input type="text" class="form-control span3" placeholder="NO. FACTURA" id="nofactura" name="nofactura" value="">
    </div>


    <div class="form-group">
      <label>NO. DE FACTURA A APLICAR</label>
      <input type="text" class="form-control span3" placeholder="NO. FACTURA" id="facturaa" name="facturaa">
    </div>


    <div class="form-group">
      <label>FECHA DE EMISION DE LA FACTURA</label>
      <input type="text" class="form-control" placeholder="DD/MM/AAAA" id="fechafactura" name="fechafactura">
    </div>

    <div class="form-group">
      <label>FECHA REFACTURA</label>
      <input type="text" class="form-control" placeholder="FECHA REFACTURA" id="fecharefactura" name="fecharefactura" value="${fecha}" aria-required="true">
    </div>

    <div class="form-group">
      <label>MOTIVO</label>
      <select class="form-control" id="motivo" name="motivo" aria-required="true">
        <option value="0">SELECCIONE UN MOTIVO</option>
        <option value="FINDEMES">POR FIN DE MES</option>
        <option value="ERROR_CARGOS">ERROR EN CARGOS</option>
        <option value="ERROR_DATOS">ERROR EN DATOS</option>
        <option value="COMER_NEGOCIA">COMERCIAL-NEGOCIACION</option>
        <option value="COMER_SOLICITUD">COMERCIAL-SOLICITUD</option>
        <option value="CLIENTE_NO ACEPTA">CLIENTE NO ACEPTA</option>
        <option value="CXC_SOLICITA">CXC-SOLICITUD</option>
        <option value="CXC_EXTRAVIA">CXC-EXTRAVIO</option>
        <option value="OTROS">OTROS</option>
      </select>
    </div>
  </div>
  <div class="bloqueforma">


    <div class="form-group">
      <label>CONCEPTO</label>
      <select class="form-control" id="concepto" name="concepto" aria-required="true">
        <option value="">SELECCCIONE UN MOTIVO</option>
        <option name="idservicio" value="ALM"> ALMACENAJE</option>
        <option name="idservicio" value="NT"> NIVELACION TEMPERATURA</option>
        <option name="idservicio" value="EMPLAYADO"> EMPLAYADO</option>
        <option name="idservicio" value="T- EXTRA"> TIEMPO EXTRA</option>
        <option name="idservicio" value="ALMC"> ALMACENAJE CONTINUIDAD</option>
        <option name="idservicio" value="CCR"> COMPLENTO CR</option>
        <option name="idservicio" value="MG"> MINIMO GLOBAL</option>
        <option name="idservicio" value="MAQUILA"> MAQUILA</option>
        <option name="idservicio" value="COMP1%IVA"> COMPLEMENTO IVA</option>
        <option name="idservicio" value="RENT-OF"> RENTA OFICINA</option>
        <option name="idservicio" value="JVCONG014"> ALMACENAJE JUGOS DEL VALLE</option>
        <option name="idservicio" value="TRASPALEO"> TRASPALEO</option>
        <option name="idservicio" value="ROMANEO"> ROMANEO</option>
        <option name="idservicio" value="ESTACIONA"> ESTACIONAMIENTO</option>
        <option name="idservicio" value="EXCED-ALM"> EXCEDENTE EXCLUSIVIDAD</option>
        <option name="idservicio" value="EXCLUSIV"> EXCLUSIVIDAD</option>
        <option name="idservicio" value="SEGURO"> SEGURO</option>
        <option name="idservicio" value="TIF"> TIF</option>
        <option name="idservicio" value="TIF-DER"> TIF PAGO DERECHOS</option>
        <option name="idservicio" value="TIF-EXP"> TIF EXPEDICION CERTIFICADO</option>
        <option name="idservicio" value="ME"> MANIOBRA ESPECIAL</option>
        <option name="idservicio" value="TUNEL"> TUNEL</option>
        <option name="idservicio" value="MEYS"> MANIOBRAS ENTRADA Y SALIDA</option>
        <option name="idservicio" value="ETIQUE"> ETIQUETADO</option>
        <option name="idservicio" value="MOVEXTRA"> MOVIMIENTO EXTRA</option>
        <option name="idservicio" value="ACT"> ACONDICIONAMIENTO DE TEMPERATURA</option>
        <option name="idservicio" value="OTROS"> OTROS CARGOS</option>
        <option name="idservicio" value="CLASIF"> CLASIFICADO</option>
        <option name="idservicio" value="SANITZ"> SANITIZACION</option>
        <option name="idservicio" value="C-EMPAQ"> CINTA DE EMPAQUE</option>
        <option name="idservicio" value="MGTUNEL"> MINIMO GLOBAL TUNEL</option>
        <option name="idservicio" value="LOGISTICA"> SERVICIOS DE LOGISTICA</option>
        <option name="idservicio" value="CRC-ANDEN"> CRUCE DE ANDEN</option>
        <option name="idservicio" value="M_ENTRADA"> MANIOBRA ENTRADA</option>
        <option name="idservicio" value="M_SALIDA"> MANIOBRA SALIDA</option>
        <option name="idservicio" value="T_MOVERE"> TARIMA MOVERE</option>
        <option name="idservicio" value="LINEA_TEL"> LINEA TELEFONICA</option>
        <option name="idservicio" value="VENTA"> VENTA DE PRODUCTO</option>
        <option name="idservicio" value="TARIMAS"> TARIMAS</option>
        <option name="idservicio" value="COPIAS"> COPIAS</option>
        <option name="idservicio" value="SERV_ADMON"> SERVICIOS ADMINISTRATIVOS</option>
        <option name="idservicio" value="PICKING"> PREPARACION DE PEDIDOS</option>
        <option name="idservicio" value="ESCANEO"> ESCANEO</option>
        <option name="idservicio" value="REETIQUE"> REETIQUETADO</option>
        <option name="idservicio" value="PORTEO"> PORTEO</option>
        <option name="idservicio" value="GTRANSITO"> GUIA DE TRANSITO</option>
        <option name="idservicio" value="EXCED-RFG"> EXCENDENTE REFRIGERACION</option>
        <option name="idservicio" value="REENTARIMADO"> REENTARIMADO</option>
        <option name="idservicio" value="REEMPAQUE"> REEMPAQUE</option>
        <option name="idservicio" value="ME TUN-CAM"> MANIOBRA TUNEL A CAMARA</option>
        <option name="idservicio" value="ME CAM-TUN"> MANIOBRA CAMARA A TUNEL</option>
        <option name="idservicio" value="MTIF-TIF"> MANIOBRA DE TIF A TIF</option>
        <option name="idservicio" value="1"> INFRAESTRUCTURA</option>
        <option name="idservicio" value="RPLAYO1.50"> ROLLO DE PLAYO HASTA 1.50</option>
        <option name="idservicio" value="RPLAYO1.80"> ROLLO DE PLAYO HASTA 1.80</option>
        <option name="idservicio" value="ARRANQUE-TUN"> ARRANQUE TUNEL</option>
        <option name="idservicio" value="ME CAM-CAM"> MANIOBRA CAMARA A CAMARA</option>
        <option name="idservicio" value="EXCED-ALT"> EXCEDENTE DE ALTURA</option>
        <option name="idservicio" value="TIF-SALIDA"> TIF SALIDA MOVILIZACION</option>
        <option name="idservicio" value="RSPBANDA"> RASPADO DE BANDA</option>
        <option name="idservicio" value="CHAROLA"> CHAROLA</option>
        <option name="idservicio" value="SELECCION"> SELECCION DE PRODUCTO</option>
        <option name="idservicio" value="ETIQ_FAJILLAS"> ETIQUETADO DE FAJILLAS</option>
        <option name="idservicio" value="ESCANEO_SAL"> ESCANEO SALIDA</option>
        <option name="idservicio" value="RPLAYO2.20"> ROLLO DE PLAYO HASTA 2.20</option>
        <option name="idservicio" value="A_MOVILIZACION"> AVISO DE MOVILIZACION</option>
        <option name="idservicio" value="BOLSAS"> BOLSAS DE PLASTICO</option>
        <option name="idservicio" value="CJS-CARTON"> CAJAS DE CARTON</option>
        <option name="idservicio" value="INVENTARIO"> INVENTARIO</option>
        <option name="idservicio" value="CANCELACION"> CANCELACION DE PEDIDOS</option>
        <option name="idservicio" value="2"> MONTACARGAS</option>
        <option name="idservicio" value="3"> PATIN ELECTRICO</option>
        <option name="idservicio" value="4"> INSTALACION</option>
        <option name="idservicio" value="5"> PUERTAS</option>
        <option name="idservicio" value="MANIOBRAS"> MANIOBRAS BACHOCO</option>
        <option name="idservicio" value="SCONG001"> BACHOCO ALMACENAJE</option>
        <option name="idservicio" value="SCONG010"> BACHOCO TUNEL</option>
        <option name="idservicio" value="SCONG057"> BACHOCO MINIMO GLOBAL TUNEL</option>
        <option name="idservicio" value="SCONG011"> BACHOCO ACONDICIONAMIENTO DE TEMPERATURA</option>
        <option name="idservicio" value="SCONG06"> BACHOCO MANIOBRAS DE ENTRADA Y SALIDA</option>
        <option name="idservicio" value="SCONG018"> BACHOCO TIEMPO EXTRA</option>
        <option name="idservicio" value="SCONG08"> BACHOCO PLAYO</option>
        <option name="idservicio" value="SCONG019"> BACHOCO ESCANEO</option>
        <option name="idservicio" value="SCONG007"> BACHOCO PREPARACION DE PEDIDOS</option>
        <option name="idservicio" value="SCONG009"> BACHOCO TRASPALEOS</option>
        <option name="idservicio" value="SCONG025"> BACHOCO MANIOBRAS A GRANEL</option>
        <option name="idservicio" value="SCONG016"> BACHOCO TIF SALIDA</option>
        <option name="idservicio" value="SCONG034"> BACHOCO CRUCE DE ANDEN</option>
        <option name="idservicio" value="SCONG031"> BACHOCO COPIAS Y ETIQUETAS</option>
        <option name="idservicio" value="SCONG054"> BACHOCO MANIOBRAS Y SELECCION DE PRODUCTO</option>
        <option name="idservicio" value="ETIQ_ANTOJOS"> ETIQUETA ANTOJOS</option>
        <option name="idservicio" value="SCONG072"> IMPRESION ETIQ MAQUILA ANTOJOS</option>
        <option name="idservicio" value="SCONG071"> MAQUILA ANTOJOS</option>
        <option name="idservicio" value="MRECODIF"> RECODIFICADO</option>
        <option name="idservicio" value="MALASC"> MALASCULIACAN</option>
        <option name="idservicio" value="MKFC"> MKFC</option>
        <option name="idservicio" value="JVCONG016"> MEYS JUGOS DEL VALLE</option>
        <option name="idservicio" value="AMP-HORARIO"> AMPLIACION HORARIO</option>
        <option name="idservicio" value="JVCONG008"> ME JUGOS DEL VALLE</option>
        <option name="idservicio" value="JVCONG006"> MANIOBRAS DE ENTRADA Y SALIDA JUGOS DEL VALLE</option>
        <option name="idservicio" value="JVCONG007"> TIEMPO EXTRA JUGOS DEL VALLE</option>
        <option name="idservicio" value="SERVICIO"> SERVICIO</option>
        <option name="idservicio" value="RPLAYO1.95"> ROLLO DE PLAYO HASTA 1.95</option>
      </select>
    </div>

    <div class="form-group">
      <label>SUBTOTAL</label>
      <input type="text" class="form-control" placeholder="SUBTOTAL" id="subtotal" name="subtotal" value="" aria-required="true">
    </div>

    <div class="form-group">
      <label>CAMARA</label>
      <input type="text" class="form-control" placeholder="CAMARA" id="camara" name="camara" value="" aria-required="true">
    </div>
    <div class="form-group">
      <label>AUTORIZA</label>
      <input type="text" class="form-control" placeholder="AUTORIZA" id="autoriza" name="autoriza" value="" aria-required="true">
    </div>
    <div class="form-group">
      <label>REALIZA</label>
      <input type="text" class="form-control" placeholder="REALIZA" id="realiza" name="realiza" value="" aria-required="true">
    </div>

    <div class="form-group">
      <label>OBSERVACIONES</label>
           <textarea placeholder="OBSERVACIONES" id="observaciones" name='observaciones' data-maxlength="80"
                     style="width: 350px; height: 156px;" class="form-control limited"></textarea>
    </div>

    <div class="group-forma">
      <button class="btn btn-primary btn-small ingresarNota" type="submit">
        <i class="icon-edit  bigger-125"></i>
        Procesar Nota
      </button>
    </div>
    <br>
    <div class="group-forma">
      <button class="btn btn-primary btn-small finNota" type="submit">
        <i class="icon-edit  bigger-125"></i>
        Finalizar Nota
      </button>
    </div>
  </div>
</section>
<br><br>
<div id="buscaretemes"></div>