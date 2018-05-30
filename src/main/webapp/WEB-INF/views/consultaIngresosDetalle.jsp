<%@ include file="/WEB-INF/views/include.jsp" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<link rel="stylesheet" type="text/css"	href="resources/bootstrap/css/capturaDetalle.css" />
<html>
<head>
    <title><fmt:message key="title"/></title>
</head>
<body>
<section class="container">
    <h1 id="item_name"><fmt:message key="consultas.ingreso.heading"/></h1>
    <!--h3><fmt:message key="consultas.ingreso.subtitle"/></h3-->
    <form:form commandName="vistaIngresodetalle" method="post" action="insRegDetalle">
        <form:input type="hidden" path="nombreCliente"/>
        <form:input type="hidden" path="tipoRecibo"/>
        <form:input type="hidden" path="numRecibo"/>
        <form:input type="hidden" path="nombrePlanta"/>
        <form:input type="hidden" path="nombrePuerta"/>
        <form:input type="hidden" path="fechaEntrada"/>
        <form:input type="hidden" path="tipofleje"/>
        <form:input type="hidden" path="fleje"/>
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
                    <div class="alert-info"  id="tipoRecibo">${vistaIngresodetalle.tipoRecibo}</div>
                </div>
                <!-- div class="form-group">
                    <label for="numerorecibo">NUMERO DE RECIBO</label>
                    <div class="alert-info"  id="numerorecibo">${vistaIngresodetalle.numRecibo}</div>
                </div-->
                <div class="form-group">
                    <label for="cliente">CLIENTE</label>
                    <div class="alert-info"  id="cliente">${vistaIngresodetalle.nombreCliente}</div>

                </div>
                <div class="form-group">
                    <label for="fechaingreso">FECHA DE INGRESO</label>
                    <div class="alert-info"  id="fechaingreso">${vistaIngresodetalle.fechaEntrada}</div>
                </div>
                <div class="form-group">
                    <label for="horaEntrada">HORA INGRESO</label>
                    <div class="alert-info"  id="horaEntrada">${vistaIngresodetalle.horaEntrada}</div>
                </div>
                <div class="form-group">
                    <label for="nombreCiaTransporte">COMPANIA DE TRANSPORTE</label>
                    <div class="alert-info"  id="nombreCiaTransporte">${vistaIngresodetalle.nombreCiaTransporte}</div>
                </div>
                <div class="form-group">
                    <label for="vehiculo">VEHICULO</label>
                    <div class="alert-info"  id="vehiculo">${vistaIngresodetalle.vehiculo}</div>
                </div>
            </div>

            <div class="bloquedata">
            <div class="form-group">
                    <label for="transporte">TRANSPORTE</label>
                    <div class="alert-info"  id="transporte">${vistaIngresodetalle.transporte}</div>
                </div>
            <div class="form-group">
                    <label for="puerta">PUERTA</label>
                    <div class="alert-info"  id="puerta">${vistaIngresodetalle.nombrePuerta}</div>
                </div>
                <div class="form-group">
                    <label for="planta">PLANTA</label>
                    <div class="alert-info"  id="planta">${vistaIngresodetalle.nombrePlanta}</div>
                </div>
                <div class="form-group">
                    <label for="tipofleje">TIPO DE FLEJE</label>
                    <div class="alert-info"  id="tipofleje">${vistaIngresodetalle.tipofleje}</div>
                </div>
                <div class="form-group">
                    <label for="fleje">FLEJE</label>
                    <div class="alert-info"  id="fleje">${vistaIngresodetalle.fleje}</div>
                </div>
                <div class="btniniciodetallereg">
                <br>
                    <button class="btn btn-primary btn-small" type="submit">
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
