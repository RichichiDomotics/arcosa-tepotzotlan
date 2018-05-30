<%@ include file="/WEB-INF/views/include.jsp" %>
<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js"></script>
<script>
    $(document).ready(function(){
        $(document).bind("contextmenu",function(e){
            return false;
        });
    });
</script>
<style>
    .tabla, .tabla th, .tabla td{
        border: 2px solid black;
        border-collapse: collapse;
    }
    .tabla thead{
        background-color: #BDBDBD;
    }
    .tabla tbody{
        font-size: 10px;
    }
    .tablitas{
        display: inline-block;
        width: 30%;
    }
    .otras{
        display: inline-block;
        width: 33%;
    }
    .firmas{
        font-size: 12px;
        font-family: Arial, Helvetica, sans-serif;
        font-weight: bold;
    }
</style>
<table width="98%" style="border-collapse: collapse">
    <tr>
        <td width="142"><img class="nav-user-photo" src="http://localhost:8080/almacen/resources/img/logo-arcosa.png" alt="Arcosa" /></td>
        <td height="65" colspan="2" align="center" valign="middle" style="background-color: #e6e6e6;"><p><font size="2" face="Arial, Helvetica, sans-serif"><strong>FORMATO DE
            CAPTURA DE INGRESO, CLASIFICACION Y VALIDACION DE PESO DEL PRODUCTO AL
            ALMACEN</strong></font></p>
            <p>&nbsp;</p></td>
        <td align="center" valign="middle" style="background-color: #e6e6e6;"><font size="1" face="Arial, Helvetica, sans-serif"> <strong>GRUPO ARCOSA<BR>
            PTO.OPER.01.01
            <BR>
            REV.1<BR>
            EMISION:21/02/2018</strong></font></td>
    </tr>
    <tr>
        <td colspan="4"><font size="1" face="Arial, Helvetica, sans-serif">



        </font></td>
    </tr>
    <tr>
        <td><div align="right"><font size="2" face="Arial, Helvetica, sans-serif"><strong></strong></font>
        </div></td>
        <td width="197"><font size="2" face="Arial, Helvetica, sans-serif"><strong></strong></font></td>
        <td width="454"><font size="2" face="Arial, Helvetica, sans-serif"><strong>ANDEN
            DESIGNADO: &nbsp;</strong></font></td>
        <td width="249"><font size="2" face="Arial, Helvetica, sans-serif"><strong>
            <strong><font size="2" face="Arial, Helvetica, sans-serif"> FECHA DE INGRESO:</font>
            </strong></strong></font></td>
    </tr>
</table>
<strong><font size="3" face="Arial, Helvetica, sans-serif">RECIBO DE ENTRADA:</font></strong><strong></strong><strong></strong><BR>
<table width="98%" class="tabla">
    <tr>
        <td width="8%" ><strong><font size="1" face="Arial, Helvetica, sans-serif">CLIENTE:</font></strong></td>
        <td width="34%">&nbsp;</td>
        <td width="24%"><font size="1" face="Arial, Helvetica, sans-serif">TEMPERATURA
            THERMOKING: &nbsp;____&deg;C</font></td>
        <td width="16%"><div align="right"><font size="1" face="Arial, Helvetica, sans-serif">ENTRA
            A TUNEL: SI
            <input type="radio" name="radiobutton" value="radiobutton">
        </font></div></td>
        <td width="18%"><font size="1" face="Arial, Helvetica, sans-serif">CAMARA
            INICIAL:</font></td>
    </tr>
    <tr>
        <td height="15"><strong><font size="1" face="Arial, Helvetica, sans-serif">
            CHOFER :</font></strong></td>
        <td>&nbsp;</td>
        <td><div align="left"><font size="1" face="Arial, Helvetica, sans-serif">
            TEMPERATURA DEL PRODUCTO: _____&deg;C</font></div></td>
        <td><div align="right"><font size="1" face="Arial, Helvetica, sans-serif">
            NO
            <input type="radio" name="radiobutton" value="radiobutton">
        </font></div></td>
        <td><font size="1" face="Arial, Helvetica, sans-serif">&nbsp;CAMARA FINAL:</font></td>
    </tr>
</table>
<br>
<font size="2" face="Arial, Helvetica, sans-serif"><strong>DETALLE DEL RECIBO
    DE ENTRADA</strong></font>:
