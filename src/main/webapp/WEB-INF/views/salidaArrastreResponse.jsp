<%@ include file="/WEB-INF/views/include.jsp" %>
<h3 id="item_name">Reporte de Arrastre de Saldos para el RD : ${reciboEntrada}</h3> 
<c:if test="${not empty arrastreSalida}">
<div id="contenidodelreporte">
<c:if test="${not empty detallesRdAgrupado}">
    <a class="btn btn-primary btn-small" target="_blank" href="exportSalArrastre?type=xls&reciboEntrada=${reciboEntrada}">EXPORTA XLS  <span class="glyphicon glyphicon-list-alt"></span></a>
    <a class="btn btn-primary btn-small" target="_blank" href="exportSalArrastre?type=pdf&reciboEntrada=${reciboEntrada}">EXPORTA PDF  <span class="glyphicon glyphicon-th-list"></span></a>
<section class="grupodedatosreportefinal">
  		<div class="bloquedata">
  			<div class="form-group">
			<h5 id="item_name">FECHA CONSULTA : ${now}</h5>
			<h5 id="item_name">CLIENTE : ${detallesRdAgrupado.get(0)[3]} ${detallesRdAgrupado.get(0)[4]}</h5>
			</div>
		</div>
</section>
</c:if>
<c:choose>	
	<c:when test="${not empty arrastreSalida && not empty detallesRdAgrupado}">
		<h5 id="item_name">INFORMACI&Oacute;N DE LAS SALIDAS DEL RECIBO DE ENTRADA : ${reciboEntrada} &nbsp;&nbsp;FECHA DE INGRESO:${fechaIngreso}</h5>
        <table aria-describedby="sample-table-2_info" id="sample-table-2" class="table table-striped table-bordered table-hover dataTable">
          <thead>
        	<tr role="row">
                <th align="right" >Folio Salida</th>
                <th align="right" >Fecha Salida</th>
                <th align="right" >Rengl&oacute;n</th>
                <th align="right" >Producto que salio</th>
                <th align="right" >Marca</th>
                <th align="right" >Lote</th>
                <th align="right" >Piezas</th>
                <th align="right" >Peso U(kg)</th>
                <th align="right" >Total kilos (kg)</th>
            </tr>
           </thead> 
            <c:set var="totalpzas" value="${0}"/>  
            <c:set var="totalkilos" value="${0}"/>
            <tbody aria-relevant="all" aria-live="polite" role="alert"><tr class="odd">
            <c:forEach items="${arrastreSalida}" var="e">
                    <td align="right" >${e[0]}</td>
                    <td align="right" >
                   		<fmt:parseDate value="${e[1]}" var="parsedEmpDate" pattern="yyyy-MM-dd" />
                        <fmt:formatDate pattern="dd/MM/yyyy" value="${parsedEmpDate}" />
                    </td>
                    <td align="right" >${e[2]}</td>
                    <td align="right" >${e[3]}</td>
                    <td align="right" >${e[9]}</td>
                    <td align="right" >${e[10]}</td>
                    <td align="right" >${e[4]}</td>
                    <td align="right" ><fmt:formatNumber value="${e[5]}" maxFractionDigits="2"/> KGS</td>
                    <td align="right" ><fmt:formatNumber value="${e[6]}" maxFractionDigits="2"/> KGS</td>
					<c:set var="totalpzas" value="${totalpzas+(e[5])}"/>
               		<c:set var="totalkilos" value="${totalkilos+(e[6])}"/>
                </tr>
            </c:forEach>
            </tbody>
            <!-- tr class="trcontenido">
                <td align="right" ></td>
                <td align="right" >TOTAL PZAS:<fmt:formatNumber value="${totalpzas}" maxFractionDigits="2"/></td>
                <td align="right" >TOTAL KILOS:<fmt:formatNumber value="${totalkilos}" maxFractionDigits="2"/></td>
            </tr-->
        </table>
	</c:when>
	<c:when test="${arrastreSalida eq '[]'}">
      <!--div id="mensaje_reporte_vacio">No existen registros con esa fecha.</div-->
      <h5>INFORMACI&Oacute;N DE LAS SALIDAS DEL RECIBO DE ENTRADA : ${reciboEntrada}</h5>
        <table aria-describedby="sample-table-2_info" id="sample-table-2" class="table table-striped table-bordered table-hover dataTable">
          <thead>
        	<tr role="row">
                <th align="right" >Folio Salida</th>
                <th align="right" >Fecha Salida</th>
                <th align="right" >Renglon</th>
                <th align="right" >Producto que salio</th>
                <th align="right" >Piezas</th>
                <th align="right" >Peso U(kg)</th>
                <th align="right" >Total kilos (kg)</th>
            </tr>
           </thead> 
            <c:set var="totalpzas" value="${0}"/>  
            <c:set var="totalkilos" value="${0}"/>
            <tbody aria-relevant="all" aria-live="polite" role="alert"><tr class="odd">
            <c:forEach items="${arrastreSalida}" var="e">
                    <td align="right" >${e[0]}</td>
                    <td align="right" >
                   		<fmt:parseDate value="${e[1]}" var="parsedEmpDate" pattern="yyyy-MM-dd" />
                        <fmt:formatDate pattern="dd/MM/yyyy" value="${parsedEmpDate}" />
                    </td>
                    <td align="right" >${e[2]}</td>
                    <td align="right" >${e[3]}</td>
                    <td align="right" >${e[4]}</td>
                    <td align="right" ><fmt:formatNumber value="${e[5]}" maxFractionDigits="2"/> KGS</td>
                    <td align="right" ><fmt:formatNumber value="${e[6]}" maxFractionDigits="2"/> KGS</td>
					<c:set var="totalpzas" value="${totalpzas+(e[5])}"/>
               		<c:set var="totalkilos" value="${totalkilos+(e[6])}"/>
                </tr>
            </c:forEach>
            </tbody>
            	<!-- tr class="trcontenido">
                    <td align="right" ></td>
                    <td align="right" >TOTAL PZAS:<fmt:formatNumber value="${totalpzas}" maxFractionDigits="2"/></td>
					<td align="right" >TOTAL KILOS:<fmt:formatNumber value="${totalkilos}" maxFractionDigits="2"/></td>
                </tr-->
        </table>
    </c:when>        
