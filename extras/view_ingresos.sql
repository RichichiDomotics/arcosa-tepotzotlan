CREATE or REPLACE
    ALGORITHM = UNDEFINED 
    DEFINER = `root`@`localhost` 
    SQL SECURITY DEFINER
VIEW `consultaingreso` AS
    select 
        `a`.`idIngresoVehiculo` AS `idIngresoVehiculo`,
        `a`.`fechaEntrada` AS `fechaEntrada`,
        `a`.`horaEntrada` AS `horaEntrada`,
        `a`.`numRecibo` AS `numRecibo`,
        `b`.`NOM_CLIENTE` AS `nombreCliente`,
        `c`.`descripcion` AS `nombrePlanta`,
        `d`.`descripcion` AS `nombrePuerta`
    from
        (((`vehiculo` `a`
        join `clientes` `b`)
        join `cat_planta` `c`)
        join `cat_general` `d`)
    where
        ((`a`.`idCliente` = `b`.`ID_CLIENTE`)
            and (`a`.`idPlanta` = `c`.`idPlanta`)
            and (`a`.`idPuerta` = `d`.`idCatalogo`))
    order by `a`.`fechaEntrada` , `a`.`horaEntrada`;
    
    --***************************************************************
    
    CREATE OR REPLACE
    ALGORITHM = UNDEFINED 
    DEFINER = `root`@`localhost` 
    SQL SECURITY DEFINER
VIEW `consultaingresodet` AS
    select 
        `a`.`idIngresoVehiculo` AS `idIngresoVehiculo`,
        `a`.`fechaEntrada` AS `fechaEntrada`,
        `a`.`horaEntrada` AS `horaEntrada`,
        `a`.`numRecibo` AS `numRecibo`,
        `b`.`ID_CLIENTE` AS `idCliente`,
        `b`.`NOM_CLIENTE` AS `nombreCliente`,
        `c`.`descripcion` AS `nombrePlanta`,
        `d`.`descripcion` AS `nombrePuerta`,
        `a`.`nombreOperador` AS `nombreOperador`,
        `a`.`placasVehiculo` AS `placasVehiculo`,
        `a`.`nombreCiaTransporte` AS `nombreCiaTransporte`,
        `a`.`permisos` AS `permisos`,
        `e`.`descripcion` AS `maniobra`,
        `f`.`descripcion` AS `tipofleje`,
        `a`.`fleje` AS `fleje`,
        `g`.`descripcion` AS `vehiculo`,
        `h`.`descripcion` AS `transporte`,
        `i`.`descripcion` AS `tipoRecibo`
    from
        ((((((((`vehiculo` `a`
        join `clientes` `b`)
        join `cat_planta` `c`)
        join `cat_general` `d`)
        join `cat_general` `e`)
        join `cat_general` `f`)
        join `cat_general` `g`)
        join `cat_general` `h`)
        join `cat_general` `i`)
    where
        ((`a`.`idCliente` = `b`.`ID_CLIENTE`)
            and (`a`.`idPlanta` = `c`.`idPlanta`)
            and (`a`.`idPuerta` = `d`.`idCatalogo`)
            and (`a`.`idManiobra` = `e`.`idCatalogo`)
            and (`a`.`idFleje` = `f`.`idCatalogo`)
            and (`a`.`idVehiculo` = `g`.`idCatalogo`)
            and (`a`.`idTransporte` = `h`.`idCatalogo`)
            and (`a`.`idTipoRecibo` = `i`.`idCatalogo`))
    order by `a`.`fechaEntrada` , `a`.`horaEntrada`;