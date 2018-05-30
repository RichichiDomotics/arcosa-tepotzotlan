<%@ include file="/WEB-INF/views/include.jsp" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<link rel="stylesheet" type="text/css" href="resources/bootstrap/css/inventario.css" />
<div id="contenidoconsultaInOutVehiculo">
    <!--h1><fmt:message key="reportes.tiempos.heading"/></h1>
<h3><fmt:message key="reportes.tiempos.subtitle"/></h3-->
    <br>
    <br>
    <br>
    <br>
    <table width="98%" style="border-collapse: collapse">
        <tr>
            <td width="142"><img class="nav-user-photo" src="resources/img/logo-arcosa.png" alt="Arcosa" /></td>
            <td height="65" colspan="2" align="center" valign="middle" style="background-color: #e6e6e6;"><p><font size="2" face="Arial, Helvetica, sans-serif"><strong>TIEMPOS DE ATENCION</strong></font></p>
                <p>&nbsp;</p></td>
            <td align="center" valign="middle" style="background-color: #e6e6e6;"><font size="1" face="Arial, Helvetica, sans-serif"> <strong>GRUPO ARCOSA<BR>
                IT.TAT.01
                <BR>
                REV.1<BR>
                31/12/16</strong></font></td>
        </tr>
        <tr>
            <td colspan="4"><font size="1" face="Arial, Helvetica, sans-serif">



            </font></td>
        </tr>
    </table>
    <br>
    <br>
    <br>
    <br>
    <table aria-describedby="sample-table-2_info" id="sample-table-2" class="table table-striped table-bordered table-hover dataTable">
        <thead>
        <tr role="row">
            <th class="center">Cliente</th>
            <th class="center">Fecha de Entrada</th>
            <th class="center">Tipo de Vehiculo</th>
            <th class="center">Placas</th>
            <th class="center">Incidencia al Ingreso</th>
            <th class="center">Hora de Entrada</th>
            <th class="center">Hora de Salida</th>
            <th class="center">Tiempo Est&aacute;ndar</th>
            <th class="center">Tiempo de Atenci&oacute;n</th>
            <th class="center">Tiempo Restante</th>
        </tr>
        </thead>
        <c:set var="contador" value="0" />
        <tbody aria-relevant="all" aria-live="polite" role="alert">
            <c:forEach items="${vehiculos}" var="e">
                <tr class="odd">
                    <td class="center">${e[0]} ${e[1]}</td>

                    <td class="center">
                        <fmt:parseDate value="${e[2]}" var="parsedEmpDate" pattern="yyyy-MM-dd" />
                        <fmt:formatDate pattern="dd-MM-yyyy" value="${parsedEmpDate}" />
                    </td>
                    <td class="center">${e[3]}</td>
                    <td class="center">${e[4]}</td>
                    <td class="center">${e[5]}</td>
                    <td class="center">${e[6]}</td>
                    <td class="center">${e[7]}</td>
                    <td class="center">${e[8]}</td>
                    <td class="center">${e[9]}</td>
                    <td class="center">${e[10]}</td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
    <br>
    <div id="totalInventario">Total de Veh&iacute;culos : ${totales} veh&iacute;culos</div>
    <br><div id="totalInventario">Total Cumplidos : ${cumplidos} veh&iacute;culos (<fmt:formatNumber type="number" pattern="###,###,###.##" value="${(cumplidos*100)/totales}"/>%)</div>
    <br><div id="totalInventario">Total no Cumplidos : ${nocumplidos} veh&iacute;culos (<fmt:formatNumber type="number" pattern="###,###,###.##" value="${(nocumplidos*100)/totales}"/>%)</div>
    <div id="suprdiv"><div id="CapSeg"></div> </div>
</div>