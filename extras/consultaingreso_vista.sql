select `a`.`idFormEntrada` AS `idFormEntrada`,
`a`.`fechaEntrada` AS `fechaEntrada`,
`a`.`horaEntrada` AS `horaEntrada`,
`a`.`numRecibo` AS `numRecibo`,
`b`.`NOM_CLIENTE` AS `nombreCliente`,
`c`.`descripcion` AS `nombrePlanta`,
`d`.`descripcion` AS `nombrePuerta`,
        `a`.`status` AS `status`
from (((`form_entrada` `a` join `clientes` `b`) 
join `cat_planta` `c`) 
join `cat_general` `d`) 
where ((`a`.`idCliente` = `b`.`ID_CLIENTE`) 
and (`a`.`idPlanta` = `c`.`idPlanta`) 
and (`a`.`idPuerta` = `d`.`idCatalogo`)) 
order by `a`.`fechaEntrada`,`a`.`horaEntrada`