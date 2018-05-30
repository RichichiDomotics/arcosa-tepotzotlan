CREATE TABLE `vehiculo` (
  `idIngresoVehiculo` int(11) NOT NULL AUTO_INCREMENT,
  `idCliente` bigint(20) DEFAULT NULL,
  `fechaEntrada` varchar(10) DEFAULT NULL,
  `horaEntrada` varchar(8) DEFAULT NULL,
  `idTipoRecibo` int(11) DEFAULT NULL,
  `idPlanta` int(11) DEFAULT NULL,
  `idPuerta` int(11) DEFAULT NULL,
  `nombreOperador` varchar(255) DEFAULT NULL,
  `idManiobra` int(11) DEFAULT NULL,
  `placasVehiculo` varchar(12) DEFAULT NULL,
  `idFleje` int(11) DEFAULT NULL,
  `idVehiculo` int(11) DEFAULT NULL,
  `idTransporte` int(11) DEFAULT NULL,
  `nombreCiaTransporte` varchar(255) DEFAULT NULL,
  `numRecibo` varchar(45) DEFAULT NULL,
  `permisos` varchar(255) DEFAULT NULL,
  `fleje` varchar(50) DEFAULT NULL,
  `status` varchar(45) NOT NULL,
  PRIMARY KEY (`idIngresoVehiculo`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8;
 FROM catalogo.vehiculo;