</c:choose>
<c:choose>	
	<c:when test="${not empty detallesRdAgrupado}">
<h5 id="item_name">INFORMACI&Oacute;N GENERAL DE MOVIMIENTOS DEL RECIBO DE ENTRADA : ${reciboEntrada}</h5>
</c:when>        
</c:choose>
<section class="grupodedatosreportetabla">
  		<div class="bloquedata">
  			<div class="form-group">
<c:choose>	
	<c:when test="${not empty detallesRdAgrupado}">
        <table aria-describedby="sample-table-2_info" id="sample-table-2" class="table table-striped table-bordered table-hover dataTable">
          <thead>
        	<tr role="row">
        	<th colspan="2">INGRESOS POR RENGLON</th></tr>
            <tr class="trtitulo">
                <th align="right" width="20%">Rengl&oacute;n</th>
                <th align="right" width="20%">Ingreso Pzas</th>
            </tr>
          </thead>  
            <c:set var="totalpzasEntrada" value="${0}"/>  
            <c:set var="totalkilosEntrada" value="${0}"/>
            <tbody aria-relevant="all" aria-live="polite" role="alert"><tr class="odd">
            <c:forEach items="${detallesRdAgrupado}" var="e">
                    <td align="right" width="20%"><fmt:formatNumber value="${e[0]}" maxFractionDigits="0"/></td>
                    <td align="right" width="20%"><fmt:formatNumber value="${e[1]}" maxFractionDigits="2"/></td>
                    <c:set var="totalpzasEntrada" value="${totalpzasEntrada+e[1]}"/>
                    <c:set var="totalkilosEntrada" value="${totalkilosEntrada+e[2]}"/>
                </tr>
            </c:forEach>
            </tbody>
            <tr class="trcontenido">
                    <td align="right" width="20%">Total Entradas</td>
                    <td align="right" width="20%"><fmt:formatNumber value="${totalpzasEntrada}" maxFractionDigits="2"/> PZAS</td>
                </tr>
                <tr class="trcontenido">
                    <td align="right" width="20%"></td>
                    <td align="right" width="20%"><fmt:formatNumber value="${totalkilosEntrada}" maxFractionDigits="2"/> KGS</td>
                </tr>
        </table>
	</c:when>
	<c:when test="${detallesRdAgrupado eq '[]'}">
      <table aria-describedby="sample-table-2_info" id="sample-table-2" class="table table-striped table-bordered table-hover dataTable">
          <thead>
        	<tr role="row">
        	<th colspan="2">INGRESOS POR RENGLON</th></tr>
            <tr class="trtitulo">
                <th align="right" width="20%">Rengl&oacute;n</th>
                <th align="right" width="20%">Ingreso Pzas</th>
            </tr>
           </thead> 
            <c:set var="totalpzasEntrada" value="${0}"/>  
            <c:set var="totalkilosEntrada" value="${0}"/>
          <tbody aria-relevant="all" aria-live="polite" role="alert"><tr class="odd">
            <c:forEach items="${detallesRdAgrupado}" var="e">
                    <td align="right" width="20%"><fmt:formatNumber value="${e[0]}" maxFractionDigits="0"/></td>
                    <td align="right" width="20%"><fmt:formatNumber value="${e[1]}" maxFractionDigits="2"/></td>
                    <c:set var="totalpzasEntrada" value="${totalpzasEntrada+e[1]}"/>
                    <c:set var="totalkilosEntrada" value="${totalkilosEntrada+e[2]}"/>
                </tr>
            </c:forEach>
          </tbody>
          <tr class="trcontenido">
                    <td align="right" width="20%">Total Entradas</td>
                    <td align="right" width="20%"><fmt:formatNumber value="${totalpzasEntrada}" maxFractionDigits="2"/> PZAS</td>
                </tr>
                <tr class="trcontenido">
                    <td align="right" width="20%"></td>
                    <td align="right" width="20%"><fmt:formatNumber value="${totalkilosEntrada}" maxFractionDigits="2"/> KGS</td>
                </tr>
        </table>
    </c:when>        
