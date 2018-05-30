<%@ include file="/WEB-INF/views/include.jsp" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<link rel="stylesheet" type="text/css"	href="resources/bootstrap/css/capturaDetalle.css" />
<html>
<head>
    <title><fmt:message key="title"/></title>
</head>
<body>
<section class="container">
    <h1 class="item_name"><fmt:message key="consultas.salidas.heading"/></h1>
    <!--h3><fmt:message key="consultas.ingreso.subtitle"/></h3-->
    <form:form commandName="vistaSalidadetalle" method="post" action="formSalidasSubmit">
        <form:input type="hidden" path="tipoRecibo"/>
        <form:input type="hidden" path="numRecibo"/>
        <form:input type="hidden" path="nombreCliente"/>
        <form:input type="hidden" path="nombrePlanta"/>
        <form:input type="hidden" path="nombrePuerta"/>
        <form:input type="hidden" path="fechaEntrada"/>
        <form:input type="hidden" path="tipofleje"/>
        <form:input type="hidden" path="fleje"/>
        <form:input type="hidden" path="entregadoA"/>
        <form:input type="hidden" path="vehiculo"/>
        <form:input type="hidden" path="transporte"/>
        <form:input type="hidden" path="horaEntrada"/>
        <form:input type="hidden" path="nombreCiaTransporte"/>
        <form:input type="hidden" path="permisos"/>
        <form:input type="hidden" path="idCliente"/>
        <form:input type="hidden" path="nombreOperador"/>
        <form:input type="hidden" path="idIngresoVehiculo"/>
        <section class="grupodedatosreaddetalle">
            <div class="bloquedata">
                <div class="form-group">
                    <label for="tipoRecibo">TIPO DE RECIBO</label>
                    <div class="alert-info"  id="tipoRecibo">${vistaSalidadetalle.tipoRecibo}</div>
                </div>
                <div class="form-group">
                    <label for="cliente">CLIENTE</label>
                    <div class="alert-info"  id="cliente">${vistaSalidadetalle.nombreCliente}</div>
                </div>
                <div class="form-group">
                    <label for="fechaingreso">FECHA DE INGRESO</label>
                    <div class="alert-info"  id="fechaingreso">${vistaSalidadetalle.fechaEntrada}</div>
                </div>
                <div class="form-group">
                    <label for="horaEntrada">HORA INGRESO</label>
                    <div class="alert-info"  id="horaEntrada">${vistaSalidadetalle.horaEntrada}</div>
                </div>
                <div class="form-group">
                    <label for="nombreCiaTransporte">COMPA�IA DE TRANSPORTE</label>
                    <div class="alert-info"  id="nombreCiaTransporte">${vistaSalidadetalle.nombreCiaTransporte}</div>
                </div>
                <div class="form-group">
                    <label for="vehiculo">VEHICULO</label>
                    <div class="alert-info"  id="vehiculo">${vistaSalidadetalle.vehiculo}</div>
                </div>
            </div>

            <div class="bloquedata">
                <div class="form-group">
                    <label for="transporte">TRANSPORTE</label>
                    <div class="alert-info"  id="transporte">${vistaSalidadetalle.transporte}</div>
                </div>
                <div class="form-group">
                    <label for="planta">PLANTA</label>
                    <div class="alert-info"  id="planta">${vistaSalidadetalle.nombrePlanta}</div>
                </div>
                <div class="form-group">
                    <label for="puerta">PUERTA</label>
                    <div class="alert-info"  id="puerta">${vistaSalidadetalle.nombrePuerta}</div>
                </div>
                <div class="form-group">
                    <label for="tipofleje">TIPO DE FLEJE</label>
                    <div class="alert-info"  id="tipofleje">${vistaSalidadetalle.tipofleje}</div>
                </div>
                <div class="form-group">
                    <label for="fleje">FLEJE</label>
                    <div class="alert-info"  id="fleje">${vistaSalidadetalle.fleje}</div>
                </div>
                <div class="form-group">
                    <label for="fleje">ENTREGADO A</label>
                    <div class="alert-info"  id="entregadoA">${vistaSalidadetalle.entregadoA}</div>
                </div>
                <!--input type="hidden" id="entregado" value="${vistaSalidadetalle.entregadoA}"-->
                <br>
                <div class="btniniciodetallereg">
                    <button class="btn btn-primary btn-small buscasalidasImp" type="submit">
						<i class="icon-edit  bigger-125"></i>
						Continuar
					</button>
                </div>
            </div>
        </section>
    </form:form>
</section>
</body>
</html>