<table width="98%" class="tabla">
    <tr>
        <td width="5%" rowspan="2"><div align="center"><font size="1" face="Arial, Helvetica, sans-serif"><b>RENGLON</b></font></div></td>
        <td width="5%" rowspan="2"><div align="center"><font size="1" face="Arial, Helvetica, sans-serif"><b>CANTIDAD</b></font></div></td>
        <td width="15%" rowspan="2"><div align="center"><font size="1" face="Arial, Helvetica, sans-serif"><b>EMBALAJE</b></font></div></td>
        <td width="36%" rowspan="2"><div align="center"><font size="1" face="Arial, Helvetica, sans-serif"><b>PRODUCTO</b></font></div></td>
        <td width="10%" rowspan="2"><div align="center"><font size="1" face="Arial, Helvetica, sans-serif"><b>MARCA</b></font></div></td>
        <td width="8%" rowspan="2"><div align="center"><font size="1" face="Arial, Helvetica, sans-serif"><b>LOTE</b></font></div></td>
        <td width="10%" rowspan="2"><div align="center"><font size="1" face="Arial, Helvetica, sans-serif"><b>CADUCIDAD</b></font></div></td>
        <td width="12%" colspan="2"><div align="center"><font size="1" face="Arial, Helvetica, sans-serif"><b>PESO
            UNITARIO</b> </font></div></td>
        <!--td width="5%"><div align="center"><font size="1" face="Arial, Helvetica, sans-serif">BULTOS</font></div></td-->
    </tr>
    <tr>
        <td width="6%"><div align="center"><font size="1" face="Arial, Helvetica, sans-serif"><b>NETO</b></font></div></td>
        <td width="6%"><div align="center"><font size="1" face="Arial, Helvetica, sans-serif"><b>BRUTO</b></font></div></td>
    </tr>
    <tr>
        <td>&nbsp;</td>
        <td>&nbsp;</td>
        <td>&nbsp;</td>
        <td>&nbsp;</td>
        <td>&nbsp;</td>
        <td>&nbsp;</td>
        <td>&nbsp;</td>
        <td>&nbsp;</td>
        <td>&nbsp;</td>
    </tr>
    <tr>
        <td>&nbsp;</td>
        <td>&nbsp;</td>
        <td>&nbsp;</td>
        <td>&nbsp;</td>
        <td>&nbsp;</td>
        <td>&nbsp;</td>
        <td>&nbsp;</td>
        <td>&nbsp;</td>
        <td>&nbsp;</td>
    </tr>
    <tr>
        <td>&nbsp;</td>
        <td>&nbsp;</td>
        <td>&nbsp;</td>
        <td>&nbsp;</td>
        <td>&nbsp;</td>
        <td>&nbsp;</td>
        <td>&nbsp;</td>
        <td>&nbsp;</td>
        <td>&nbsp;</td>
    </tr>
    <tr>
        <td>&nbsp;</td>
        <td>&nbsp;</td>
        <td>&nbsp;</td>
        <td>&nbsp;</td>
        <td>&nbsp;</td>
        <td>&nbsp;</td>
        <td>&nbsp;</td>
        <td>&nbsp;</td>
        <td>&nbsp;</td>
    </tr>
    <tr>
        <td>&nbsp;</td>
        <td>&nbsp;</td>
        <td>&nbsp;</td>
        <td>&nbsp;</td>
        <td>&nbsp;</td>
        <td>&nbsp;</td>
        <td>&nbsp;</td>
        <td>&nbsp;</td>
        <td>&nbsp;</td>
    </tr>
    <tr>
        <td>&nbsp;</td>
        <td>&nbsp;</td>
        <td>&nbsp;</td>
        <td>&nbsp;</td>
        <td>&nbsp;</td>
        <td>&nbsp;</td>
        <td>&nbsp;</td>
        <td>&nbsp;</td>
        <td>&nbsp;</td>
    </tr>
    <tr>
        <td>&nbsp;</td>
        <td>&nbsp;</td>
        <td>&nbsp;</td>
        <td>&nbsp;</td>
        <td>&nbsp;</td>
        <td>&nbsp;</td>
        <td>&nbsp;</td>
        <td>&nbsp;</td>
        <td>&nbsp;</td>
    </tr>
    <tr>
        <td>&nbsp;</td>
        <td>&nbsp;</td>
        <td>&nbsp;</td>
        <td>&nbsp;</td>
        <td>&nbsp;</td>
        <td>&nbsp;</td>
        <td>&nbsp;</td>
        <td>&nbsp;</td>
        <td>&nbsp;</td>
    </tr>
    <tr>
        <td>&nbsp;</td>
        <td>&nbsp;</td>
        <td>&nbsp;</td>
        <td>&nbsp;</td>
        <td>&nbsp;</td>
        <td>&nbsp;</td>
        <td>&nbsp;</td>
        <td>&nbsp;</td>
        <td>&nbsp;</td>
    </tr>
    <tr>
        <td>&nbsp;</td>
        <td>&nbsp;</td>
        <td>&nbsp;</td>
        <td>&nbsp;</td>
        <td>&nbsp;</td>
        <td>&nbsp;</td>
        <td>&nbsp;</td>
        <td>&nbsp;</td>
        <td>&nbsp;</td>
    </tr>
    <tr>
        <td>&nbsp;</td>
        <td>&nbsp;</td>
        <td>&nbsp;</td>
        <td>&nbsp;</td>
        <td>&nbsp;</td>
        <td>&nbsp;</td>
        <td>&nbsp;</td>
        <td>&nbsp;</td>
        <td>&nbsp;</td>
    </tr>