</c:choose>
</div>
  		</div>
  	</section>
<br>
<section class="grupodedatosreportetabla">
  		<div class="bloquedata">
  			<div class="form-group">
<c:choose>	
	<c:when test="${not empty salidaAgrupado}">
        <table aria-describedby="sample-table-2_info" id="sample-table-2" class="table table-striped table-bordered table-hover dataTable">
          <thead>
        	<tr role="row">
        	<th colspan="2">SALIDAS POR RENGLON</th></tr>
            <tr class="trtitulo">
                <th align="right" width="20%">Rengl&oacute;n</th>
                <th align="right" width="20%">Salida Pzas</th>
            </tr>
          </thead>  
            <c:set var="totalpzasSalida" value="${0}"/>  
            <c:set var="totalkilosSalida" value="${0}"/>
            <tbody aria-relevant="all" aria-live="polite" role="alert"><tr class="odd">
            <c:forEach items="${salidaAgrupado}" var="d">
                    <td align="right" width="20%"><fmt:formatNumber value="${d[0]}" maxFractionDigits="0"/></td>
                    <td align="right" width="20%"><fmt:formatNumber value="${d[1]}" maxFractionDigits="2"/></td>
                    <c:set var="totalpzasSalida" value="${totalpzasSalida+d[1]}"/>
                    <c:set var="totalkilosSalida" value="${totalkilosSalida+d[2]}"/>
                </tr>
            </c:forEach>
            </tbody>
            	<tr class="trcontenido">
                    <td align="right" width="20%">Total Salidas</td>
                    <td align="right" width="20%"><fmt:formatNumber value="${totalpzasSalida}" maxFractionDigits="2"/> PZAS</td>
                </tr>
                <tr class="trcontenido">
                    <td align="right" width="20%"></td>
                    <td align="right" width="20%"><fmt:formatNumber value="${totalkilosSalida}" maxFractionDigits="2"/> KGS</td>
                </tr>
        </table>
	</c:when>
	<c:when test="${salidaAgrupado eq '[]'}">
      <!-- div id="mensaje_reporte_vacio">No existen registros con ese RD.</div-->
      <table aria-describedby="sample-table-2_info" id="sample-table-2" class="table table-striped table-bordered table-hover dataTable">
          <thead>
        	<tr role="row">
        	<th colspan="2">SALIDAS POR RENGLON</th></tr>
            <tr class="trtitulo">
                <th align="right" width="20%">Rengl&oacute;n</th>
                <th align="right" width="20%">Salida Pzas</th>
            </tr>
           </thead> 
            <c:set var="totalpzasSalida" value="${0}"/>  
            <c:set var="totalkilosSalida" value="${0}"/>
          <tbody aria-relevant="all" aria-live="polite" role="alert"><tr class="odd">
            <c:forEach items="${salidaAgrupado}" var="d">
                    <td align="right" width="20%"><fmt:formatNumber value="${d[0]}" maxFractionDigits="0"/></td>
                    <td align="right" width="20%"><fmt:formatNumber value="${d[1]}" maxFractionDigits="2"/></td>
                    <c:set var="totalpzasSalida" value="${totalpzasSalida+d[1]}"/>
                    <c:set var="totalkilosSalida" value="${totalkilosSalida+d[2]}"/>
                </tr>
            </c:forEach>
          </tbody>
          <tr class="trcontenido">
                    <td align="right" width="20%">Total Salidas</td>
                    <td align="right" width="20%"><fmt:formatNumber value="${totalpzasSalida}" maxFractionDigits="2"/> PZAS</td>
                </tr>
                <tr class="trcontenido">
                    <td align="right" width="20%"></td>
                    <td align="right" width="20%"><fmt:formatNumber value="${totalkilosSalida}" maxFractionDigits="2"/> KGS</td>
                </tr>
        </table>
    </c:when>        
</c:choose>
</div>
  		</div>
  	</section>
<br>
<section class="grupodedatosreportetabla">
  		<div class="bloquedata">
  			<div class="form-group">
<c:choose>	
	<c:when test="${not empty inventarioAgrupado}">
        <table aria-describedby="sample-table-2_info" id="sample-table-2" class="table table-striped table-bordered table-hover dataTable">
          <thead>
        	<tr role="row">
        	<th colspan="2">INVENTARIO POR RENGLON</th></tr>
            <tr class="trtitulo">
                <th align="right" width="20%">Rengl&oacute;n</th>
                <th align="right" width="20%">Saldo Pzas</th>
            </tr>
          </thead>  
            <c:set var="totalpzsInventario" value="${0}"/>
            <c:set var="totalkgInventario" value="${0}"/>
            <tbody aria-relevant="all" aria-live="polite" role="alert"><tr class="odd">
            <c:forEach items="${inventarioAgrupado}" var="f">
                    <td align="right" width="20%"><fmt:formatNumber value="${f[0]}" maxFractionDigits="0"/></td>
                    <td align="right" width="20%"><fmt:formatNumber value="${f[1]}" maxFractionDigits="2"/></td>
                    <c:set var="totalpzsInventario" value="${totalpzsInventario+f[1]}"/>
                    <c:set var="totalkgInventario" value="${totalkgInventario+f[2]}"/>  
                </tr>
            </c:forEach>
            </tbody>
            <tr class="trcontenido">
                    <td align="right" width="20%">Saldo del recibo : ${reciboEntrada}</td>
                    <td align="right" width="20%"><fmt:formatNumber value="${totalpzsInventario}" maxFractionDigits="2"/> PZAS</td>
                </tr>
                <tr class="trcontenido">
                    <td align="right" width="20%"></td>
                    <td align="right" width="20%"><fmt:formatNumber value="${totalkgInventario}" maxFractionDigits="2"/> KGS</td>
                </tr>
        </table>
	</c:when>
	<c:when test="${inventarioAgrupado eq '[]'}">
      <!-- div id="mensaje_reporte_vacio">No existen registros con ese RD.</div-->
      <table aria-describedby="sample-table-2_info" id="sample-table-2" class="table table-striped table-bordered table-hover dataTable">
          <thead>
        	<tr role="row">
        	<th colspan="2">INVENTARIO POR RENGLON</th></tr>
            <tr class="trtitulo">
                <th align="right" width="20%">Rengl&oacute;n</th>
                <th align="right" width="20%">Saldo Pzas</th>
            </tr>
           </thead> 
            <c:set var="totalpzsInventario" value="${0}"/>
            <c:set var="totalkgInventario" value="${0}"/>
          <tbody aria-relevant="all" aria-live="polite" role="alert"><tr class="odd">
            <c:forEach items="${inventarioAgrupado}" var="f">
                    <td align="right" width="20%"><fmt:formatNumber value="${f[0]}" maxFractionDigits="0"/></td>
                    <td align="right" width="20%"><fmt:formatNumber value="${f[1]}" maxFractionDigits="2"/></td>
                    <c:set var="totalpzsInventario" value="${totalpzsInventario+f[1]}"/>
                    <c:set var="totalkgInventario" value="${totalkgInventario+f[2]}"/>  
                </tr>
            </c:forEach>
          </tbody>
          <tr class="trcontenido">
                    <td align="right" width="20%">Saldo del recibo : ${reciboEntrada}</td>
                    <td align="right" width="20%"><fmt:formatNumber value="${totalpzsInventario}" maxFractionDigits="2"/> PZAS</td>
                </tr>
                <tr class="trcontenido">
                    <td align="right" width="20%"></td>
                    <td align="right" width="20%"><fmt:formatNumber value="${totalkgInventario}" maxFractionDigits="2"/> KGS</td>
                </tr>
        </table>
    </c:when>        