</table>
<br>
<div class="tablitas">
    <table width="65%" class="tabla">
        <tr>
            <td>Total de Piezas:</td>
            <td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
        </tr>
    </table>
</div>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
<div class="tablitas">
    <table width="90%" class="tabla">
        <tr>
            <td>Total Convertidor/Tarima:</td>
            <td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
        </tr>
    </table>
</div>
&nbsp;&nbsp;&nbsp;&nbsp;
<div class="tablitas">
    <table width="94%" class="tabla">
        <tr>
            <td>Total kilos:</td>
            <td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
            <td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
        </tr>
    </table>
</div>
<br>
<br>
<div class="otras">
    <table class="tabla" width="94%">
        <thead>
        <tr>
            <th colspan="6">Observaciones</th>
        </tr>
        </thead>
        <tbody>
        <tr>
            <td colspan="2">Tipo de Tarima:</td>
            <td>Chep</td>
            <td colspan="2">Est&aacute;ndar</td>
            <td>ARCOSA</td>
        </tr>
        <tr>
            <td colspan="6">Altura Convertidor/Tarima:</td>
        </tr>
        <tr>
            <td>Calle:</td>
            <td>Fila:</td>
            <td>Rack/Conv:</td>
            <td>Pasillo</td>
            <td>A&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
            <td>B</td>
        </tr>
        </tbody>
    </table>
</div>
<div class="otras">
    <table class="tabla" width="94%">
        <thead>
        <tr>
            <th colspan="5">Reporte de tiempo extra y maniobra especial</th>
        </tr>
        </thead>
        <tbody>
        <tr>
            <td colspan="3">Escaneo</td>
            <td>Si</td>
            <td>No</td>
        </tr>
        <tr>
            <td colspan="3">Selecci&oacute;n de Producto</td>
            <td>Si</td>
            <td>No</td>
        </tr>
        <tr>
            <td colspan="2">Tiempo Extra</td>
            <td>Hrs.: </td>
            <td>Doble</td>
            <td>Triple</td>
        </tr>
        </tbody>
    </table>
</div>
<div class="otras">
    <table class="tabla" width="94%">
        <thead>
        <tr>
            <th colspan="4">Checklist</th>
        </tr>
        </thead>
        <tbody>
        <tr>
            <td colspan="2">Apertura de Puertas</td>
            <td colspan="2">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
        </tr>
        <tr>
            <td colspan="2">Cierre de puertas</td>
            <td colspan="2">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
        </tr>
        <tr>
            <td colspan="2">Libre de vidrio</td>
            <td>Si</td>
            <td>No</td>
        </tr>
        <tr>
            <td colspan="2">Libre de plagas</td>
            <td>Si</td>
            <td>No</td>
        </tr>
        <tr>
            <td colspan="2">Libre de vidrio/PD</td>
            <td>Si</td>
            <td>No</td>
        </tr>
        <tr>
            <td colspan="2">Limpieza General</td>
            <td>Si</td>
            <td>No</td>
        </tr>
        </tbody>
    </table>
</div>
<br><br>
<div class="firmas">
    NOMBRE Y FIRMA JEFE/SUBJEFE ________________________ NOMBRE Y FIRMA UPP________________________    NOMBRE Y FIRMA ALMACENISTA    _____________________
</div>
<form name="form1" method="post" action="entrada_capturaimpresion.asp">
    <font size="1" face="Arial, Helvetica, sans-serif">ESTE FORMATO FUE IMPRESO POR</font>:
    ${usuario}
    &nbsp;&nbsp;&nbsp;&nbsp;
    <input name="horasate2" type="hidden" value="">
    <input type="hidden" value="" name="consecutivo">
    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
</form>