</c:choose>
</div>
</div>
</section>

</div>
<br><br>
<div class="tablafin"> 			
<c:choose>	
	<c:when test="${not empty arrastreSalida}">
        <table aria-describedby="sample-table-2_info" id="sample-table-2" class="table table-striped table-bordered table-hover dataTable">
          <thead>
        	<tr role="row">
        	<th colspan="3">TOTAL DE MOVIMIENTOS DEL RECIBO : ${reciboEntrada}</th></tr>
            <tr class="trtitulo">
                <th align="right" width="20%">Fecha de ingreso : ${fechaIngreso}</th>
                <th align="right" width="20%">Piezas</th>
                <th align="right" width="20%">Kilogramos</th>
            </tr>
           </thead>   
            <tbody aria-relevant="all" aria-live="polite" role="alert"><tr class="odd">
                 <td align="right" width="20%">TOTAL QUE INGRESO</td>
                 <td align="right" width="20%"><fmt:formatNumber value="${totalpzasEntrada}" maxFractionDigits="0"/></td>
                 <td align="right" width="20%"><fmt:formatNumber value="${totalkilosEntrada}" maxFractionDigits="2"/></td>
            </tr>
            
            <tr class="trcontenido">
                 <td align="right" width="20%">TOTAL QUE HA SALIDO</td>
                 <td align="right" width="20%"><fmt:formatNumber value="${totalpzasSalida}" maxFractionDigits="0"/></td>
                 <td align="right" width="20%"><fmt:formatNumber value="${totalkilosSalida}" maxFractionDigits="2"/></td>
            </tr>
            <tr class="trtitulo">
                 <td align="right" width="20%" align="right">SALDO DEL RECIBO : ${reciboEntrada}</td>
                 <td align="right" width="20%"><fmt:formatNumber value="${totalpzsInventario}" maxFractionDigits="0"/></td>
                 <td align="right" width="20%"><fmt:formatNumber value="${totalkgInventario}" maxFractionDigits="2"/></td>
            </tr>
            </tbody>
        </table>
	</c:when>
	<c:when test="${arrastreSalida eq '[]'}">
      <!-- div id="mensaje_reporte_vacio">No existen registros con esa fecha.</div-->
      <table aria-describedby="sample-table-2_info" id="sample-table-2" class="table table-striped table-bordered table-hover dataTable">
          <thead>
        	<tr role="row">
        	<tr class="trtitulo"><th colspan="3">TOTAL DE MOVIMIENTOS DEL RECIBO : ${reciboEntrada}</th></tr>
            <tr class="trtitulo">
                <th align="right" width="20%">Fecha de ingreso : </th>
                <th align="right" width="20%">Piezas</th>
                <th align="right" width="20%">Kilogramos</th>
            </tr>
            </thead>   
            <tbody aria-relevant="all" aria-live="polite" role="alert"><tr class="odd">
                 <td align="right" width="20%">TOTAL QUE INGRESO</td>
                 <td align="right" width="20%"><fmt:formatNumber value="${totalpzasEntrada}" maxFractionDigits="0"/></td>
                 <td align="right" width="20%"><fmt:formatNumber value="${totalkilosEntrada}" maxFractionDigits="2"/></td>
            </tr>
            <tr class="trcontenido">
                 <td align="right" width="20%">TOTAL QUE HA SALIDO</td>
                 <td align="right" width="20%"><fmt:formatNumber value="${totalpzasSalida}" maxFractionDigits="0"/></td>
                 <td align="right" width="20%"><fmt:formatNumber value="${totalkilosSalida}" maxFractionDigits="2"/></td>
            </tr>
            <tr class="trtitulo">
                 <td align="right" width="20%" align="right">SALDO DEL RECIBO : ${reciboEntrada}</td>
                 <td align="right" width="20%"><fmt:formatNumber value="${totalpzsInventario}" maxFractionDigits="0"/></td>
                 <td align="right" width="20%"><fmt:formatNumber value="${totalkgInventario}" maxFractionDigits="2"/></td>
            </tr>
            </tbody>
        </table>
    </c:when>        
</c:choose>
</div>
</c:if>
<c:if test="${arrastreSalida eq '[]'}">
	<div id="mensaje_reporte_vacio"><h1 id="item_name">No hay movimientos.</h1></div>
</c:if>